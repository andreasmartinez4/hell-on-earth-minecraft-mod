package com.amartinez.hellonearth.world.gen.biomes;

import java.util.Random;
import java.util.Set;

import com.amartinez.hellonearth.events.init.ModBiomes;
import com.amartinez.hellonearth.world.gen.HellGenSettings;
import com.amartinez.hellonearth.world.gen.worldtype.ModLayer;
import com.amartinez.hellonearth.world.gen.worldtype.ModLayerUtil;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.PerlinNoiseGenerator;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.LayerUtil;

public class HellBiomeProvider extends OverworldBiomeProvider
{
	private final Layer noiseBiomeLayer;
	protected final Set<Biome> possibleBiomes;

	private static final Set<Biome> biomeList = ImmutableSet.of(ModBiomes.canyon, ModBiomes.hell); 
	public HellBiomeProvider(OverworldBiomeProviderSettings  settingsProvider)
	{
		
		super(settingsProvider);
		this.possibleBiomes = Sets.newHashSet(super.field_226837_c_);
        this.possibleBiomes.addAll(biomeList);
        this.noiseBiomeLayer = ModLayerUtil.createGenLayers(settingsProvider.func_226850_a_(), settingsProvider.func_226851_b_(), (HellGenSettings)settingsProvider.getGeneratorSettings());
	}
	
	@Override
	public Biome getNoiseBiome(int x, int y, int z) 
	{
		return this.noiseBiomeLayer.func_215738_a(x, z);
	}
}
