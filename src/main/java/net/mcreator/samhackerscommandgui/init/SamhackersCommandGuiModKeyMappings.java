
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.samhackerscommandgui.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import net.mcreator.samhackerscommandgui.network.LaunchCommandGUIMessage;
import net.mcreator.samhackerscommandgui.SamhackersCommandGuiMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class SamhackersCommandGuiModKeyMappings {
	public static final KeyMapping LAUNCH_COMMAND_GUI = new KeyMapping("key.samhackers_command_gui.launch_command_gui", GLFW.GLFW_KEY_R, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				SamhackersCommandGuiMod.PACKET_HANDLER.sendToServer(new LaunchCommandGUIMessage(0, 0));
				LaunchCommandGUIMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(LAUNCH_COMMAND_GUI);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				LAUNCH_COMMAND_GUI.consumeClick();
			}
		}
	}
}
