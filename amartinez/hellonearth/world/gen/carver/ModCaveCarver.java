package com.amartinez.hellonearth.world.gen.carver;

import java.util.BitSet;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

import com.amartinez.hellonearth.events.init.RegistryEvents;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.storage.WorldInfo;

public class ModCaveCarver extends CaveWorldCarver
{

	public ModCaveCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> p_i49929_1_, int maxHeight) 
	{
		super(p_i49929_1_, maxHeight);
		this.carvableBlocks = ImmutableSet.of(RegistryEvents.bloodstone, Blocks.BLACK_STAINED_GLASS, Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.GRAVEL);
	}

	// Sets the radius of caves - too big will look circular
	@Override
	protected float generateCaveRadius(Random rand) 
	{
		//Random rand = new Random();
		float f = rand.nextFloat() *2.0F  + rand.nextFloat();
		if (rand.nextInt(10) == 0) {
			f *= rand.nextFloat() * rand.nextFloat() * 3.0F + 1.0F;
	 	    }
		
		return f;
	}
	
	
	  public boolean func_225555_a_(IChunk p_225555_1_, Function<BlockPos, Biome> p_225555_2_, Random p_225555_3_, int p_225555_4_, int p_225555_5_, int p_225555_6_, int p_225555_7_, int p_225555_8_, BitSet p_225555_9_, ProbabilityConfig p_225555_10_) {
	      int i = (this.func_222704_c() * 2 - 1) * 16;
	      int j = p_225555_3_.nextInt(p_225555_3_.nextInt(p_225555_3_.nextInt(this.func_222724_a()) + 1) + 1);

	      for(int k = 0; k < j; ++k) {
	         double d0 = (double)(p_225555_5_ * 16 + p_225555_3_.nextInt(16));
	         double d1 = (double)this.generateCaveStartY(p_225555_3_);
	         double d2 = (double)(p_225555_6_ * 16 + p_225555_3_.nextInt(16));
	         int l = 1;
	         if (p_225555_3_.nextInt(4) == 0) {
	            double d3 = 0.5D;
	            float f1 = 1.0F + p_225555_3_.nextFloat() * 6.0F; // makes those circular creators
	            this.func_227205_a_(p_225555_1_, p_225555_2_, p_225555_3_.nextLong(), p_225555_4_, p_225555_7_, p_225555_8_, d0, d1, d2, f1, 0.5D, p_225555_9_);
	            l += p_225555_3_.nextInt(4);
	         }

	         for(int k1 = 0; k1 < l; ++k1) {
	            float f = p_225555_3_.nextFloat() * ((float)Math.PI * 2F);
	            float f3 = (p_225555_3_.nextFloat() - 0.5F) / 100.0F;
	            float f2 = this.generateCaveRadius(p_225555_3_);
	            int i1 = i - p_225555_3_.nextInt(i / 4);
	            int j1 = 0;
	            func_227206_a_(p_225555_1_, p_225555_2_, p_225555_3_.nextLong(), p_225555_4_, p_225555_7_, p_225555_8_, d0, d1, d2, f2, f, f3, 0, i1, this.func_222725_b(), p_225555_9_);
	         }
	      }

	      return true;
	   }
	
	@Override
	 protected void func_227206_a_(IChunk chunk, Function<BlockPos, Biome> p_227206_2_, long p_227206_3_, int cave_step_value, int chunkX, int chunkZ, double p_227206_8_, double caveStartY, double p_227206_12_, float p_227206_14_, float horozontallness, float verticallity, int p_227206_17_, int p_227206_18_, double p_227206_19_, BitSet p_227206_21_) {
	      Random random = new Random(p_227206_3_);
	      int i = random.nextInt(p_227206_18_ / 2) + p_227206_18_ / 4;
	      boolean flag = random.nextInt(6) == 0;
	      float f = 0.0F;
	      float f1 = 0.0F;
	      

	      for(int j = p_227206_17_; j < p_227206_18_; ++j) {
	    	 // d0 the radius of the skiny cave things
	         double d0 = 3.4 + (double)(MathHelper.sin((float)Math.PI * (float)j / (float)p_227206_18_) * p_227206_14_);
	         double d1 = d0 * p_227206_19_;
	         // Stuff that makes the cave carve like a cave
	         float f2 = MathHelper.cos(verticallity);
	         p_227206_8_ += (double)(MathHelper.cos(horozontallness) * f2);
	         caveStartY += (double)MathHelper.sin(verticallity);
	         p_227206_12_ += (double)(MathHelper.sin(horozontallness) * f2);
	         verticallity = verticallity * (flag ? 0.92F : 0.7F);
	         // Next two seem to affect how the cave spreads vertically and vertically - higher is more vertical, lower is more horozontal
	         verticallity = verticallity + f1 * 0.1F; // verticality
	         // Something to do with the verticallity of the cave
	         horozontallness += f * 0.1F; // horontalniess
	         // Something to do with y values or offest
	         f1 = f1 * 0.9F;
	         f = f * 0.75F;
	         f1 = f1 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
	         f = f + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
	         if (j == i && p_227206_14_ > 1.0F) {
	            this.func_227206_a_(chunk, p_227206_2_, random.nextLong(), cave_step_value, chunkX, chunkZ, p_227206_8_, caveStartY, p_227206_12_, random.nextFloat() * 0.5F + 0.5F, horozontallness - ((float)Math.PI / 2F), 12.0F, j, p_227206_18_, 1.0D, p_227206_21_);
	            this.func_227206_a_(chunk, p_227206_2_, random.nextLong(), cave_step_value, chunkX, chunkZ, p_227206_8_, caveStartY, p_227206_12_, random.nextFloat() * 0.5F + 0.5F, horozontallness + ((float)Math.PI / 2F), 12.0F, j, p_227206_18_, 1.0D, p_227206_21_);
	            return;
	         }

	         if (random.nextInt(4) != 0) {
	            if (!idkwhatthisdoes(chunkX, chunkZ, p_227206_8_, p_227206_12_, j, p_227206_18_, p_227206_14_)) {
	               return;
	            }
	            // probs function that prepares to carve
	            whatido(chunk, p_227206_2_, p_227206_3_, cave_step_value, chunkX, chunkZ, p_227206_8_, caveStartY, p_227206_12_, d0, d1, p_227206_21_);
	         }
	      }

	   }
	
	   protected boolean idkwhatthisdoes(int p_222702_1_, int p_222702_2_, double p_222702_3_, double p_222702_5_, int p_222702_7_, int p_222702_8_, float p_222702_9_) {
		      double d0 = (double)(p_222702_1_ * 16 + 8);
		      double d1 = (double)(p_222702_2_ * 16 + 8);
		      double d2 = p_222702_3_ - d0;
		      double d3 = p_222702_5_ - d1;
		      double d4 = (double)(p_222702_8_ - p_222702_7_);
		      double d5 = (double)(p_222702_9_ + 2.0F + 16.0F);
		      return d2 * d2 + d3 * d3 - d4 * d4 <= d5 * d5;
		   }
	   
	   protected boolean whatido(IChunk chunk, Function<BlockPos, Biome> p_227208_2_, long p_227208_3_, int p_227208_5_, int chunkX, int chunkZ, double p_227208_8_, double caveStartY, double p_227208_12_, double p_227208_14_, double p_227208_16_, BitSet p_227208_18_) {
		      Random random = new Random(p_227208_3_ + (long)chunkX + (long)chunkZ);
		      double d0 = (double)(chunkX * 16 + 8);
		      double d1 = (double)(chunkZ * 16 + 8);
		      if (!(p_227208_8_ < d0 - 16.0D - p_227208_14_ * 2.0D) && !(p_227208_12_ < d1 - 16.0D - p_227208_14_ * 2.0D) && !(p_227208_8_ > d0 + 16.0D + p_227208_14_ * 2.0D) && !(p_227208_12_ > d1 + 16.0D + p_227208_14_ * 2.0D)) {
		         int minX = Math.max(MathHelper.floor(p_227208_8_ - p_227208_14_) - chunkX * 16 - 1, 0);
		         int maxX = Math.min(MathHelper.floor(p_227208_8_ + p_227208_14_) - chunkX * 16 + 1, 16);
		         int minY = Math.max(MathHelper.floor(caveStartY - p_227208_16_) - 1, 1);
		         int maxY = Math.min(MathHelper.floor(caveStartY + p_227208_16_) + 1, this.maxHeight - 8);
		         int minZ = Math.max(MathHelper.floor(p_227208_12_ - p_227208_14_) - chunkZ * 16 - 1, 0);
		         int maxZ = Math.min(MathHelper.floor(p_227208_12_ + p_227208_14_) - chunkZ * 16 + 1, 16);
		         // Prettry sure stuff above is cordinate related
		         if (this.func_222700_a(chunk, chunkX, chunkZ, minX, maxX, minY, maxY, minZ, maxZ)) {
		            return false;
		         } else {
		        	 
		            boolean flag = false;
		            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		            BlockPos.Mutable blockpos$mutable1 = new BlockPos.Mutable();
		            BlockPos.Mutable blockpos$mutable2 = new BlockPos.Mutable();
		            
		            BlockPos.Mutable test = new BlockPos.Mutable();

		            for(int xInChunk = minX; xInChunk < maxX; ++xInChunk) {
		               int xPos = xInChunk + chunkX * 16;
		               double d2 = ((double)xPos + 0.5D - p_227208_8_) / p_227208_14_;

		               for(int zInChunk = minZ; zInChunk < maxZ; ++zInChunk) {
		                  int zPos = zInChunk + chunkZ * 16;
		                  double d3 = ((double)zPos + 0.5D - p_227208_12_) / p_227208_14_;
		                  if (!(d2 * d2 + d3 * d3 >= 1.0D)) {
		                     AtomicBoolean atomicboolean = new AtomicBoolean(false);

		                     for(int yPos = maxY; yPos > minY; --yPos) {
		                        double d4 = ((double)yPos - 0.5D - caveStartY) / p_227208_16_;
		                        
		                       // if(yPos+1 == minY)
		                       // {
		                        	
		                        //}
		                        if (!this.func_222708_a(d2, d4, d3, yPos)) {
		                        	
		                        	flag |= this.thing(chunk, p_227208_2_, p_227208_18_, random, blockpos$mutable, blockpos$mutable1, blockpos$mutable2, p_227208_5_, chunkX, chunkZ, xPos, zPos, xInChunk, yPos, zInChunk, atomicboolean);
		                        }
		                        
		                     }
		                  }
		               }
		           
		            }
		            return flag;
		         }
		      } else {
		    	  //chunk.setBlockState(blockpos$mutable, Blocks.GLOWSTONE.getDefaultState(), false);
		         return false;
		      }
		   }
	   protected boolean thing(IChunk chunk, Function<BlockPos, Biome> p_225556_2_, BitSet p_225556_3_, Random p_225556_4_, BlockPos.Mutable block1, BlockPos.Mutable p_225556_6_, BlockPos.Mutable p_225556_7_, int p_225556_8_, int p_225556_9_, int p_225556_10_, int xPos, int zPos, int xInChunk, int yPos, int zInChunk, AtomicBoolean p_225556_16_) {
		      int i = xInChunk | zInChunk << 4 | yPos << 8;
		      if (p_225556_3_.get(i)) {
		         return false;
		      } else {
		         p_225556_3_.set(i);
		         block1.setPos(xPos, yPos, zPos);
		         BlockState blockstate = chunk.getBlockState(block1);
		         BlockState blockstate1 = chunk.getBlockState(p_225556_6_.setPos(block1).move(Direction.UP)); // Checks the pos of blocks above
		         if (blockstate.getBlock() == Blocks.GRASS_BLOCK || blockstate.getBlock() == Blocks.MYCELIUM) {
		            p_225556_16_.set(true);
		         }
		          BlockPos.Mutable mutable1 = new BlockPos.Mutable();
		           BlockPos.Mutable mutable2 = new BlockPos.Mutable();
		         
		        mutable1.setPos(block1.getX(), block1.getY()+3, block1.getZ());
		      BlockState blockstate3 = chunk.getBlockState(mutable1);

		         if (!this.canCarveBlock(blockstate, blockstate1)) {
		            return false;
		         } else {
		            if (yPos < 11) {
		            	chunk.setBlockState(block1, LAVA.getBlockState(), false);
		            } else {
		            	

		            	chunk.setBlockState(block1, CAVE_AIR, false);
		            	// After all the code, this is what carves the blocks

		               if (p_225556_16_.get()) {
		                  p_225556_7_.setPos(block1).move(Direction.DOWN);
		                  if (chunk.getBlockState(p_225556_7_).getBlock() == Blocks.DIRT) {
		                	  chunk.setBlockState(p_225556_7_, p_225556_2_.apply(block1).getSurfaceBuilderConfig().getTop(), false);
		                  
		               }
		            }
		            }

		            return true;
		         }
		      }
		   }
}
	

