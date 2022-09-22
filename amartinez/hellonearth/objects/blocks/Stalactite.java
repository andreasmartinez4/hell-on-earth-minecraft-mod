package com.amartinez.hellonearth.objects.blocks;

import java.util.stream.Stream;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;


public class Stalactite extends Block implements IWaterLoggable
{

	public static final BooleanProperty HANGING = BlockStateProperties.HANGING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	private static final VoxelShape SHAPE_UP = Stream.of(
			Block.makeCuboidShape(3, -16, 3, 13, 0, 13),
			Block.makeCuboidShape(5, 0, 5, 11, 16, 11),
			Block.makeCuboidShape(7, 16, 7, 9, 32, 9)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	
	private static final VoxelShape SHAPE_DOWN = Stream.of(
			Block.makeCuboidShape(3, 16, 3, 13, 32, 13),
			Block.makeCuboidShape(5, 0, 5, 11, 16, 11),
			Block.makeCuboidShape(7, -16, 7, 9, 0, 9)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	
	public Stalactite(Properties properties) 
	{
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(HANGING, Boolean.valueOf(false)).with(WATERLOGGED, false));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		return state.get(HANGING) ? SHAPE_DOWN : SHAPE_UP;
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
