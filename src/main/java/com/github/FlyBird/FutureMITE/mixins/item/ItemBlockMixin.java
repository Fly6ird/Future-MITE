package com.github.FlyBird.FutureMITE.mixins.item;

import com.github.FlyBird.FutureMITE.blocks.BlockModStairs;
import com.github.FlyBird.FutureMITE.blocks.BlockModWoodPlanks;
import com.github.FlyBird.FutureMITE.blocks.BlockStrippedLog;
import net.minecraft.Block;
import net.minecraft.Item;
import net.minecraft.ItemBlock;
import net.minecraft.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemBlock.class)
public class ItemBlockMixin extends Item {
    @Shadow
    public Block getBlock() {
        return null;
    }


    @Inject(method = "getBurnTime", at = @At("HEAD"), cancellable = true)
    private void inject(ItemStack item_stack, CallbackInfoReturnable<Integer> cir) {
        Block block = this.getBlock();
        if (block instanceof BlockStrippedLog) {
            cir.setReturnValue(1600);
        }
        if (block instanceof BlockModWoodPlanks) {
            cir.setReturnValue(400);
        }
        if (block instanceof BlockModStairs) {
            cir.setReturnValue(400);
        }
    }
}
