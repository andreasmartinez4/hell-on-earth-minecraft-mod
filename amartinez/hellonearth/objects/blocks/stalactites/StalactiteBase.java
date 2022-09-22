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

public class StalactiteBase extends StalactiteBlock 
{
	private static final VoxelShape SHAPE = null;

	public StalactiteBase(Properties properties) 
	{
		super(properties, SHAPE);
	}
}
