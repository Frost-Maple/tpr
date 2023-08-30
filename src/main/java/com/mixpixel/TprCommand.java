package com.mixpixel;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TprCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be executed by players.");
            return true;
        }
        if (args.length != 0) {
            if (args[0].equals("reload")) {
                if (sender.isOp()) {
                    Tpr.main.reloadConfig();
                }
            } else {
                sender.sendMessage("This command doesn't come with Arguments.");
                return false;
            }
        }
        if (Tpr.main.coolDown.containsKey(player)){
            long secondsLeft = (Tpr.main.coolDown.get(player) / 1000) + Tpr.main.coolDownTime - (System.currentTimeMillis()/1000);
            if (secondsLeft > 0){
                player.sendMessage("你需要等待"+secondsLeft+"秒才能继续传送！");
                return true;
            }
        }
        Random random = new Random();
        Random Ran = new Random();
        int Rand = random.nextInt(1000);
        int Rand2 = Ran.nextInt(1000);
        int x = Rand - 500;
        int z = Rand2 - 500;
        int y = player.getWorld().getHighestBlockYAt(x,z);
        Location location = new Location(player.getWorld(), x, y, z);
        player.teleport(location);
        Tpr.main.coolDown.put(player,System.currentTimeMillis());
        return false;
    }
}
