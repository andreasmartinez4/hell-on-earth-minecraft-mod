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

public class StalactiteBlock extends Block implements IWaterLoggable
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty LAVALOGGED = BooleanProperty.create("lavalogged");
	private final VoxelShape SHAPE;
	
	public StalactiteBlock(Properties properties, VoxelShape shape) 
	{
		super(properties);
		SHAPE = shape;
		this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, false).with(LAVALOGGED, false));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		return SHAPE;
	}
	
	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) 
	{
		// Destroys Block if there aren't any connecting air blocks
		BlockPos down = pos;
		BlockPos up = pos;
		
		while(worldIn.getBlockState(down) == BlockInit.STALACTITE_MIDDLE.getDefaultState() || worldIn.getBlockState(down) == BlockInit.STALACTITE_TOP.getDefaultState() || worldIn.getBlockState(down) == BlockInit.STALACTITE_BASE.getDefaultState())
		{
			down = down.offset(Direction.DOWN);
		}
		
		if(!worldIn.getBlockState(down).isSolid())
		{
			while(worldIn.getBlockState(up) == BlockInit.STALACTITE_MIDDLE.getDefaultState() || worldIn.getBlockState(up) == BlockInit.STALACTITE_TOP.getDefaultState() || worldIn.getBlockState(up) == BlockInit.STALACTITE_BASE.getDefaultState())
			{
				up = up.offset(Direction.UP);
			}
			
			if(!worldIn.getBlockState(up).isSolid())
			{
				worldIn.destroyBlock(pos, false);
			}	
		}
	}
	
	// Allows the block to place if theres another block above or below it
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) 
	{		
		if(!worldIn.getBlockState(pos.offset(Direction.UP)).isSolid() && !worldIn.getBlockState(pos.offset(Direction.DOWN)).isSolid())
			return false;
		else
			return true;
	}
	
	@Override
	public IFluidState getFluidState(BlockState state) 
	{
		if(state.get(WATERLOGGED))
		{
			return Fluids.WATER.getStillFluidState(false);
		}
		
		return state.get(LAVALOGGED) ? Fluids.LAVA.getStillFluidState(false) : super.getFluidState(state);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		for(Direction direction : context.getNearestLookingDirections()) 
		{
			if(direction.getAxis() == Direction.Axis.Y) 
			{
				return this.getDefaultState().with(WATERLOGGED, context.getWorld().getFluidState(context.getPos()).getFluid() == Fluids.WATER).with(LAVALOGGED, context.getWorld().getFluidState(context.getPos()).getFluid() == Fluids.LAVA);
			}
		}
		return null;
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) 
	{
		builder.add(WATERLOGGED, LAVALOGGED);
	}
}
