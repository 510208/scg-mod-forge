
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.samhackerscommandgui.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.mcreator.samhackerscommandgui.world.inventory.CommandGuiMenu;
import net.mcreator.samhackerscommandgui.SamhackersCommandGuiMod;

public class SamhackersCommandGuiModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, SamhackersCommandGuiMod.MODID);
	public static final RegistryObject<MenuType<CommandGuiMenu>> COMMAND_GUI = REGISTRY.register("command_gui", () -> IForgeMenuType.create(CommandGuiMenu::new));
}
