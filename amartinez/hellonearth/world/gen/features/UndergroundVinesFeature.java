package com.amartinez.hellonearth.world.gen.features;

import java.util.Random;
import java.util.function.Function;

import com.amartinez.hellonearth.events.init.RegistryEvents;
import com.amartinez.hellonearth.init.BlockInit;
import com.amartinez.hellonearth.objects.blocks.stalactites.StalactiteBase;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SixWayBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class UndergroundVinesFeature extends Feature
{

	public UndergroundVinesFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn)
	{
		super(configFactoryIn);
	}

	@Override
	public boolean place(IWorld worldIn, ChunkGenerator generator, Random rand, BlockPos pos, IFeatureConfig config) 
	{
		boolean isPlaced = false;
		int spread = 20;
		int innerSpread = 6;
		int innerTries = 16;
		int offset = 6;
		int tries = 10;
		int upperBound = 100;
		
		for(int i = 0; i < 20; i++)
		{
			BlockPos target = pos.add(rand.nextInt(spread), rand.nextInt(upperBound) + offset, rand.nextInt(spread));
			if(placeStalactites(rand, worldIn, target, innerSpread, innerTries))
				i++;
		}
		
		return true;
	}
	
	private boolean placeStalactites(Random rand, IWorld worldIn, BlockPos pos, int spread, int tries) 
	{
		if(!findAndPlaceVines(rand, worldIn, pos))
			return false;
		
		for(int i = 0; i < tries; i++) 
		{
			//TODO: Will have to mess around with spread and how they gen --- before findAndPlaceStalactites was good with just (rand, worldIn, pos)
			BlockPos target = pos.add(rand.nextInt(spread * 3 + 1) - spread, rand.nextInt(spread + 1) - spread, rand.nextInt(spread * 3 + 1) - spread);
			findAndPlaceVines(rand, worldIn, target);
		}
		
		return true;
	}

	private boolean findAndPlaceVines(Random rand, IWorld worldIn, BlockPos pos)
	{
	
		final BooleanProperty UP = SixWayBlock.UP;
		final BooleanProperty NORTH = SixWayBlock.NORTH;
		final BooleanProperty EAST = SixWayBlock.EAST;
		final BooleanProperty SOUTH = SixWayBlock.SOUTH;
		final BooleanProperty WEST = SixWayBlock.WEST;
		
		boolean blockUp = false;
		boolean blockNorth = false;
		boolean blockSouth = false;
		boolean blockEast = false;
		boolean blockWest = false;
		
		BlockState blockAt = worldIn.getBlockState(pos);
		
		if(worldIn.isAirBlock(pos) || blockAt.getBlock() instanceof StalactiteBase)
			return false;
		
		int offset = 0;
		BlockState stateAt;
		
		do
		{
			pos = pos.offset(Direction.DOWN);
			stateAt = worldIn.getBlockState(pos);
			offset++;
		} while(pos.getY() > 4 && pos.getY() < 200 && stateAt.isSolid() && offset < 10);
		
		if(stateAt.isSolid())
			return false;
		
		if(worldIn.getBlockState(pos.offset(Direction.NORTH)).isSolid())
		{
			//worldIn.setBlockState(pos.offset(Direction.DOWN), BlockInit.HELL_VINE.getDefaultState().with(BlockStateProperties.FACING, Direction.NORTH), 0);
			blockNorth = true;
			//dirToFace = Direction.NORTH;
		}
		else if(worldIn.getBlockState(pos.offset(Direction.SOUTH)).isSolid())
		{
			//worldIn.setBlockState(pos.offset(Direction.DOWN), BlockInit.HELL_VINE.getDefaultState().with(BlockStateProperties.FACING, Direction.SOUTH), 0);
			blockSouth = true;
			//dirToFace = Direction.SOUTH;
		}
		else if(worldIn.getBlockState(pos.offset(Direction.EAST)).isSolid())
		{
		//	worldIn.setBlockState(pos.offset(Direction.DOWN), BlockInit.HELL_VINE.getDefaultState().with(BlockStateProperties.FACING, Direction.EAST), 0);
			blockEast = true;
			//dirToFace = Direction.EAST;
		}
		else if(worldIn.getBlockState(pos.offset(Direction.WEST)).isSolid())
		{
			//worldIn.setBlockState(pos.offset(Direction.DOWN), BlockInit.HELL_VINE.getDefaultState().with(BlockStateProperties.FACING, Direction.WEST), 0);
			blockWest = true;
			//dirToFace = Direction.WEST;
		}
		
		if(!blockNorth && !blockSouth && !blockEast && !blockWest)
		{
			switch(rand.nextInt(4))
			{
			case 0:
				blockNorth = true;
				break;
			case 1:
				blockEast = true;
				break;
			case 2:
				blockSouth = true;
				break;
			case 3:
				blockWest = true;
				break;
				
			}
			
		}

		worldIn.setBlockState(pos, BlockInit.HELL_VINE.getDefaultState().with(UP, Boolean.valueOf(true)).with(NORTH, Boolean.valueOf(blockNorth)).with(EAST, Boolean.valueOf(blockEast)).with(SOUTH, Boolean.valueOf(blockSouth)).with(WEST, Boolean.valueOf(blockWest)), 0);
			//worldIn.setBlockState(pos.offset(Direction.DOWN), BlockInit.HELL_VINE.getDefaultState().with(BlockStateProperties.FACING, Direction.UP), 0);
		
		int max = rand.nextInt(10);
		int i = 1;
		do
		{
			//System.out.println("stuck??");
			worldIn.setBlockState(pos.offset(Direction.DOWN, i), BlockInit.HELL_VINE.getDefaultState().with(UP, Boolean.valueOf(blockUp)).with(NORTH, Boolean.valueOf(blockNorth)).with(EAST, Boolean.valueOf(blockEast)).with(SOUTH, Boolean.valueOf(blockSouth)).with(WEST, Boolean.valueOf(blockWest)), 0);
			i++;
			// TODO: change the while to check if the next block down is cave air, then place the block
		} while(i < max && !worldIn.getBlockState(pos.offset(Direction.DOWN, i)).isSolid() && worldIn.getBlockState(pos.offset(Direction.DOWN, i)) != Blocks.LAVA.getDefaultState());
		
		return true;
	}

}
