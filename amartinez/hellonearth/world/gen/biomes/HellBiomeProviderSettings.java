package com.amartinez.hellonearth.world.gen.biomes;

import com.amartinez.hellonearth.events.init.ModWorldType;
import com.amartinez.hellonearth.world.gen.HellGenSettings;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.IBiomeProviderSettings;
import net.minecraft.world.storage.WorldInfo;

public class HellBiomeProviderSettings implements IBiomeProviderSettings {
	private final long seed;
	private final WorldType worldType;
	private HellGenSettings generatorSettings = new HellGenSettings();

	public HellBiomeProviderSettings(WorldInfo info)
	{
		this.seed = info.getSeed();
		this.worldType = info.getGenerator();
	}

	public HellBiomeProviderSettings setGeneratorSettings(HellGenSettings settings) 
	{
		this.generatorSettings = settings;
		return this;
	}

	public long getSeed()
	{
		return this.seed;
	}

	public WorldType getWorldType()
	{
		return this.worldType;
	}

	public HellGenSettings getGeneratorSettings() 
	{
		return this.generatorSettings;
	}

}
