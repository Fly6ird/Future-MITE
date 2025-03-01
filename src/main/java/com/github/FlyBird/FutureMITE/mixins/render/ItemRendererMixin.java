package com.github.FlyBird.FutureMITE.mixins.render;

import com.github.FlyBird.FutureMITE.api.EnumItemRenderType;
import com.github.FlyBird.FutureMITE.api.IRenderBlock;
import net.minecraft.EntityLivingBase;
import net.minecraft.ItemRenderer;
import net.minecraft.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ItemRenderer.class})
public class ItemRendererMixin {
    @Unique
    private final ItemRenderer instance = (ItemRenderer) (Object) this;


    //手持
    @Inject(method = {"renderItem"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/RenderBlocks;renderBlockAsItem(Lnet/minecraft/Block;IF)V")})
    private void renderItem(EntityLivingBase par1EntityLivingBase, ItemStack par2ItemStack, int par3, CallbackInfo ci) {
        ((IRenderBlock) (instance.renderBlocksInstance)).FutureMITE$setFlag(EnumItemRenderType.HAND);

    }
}
