package com.amartinez.hellonearth.world.gen.carver;

import java.util.BitSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

import com.amartinez.hellonearth.events.init.RegistryEvents;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.CanyonWorldCarver;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.carver.NetherCaveWorldCarver;
import net.minecraft.world.gen.carver.UnderwaterCanyonWorldCarver;
import net.minecraft.world.gen.carver.UnderwaterCaveWorldCarver;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public abstract class ModCarver extends WorldCarver {


	public static final WorldCarver<ProbabilityConfig> CAVE = register("hellonearth_cave", new ModCaveCarver(ProbabilityConfig::deserialize, 256));
	public static final WorldCarver<ProbabilityConfig> CANYON = register("hellonearth_canyon", new ModCanyonCarver(ProbabilityConfig::deserialize));
	
	protected Set<Block> carvableBlocks = ImmutableSet.of(RegistryEvents.bloodstone, Blocks.GLOWSTONE, Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.TERRACOTTA, Blocks.WHITE_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, Blocks.MAGENTA_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.YELLOW_TERRACOTTA, Blocks.LIME_TERRACOTTA, Blocks.PINK_TERRACOTTA, Blocks.GRAY_TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.CYAN_TERRACOTTA, Blocks.PURPLE_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.BROWN_TERRACOTTA, Blocks.GREEN_TERRACOTTA, Blocks.RED_TERRACOTTA, Blocks.BLACK_TERRACOTTA, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.MYCELIUM, Blocks.SNOW, Blocks.PACKED_ICE);
	
	private static <C extends ICarverConfig, F extends WorldCarver<C>> F register(String key, F carver) 
	{
		return (F)(Registry.<WorldCarver<?>>register(Registry.CARVER, key, carver));
	}
	   
	public ModCarver(Function p_i49921_1_, int p_i49921_2_) 
	{
		super(p_i49921_1_, p_i49921_2_);
	}
}