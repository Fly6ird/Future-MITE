package com.github.FlyBird.FutureMITE.mixins.item;

import com.github.FlyBird.FutureMITE.blocks.BlockModSapling;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.ItemDye.suppress_standard_particle_effect;
import static net.minecraft.ItemDye.tryFertilize;

@Mixin(ItemDye.class)
public class ItemDyeMixin extends Item {

    @Inject(method = "tryFertilize", at = @At("HEAD"), cancellable = true)
    private static void onTryFertilize(ItemStack item_stack, World world, int x, int y, int z, EnumFace face, CallbackInfoReturnable<Boolean> cir) {
        // 你自己的逻辑，比如我们在施肥时添加一个额外的检查
        Block block = world.getBlock(x, y, z);
        if (block instanceof BlockModSapling) {

            if((double)world.rand.nextFloat() < 0.45D) {
                if(((BlockModSapling) block).markOrGrowMarked(world, x, y, z, world.rand))
                {
                    if (!world.isRemote)
                    {
                        world.playAuxSFX(2005, x, y, z, 0);
                    }
                }

            }

            cir.setReturnValue(true); // 如果我们想要阻止原方法的执行并返回true
        }
    }

}
