package com.amartinez.hellonearth.objects.blocks;

import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.block.BlockState;
import net.minecraft.block.VineBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;

public class HellVine extends VineBlock
{
	private int timeCollided = 0;
	
	public HellVine(Properties properties) 
	{
		super(properties);
	}
	
	// PROBS NEED TO USE SOMETING WITH NBT DATA - EACH BLOCK HAS A DATA TAG, AND IF THAT TAG GETS HIT, IT SPAWNS SOMETHING
	
	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) 
	{
		
		
		
		if(entityIn instanceof PlayerEntity)
		{
			entityIn.attackEntityFrom(DamageSource.ON_FIRE, 3.0F);
			entityIn.setFire(5);
			WorldInfo worldinfo = worldIn.getWorldInfo();
			spawnCreeper(worldIn, pos);
			System.out.println("TIMES COLLIDED " + timeCollided);
			timeCollided++;
	
		}
	}
	
	private void spawnCreeper(World worldIn, BlockPos pos)
	{
		if(worldIn.getDifficulty() != Difficulty.PEACEFUL)
		{
			CreeperEntity mob = EntityType.CREEPER.create(worldIn);
			mob.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0.0F, 0.0F);
			worldIn.addEntity(mob);
		}
	}
	
	
}
