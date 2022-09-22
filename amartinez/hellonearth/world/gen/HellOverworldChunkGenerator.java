package com.amartinez.hellonearth.world.gen;

import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.OverworldChunkGenerator;

public class HellOverworldChunkGenerator extends OverworldChunkGenerator
{
	public HellOverworldChunkGenerator(IWorld worldIn, BiomeProvider provider, HellGenSettings settingsIn) 
	{
		super(worldIn, provider, settingsIn);
	}
}
