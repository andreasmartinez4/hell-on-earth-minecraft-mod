package com.amartinez.hellonearth.world.gen.features;

import java.util.Random;
import java.util.function.Function;

import com.amartinez.hellonearth.events.init.RegistryEvents;
import com.amartinez.hellonearth.init.BlockInit;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class StalactitesFeature extends Feature {

	public StalactitesFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) 
	{
		super(configFactoryIn);
	}
	
	@Override
	public boolean place(IWorld worldIn, ChunkGenerator generator, Random rand, BlockPos pos, IFeatureConfig config)
	{
		int spread = 15;
		int innerSpread = 6;
		int innerTries = 16;
		int upperBound = 55;
		int offset = 6;

		for(int i = 0; i < 40; i++)
		{
			BlockPos target = pos.add(rand.nextInt(spread), rand.nextInt(upperBound) + offset, rand.nextInt(spread));
			if(placeStalactites(rand, worldIn, target, innerSpread, innerTries))
				i++;
		}

		return false;
	}

	private boolean placeStalactites(Random rand, IWorld worldIn, BlockPos pos, int spread, int tries) 
	{
		if(!findAndPlaceStalactites(rand, worldIn, pos))
			return false;
		
		for(int i = 0; i < tries; i++) 
		{
			//TODO: Will have to mess around with spread and how they gen --- before findAndPlaceStalactites was good with just (rand, worldIn, pos)
			BlockPos target = pos.add(rand.nextInt(spread * 3 + 1) - spread, rand.nextInt(spread + 1) - spread, rand.nextInt(spread * 3 + 1) - spread);
			findAndPlaceStalactites(rand, worldIn, target);
		}
		
		return true;
	}
	
	private boolean findAndPlaceStalactites(Random rand, IWorld worldIn, BlockPos pos)
	{
		if(!worldIn.isAirBlock(pos))
			return false;
		
		
		int off = 0;
		boolean up = rand.nextBoolean();
		Direction diff = (up ? Direction.UP : Direction.DOWN);
		Direction stalactyteDirection = (diff == Direction.UP ? Direction.DOWN : Direction.UP);
		
		if(!up && worldIn.canBlockSeeSky(pos))
 			return false;
		
		BlockState stateAt;
		do
		{
			pos = pos.offset(diff);
			stateAt = worldIn.getBlockState(pos);
			off++;
		} while (pos.getY() > 4 && pos.getY() < 200 && !stateAt.isSolid() && off < 10);
		
		if(!stateAt.isSolid() || worldIn.getBlockState(pos) != RegistryEvents.bloodstone.getDefaultState())
			return false;
		
		if(worldIn.getBlockState(pos.offset(stalactyteDirection)) == Blocks.LAVA.getDefaultState())
		{
			if(worldIn.getBlockState(pos.offset(stalactyteDirection, 5)) != Blocks.CAVE_AIR.getDefaultState())
			{
				return false;
			}
			else
			{
				int count = 0;
				pos = pos.offset(stalactyteDirection);
				do
				{
					worldIn.setBlockState(pos, RegistryEvents.bloodstone.getDefaultState(), 0);
					pos = pos.offset(stalactyteDirection);
					count++;
				} while(worldIn.getBlockState(pos) != Blocks.CAVE_AIR.getDefaultState() && count < 5);
				pos = pos.offset(diff);
			}
		}
		

		pos = pos.offset(stalactyteDirection);
		/*if(stalactyteDirection == Direction.UP)
		{
			worldIn.setBlockState(pos, BlockInit.STALACTITE_BASE.getDefaultState(), 0);
			worldIn.setBlockState(pos.offset(Direction.UP), BlockInit.STALACTITE_MIDDLE.getDefaultState(), 0);
			worldIn.setBlockState(pos.offset(Direction.UP, 2), BlockInit.STALACTITE_TOP.getDefaultState(), 0);
		}
		else
		{
			worldIn.setBlockState(pos, BlockInit.STALACTITE_BASE.getDefaultState(), 0);
			worldIn.setBlockState(pos.offset(Direction.DOWN), BlockInit.STALACTITE_MIDDLE.getDefaultState(), 0);
			worldIn.setBlockState(pos.offset(Direction.DOWN, 2), BlockInit.STALACTITE_TOP.getDefaultState(), 0);
		}*/
		createStalactite(rand, stalactyteDirection, worldIn, pos);
		
		return true;
	}
	
	// Still needs work
	// TODO: Make it check that the space is big enough to place taller stalactites
	// TODO: Fix lava gen so that the stalctites are longer and stick out of lava instead of using blocks
	private void createStalactite(Random rand, Direction dir, IWorld worldIn, BlockPos pos)
	{
		int baseLength = 1;
		int midLength = 1;
		//int topLength = 1;
		int totalHeight = 0;
		
		boolean isBiggerBase = false;
		
		if(rand.nextInt(5) == 0 && dir == Direction.DOWN)
		{
			
			BlockPos downCheck = pos;
			
			while(worldIn.getBlockState(downCheck) == Blocks.CAVE_AIR.getDefaultState())
			{
				downCheck = downCheck.offset(dir);
				totalHeight++;
			}
			
			if(totalHeight > 4)
			{
				totalHeight = (int) totalHeight / 4;
				baseLength = rand.nextInt(totalHeight) + 1;
			}
				
			
			isBiggerBase = true;
		}
			
		if(isBiggerBase)
		{
			midLength = rand.nextInt(2) + 1;
		}
		
		for(int i = 0; i < baseLength; i++)
		{
			worldIn.setBlockState(pos, BlockInit.STALACTITE_BASE.getDefaultState(), 0);
			pos = pos.offset(dir);
		}
		
		for(int i = 0; i < midLength; i++)
		{
			worldIn.setBlockState(pos, BlockInit.STALACTITE_MIDDLE.getDefaultState(), 0);
			pos = pos.offset(dir);
		}
		
		worldIn.setBlockState(pos, BlockInit.STALACTITE_TOP.getDefaultState(), 0);
	}

}
