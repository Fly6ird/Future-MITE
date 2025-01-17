/*
package com.github.FlyBird.FutureMITE.world.structure.monument;

i
import net.minecraft.Block;
import net.minecraft.IChunkProvider;
import net.minecraft.World;

import java.util.Random;


public class MonumentGeneratorIWG implements IWorldGenerator {

	public static StructureOceanMonument oceanMonumentGenerator = new StructureOceanMonument();
	
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		Block[] ablock = new Block[65536];
		
		if (
				(world.provider.dimensionId == 0)
				&& (world.getWorldInfo().isMapFeaturesEnabled())
				)
		{
			// THIS BLOCK RUNS REGARDLESS OF WHETHER GENERATION TYPE IS VANILLA, ALTERNATE, REALISTIC
			oceanMonumentGenerator = (StructureOceanMonument) TerrainGen.getModdedMapGen(new StructureOceanMonument(), CUSTOM);
		
			// This block only seems to fire with a vanilla world generator :(
			if (StructureOceanMonument.canSpawnStructureAtCoords(world, chunkX, chunkZ)) {
				
				//Added this line to update monument list used by event listener.
				//SpawnEventListener.refreshMonumentCoordsList(world);
				
				int chunkOffset = 2;
				for (int OM_x = -chunkOffset; OM_x <= chunkOffset-1; OM_x++) {
					for (int OM_z = -chunkOffset; OM_z <= chunkOffset-1; OM_z++) {
						
						this.oceanMonumentGenerator.func_151539_a(world.getChunkProvider(), world, chunkX, chunkZ, ablock); //THIS needed for chunk generation
						
						try {
							boolean generateInChunk = this.oceanMonumentGenerator.generateStructuresInChunk(world, rand, chunkX+OM_x, chunkZ+OM_z); //THIS needed for chunk generation
		                }
		                catch (Exception e) {
		                }
						
					}
				}
				
			}
		}

	}
	
	public static StructureOceanMonument getOceanMonumentGenerator()
	{
		return oceanMonumentGenerator;
	}
	
}*/
