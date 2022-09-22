package com.amartinez.hellonearth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amartinez.hellonearth.events.init.ModWorldType;
import com.amartinez.hellonearth.events.init.RegistryEvents;
import com.amartinez.hellonearth.init.BlockInit;
import com.amartinez.hellonearth.world.gen.ModOreGen;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("hellonearth")
@Mod.EventBusSubscriber(modid = HellOnEarth.MOD_ID, bus = Bus.MOD)
public class HellOnEarth 
{

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
	public static final ItemGroup tab = new HellOnEarth.HellOnEarthCreativeTab("hell_on_earth");
    public static final String MOD_ID = "hellonearth";
    public static final WorldType Hell_On_Earth = new ModWorldType();

    public HellOnEarth()
    {
        // Register the setup, enqueueIMC, processIMC, and doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
		//IForgeRegistry.registerWorldGenerator(new TestCaveThing(), 1000);
    	RenderTypeLookup.setRenderLayer(BlockInit.CORRUPTED_DOOR, RenderType.getCutout());
    	RenderTypeLookup.setRenderLayer(BlockInit.HELL_VINE, RenderType.getCutout());
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {

    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {

    }

    private void processIMC(final InterModProcessEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event)
    {

    }
    
    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event)
    {
    	ModOreGen.generateOre();
    }
    
    public static class HellOnEarthCreativeTab extends ItemGroup
    {	
    	private HellOnEarthCreativeTab(String label)
    	{
    		super(label);
    	}
    	
    	
    	@Override
    	public ItemStack createIcon()
    	{
    		return new ItemStack(RegistryEvents.silver_ore);
    	}
    }


}
