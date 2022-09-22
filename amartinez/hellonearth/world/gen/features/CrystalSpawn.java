package com.amartinez.hellonearth.world.gen.features;

import java.util.Random;
import java.util.function.Function;

import com.amartinez.hellonearth.events.init.RegistryEvents;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class CrystalSpawn extends Feature {

	public CrystalSpawn(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn)
	{
		super(configFactoryIn);
	}

	@Override
	public boolean place(IWorld worldIn, ChunkGenerator generator, Random rand, BlockPos pos, IFeatureConfig config) 
	{
		int tries = 100;
		int spread = 15;
		int upperBound = 30; // max hieght that crystal spawns
		int offset = 6;
		boolean isPlaced = false;
		
		int i = 0;
		while(i < tries && !isPlaced)
		{
			BlockPos target = pos.add(rand.nextInt(spread), rand.nextInt(upperBound) + offset, rand.nextInt(spread));
			isPlaced = placeBlock(rand, worldIn, target);
			i++;
		}
		
		return isPlaced ? true : false;
	}
	
	// Finding a spot to place the block
	private boolean placeBlock(Random rand, IWorld worldIn, BlockPos pos)
	{
		if(!worldIn.isAirBlock(pos))
			return false;
		
		// Amt of attempts to find a place to place a block
		int offset = 0;
		
		Direction dir = Direction.random(rand);
		
		BlockState stateAt;
		do
		{
			pos = pos.offset(dir);
			stateAt = worldIn.getBlockState(pos);
			offset++;
		} while (pos.getY() > 4 && pos.getY() < 200 && !stateAt.isSolid() && offset < 10);
		
		BlockPos placingPos = pos.offset(dir.getOpposite());
		
		if(!stateAt.isSolid() || worldIn.getBlockState(pos) != RegistryEvents.bloodstone.getDefaultState() || worldIn.getBlockState(placingPos) != Blocks.CAVE_AIR.getDefaultState())
			return false;
		
		pos = pos.offset(dir.getOpposite());
		
		worldIn.setBlockState(pos, RegistryEvents.CRYSTAL.getDefaultState().with(BlockStateProperties.FACING, dir.getOpposite()), 0);
		
		return true;
	}
}
