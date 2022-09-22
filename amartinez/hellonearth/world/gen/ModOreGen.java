package com.amartinez.hellonearth.world.gen;

import java.util.function.Predicate;

import com.amartinez.hellonearth.events.init.RegistryEvents;
import com.amartinez.hellonearth.world.gen.features.ModWorldFeatures;
import com.google.common.collect.ImmutableList;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockBlobConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureRadiusConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraftforge.registries.ForgeRegistries;

public class ModOreGen 
{
	public static final TreeFeatureConfig treething = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.BIRCH_LOG.getDefaultState()), new SimpleBlockStateProvider(Blocks.BIRCH_LEAVES.getDefaultState()), new BlobFoliagePlacer(4, 0))).baseHeight(40).heightRandA(5).foliageHeight(3).ignoreVines().decorators(ImmutableList.of(new BeehiveTreeDecorator(0.002F))).setSapling((net.minecraftforge.common.IPlantable)Blocks.BIRCH_SAPLING).build();
	public static OreFeatureConfig.FillerBlockType BLOODSTONE =  OreFeatureConfig.FillerBlockType.create("BLOODSTONE", null, new BlockMatcher(RegistryEvents.bloodstone));
	public static OreFeatureConfig.FillerBlockType GLOWSTONE = OreFeatureConfig.FillerBlockType.create("GLOWSTONE", null, new BlockMatcher(Blocks.GLOWSTONE));
	
	public static void generateOre() 
	{
		// Runs through every biome
		for(Biome biome : ForgeRegistries.BIOMES) 
		{
			// Placement Configs
			ConfiguredPlacement silverPlacement = Placement.COUNT_RANGE.configure(new CountRangeConfig(15, 5, 5, 40));
			ConfiguredPlacement titaniumPlacement = Placement.COUNT_RANGE.configure(new CountRangeConfig(1, 2, 2, 10));
			//biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, ModWorldFeatures.HELL_SPIKE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(1))));
			// Gen
			//biome.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.HELL_CAVE, new ProbabilityConfig(0.14285715F)));
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
					Feature.ORE.withConfiguration(new OreFeatureConfig(BLOODSTONE,
							RegistryEvents.silver_ore.getDefaultState(), 10)).withPlacement(silverPlacement));
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
					Feature.ORE.withConfiguration(new OreFeatureConfig(BLOODSTONE,
							RegistryEvents.titanium_ore.getDefaultState(), 3)).withPlacement(titaniumPlacement));
			// Adding Vanillia Ores
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(BLOODSTONE, Blocks.COAL_ORE.getDefaultState(), 17)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 128))));
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(BLOODSTONE, Blocks.IRON_ORE.getDefaultState(), 9)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 64))));
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(BLOODSTONE, Blocks.GOLD_ORE.getDefaultState(), 9)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(2, 0, 0, 32))));
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(BLOODSTONE, Blocks.REDSTONE_ORE.getDefaultState(), 8)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(8, 0, 0, 16))));
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(BLOODSTONE, Blocks.DIAMOND_ORE.getDefaultState(), 8)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(1, 0, 0, 16))));
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(BLOODSTONE, Blocks.LAPIS_ORE.getDefaultState(), 7)).withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(1, 16, 16))));
			//biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.BAMBOO.withConfiguration(new ProbabilityConfig(0.2F)).withPlacement(Placement.TOP_SOLID_HEIGHTMAP_NOISE_BIASED.configure(new TopSolidWithNoiseConfig(160, 80.0D, 0.3D, Heightmap.Type.WORLD_SURFACE_WG))));
		    //biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(Feature.FANCY_TREE.withConfiguration(FANCY_TREE_CONFIG).func_227227_a_(0.05F), Feature.JUNGLE_GROUND_BUSH.withConfiguration(JUNGLE_GROUND_BUSH_CONFIG).func_227227_a_(0.15F), Feature.MEGA_JUNGLE_TREE.withConfiguration(MEGA_JUNGLE_TREE_CONFIG).func_227227_a_(0.7F)), Feature.RANDOM_PATCH.withConfiguration(LUSH_GRASS_CONFIG))).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(30, 0.1F, 1))));
		//	biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(OAK_LOG), new SimpleBlockStateProvider(OAK_LEAVES), new BlobFoliagePlacer(2, 0))).baseHeight(4).heightRandA(2).foliageHeight(3).ignoreVines().setSapling((net.minecraftforge.common.IPlantable)Blocks.OAK_SAPLING).build(););
			//biome.getGrassColor(8328198, 10951947);
			//biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(Feature.NORMAL_TREE.withConfiguration(treething).func_227227_a_(0.2F), Feature.FANCY_TREE.withConfiguration(treething).func_227227_a_(0.1F)), Feature.NORMAL_TREE.withConfiguration(treething))).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));
		}
	}
}
