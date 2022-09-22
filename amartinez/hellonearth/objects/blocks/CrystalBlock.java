package com.amartinez.hellonearth.objects.blocks;

import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;


public class CrystalBlock extends DirectionalBlock
{

	//public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	
	public static final VoxelShape SHAPE_UP = Stream.of(
			Block.makeCuboidShape(5, 0, 5, 9, 4, 9),
			Block.makeCuboidShape(3, 0, 2, 7, 4, 6),
			Block.makeCuboidShape(2, 0, 9, 5, 4, 13),
			Block.makeCuboidShape(5, 0, 9, 9, 4, 15),
			Block.makeCuboidShape(9, 0, 8, 13, 4, 12),
			Block.makeCuboidShape(11, 0, 4, 15, 4, 8),
			Block.makeCuboidShape(10, 4, 7, 14, 8, 11),
			Block.makeCuboidShape(6, 4, 9, 10, 8, 13),
			Block.makeCuboidShape(8, 0, 0, 12, 4, 9),
			Block.makeCuboidShape(4, 3, 3, 8, 7, 7),
			Block.makeCuboidShape(7, 4, 5, 11, 10, 9),
			Block.makeCuboidShape(9, 2, 3, 13, 6, 7)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	
	public static final VoxelShape SHAPE_NS = Stream.of(
			Block.makeCuboidShape(7, 0, 5, 11, 4, 9),
			Block.makeCuboidShape(9, 0, 2, 13, 4, 6),
			Block.makeCuboidShape(11, 0, 9, 14, 4, 13),
			Block.makeCuboidShape(7, 0, 9, 11, 4, 15),
			Block.makeCuboidShape(3, 0, 8, 7, 4, 12),
			Block.makeCuboidShape(1, 0, 4, 5, 4, 8),
			Block.makeCuboidShape(2, 4, 7, 6, 8, 11),
			Block.makeCuboidShape(6, 4, 9, 10, 8, 13),
			Block.makeCuboidShape(4, 0, 0, 8, 4, 9),
			Block.makeCuboidShape(8, 3, 3, 12, 7, 7),
			Block.makeCuboidShape(5, 4, 5, 9, 10, 9),
			Block.makeCuboidShape(3, 2, 3, 7, 6, 7)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	
	public static final VoxelShape SHAPE_EW = Stream.of(
			Block.makeCuboidShape(7, 0, 7, 11, 4, 11),
			Block.makeCuboidShape(9, 0, 10, 13, 4, 14),
			Block.makeCuboidShape(11, 0, 3, 14, 4, 7),
			Block.makeCuboidShape(7, 0, 1, 11, 4, 7),
			Block.makeCuboidShape(3, 0, 4, 7, 4, 8),
			Block.makeCuboidShape(1, 0, 8, 5, 4, 12),
			Block.makeCuboidShape(2, 4, 5, 6, 8, 9),
			Block.makeCuboidShape(6, 4, 3, 10, 8, 7),
			Block.makeCuboidShape(4, 0, 7, 8, 4, 16),
			Block.makeCuboidShape(8, 3, 9, 12, 7, 13),
			Block.makeCuboidShape(5, 4, 7, 9, 10, 11),
			Block.makeCuboidShape(3, 2, 9, 7, 6, 13)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
		
	public CrystalBlock(Properties properties) 
	{
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.UP));
	}
	
@Override
public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
	// TODO Auto-generated method stub
	return 0.0F;
}
	
	public BlockState setStateByRotation(Direction dir)
	{
		switch(dir)
		{
		case UP:
			return this.stateContainer.getBaseState().with(FACING, Direction.DOWN);
		case DOWN:
			return this.stateContainer.getBaseState().with(FACING, Direction.UP);
		case NORTH:
			return this.stateContainer.getBaseState().with(FACING, Direction.SOUTH);
		case SOUTH:
			return this.stateContainer.getBaseState().with(FACING, Direction.NORTH);
		case EAST:
			return this.stateContainer.getBaseState().with(FACING, Direction.WEST);
		case WEST:
			return this.stateContainer.getBaseState().with(FACING, Direction.EAST);
		}
		return null;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
	      switch(state.get(FACING).getAxis()) 
	      {
	      case X:
	      default:
	         return SHAPE_EW;
	      case Z:
	         return SHAPE_NS;
	      case Y:
	         return SHAPE_UP;
	      }
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		Direction direction = context.getFace();
		BlockState blockstate = context.getWorld().getBlockState(context.getPos().offset(direction.getOpposite()));
		return blockstate.getBlock() == this && blockstate.get(FACING) == direction ? this.getDefaultState().with(FACING, direction.getOpposite()) : this.getDefaultState().with(FACING, direction);
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) 
	{
		builder.add(FACING);
	}
	
	public BlockState rotate(BlockState state, Rotation rot)
	{
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}
		
	public BlockState mirror(BlockState state, Mirror mirrorIn)
	{
		return state.with(FACING, mirrorIn.mirror(state.get(FACING)));
	}


	
}
