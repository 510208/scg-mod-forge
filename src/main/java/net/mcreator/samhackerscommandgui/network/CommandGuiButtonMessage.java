
package net.mcreator.samhackerscommandgui.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.samhackerscommandgui.world.inventory.CommandGuiMenu;
import net.mcreator.samhackerscommandgui.procedures.TpPlayerProcedure;
import net.mcreator.samhackerscommandgui.procedures.TimeNoonsetProcedure;
import net.mcreator.samhackerscommandgui.procedures.TimeNightsetProcedure;
import net.mcreator.samhackerscommandgui.procedures.TimeMidnightsetProcedure;
import net.mcreator.samhackerscommandgui.procedures.TimeDaysetProcedure;
import net.mcreator.samhackerscommandgui.procedures.GamemodeSurvivalProcedure;
import net.mcreator.samhackerscommandgui.procedures.GamemodeSpectorProcedure;
import net.mcreator.samhackerscommandgui.procedures.GamemodeCreativeProcedure;
import net.mcreator.samhackerscommandgui.procedures.GamemodeAdventureProcedure;
import net.mcreator.samhackerscommandgui.SamhackersCommandGuiMod;

import java.util.function.Supplier;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommandGuiButtonMessage {
	private final int buttonID, x, y, z;

	public CommandGuiButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public CommandGuiButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(CommandGuiButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(CommandGuiButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = CommandGuiMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			TimeDaysetProcedure.execute(entity);
		}
		if (buttonID == 1) {

			TimeNoonsetProcedure.execute(entity);
		}
		if (buttonID == 2) {

			TimeNightsetProcedure.execute(entity);
		}
		if (buttonID == 3) {

			TimeMidnightsetProcedure.execute(entity);
		}
		if (buttonID == 4) {

			GamemodeAdventureProcedure.execute(entity);
		}
		if (buttonID == 5) {

			GamemodeSpectorProcedure.execute(entity);
		}
		if (buttonID == 6) {

			GamemodeSurvivalProcedure.execute(entity);
		}
		if (buttonID == 7) {

			GamemodeCreativeProcedure.execute(entity);
		}
		if (buttonID == 8) {

			TpPlayerProcedure.execute(entity, guistate);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		SamhackersCommandGuiMod.addNetworkMessage(CommandGuiButtonMessage.class, CommandGuiButtonMessage::buffer, CommandGuiButtonMessage::new, CommandGuiButtonMessage::handler);
	}
}
