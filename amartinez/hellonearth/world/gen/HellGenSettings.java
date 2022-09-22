package com.amartinez.hellonearth.world.gen;

import net.minecraft.world.gen.OverworldGenSettings;

public class HellGenSettings extends OverworldGenSettings 
{
	   public int getBiomeSize() 
	   {
	      return 4;
	   }

	   public int getRiverSize() 
	   {
	      return 4;
	   }

	   public int getBiomeId()
	   {
	      return -1;
	   }

	   public int getBedrockFloorHeight() 
	   {
	      return 0;
	   }
}
