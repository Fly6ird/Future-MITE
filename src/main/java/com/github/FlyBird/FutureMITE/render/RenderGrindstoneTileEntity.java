package com.github.FlyBird.FutureMITE.render;

import com.github.FlyBird.FutureMITE.models.ModelGrindstone;
import com.github.FlyBird.FutureMITE.tileentities.TileEntityGrindstone;
import net.minecraft.*;
import org.lwjgl.opengl.GL11;

public class RenderGrindstoneTileEntity extends TileEntitySpecialRenderer {
    private final ModelGrindstone modelGrindstone = new ModelGrindstone();
    public static final ResourceLocation texturePath= new ResourceLocation("textures/blocks/grindstone.png"  );

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double d, double e, double f, float g) {
        TileEntityGrindstone tileEntityGrindstone = (TileEntityGrindstone)tileEntity;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d + 0.5F, (float)e + 1.5F, (float)f + 0.5F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        TextureManager manager = Minecraft.getMinecraft().getTextureManager();
        manager.bindTexture(texturePath);
        this.modelGrindstone.renderGrindstone(0.0625F);
        GL11.glPopMatrix();

    }
}
