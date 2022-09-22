package com.amartinez.hellonearth.material;

import java.util.function.Supplier;

import com.amartinez.hellonearth.HellOnEarth;
import com.amartinez.hellonearth.events.init.RegistryEvents;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum ModArmorMaterial implements IArmorMaterial
{
    SILVER(HellOnEarth.MOD_ID + ":silver", 20, new int[]{2, 5, 7, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, () -> {
    	return Ingredient.fromItems(RegistryEvents.silver);
    }),
    TITANIUM(HellOnEarth.MOD_ID + ":silver", 50, new int[]{5000, 7000, 10000, 5000}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F, () -> {
    	return Ingredient.fromItems(RegistryEvents.titanium);
    });

	private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
	private final String name;
	private final int maxDamageFactor;
	private final int[] damageReductionAmountArray;
	private final int enchantability;
	private final SoundEvent soundEvent;
	private final float toughness;
	private final LazyValue<Ingredient> repairMaterial;
	
	private ModArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountsIn, int enchantabilityIn, SoundEvent equipSoundIn, float toughnessIn, Supplier<Ingredient> repairMaterialSupplier)
	{
		this.name = nameIn;
		this.maxDamageFactor = maxDamageFactorIn;
		this.damageReductionAmountArray = damageReductionAmountsIn;
		this.enchantability = enchantabilityIn;
		this.soundEvent = equipSoundIn;
		this.toughness = toughnessIn;
		this.repairMaterial = new LazyValue<>(repairMaterialSupplier);
	}
	
	public int getDurability(EquipmentSlotType slotIn) 
	{
		return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
	}

	public int getDamageReductionAmount(EquipmentSlotType slotIn) {
		return this.damageReductionAmountArray[slotIn.getIndex()];
	}

	public int getEnchantability() 
	{
		return this.enchantability;
	}

	public SoundEvent getSoundEvent() 
	{
		return this.soundEvent;
	}

	public Ingredient getRepairMaterial()
	{
     	return this.repairMaterial.getValue();
	}

	@OnlyIn(Dist.CLIENT)
	public String getName() 
	{
		return this.name;
	}

	public float getToughness() 
	{
      	return this.toughness;
	}
	
}
