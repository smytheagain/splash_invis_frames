package com.smythesoft.splash_invis_frames;

import com.smythesoft.splash_invis_frames.events.InvisibilitySplashedEvents;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class SplashInvisFrames extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.YELLOW+ "[splash_invis_frames]: Enabling plugin");
        getServer().getPluginManager().registerEvents(new InvisibilitySplashedEvents(), this);

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[splash_invis_frames]: Enabled");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED +"[splash_invis_frames]: Disabling plugin");

    }
}
