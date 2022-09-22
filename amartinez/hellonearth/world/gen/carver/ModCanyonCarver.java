package com.amartinez.hellonearth.world.gen.carver;

import java.util.function.Function;

import com.amartinez.hellonearth.events.init.RegistryEvents;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.carver.CanyonWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class ModCanyonCarver extends CanyonWorldCarver
{

	public ModCanyonCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> p_i49930_1_) 
	{
		super(p_i49930_1_);
		this.carvableBlocks = ImmutableSet.of(RegistryEvents.bloodstone, Blocks.GLOWSTONE, Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.GRAVEL);
	}

}
