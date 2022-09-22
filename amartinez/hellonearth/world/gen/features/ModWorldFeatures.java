package com.amartinez.hellonearth.world.gen.features;

import java.util.Random;
import java.util.function.Function;

import com.amartinez.hellonearth.events.init.RegistryEvents;
import com.amartinez.hellonearth.init.BlockInit;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.IceSpikeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;

public abstract class ModWorldFeatures extends Feature
{
	
	public static final Feature<NoFeatureConfig> HELL_SPIKE = register("hell_spike", new HellSpikeFeature(NoFeatureConfig::deserialize));
	public static final Feature<NoFeatureConfig> STALACTITES = register("stalactites", new StalactitesFeature(NoFeatureConfig::deserialize));
	public static final Feature<NoFeatureConfig> EXPLOSIVE_TRAP = register("explosive_trap", new CaveBlockSpawn(NoFeatureConfig::deserialize, 10, 55, true, RegistryEvents.EXPLOSIVE_TRAP, Direction.DOWN));
	public static final Feature<NoFeatureConfig> TEST_FEATURE = register("test_feature", new CaveBlockSpawn(NoFeatureConfig::deserialize, 1, 100, false, Blocks.END_ROD, null));
	public static final Feature<NoFeatureConfig> CRYSTALS = register("crystals", new CrystalSpawn(NoFeatureConfig::deserialize));
	public static final Feature<NoFeatureConfig> UNDERGROUND_VINES = register("underground_vines", new UndergroundVinesFeature(NoFeatureConfig::deserialize));
	
	public static final BlockClusterFeatureConfig WITHER_ROSE_FIELD = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).func_227407_a_(Blocks.WITHER_ROSE.getDefaultState(), 2), new SimpleBlockPlacer())).tries(64).build();
	
	public static final TreeFeatureConfig HELL_TREE = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.ANDESITE.getDefaultState()), new SimpleBlockStateProvider(Blocks.BIRCH_LEAVES.getDefaultState()), new BlobFoliagePlacer(2, 0))).baseHeight(4).heightRandA(2).foliageHeight(3).ignoreVines().build();
	public static final TreeFeatureConfig WITHERED_TREE = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.CORRUPTED_LOG.getDefaultState()), new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()), new BlobFoliagePlacer(2, 0))).baseHeight(6).heightRandA(3).foliageHeight(3).ignoreVines().build();
	
	public ModWorldFeatures(Function configFactoryIn) 
	{
		super(configFactoryIn);
	}
	
	private static <C extends IFeatureConfig, F extends Feature<C>> F register(String key, F value)
	{
		return (F)(Registry.<Feature<?>>register(Registry.FEATURE, key, value));
	}



}
