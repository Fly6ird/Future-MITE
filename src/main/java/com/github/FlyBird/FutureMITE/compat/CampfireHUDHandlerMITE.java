package com.github.FlyBird.FutureMITE.compat;


import com.github.FlyBird.FutureMITE.blocks.BlockCampfire;
import com.github.FlyBird.FutureMITE.tileentities.TileEntityCampfire;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import mcp.mobius.waila.api.impl.ModuleRegistrar;
import net.minecraft.*;

import java.util.List;

public class CampfireHUDHandlerMITE implements IWailaDataProvider {

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return null;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        Block block = accessor.getBlock();
        if (block instanceof BlockCampfire ) {
            TileEntity tileEntity = accessor.getTileEntity();
            if (tileEntity instanceof TileEntityCampfire) {
                int burntime = ((TileEntityCampfire) tileEntity).getBurnTime();
                currenttip.add(EnumChatFormatting.RESET + StatCollector.translateToLocal("campfire.burntime") + ":" + burntime);
            }
        }
        return currenttip;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    @Override
    public NBTTagCompound getNBTData(ServerPlayer player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
        return null;
    }

    public static void register() {
        callbackRegister(ModuleRegistrar.instance());
    }

    public static void callbackRegister(IWailaRegistrar registrar) {
        IWailaDataProvider instance = new CampfireHUDHandlerMITE();
        registrar.registerBodyProvider(instance, BlockCampfire.class);
        //registrar.registerBodyProvider(instance, BlockExtinguishedCampfire.class);
    }
}
