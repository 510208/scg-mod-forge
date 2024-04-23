
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.samhackerscommandgui.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.mcreator.samhackerscommandgui.client.gui.CommandGuiScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SamhackersCommandGuiModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(SamhackersCommandGuiModMenus.COMMAND_GUI.get(), CommandGuiScreen::new);
		});
	}
}
