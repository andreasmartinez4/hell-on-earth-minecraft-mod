/*
 * 2.27.20
 * Andreas Martinez
 * This file contains the base for the stalactite block
 */

package com.amartinez.hellonearth.objects.blocks.stalactites;

import com.amartinez.hellonearth.init.BlockInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class StalactiteTop extends Block implements IWaterLoggable
{
	public static final BooleanProperty HANGING = BlockStateProperties.HANGING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	private static final VoxelShape SHAPE = Block.makeCuboidShape(7, 0, 7, 9, 16, 9);

	public StalactiteTop(Properties properties) 
	{
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(HANGING, Boolean.valueOf(false)).with(WATERLOGGED, false));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		return SHAPE;
	}
	
	@Override
	public IFluidState getFluidState(BlockState state) 
	{
		return state.get(WATERLOGGED) ? Fluids.LAVA.getStillFluidState(false) : super.getFluidState(state);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		for(Direction direction : context.getNearestLookingDirections()) 
		{
			if(direction.getAxis() == Direction.Axis.Y) 
			{
				BlockState blockstate = this.getDefaultState().with(HANGING, Boolean.valueOf(direction == Direction.UP)).with(WATERLOGGED, context.getWorld().getFluidState(context.getPos()).getFluid() == Fluids.LAVA);
				if(blockstate.isValidPosition(context.getWorld(), context.getPos())) 
				{
					return blockstate;
				}
			}
		}
		
		return null;
	}
	
	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) 
	{
		// Destroys Block if there aren't any connecting air blocks
		if(state.get(HANGING))
		{
			BlockPos down = pos;
			boolean canBreak = false;
			while(worldIn.getBlockState(down) != BlockInit.STALACTITE_MIDDLE.getDefaultState() || worldIn.getBlockState(down) != BlockInit.STALACTITE_TOP.getDefaultState() || worldIn.getBlockState(down) != this.getDefaultState())
			{
				down = down.offset(Direction.DOWN);
			}
			
			if(worldIn.getBlockState(down) != Blocks.CAVE_AIR.getDefaultState() || worldIn.getBlockState(down) != Blocks.AIR.getDefaultState())
				canBreak = false;
			else
				canBreak = true;
			
			if(worldIn.getBlockState(pos.offset(Direction.UP)) == Blocks.CAVE_AIR.getDefaultState() || worldIn.getBlockState(pos.offset(Direction.UP)) == Blocks.AIR.getDefaultState() && canBreak) 
			{
				worldIn.destroyBlock(pos, false);
			}
		}
		else
		{
			BlockPos up = pos;
			boolean canBreak = false;
			while(worldIn.getBlockState(up) != BlockInit.STALACTITE_MIDDLE.getDefaultState() || worldIn.getBlockState(up) != BlockInit.STALACTITE_TOP.getDefaultState() || worldIn.getBlockState(up) != this.getDefaultState())
			{
				up = up.offset(Direction.UP);
			}
			
			if(worldIn.getBlockState(up) != Blocks.CAVE_AIR.getDefaultState() || worldIn.getBlockState(up) != Blocks.AIR.getDefaultState())
				canBreak = false;
			else
				canBreak = true;
			
			if(worldIn.getBlockState(pos.offset(Direction.DOWN)) == Blocks.CAVE_AIR.getDefaultState() || worldIn.getBlockState(pos.offset(Direction.DOWN)) == Blocks.AIR.getDefaultState() && canBreak) 
			{
				worldIn.destroyBlock(pos, false);
			}
		}
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) 
	{
		builder.add(HANGING, WATERLOGGED);
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) 
	{
		Direction direction = func_220277_j(state).getOpposite();
		return Block.hasEnoughSolidSide(worldIn, pos.offset(direction), direction.getOpposite());
	}

	protected static Direction func_220277_j(BlockState p_220277_0_)
	{
		return p_220277_0_.get(HANGING) ? Direction.DOWN : Direction.UP;
	}
}
