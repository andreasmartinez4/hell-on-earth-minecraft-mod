package com.amartinez.hellonearth.world.gen.worldtype;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amartinez.hellonearth.world.gen.biomes.HellBiomeProvider;

import net.minecraft.util.SharedConstants;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.Layer;

public class ModLayer
{

   public ModLayer(IAreaFactory<LazyArea> lazyAreaFactoryIn) {
		 this.field_215742_b = lazyAreaFactoryIn.make();
		// TODO Auto-generated constructor stub
	}
		 private static final Logger LOGGER = LogManager.getLogger();
		   private final LazyArea field_215742_b;

	private Biome func_215739_a(int p_215739_1_) {
		Random rand = new Random();
		
		Biome biome =  Registry.BIOME.getByValue(p_215739_1_);
	     // Biome biome = HellBiomeProvider.modBiomeList[rand.nextInt(3)];
	      if (biome == null) {
	         if (SharedConstants.developmentMode) {
	            throw (IllegalStateException)Util.pauseDevMode(new IllegalStateException("Unknown biome id: " + p_215739_1_));
	         } else {
	            LOGGER.warn("Unknown biome id: ", (int)p_215739_1_);
	            return Biomes.DEFAULT;
	         }
	      } else {
	         return biome;
	      }
	   }

	   public Biome func_215738_a(int p_215738_1_, int p_215738_2_) {
	      return this.func_215739_a(this.field_215742_b.getValue(p_215738_1_, p_215738_2_));
	   }
}
