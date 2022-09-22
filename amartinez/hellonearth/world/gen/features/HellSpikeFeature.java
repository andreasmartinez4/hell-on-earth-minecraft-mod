package com.amartinez.hellonearth.world.gen.features;

import java.util.Random;
import java.util.function.Function;

import com.amartinez.hellonearth.events.init.RegistryEvents;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class HellSpikeFeature extends Feature {
	public HellSpikeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51493_1_) {
		super(p_i51493_1_);
	}
	
	@Override
	public boolean place(IWorld worldIn, ChunkGenerator generator, Random rand, BlockPos pos, IFeatureConfig config)
	{
		if(rand.nextInt(8) != 1)
			return false;
		
		while(worldIn.isAirBlock(pos) && pos.getY() > 2)
		{
			pos = pos.down();
		}
		
		if(worldIn.getBlockState(pos).getBlock() != Blocks.GRASS_BLOCK)
		{
			return false;
		}
		else
		{
			// Makes the position go up
			pos = pos.up(0); // Y starting pos
			// Probs have to do with width and length values of spike
			int i = rand.nextInt(4) + 15; // Height
			int j = i / 4 + rand.nextInt(8); //  Width
			int amtRun = 0;
			// Runs as long as the width
			
			int direction = rand.nextInt(2);
			for(int y = -i; y < i; ++y)
			{
				float f = (1.0F - (float)y / (float)i) * (float)j; // fatness
				int l = MathHelper.ceil(f);
				// runs about 0 - 10 times
	
				 for(int x = -l; x <= l; ++x) { // places the blocks around the base
		               float f1 = (float)MathHelper.abs(x) - 0.25F;
		               boolean curve = rand.nextBoolean();
		               // Also runs about 10 times
		               for(int z = -l; z <= l; ++z) {
		                  float f2 = (float)MathHelper.abs(z) - 0.25F;
		                  if ((x == 0 && z == 0 || !(f1 * f1 + f2 * f2 > f * f)) && (x != -l && x != l && z != -l && z != l || !(rand.nextFloat() > 0.75F))) {
		                	  
		                	  BlockState blockstate = worldIn.getBlockState(pos.add(x, y, z));
		                     Block block = blockstate.getBlock();
		                     // Generates top of spike
		                     if (blockstate.isAir(worldIn, pos.add(x, y, z)) || isDirt(block) || block == Blocks.GRASS_BLOCK || block == RegistryEvents.bloodstone) {

		                     if(direction == 0)
		                     {
		                    	 this.setBlockState(worldIn, pos.add(x, y, z+amtRun), Blocks.GLOWSTONE.getDefaultState());
			                        this.setBlockState(worldIn, pos.add(x, y, z+amtRun-1), Blocks.GLOWSTONE.getDefaultState());
		                     }
		                     else if(direction == 1)
		                     {
		                    	 this.setBlockState(worldIn, pos.add(x+amtRun, y, z), Blocks.GLOWSTONE.getDefaultState());
			                        this.setBlockState(worldIn, pos.add(x+amtRun-1, y, z), Blocks.GLOWSTONE.getDefaultState());
		                     }
		                     
		                     }

		                     // Generates bottom of spike
		                     if (y != 0 && l > 1) {
		                        blockstate = worldIn.getBlockState(pos.add(x, -y, z));
		                        block = blockstate.getBlock();
		                        if (blockstate.isAir(worldIn, pos.add(x, -y, z)) || isDirt(block) || block == Blocks.GRASS_BLOCK || block == RegistryEvents.bloodstone) {
		                           this.setBlockState(worldIn, pos.add(x, -y, z), Blocks.SEA_LANTERN.getDefaultState());
		                        }
		                     }
		                  }
		               }
		            }
				
					amtRun++;
		         }

		         int k1 = j - 1;
		         if (k1 < 0) {
		            k1 = 0;
		         } else if (k1 > 1) {
		            k1 = 1;
		         }

		         // Generates base of spike
		         for(int l1 = -k1; l1 <= k1; ++l1) {
		            for(int i2 = -k1; i2 <= k1; ++i2) {
		               BlockPos blockpos = pos.add(l1, -1, i2);
		               int j2 = 50;
		               if (Math.abs(l1) == 1 && Math.abs(i2) == 1) {
		                  j2 = rand.nextInt(5);
		               }

		               while(blockpos.getY() > 50) {
		                  BlockState blockstate1 = worldIn.getBlockState(blockpos);
		                  Block block1 = blockstate1.getBlock();
		                  if (!blockstate1.isAir(worldIn, blockpos) && !isDirt(block1) && block1 != Blocks.GRASS_BLOCK && block1 != RegistryEvents.bloodstone && block1 != Blocks.GLOWSTONE && block1 != Blocks.SEA_LANTERN) {
		                     break;
		                  }

		                  this.setBlockState(worldIn, blockpos, Blocks.ACACIA_LOG.getDefaultState());
		                  blockpos = blockpos.down();
		                  --j2;
		                  if (j2 <= 0) {
		                     blockpos = blockpos.down(rand.nextInt(5) + 1);
		                     j2 = rand.nextInt(5);
		                  }
		               }
		            }
		         }
	
			}
		
		return true;
	}
	
	

	public boolean placeing(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config)
	{
		if(rand.nextInt(8) != 1)
			return false;
		
		while(worldIn.isAirBlock(pos) && pos.getY() > 2)
		{
			pos = pos.down();
		}
		
		if(worldIn.getBlockState(pos).getBlock() != Blocks.GRASS_BLOCK)
		{
			return false;
		}
		else
		{
			// Makes the position go up
			pos = pos.up(0); // Y starting pos
			// Probs have to do with width and length values of spike
			int i = rand.nextInt(4) + 15; // Height
			int j = i / 4 + rand.nextInt(5); //  Width
			int amtRun = 0;
			// Runs as long as the width
			
			int direction = rand.nextInt(2);
			for(int y = 0; y < i; ++y)
			{
				float f = (1.0F - (float)y / (float)i) * (float)j; // fatness
				int l = MathHelper.ceil(f);
				// runs about 0 - 10 times
	
				 for(int x = 0; x <= l; ++x) { // places the blocks around the base
		               float f1 = (float)MathHelper.abs(x) - 0.25F;
		               boolean curve = rand.nextBoolean();
		               // Also runs about 10 times
		               for(int z = 0; z <= l; ++z) {
		                  float f2 = (float)MathHelper.abs(z) - 0.25F;
		                  if ((x == 0 && z == 0 || !(f1 * f1 + f2 * f2 > f * f)) && (x != -l && x != l && z != -l && z != l || !(rand.nextFloat() > 0.75F))) {
		                	  
		                	  BlockState blockstate = worldIn.getBlockState(pos.add(x, y, z));
		                     Block block = blockstate.getBlock();
		                     // Generates top of spike
		                     if (blockstate.isAir(worldIn, pos.add(x, y, z)) || isDirt(block) || block == Blocks.GRASS_BLOCK || block == RegistryEvents.bloodstone) {

		                     if(direction == 0)
		                     {
		                    	 this.setBlockState(worldIn, pos.add(x, y, z+amtRun), Blocks.GLOWSTONE.getDefaultState());
			                        this.setBlockState(worldIn, pos.add(x, y, z+amtRun-1), Blocks.GLOWSTONE.getDefaultState());
		                     }
		                     else if(direction == 1)
		                     {
		                    	 this.setBlockState(worldIn, pos.add(x+amtRun, y, z), Blocks.GLOWSTONE.getDefaultState());
			                        this.setBlockState(worldIn, pos.add(x+amtRun-1, y, z), Blocks.GLOWSTONE.getDefaultState());
		                     }
		                     
		                     }

		                     // Generates bottom of spike
		                     if (y != 0 && l > 1) {
		                        blockstate = worldIn.getBlockState(pos.add(x, -y, z));
		                        block = blockstate.getBlock();
		                        if (blockstate.isAir(worldIn, pos.add(x, -y, z)) || isDirt(block) || block == Blocks.GRASS_BLOCK || block == RegistryEvents.bloodstone) {
		                           this.setBlockState(worldIn, pos.add(x, -y, z), Blocks.SEA_LANTERN.getDefaultState());
		                        }
		                     }
		                  }
		               }
		            }
				
					amtRun++;
		         }

		         int k1 = j - 1;
		         if (k1 < 0) {
		            k1 = 0;
		         } else if (k1 > 1) {
		            k1 = 1;
		         }

		         // Generates base of spike
		         for(int l1 = -k1; l1 <= k1; ++l1) {
		            for(int i2 = -k1; i2 <= k1; ++i2) {
		               BlockPos blockpos = pos.add(l1, -1, i2);
		               int j2 = 50;
		               if (Math.abs(l1) == 1 && Math.abs(i2) == 1) {
		                  j2 = rand.nextInt(5);
		               }

		               while(blockpos.getY() > 50) {
		                  BlockState blockstate1 = worldIn.getBlockState(blockpos);
		                  Block block1 = blockstate1.getBlock();
		                  if (!blockstate1.isAir(worldIn, blockpos) && !isDirt(block1) && block1 != Blocks.GRASS_BLOCK && block1 != RegistryEvents.bloodstone && block1 != Blocks.GLOWSTONE && block1 != Blocks.SEA_LANTERN) {
		                     break;
		                  }

		                  this.setBlockState(worldIn, blockpos, Blocks.ACACIA_LOG.getDefaultState());
		                  blockpos = blockpos.down();
		                  --j2;
		                  if (j2 <= 0) {
		                     blockpos = blockpos.down(rand.nextInt(5) + 1);
		                     j2 = rand.nextInt(5);
		                  }
		               }
		            }
		         }
	
			}
		
		return true;
	}
	
	   public boolean placea(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		      while(worldIn.isAirBlock(pos) && pos.getY() > 2) {
		         pos = pos.down();
		      }

		      if (worldIn.getBlockState(pos).getBlock() != Blocks.GRASS_BLOCK) {
		         return false;
		      } else {
		         pos = pos.up(rand.nextInt(4));
		         int i = rand.nextInt(4) + 7;
		         int j = i / 4 + rand.nextInt(2);
		         // makes super tall spikes
		         if (j > 1 && rand.nextInt(60) == 0) {
		            pos = pos.up(10 + rand.nextInt(30));
		         }

		         for(int k = 0; k < i; ++k) {
		            float f = (1.0F - (float)k / (float)i) * (float)j;
		            int l = MathHelper.ceil(f);

		            for(int i1 = -l; i1 <= l; ++i1) {
		               float f1 = (float)MathHelper.abs(i1) - 0.25F;

		               for(int j1 = -l; j1 <= l; ++j1) {
		                  float f2 = (float)MathHelper.abs(j1) - 0.25F;
		                  if ((i1 == 0 && j1 == 0 || !(f1 * f1 + f2 * f2 > f * f)) && (i1 != -l && i1 != l && j1 != -l && j1 != l || !(rand.nextFloat() > 0.75F))) {
		                     BlockState blockstate = worldIn.getBlockState(pos.add(i1, k, j1));
		                     Block block = blockstate.getBlock();
		                     if (blockstate.isAir(worldIn, pos.add(i1, k, j1)) || isDirt(block) || block == Blocks.GRASS_BLOCK || block == RegistryEvents.bloodstone) {
		                        this.setBlockState(worldIn, pos.add(i1, k, j1), Blocks.GLOWSTONE.getDefaultState());
		                     }

		                     if (k != 0 && l > 1) {
		                        blockstate = worldIn.getBlockState(pos.add(i1, -k, j1));
		                        block = blockstate.getBlock();
		                        if (blockstate.isAir(worldIn, pos.add(i1, -k, j1)) || isDirt(block) || block == Blocks.GRASS_BLOCK || block == RegistryEvents.bloodstone) {
		                           this.setBlockState(worldIn, pos.add(i1, -k, j1), Blocks.GLOWSTONE.getDefaultState());
		                        }
		                     }
		                  }
		               }
		            }
		         }

		         int k1 = j - 1;
		         if (k1 < 0) {
		            k1 = 0;
		         } else if (k1 > 1) {
		            k1 = 1;
		         }

		         for(int l1 = -k1; l1 <= k1; ++l1) {
		            for(int i2 = -k1; i2 <= k1; ++i2) {
		               BlockPos blockpos = pos.add(l1, -1, i2);
		               int j2 = 50;
		               if (Math.abs(l1) == 1 && Math.abs(i2) == 1) {
		                  j2 = rand.nextInt(5);
		               }

		               while(blockpos.getY() > 50) {
		                  BlockState blockstate1 = worldIn.getBlockState(blockpos);
		                  Block block1 = blockstate1.getBlock();
		                  if (!blockstate1.isAir(worldIn, blockpos) && !isDirt(block1) && block1 != Blocks.GRASS_BLOCK && block1 != RegistryEvents.bloodstone && block1 != Blocks.GLOWSTONE) {
		                     break;
		                  }

		                  this.setBlockState(worldIn, blockpos, Blocks.GLOWSTONE.getDefaultState());
		                  blockpos = blockpos.down();
		                  --j2;
		                  if (j2 <= 0) {
		                     blockpos = blockpos.down(rand.nextInt(5) + 1);
		                     j2 = rand.nextInt(5);
		                  }
		               }
		            }
		         }

		         return true;
		      }
		   }
}
