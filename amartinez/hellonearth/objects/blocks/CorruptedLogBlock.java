package com.amartinez.hellonearth.objects.blocks;

import com.amartinez.hellonearth.init.BlockInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LogBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class CorruptedLogBlock extends LogBlock
{	
	public CorruptedLogBlock(MaterialColor verticalColorIn, Properties properties) 
	{
		super(verticalColorIn, properties);
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult p_225533_6_) 
	{
		ItemStack itemstack = player.getHeldItem(handIn);
		
		if(itemstack.getItem() instanceof AxeItem)
		{
			worldIn.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
			if (!worldIn.isRemote)
			{
				worldIn.setBlockState(pos, BlockInit.STRIPPED_CORRUPTED_LOG.getDefaultState().with(RotatedPillarBlock.AXIS, state.get(RotatedPillarBlock.AXIS)), 11);
				if (player != null)
				{
					itemstack.damageItem(1, player, (p_220040_1_) -> {
						p_220040_1_.sendBreakAnimation(handIn);
					});
				}
			}
			return ActionResultType.SUCCESS;
		}
		else
		{
			return ActionResultType.PASS;		
		}
	}
}
