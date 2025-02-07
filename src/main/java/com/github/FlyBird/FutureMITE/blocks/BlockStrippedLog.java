package com.github.FlyBird.FutureMITE.blocks;

import net.minecraft.*;

public class BlockStrippedLog extends BlockRotatedPillar {
  private Icon stripped_top;
  private Icon stripped_side;
  
  protected BlockStrippedLog(int par1) {
    super(par1, Material.wood);
    modifyMinHarvestLevel(1);
    setHardness(0.9F);
    setMaxStackSize(4);
    setStepSound(Blocks.stepSoundWood);
    setCreativeTab(CreativeTabs.tabBlock);
  }

  protected BlockStrippedLog(int par1,String id) {
    super(par1, Material.wood);
    modifyMinHarvestLevel(1);
    setHardness(0.9F);
    setMaxStackSize(4);
    setStepSound(Blocks.stepSoundWood);
    setCreativeTab(CreativeTabs.tabBlock);
    this.setTextureName(id);
    this.setUnlocalizedName("log."+id);
  }

  protected Icon getSideIcon(int par1) {
    return this.stripped_side;
  }
  
  protected Icon getEndIcon(int par1) {
    return this.stripped_top;
  }
  
  public String getMetadataNotes() {
    return "Bits 1 and 2 used for subtype, bit 4 set if aligned WE, and bit 8 set if aligned NS";
  }
  
  public boolean isValidMetadata(int metadata) {
    return (metadata >= 0 && metadata < 16 && !BitHelper.isBitSet(metadata, 12));
  }

  @Override
  public int getBlockSubtypeUnchecked(int metadata) {
    return 0;
  }
  
  public void registerIcons(IconRegister par1IconRegister) {
    this.stripped_side = par1IconRegister.registerIcon("futuremite:log/" + this.getTextureName() + "_side");
    this.stripped_top = par1IconRegister.registerIcon("futuremite:log/" + this.getTextureName() + "_top");
  }
  
  public int dropBlockAsEntityItem(BlockBreakInfo info) {
    if (info.wasExploded())
      return dropBlockAsEntityItem(info, Item.stick.itemID, 0, 1, 1.5F); 
    return super.dropBlockAsEntityItem(info);
  }
  
  public String getNameDisambiguationForReferenceFile(int metadata) {
    return "stripped";
  }
}
