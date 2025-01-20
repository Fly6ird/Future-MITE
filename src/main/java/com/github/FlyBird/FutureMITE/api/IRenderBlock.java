package com.github.FlyBird.FutureMITE.api;

import net.minecraft.ItemStack;

public interface IRenderBlock {
    void FutureMITE$setFlag(EnumItemRenderType type);

    EnumItemRenderType FutureMITE$getFlag();
}
