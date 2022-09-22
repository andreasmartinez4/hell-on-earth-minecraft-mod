package com.amartinez.hellonearth.events.init;

import com.amartinez.hellonearth.HellOnEarth;
import com.amartinez.hellonearth.material.ModArmorMaterial;
import com.amartinez.hellonearth.material.ModItemTiers;
import com.amartinez.hellonearth.objects.blocks.CrystalBlock;
import com.amartinez.hellonearth.objects.blocks.ExplosiveTrap;
import com.amartinez.hellonearth.objects.blocks.Stalactite;
import com.amartinez.hellonearth.world.gen.biomes.Canyon;
import com.amartinez.hellonearth.world.gen.biomes.HellForest;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Direction;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = HellOnEarth.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(HellOnEarth.MOD_ID)
public class RegistryEvents
{

    // Define Items
	public static final Item silver = null;
	public static final Item titanium = null;
	
	// Tools
	public static final Item silver_pickaxe = null;
	public static final Item silver_shovel = null;
	public static final Item silver_sword = null;
	public static final Item silver_axe = null;
	public static final Item silver_hoe = null;
	public static final Item titanium_pickaxe = null;
	public static final Item titanium_shovel = null;
	public static final Item titanium_sword = null;
	public static final Item titanium_axe = null;
	public static final Item titanium_hoe = null;
	
	// Armor
	public static final Item silver_helmet = null;
	public static final Item silver_chestplate = null;
	public static final Item silver_leggings = null;
	public static final Item silver_boots = null;
	public static final Item titanium_helmet = null;
	public static final Item titanium_chestplate = null;
	public static final Item titanium_leggings = null;
	public static final Item titanium_boots = null;
	
	// Blocks
	public static final Block silver_ore = null;
	public static final Block silver_block = null;
	public static final Block titanium_ore = null;
	public static final Block titanium_block = null;
	public static final Block bloodstone = null;
	
	public static final Block stalactite = null;
	public static final Block stalactite_down = null;
	public static final Block CRYSTAL = null;
	
	public static final Block EXPLOSIVE_TRAP = null;
	
	// Block Items

    @SubscribeEvent // Register Items
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
    	// Items
    	event.getRegistry().register(new Item(new Item.Properties().group(HellOnEarth.tab)).setRegistryName("silver"));
    	
    	// Tools
    	event.getRegistry().register(new PickaxeItem(ModItemTiers.SILVER, 1, -2.8F, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("silver_pickaxe"));
		event.getRegistry().register(new ShovelItem(ModItemTiers.SILVER, 1.5F, -3.0F, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("silver_shovel"));
		event.getRegistry().register(new SwordItem(ModItemTiers.SILVER, 3, -2.4F, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("silver_sword"));
		event.getRegistry().register(new AxeItem(ModItemTiers.SILVER, 6.0F, -3.1F, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("silver_axe"));
		event.getRegistry().register(new HoeItem(ModItemTiers.SILVER, -1.0F, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("silver_hoe"));
		event.getRegistry().register(new PickaxeItem(ModItemTiers.TITANIUM, 1, -2.8F, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("titanium_pickaxe"));
		event.getRegistry().register(new ShovelItem(ModItemTiers.TITANIUM, 1.5F, -3.0F, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("titanium_shovel"));
		event.getRegistry().register(new SwordItem(ModItemTiers.TITANIUM, 3, -2.4F, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("titanium_sword"));
		event.getRegistry().register(new AxeItem(ModItemTiers.TITANIUM, 5.0F, -3.0F, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("titanium_axe"));
		event.getRegistry().register(new HoeItem(ModItemTiers.TITANIUM, -1.0F, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("titanium_hoe"));
    	
		// Armor
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.SILVER, EquipmentSlotType.HEAD, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("silver_helmet"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.SILVER, EquipmentSlotType.CHEST, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("silver_chestplate"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.SILVER, EquipmentSlotType.LEGS, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("silver_leggings"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.SILVER, EquipmentSlotType.FEET, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("silver_boots"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.TITANIUM, EquipmentSlotType.HEAD, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("titanium_helmet"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.TITANIUM, EquipmentSlotType.CHEST, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("titanium_chestplate"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.TITANIUM, EquipmentSlotType.LEGS, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("titanium_leggings"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.TITANIUM, EquipmentSlotType.FEET, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("titanium_boots"));
        
    }

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event)
    {
		event.getRegistry().register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F)).setRegistryName("bloodstone"));
    	
    	event.getRegistry().register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5F, 3.5F)).setRegistryName("silver_ore"));
    	
    	event.getRegistry().register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(25.0F, 1200.0F)).setRegistryName("titanium_ore"));
    	
    	event.getRegistry().register(new Stalactite(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.5F, .5F).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).notSolid().noDrops()).setRegistryName("stalactite"));
    	event.getRegistry().register(new Stalactite(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.5F, .5F).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).noDrops()).setRegistryName("stalactite_down"));
    	
    	event.getRegistry().register(new ExplosiveTrap(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).noDrops()).setRegistryName("explosive_trap"));
    	event.getRegistry().register(new CrystalBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(3.5F, 1.0F).lightValue(10).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).noDrops()).setRegistryName("crystal"));
    }

    @SubscribeEvent
    public static void registerBlockItems(final RegistryEvent.Register<Item> event)
    {
    	// Block Items
    	event.getRegistry().register(new BlockItem(bloodstone, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("bloodstone"));
    	
    	event.getRegistry().register(new BlockItem(silver_ore, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("silver_ore"));
    	
    	event.getRegistry().register(new BlockItem(titanium_ore, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("titanium_ore"));
    	
    	event.getRegistry().register(new BlockItem(stalactite, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("stalactite"));
    	
    	event.getRegistry().register(new BlockItem(EXPLOSIVE_TRAP, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("explosive_trap"));
    	event.getRegistry().register(new BlockItem(CRYSTAL, new Item.Properties().group(HellOnEarth.tab)).setRegistryName("crystal"));
    }
    
	@SubscribeEvent
	public static void registerBiomes(final RegistryEvent.Register<Biome> event)
	{
		event.getRegistry().register(ModBiomes.hell = new HellForest().setRegistryName("hell_forest"));
		event.getRegistry().register(ModBiomes.canyon = new Canyon().setRegistryName("canyon"));
		
		ModBiomes.registerBiomes();
	}
}
