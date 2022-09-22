package com.amartinez.hellonearth.events.init;

import com.amartinez.hellonearth.world.gen.HellGenSettings;
import com.amartinez.hellonearth.world.gen.HellOverworldChunkGenerator;
import com.amartinez.hellonearth.world.gen.biomes.HellBiomeProvider;
import com.amartinez.hellonearth.world.gen.biomes.HellBiomeProviderSettings;
import com.amartinez.hellonearth.world.gen.worldtype.HellChunkGenerator;

import net.minecraft.block.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.storage.WorldInfo;

public class ModWorldType extends WorldType
{
	public ModWorldType()
	{
		super("Hell On Earth");
	}
	
	@Override
	public ChunkGenerator<?> createChunkGenerator(World world)
	{
		WorldInfo worldInfo = world.getWorldInfo();
		OverworldBiomeProviderSettings provider_settings = new OverworldBiomeProviderSettings(worldInfo);
		SingleBiomeProviderSettings single_provider_settings = new SingleBiomeProviderSettings(worldInfo);
		single_provider_settings.setBiome(ModBiomes.hell);
		OverworldGenSettings ovSettings = new OverworldGenSettings();
		
		HellBiomeProviderSettings providerSettings = new HellBiomeProviderSettings(worldInfo);
		
		HellGenSettings settings = new HellGenSettings();
		settings.setDefaultBlock(RegistryEvents.bloodstone.getDefaultState());
		ovSettings.setDefaultBlock(RegistryEvents.bloodstone.getDefaultState());

		//return new HellChunkGenerator(world, new HellBiomeProvider(providerSettings), settings);
		return new OverworldChunkGenerator(world, new SingleBiomeProvider(single_provider_settings), ovSettings) ;
	}
	
	/*@Override
	public ChunkGenerator<?> createChunkGenerator(World world)
	{
		HellGenSettings genSettings = new HellGenSettings();
		OverworldBiomeProviderSettings biomeProviderSettings = new OverworldBiomeProviderSettings(world.getWorldInfo());
		biomeProviderSettings.setGeneratorSettings(genSettings);
		
		return new HellOverworldChunkGenerator(world, new HellBiomeProvider(biomeProviderSettings), genSettings);
		
	}*/
	
	
}
