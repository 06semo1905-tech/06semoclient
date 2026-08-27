package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class ExampleMod implements ModInitializer {
    public static final String MOD_ID = "06semoclient";
    private boolean initialized = false;

    @Override
    public void onInitialize() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null || client.world == null) return;

            // Oyuna ilk girişte bildirim gönderir
            if (!initialized) {
                client.player.sendMessage(Text.literal("§b[06SemoClient] §aPvP Modulleri Aktif!"), false);
                initialized = true;
            }

            // Auto-Criticals: Zıplama esnasında otomatik kritik vuruş desteği
            if (client.options.attackKey.isPressed() && client.player.isOnGround()) {
                client.player.jump();
            }

            // PvP ESP / Glow: Etraftaki rakip oyuncuları parlatır
            for (PlayerEntity player : client.world.getPlayers()) {
                if (player != client.player) {
                    player.setGlowingText(true);
                }
            }
        });
    }
}


