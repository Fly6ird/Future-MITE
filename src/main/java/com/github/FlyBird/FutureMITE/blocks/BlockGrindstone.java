package com.github.FlyBird.FutureMITE.blocks;


import com.github.FlyBird.FutureMITE.api.IFutureMITEPlayer;
import com.github.FlyBird.FutureMITE.render.RenderTypes;
import com.github.FlyBird.FutureMITE.tileentities.TileEntityGrindstone;
import net.minecraft.*;


public class BlockGrindstone extends Block implements ITileEntityProvider{
    private Icon GrindStone;

    protected BlockGrindstone(int par1) {
        super(par1, Material.stone, new BlockConstants().setNotAlwaysLegal().setNeverHidesAdjacentFaces());
        this.setBlockBoundsForAllThreads(0.125, 0.0, 0.125, 0.875, 1.0, 0.875);
        this.setHardness(2.0F);
        this.setResistance(6.0F);
        setCreativeTab(CreativeTabs.tabDecorations);
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        this.GrindStone = par1IconRegister.registerIcon( "grindstone");

    }

    @Override
    public Icon getIcon(int side, int metadata) {
        return this.GrindStone;
    }

    @Override
    public int getRenderType() {
        return RenderTypes.grindStoneRenderType;
    }

    @Override
    public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
        if (!world.isAirOrPassableBlock(x, y + 1, z, false))
            return false;
        if (player.onServer()) {
            TileEntityGrindstone tile_entity = (TileEntityGrindstone)world.getBlockTileEntity(x, y, z);
            if (tile_entity != null ) {
                ((IFutureMITEPlayer) player).futureMITE$displayGUIGrindstone(x, y, z, tile_entity.getSlots());
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityGrindstone();
    }
}
