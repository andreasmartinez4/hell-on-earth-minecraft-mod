package com.amartinez.hellonearth.world.gen.biomes;

import java.util.List;
import java.util.Map;

import com.amartinez.hellonearth.events.init.RegistryEvents;
import com.amartinez.hellonearth.init.BlockInit;
import com.amartinez.hellonearth.world.gen.ModOreGen;
import com.amartinez.hellonearth.world.gen.carver.ModCarver;
import com.amartinez.hellonearth.world.gen.carver.ModConfigCarver;
import com.amartinez.hellonearth.world.gen.features.ModWorldFeatures;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HellForest extends Biome
{
	protected final Map<GenerationStage.Carving, List<ModConfigCarver<?>>> modcarvers = Maps.newHashMap();
	public HellForest() 
	{ 
		super(new Biome.Builder()
				.surfaceBuilder(new ConfiguredSurfaceBuilder<SurfaceBuilderConfig>(SurfaceBuilder.DEFAULT,
						new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState())))
						.precipitation(RainType.NONE)
						.category(Category.FOREST)
						.downfall(0.0F)
						.depth(.160F)
						.temperature(1.0F)
						.scale(0.1f)
						.waterColor(0x33FF5A)
						.waterFogColor(0xFF33E9)
						.parent(null));

		this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL)));
		this.addStructure(Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
		this.addStructure(Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(ModCarver.CAVE, new ProbabilityConfig(0.14285715F)));
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(ModCarver.CANYON, new ProbabilityConfig(0.02F)));
		//this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModWorldFeatures.HELL_SPIKE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(3, 0.01F, 0))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ModWorldFeatures.STALACTITES.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ModWorldFeatures.EXPLOSIVE_TRAP.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ModWorldFeatures.CRYSTALS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ModWorldFeatures.TEST_FEATURE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ModWorldFeatures.UNDERGROUND_VINES.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(ModOreGen.BLOODSTONE, Blocks.DIRT.getDefaultState(), 33)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 256))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FANCY_TREE.withConfiguration(ModWorldFeatures.WITHERED_TREE).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(3, 0.01F, 0))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(ModWorldFeatures.WITHER_ROSE_FIELD).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(2))));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(ModOreGen.GLOWSTONE, BlockInit.STRIPPED_CORRUPTED_LOG.getDefaultState(), 33)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 256))));
		
		this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, ModWorldFeatures.HELL_SPIKE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP.configure(new ChanceConfig(1))));
		DefaultBiomeFeatures.addLakes(this);
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.SHEEP, 12, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.PIG, 10, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.CHICKEN, 10, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.COW, 8, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.WOLF, 5, 4, 4));
		this.addSpawn(EntityClassification.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 14));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 14));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 14));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 14));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 14));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 10));   
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.GIANT, 100, 1, 10));   
	}
	
   @OnlyIn(Dist.CLIENT)
   public int getSkyColor() {
      return 10951947;
   }
	
	@OnlyIn(Dist.CLIENT)
	   public int getGrassColor(double p_225528_1_, double p_225528_3_) {
	      double d0 = INFO_NOISE.noiseAt(p_225528_1_ * 0.0225D, p_225528_3_ * 0.0225D, false);
	      return d0 < -0.1D ? 2894902 : 2894870;
	   }
	
	public <C extends ICarverConfig> void addModCarver(GenerationStage.Carving stage, ModConfigCarver<C> carver) {
	      this.modcarvers.computeIfAbsent(stage, (p_203604_0_) -> {
	         return Lists.newArrayList();
	      }).add(carver);
	   }
}
