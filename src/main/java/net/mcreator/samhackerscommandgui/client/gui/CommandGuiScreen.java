package net.mcreator.samhackerscommandgui.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.samhackerscommandgui.world.inventory.CommandGuiMenu;
import net.mcreator.samhackerscommandgui.network.CommandGuiButtonMessage;
import net.mcreator.samhackerscommandgui.SamhackersCommandGuiMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class CommandGuiScreen extends AbstractContainerScreen<CommandGuiMenu> {
	private final static HashMap<String, Object> guistate = CommandGuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox tpName;
	Button button_day;
	Button button_moon;
	Button button_night;
	Button button_mnight;
	Button button_adventure;
	Button button_spector;
	Button button_survival;
	Button button_creative;
	Button button_tp;

	public CommandGuiScreen(CommandGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 291;
		this.imageHeight = 197;
	}

	private static final ResourceLocation texture = new ResourceLocation("samhackers_command_gui:textures/screens/command_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		tpName.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (tpName.isFocused())
			return tpName.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		tpName.tick();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.samhackers_command_gui.command_gui.label_time"), 5, 16, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.samhackers_command_gui.command_gui.label_gamemode"), 5, 38, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.samhackers_command_gui.command_gui.label_transmit"), 5, 82, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.samhackers_command_gui.command_gui.label_note_please_place_player_name_o"), 66, 100, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.samhackers_command_gui.command_gui.label_and_split_by_space_key"), 97, 111, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		tpName = new EditBox(this.font, this.leftPos + 67, this.topPos + 79, 175, 18, Component.translatable("gui.samhackers_command_gui.command_gui.tpName"));
		tpName.setMaxLength(32767);
		guistate.put("text:tpName", tpName);
		this.addWidget(this.tpName);
		button_day = Button.builder(Component.translatable("gui.samhackers_command_gui.command_gui.button_day"), e -> {
			if (true) {
				SamhackersCommandGuiMod.PACKET_HANDLER.sendToServer(new CommandGuiButtonMessage(0, x, y, z));
				CommandGuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 49, this.topPos + 11, 58, 20).build();
		guistate.put("button:button_day", button_day);
		this.addRenderableWidget(button_day);
		button_moon = Button.builder(Component.translatable("gui.samhackers_command_gui.command_gui.button_moon"), e -> {
			if (true) {
				SamhackersCommandGuiMod.PACKET_HANDLER.sendToServer(new CommandGuiButtonMessage(1, x, y, z));
				CommandGuiButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 108, this.topPos + 11, 58, 20).build();
		guistate.put("button:button_moon", button_moon);
		this.addRenderableWidget(button_moon);
		button_night = Button.builder(Component.translatable("gui.samhackers_command_gui.command_gui.button_night"), e -> {
			if (true) {
				SamhackersCommandGuiMod.PACKET_HANDLER.sendToServer(new CommandGuiButtonMessage(2, x, y, z));
				CommandGuiButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 167, this.topPos + 11, 58, 20).build();
		guistate.put("button:button_night", button_night);
		this.addRenderableWidget(button_night);
		button_mnight = Button.builder(Component.translatable("gui.samhackers_command_gui.command_gui.button_mnight"), e -> {
			if (true) {
				SamhackersCommandGuiMod.PACKET_HANDLER.sendToServer(new CommandGuiButtonMessage(3, x, y, z));
				CommandGuiButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + 226, this.topPos + 11, 58, 20).build();
		guistate.put("button:button_mnight", button_mnight);
		this.addRenderableWidget(button_mnight);
		button_adventure = Button.builder(Component.translatable("gui.samhackers_command_gui.command_gui.button_adventure"), e -> {
			if (true) {
				SamhackersCommandGuiMod.PACKET_HANDLER.sendToServer(new CommandGuiButtonMessage(4, x, y, z));
				CommandGuiButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		}).bounds(this.leftPos + 212, this.topPos + 34, 72, 20).build();
		guistate.put("button:button_adventure", button_adventure);
		this.addRenderableWidget(button_adventure);
		button_spector = Button.builder(Component.translatable("gui.samhackers_command_gui.command_gui.button_spector"), e -> {
			if (true) {
				SamhackersCommandGuiMod.PACKET_HANDLER.sendToServer(new CommandGuiButtonMessage(5, x, y, z));
				CommandGuiButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		}).bounds(this.leftPos + 66, this.topPos + 55, 72, 20).build();
		guistate.put("button:button_spector", button_spector);
		this.addRenderableWidget(button_spector);
		button_survival = Button.builder(Component.translatable("gui.samhackers_command_gui.command_gui.button_survival"), e -> {
			if (true) {
				SamhackersCommandGuiMod.PACKET_HANDLER.sendToServer(new CommandGuiButtonMessage(6, x, y, z));
				CommandGuiButtonMessage.handleButtonAction(entity, 6, x, y, z);
			}
		}).bounds(this.leftPos + 139, this.topPos + 34, 72, 20).build();
		guistate.put("button:button_survival", button_survival);
		this.addRenderableWidget(button_survival);
		button_creative = Button.builder(Component.translatable("gui.samhackers_command_gui.command_gui.button_creative"), e -> {
			if (true) {
				SamhackersCommandGuiMod.PACKET_HANDLER.sendToServer(new CommandGuiButtonMessage(7, x, y, z));
				CommandGuiButtonMessage.handleButtonAction(entity, 7, x, y, z);
			}
		}).bounds(this.leftPos + 66, this.topPos + 34, 72, 20).build();
		guistate.put("button:button_creative", button_creative);
		this.addRenderableWidget(button_creative);
		button_tp = Button.builder(Component.translatable("gui.samhackers_command_gui.command_gui.button_tp"), e -> {
			if (true) {
				SamhackersCommandGuiMod.PACKET_HANDLER.sendToServer(new CommandGuiButtonMessage(8, x, y, z));
				CommandGuiButtonMessage.handleButtonAction(entity, 8, x, y, z);
			}
		}).bounds(this.leftPos + 244, this.topPos + 78, 40, 20).build();
		guistate.put("button:button_tp", button_tp);
		this.addRenderableWidget(button_tp);
	}
}
