package com.amartinez.hellonearth.world.gen;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.ReportedException;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.WorldGenRegion;

public abstract class ModChunkGenerator extends ChunkGenerator
{
	public ModChunkGenerator(IWorld worldIn, BiomeProvider biomeProviderIn, GenerationSettings generationSettingsIn) 
	{
		super(worldIn, biomeProviderIn, generationSettingsIn);
	}
	
	@Override
	public void decorate(WorldGenRegion region) {
	      int i = region.getMainChunkX();
	      int j = region.getMainChunkZ();
	      int k = i * 16;
	      int l = j * 16;
	      BlockPos blockpos = new BlockPos(k, 0, l);
	      Biome biome = this.func_225552_a_(region.getBiomeManager(), blockpos.add(8, 8, 8));
	      SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
	      long i1 = sharedseedrandom.setDecorationSeed(region.getSeed(), k, l);

	      for(GenerationStage.Decoration generationstage$decoration : GenerationStage.Decoration.values()) {
	         try {
	            biome.decorate(generationstage$decoration, this, region, i1, sharedseedrandom, blockpos);
	         } catch (Exception exception) {
	            CrashReport crashreport = CrashReport.makeCrashReport(exception, "Biome decoration");
	            crashreport.makeCategory("Generation").addDetail("CenterX", i).addDetail("CenterZ", j).addDetail("Step", generationstage$decoration).addDetail("Seed", i1).addDetail("Biome", Registry.BIOME.getKey(biome));
	            throw new ReportedException(crashreport);
	         }
	      }

	   }

}
