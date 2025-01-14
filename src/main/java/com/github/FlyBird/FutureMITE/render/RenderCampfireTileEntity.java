package com.github.FlyBird.FutureMITE.render;

import com.github.FlyBird.FutureMITE.tileentities.TileEntityCampfire;
import net.minecraft.*;
import org.lwjgl.opengl.GL11;

public class RenderCampfireTileEntity extends TileEntitySpecialRenderer {
    protected EntityItem invRender[] = new EntityItem[4];

    private static final double BASE_X_OFFSET = 0.9375;
    private static final double BASE_Y_OFFSET = 0.45;
    private static final double BASE_Z_OFFSET = 0.9375;
    private static final double ACROSS = 0.875;
    private static final double EDGE = 0.125;
    private static final double SMOKE_OFFSET = 0.15625;
    private static final double SMOKE_Y_OFFSET = 0.06;
    private static final double[][] RENDER_POSITION_ITEM = new double[][] {
            { BASE_X_OFFSET, BASE_Y_OFFSET, BASE_Z_OFFSET + EDGE - ACROSS },
            { BASE_X_OFFSET - EDGE, BASE_Y_OFFSET, BASE_Z_OFFSET },
            { BASE_X_OFFSET - ACROSS, BASE_Y_OFFSET, BASE_Z_OFFSET - EDGE },
            { BASE_X_OFFSET + EDGE - ACROSS, BASE_Y_OFFSET, BASE_Z_OFFSET - ACROSS } };
    private static final double[][] RENDER_POSITION_SMOKE = new double[][] {
            { BASE_X_OFFSET - SMOKE_OFFSET, BASE_Y_OFFSET + SMOKE_Y_OFFSET, BASE_Z_OFFSET + EDGE - ACROSS },
            { BASE_X_OFFSET - EDGE, BASE_Y_OFFSET + SMOKE_Y_OFFSET, BASE_Z_OFFSET - SMOKE_OFFSET },
            { BASE_X_OFFSET - ACROSS + SMOKE_OFFSET, BASE_Y_OFFSET + SMOKE_Y_OFFSET, BASE_Z_OFFSET - EDGE },
            { BASE_X_OFFSET + EDGE - ACROSS, BASE_Y_OFFSET + SMOKE_Y_OFFSET, BASE_Z_OFFSET - ACROSS + SMOKE_OFFSET } };
    private static final int[][] RENDER_SLOT_MAPPING = new int[][] {
            { 3, 0, 1, 2 },
            { 1, 2, 3, 0 },
            { 2, 3, 0, 1 },
            { 0, 1, 2, 3 } };
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float scale) {
        if(tileEntity instanceof TileEntityCampfire)
        {
            ItemStack[] burnItemStacks=((TileEntityCampfire) tileEntity).getBurnItemStacks();
            for (int i = 0; i < burnItemStacks.length; i++) {
                if(burnItemStacks[i]!=null)
                {
                    ItemStack stack=burnItemStacks[i];
                    if(invRender[i]==null){
                        invRender[i] = new EntityItem(tileEntity.getWorldObj());
                        invRender[i].hoverStart = 0.0F;
                    }else
                        invRender[i].setWorld(tileEntity.getWorldObj());

                    invRender[i].setEntityItemStack(stack);

                    boolean rendersAsBlock = stack.getItemSpriteNumber() == 0 && stack.getItem() instanceof ItemBlock
                            && RenderBlocks.renderItemIn3d(((ItemBlock) stack.getItem()).getBlock().getRenderType());

                    GL11.glPushMatrix();
                    GL11.glColor4f(1, 1, 1, 1);
                    RenderItem.renderInFrame = true;

                    GL11.glDisable(GL11.GL_BLEND);

                    if (!rendersAsBlock)
                        GL11.glDisable(GL11.GL_LIGHTING);

                    double[] position = getRenderPositionFromRenderSlot(i, false);
                    GL11.glTranslated(x + position[0], y + position[1], z + position[2]);

                    if (rendersAsBlock)
                    {
                        GL11.glRotatef(i * -90, 0, 1, 0);
                        GL11.glTranslated(-0.125, -0.01625, 0.0);
                    }
                    else
                    {
                        GL11.glRotatef(180, 0, 1, 1);
                        GL11.glRotatef(i * -90, 0, 0, 1);
                        GL11.glRotatef(270, 0, 0, 1);
                    }

                    GL11.glScalef(0.625F, 0.625F, 0.625F);
                    RenderManager.instance.renderEntityWithPosYaw(invRender[i], 0.0, 0.0, 0.0, 0.0F, 0.0F);

                    if (!rendersAsBlock)
                        GL11.glEnable(GL11.GL_LIGHTING);

                    RenderItem.renderInFrame = false;
                    GL11.glPopMatrix();
                }
            }
        }
    }

    public static double[] getRenderPositionFromRenderSlot(int renderslot, boolean smoke)
    {
        return (smoke ? RENDER_POSITION_SMOKE : RENDER_POSITION_ITEM)[Math.abs(renderslot) % 4];
    }
}
