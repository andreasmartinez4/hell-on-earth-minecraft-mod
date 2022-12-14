package com.amartinez.hellonearth.world.gen.biomes;

import com.amartinez.hellonearth.world.gen.carver.ModCarver;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class Canyon extends Biome
{

	public Canyon() 
	{
		super(new Biome.Builder().surfaceBuilder(SurfaceBuilder.MOUNTAIN, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG).precipitation(Biome.RainType.RAIN).category(Biome.Category.EXTREME_HILLS).depth(1.5F).scale(1F).temperature(0.2F).downfall(0.3F).waterColor(4159204).waterFogColor(329011).parent((String)null));
		
	    this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL)));
	    this.addStructure(Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	    this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(ModCarver.CAVE, new ProbabilityConfig(0.14285715F)));
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(ModCarver.CANYON, new ProbabilityConfig(0.02F)));
	    DefaultBiomeFeatures.addCarvers(this);
	    DefaultBiomeFeatures.addStructures(this);
	    DefaultBiomeFeatures.addLakes(this);
	    DefaultBiomeFeatures.addMonsterRooms(this);
	    DefaultBiomeFeatures.addStoneVariants(this);
	    DefaultBiomeFeatures.addOres(this);
	    DefaultBiomeFeatures.addSedimentDisks(this);
	    DefaultBiomeFeatures.addScatteredOakAndSpruceTrees(this);
	    DefaultBiomeFeatures.addDefaultFlowers(this);
	    DefaultBiomeFeatures.addSparseGrass(this);
	    DefaultBiomeFeatures.addMushrooms(this);
	    DefaultBiomeFeatures.addReedsAndPumpkins(this);
	    DefaultBiomeFeatures.addSprings(this);
	    DefaultBiomeFeatures.addExtraEmeraldOre(this);
	    DefaultBiomeFeatures.addInfestedStone(this);
	    DefaultBiomeFeatures.addFreezeTopLayer(this);
	    this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.SHEEP, 12, 4, 4));
	    this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.PIG, 10, 4, 4));
	    this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.CHICKEN, 10, 4, 4));
	    this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.COW, 8, 4, 4));
	    this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.LLAMA, 5, 4, 6));
	    this.addSpawn(EntityClassification.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
	    this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
	    this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
	    this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
	    this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
	    this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
	    this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
	    this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
	    this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));
	}

}
