package com.mixpixel;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Tpr extends JavaPlugin {
    @Override
    public void saveDefaultConfig() {
        super.saveDefaultConfig();
    }

    static Tpr main;
    public final Map<Player, Long> coolDown = new HashMap<>();
    public final int coolDownTime = getConfig().getInt("CoolDownTime");
    public int bounds = getConfig().getInt("TpMaxCoordinates");
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
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
                Build 1.0.0(115)
                By Lettuce
                With help from OPenAI's ChatGPT
                On 31 Aug '23
                Bye!
                """);
    }
    public void loadConfigurations(){
    }
}
