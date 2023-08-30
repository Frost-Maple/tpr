package com.mixpixel;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Tpr extends JavaPlugin {
    static Tpr main;
    @Override
    public void onEnable() {
        // Plugin startup logic
        main = this;
        Objects.requireNonNull(Bukkit.getPluginCommand("tpr")).setExecutor(new TprCommand());
        System.out.println("Tpr for MixPixel has loaded.");
        loadConfigurations();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("""
                Tpr is Unloading. Thank you for using.
                Build 0.0.10(107)
                By Lettuce
                With help from OPenAI's ChatGPT
                On 30 Aug '23
                Bye!
                """);
    }
    public void loadConfigurations(){

    }
}
