package com.github.FlyBird.FutureMITE.misc;

import net.minecraft.NBTBase;
import net.minecraft.NBTTagIntArray;
import net.minecraft.NBTTagList;

public class NBTTagListExtend {

    public static int[] func_150306_c(NBTTagList nbtTagList, int i) {
        if (i >= 0 && i < nbtTagList.tagList.size()) {
            NBTBase nbtbase = (NBTBase) nbtTagList.tagList.get(i);
            return nbtbase.getId() == 11 ? ((NBTTagIntArray) nbtbase).intArray : new int[0];
        } else {
            return new int[0];
        }
    }
}
