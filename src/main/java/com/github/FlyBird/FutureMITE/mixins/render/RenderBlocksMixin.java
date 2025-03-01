package com.github.FlyBird.FutureMITE.mixins.render;


import com.github.FlyBird.FutureMITE.api.EnumItemRenderType;
import com.github.FlyBird.FutureMITE.api.IRenderBlock;
import com.github.FlyBird.FutureMITE.blocks.BlockCampfire;
import com.github.FlyBird.FutureMITE.blocks.Blocks;
import com.github.FlyBird.FutureMITE.render.*;
import com.github.FlyBird.FutureMITE.tileentities.TileEntityGrindstone;
import net.minecraft.*;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({RenderBlocks.class})
public abstract class RenderBlocksMixin implements IRenderBlock {
    @Shadow
    private Icon overrideBlockTexture;

    @Shadow
    public IBlockAccess blockAccess;

    @Shadow
    public Icon getBlockIconFromSideAndMetadata(Block par1Block, int side, int metadata) {
        Icon par1Icon = par1Block.getIcon(side, metadata);
        return par1Icon != null ? par1Icon : ((TextureMap) Minecraft.theMinecraft.getTextureManager().getTexture(TextureMap.locationBlocksTexture)).getAtlasSprite("missingno");
    }

    @Shadow
    public abstract void drawCrossedSquares(Block par1Block, int par2, double par3, double par5, double par7, float par9);


    @Unique
    private EnumItemRenderType renderItemType;  //0为物品栏  1为手持  2为凋落物

    @Unique
    RenderGrindstoneTileEntity grindStone = new RenderGrindstoneTileEntity();

    @Inject(method = {"renderItemIn3d"}, at = {@At("HEAD")}, cancellable = true)
    private static void register(int renderType, CallbackInfoReturnable<Boolean> cir) {
        if (renderType == RenderTypes.grindStoneRenderType)
            cir.setReturnValue(Boolean.TRUE);
    }

    @Inject(method = {"renderBlockAsItem"}, at = {@At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/Block;getRenderType()I")})
    private void register(Block par1Block, int par2, float par3, CallbackInfo ci) {
        int renderType = par1Block.getRenderType();

        if (renderType == RenderTypes.grindStoneRenderType) {
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            TileEntityRenderer.instance.renderTileEntityAt(new TileEntityGrindstone(), 0.0D, 0.0D, 0.0D, 0.0F);
            GL11.glEnable(32826);
        }
    }

    @Inject(method = "renderBlockByRenderType", at = @At("HEAD"), cancellable = true)
    public void renderBlockByRenderTypes(Block par1Block, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        int RenderType = par1Block.getRenderType();
        if (RenderType == RenderTypes.chainRenderType) {
            Icon Photo = this.overrideBlockTexture == null ? this.getBlockIconFromSideAndMetadata(par1Block, 0, this.blockAccess.getBlockMetadata(x, y, z)) : this.overrideBlockTexture;
            RenderChain.instance.drawChain(par1Block, this.blockAccess.getBlockMetadata(x, y, z), x, y, z, Photo, this.blockAccess);
            cir.setReturnValue(true);
        }
        if (RenderType == RenderTypes.normalcampfireRenderType) {
            RenderNormalCampfire.instance.DrawRenderCampfire(par1Block, this.blockAccess.getBlockMetadata(x, y, z), x, y, z, this.blockAccess);
            cir.setReturnValue(true);
        }
        if (RenderType == RenderTypes.campfireRenderType) {
            if (par1Block instanceof BlockCampfire) {
                if (((BlockCampfire) par1Block).getIsActive())
                    RenderCampfire.instance.DrawRenderCampfire((BlockCampfire) par1Block, this.blockAccess.getBlockMetadata(x, y, z), x, y, z, this.blockAccess);
                else
                    RenderNormalCampfire.instance.DrawRenderCampfire(par1Block, this.blockAccess.getBlockMetadata(x, y, z), x, y, z, this.blockAccess);
                cir.setReturnValue(true);
            }

        }
        if (RenderType == RenderTypes.lanternRenderType) {
            RenderLantern.instance.DrawBlockLantern(par1Block, this.blockAccess.getBlockMetadata(x, y, z), x, y, z, this.blockAccess);
            cir.setReturnValue(true);
        }
    }


    /**
     * @author
     * @reason
     */
    @Overwrite
    public boolean renderCrossedSquares(Block par1Block, int par2, int par3, int par4) {
        Tessellator.instance.setBrightness(par1Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
        float var6 = 1.0F;
        int var7 = par1Block.colorMultiplier(this.blockAccess, par2, par3, par4);
        float var8 = (float) (var7 >> 16 & 255) / 255.0F;
        float var9 = (float) (var7 >> 8 & 255) / 255.0F;
        float var10 = (float) (var7 & 255) / 255.0F;
        if (EntityRenderer.anaglyphEnable) {
            float var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
            float var12 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
            float var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
            var8 = var11;
            var9 = var12;
            var10 = var13;
        }

        Tessellator.instance.setColorOpaque_F(var6 * var8, var6 * var9, var6 * var10);
        double var19 = (double) par2;
        double var20 = (double) par3;
        double var15 = (double) par4;
        if (par1Block == Blocks.tallGrass || par1Block == Blocks.bigGrass) {
            long var17;
            if (par1Block == Blocks.tallGrass)
                var17 = (long) (par2 * 3129871L) ^ (long) par4 * 116129781L ^ (long) par3;
            else var17 = (long) (par2 * 3129871L) ^ (long) par4 * 116129781L;
            var17 = var17 * var17 * 42317861L + var17 * 11L;
            var19 += ((double) ((float) (var17 >> 16 & 15L) / 15.0F) - 0.5) * 0.5;
            var20 += ((double) ((float) (var17 >> 20 & 15L) / 15.0F) - 1.0) * 0.2;
            var15 += ((double) ((float) (var17 >> 24 & 15L) / 15.0F) - 0.5) * 0.5;
        }

        this.drawCrossedSquares(par1Block, this.blockAccess.getBlockMetadata(par2, par3, par4), var19, var20, var15, 1.0F);
        return true;
    }

    @Override
    public void FutureMITE$setFlag(EnumItemRenderType type) {
        renderItemType = type;
    }

    @Override
    public EnumItemRenderType FutureMITE$getFlag() {
        return renderItemType;
    }
}