package com.amartinez.hellonearth.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class ExplosiveTrap extends Block
{

	public ExplosiveTrap(Properties properties) 
	{
		super(properties);
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) 
	{
		if(entityIn.getType() == EntityType.PLAYER)
		{
			activate(worldIn.getBlockState(pos), worldIn, pos);
			super.onEntityWalk(worldIn, pos, entityIn);
		}
	}
	
	// Plays sounds and sets the tnt to blow
	private static void activate(BlockState block, World world, BlockPos pos) 
	{	
		world.createExplosion(null, null, (double)pos.getX(), (double)pos.getY()+1D, (double)pos.getZ(), 2.5F, true, Explosion.Mode.BREAK);
	}
}
 