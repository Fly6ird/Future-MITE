package com.github.FlyBird.FutureMITE.mixins.item;

import com.github.FlyBird.FutureMITE.blocks.Blocks;
import net.minecraft.Block;
import net.minecraft.ItemScythe;
import net.minecraft.ItemTool;
import net.minecraft.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemScythe.class)
public abstract class ItemScytheMixin extends ItemTool {
    protected ItemScytheMixin(int par1, Material material) {
        super(par1, material);
    }

    // 在构造器调用时注入
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onPlayerConstructor(CallbackInfo ci) {
        this.addBlocksEffectiveAgainst(new Block[]{Blocks.bigGrass});
        //System.out.println("EntityPlayer构造器被调用了！");
        // 你可以在这里进行其他操作，例如修改实例的字段或执行其他逻辑
    }
}
