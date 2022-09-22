/*
 * 2.27.20
 * Andreas Martinez
 * This class registers all the blocks for the mod
 */

package com.amartinez.hellonearth.init;

import com.amartinez.hellonearth.HellOnEarth;
import com.amartinez.hellonearth.objects.blocks.CorruptedLogBlock;
import com.amartinez.hellonearth.objects.blocks.HellVine;
import com.amartinez.hellonearth.objects.blocks.ModButton;
import com.amartinez.hellonearth.objects.blocks.ModDoor;
import com.amartinez.hellonearth.objects.blocks.ModPressurePlate;
import com.amartinez.hellonearth.objects.blocks.ModStairsBlock;
import com.amartinez.hellonearth.objects.blocks.ModTrapDoor;
import com.amartinez.hellonearth.objects.blocks.stalactites.StalactiteBlock;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = HellOnEarth.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(HellOnEarth.MOD_ID)
public class BlockInit 
{
	// Cave Blocks
	public static final Block STALACTITE_BASE = null;
	public static final Block STALACTITE_MIDDLE = null;
	public static final Block STALACTITE_TOP = null;
	
	public static final Block HELL_VINE = null;
	
	// Wood 
	// TODO: WOOD, DOOR, SIGN
	public static final Block CORRUPTED_LOG = null;
	public static final Block STRIPPED_CORRUPTED_LOG = null;
	public static final Block CORRUPTED_WOOD = null;
	public static final Block STRIPPED_CORRUPTED_WOOD = null;
	public static final Block CORRUPTED_PLANKS = null;
	public static final Block CORRUPTED_SLAB = null;
	public static final Block CORRUPTED_STAIRS = null;
	public static final Block CORRUPTED_PRESSURE_PLATE = null;
	public static final Block CORRUPTED_BUTTON = null;
	public static final Block CORRUPTED_TRAPDOOR = null;
	public static final Block CORRUPTED_FENCE = null;
	public static final Block CORRUPTED_FENCE_GATE = null;
	public static final Block CORRUPTED_DOOR = null;
	public static final Block CORRUPTED_SIGN = null;

	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(new StalactiteBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.5F, 0.5F).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).noDrops().notSolid(), Block.makeCuboidShape(3, 0, 3, 13, 16, 13)).setRegistryName("stalactite_base"));
		event.getRegistry().register(new StalactiteBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.5F, 0.5F).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).noDrops().notSolid(), Block.makeCuboidShape(5, 0, 5, 11, 16, 11)).setRegistryName("stalactite_middle"));
		event.getRegistry().register(new StalactiteBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.5F, 0.5F).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).noDrops().notSolid(), Block.makeCuboidShape(7, 0, 7, 9, 16, 9)).setRegistryName("stalactite_top"));
		
		event.getRegistry().register(new HellVine(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.2F).sound(SoundType.PLANT).notSolid().noDrops()).setRegistryName("hell_vine"));
		
		event.getRegistry().register(new CorruptedLogBlock(MaterialColor.OBSIDIAN, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)).setRegistryName("corrupted_log"));
		event.getRegistry().register(new LogBlock(MaterialColor.OBSIDIAN, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)).setRegistryName("stripped_corrupted_log"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("corrupted_planks"));
		event.getRegistry().register(new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("corrupted_slab"));
		
		//TODO: Fix it to come from actual planks when switching to deferred registries
		event.getRegistry().register(new ModStairsBlock(Blocks.ACACIA_PLANKS.getDefaultState(), Block.Properties.from(Blocks.ACACIA_PLANKS)).setRegistryName("corrupted_stairs"));
		event.getRegistry().register(new ModPressurePlate(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)).setRegistryName("corrupted_pressure_plate"));
		event.getRegistry().register(new ModButton(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)).setRegistryName("corrupted_button"));
		event.getRegistry().register(new ModTrapDoor(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()).setRegistryName("corrupted_trapdoor"));
		event.getRegistry().register(new FenceBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("corrupted_fence"));
		event.getRegistry().register(new FenceGateBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("corrupted_fence_gate"));
		event.getRegistry().register(new ModDoor(Block.Properties.create(Material.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()).setRegistryName("corrupted_door"));
	}
	 	  
	@SubscribeEvent
	public static void registerBlockItems(final RegistryEvent.Register<Item> event)
	{
		event.getRegistry().register(new BlockItem(STALACTITE_BASE, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("stalactite_base"));
		event.getRegistry().register(new BlockItem(STALACTITE_MIDDLE, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("stalactite_middle"));
		event.getRegistry().register(new BlockItem(STALACTITE_TOP, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("stalactite_top"));
		
		event.getRegistry().register(new BlockItem(HELL_VINE, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("hell_vine"));
		
		event.getRegistry().register(new BlockItem(CORRUPTED_LOG, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("corrupted_log"));
		event.getRegistry().register(new BlockItem(STRIPPED_CORRUPTED_LOG, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("stripped_corrupted_log"));
		event.getRegistry().register(new BlockItem(CORRUPTED_PLANKS, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("corrupted_planks"));
		event.getRegistry().register(new BlockItem(CORRUPTED_SLAB, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("corrupted_slab"));
		event.getRegistry().register(new BlockItem(CORRUPTED_STAIRS, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("corrupted_stairs"));
		event.getRegistry().register(new BlockItem(CORRUPTED_PRESSURE_PLATE, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("corrupted_pressure_plate"));
		event.getRegistry().register(new BlockItem(CORRUPTED_BUTTON, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("corrupted_button"));
		event.getRegistry().register(new BlockItem(CORRUPTED_TRAPDOOR, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("corrupted_trapdoor"));
		event.getRegistry().register(new BlockItem(CORRUPTED_FENCE, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("corrupted_fence"));
		event.getRegistry().register(new BlockItem(CORRUPTED_FENCE_GATE, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("corrupted_fence_gate"));
		event.getRegistry().register(new BlockItem(CORRUPTED_DOOR, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("corrupted_door"));
	}
}
