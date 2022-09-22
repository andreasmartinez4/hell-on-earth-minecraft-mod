package com.amartinez.hellonearth.events.init;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.PlainsBiome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.BiomeDictionary.Type;

public class ModBiomes 
{
	public static Biome hell;
	public static Biome canyon;

	public static void registerBiomes()
	{

		registerBiome(hell, Type.PLAINS, Type.DRY);
		registerBiome(canyon, Type.MOUNTAIN);
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeEntry(hell, 10));
		BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeEntry(canyon, 10));
	}
	
	public static void registerBiome(Biome biome, Type... types)
	{
		BiomeDictionary.addTypes(biome, types);

		BiomeManager.addSpawnBiome(biome);
	}
	
	public static void getRidOfBiomes()
	{
		BiomeManager.removeBiome(BiomeType.WARM, new BiomeEntry(Biomes.PLAINS, 5));
		
	}

}
