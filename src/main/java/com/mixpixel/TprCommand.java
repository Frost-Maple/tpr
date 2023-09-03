package com.mixpixel;

import org.bukkit.Location;
import org.bukkit.World;
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
                sender.sendMessage("This command can't come with this Argument.");
                return false;
            }
        }
        if (Tpr.main.coolDown.containsKey(player)){
            long secondsLeft = (Tpr.main.coolDown.get(player) / 1000) + Tpr.main.coolDownTime - (System.currentTimeMillis()/1000);
            if (secondsLeft > 0){
                player.sendMessage("����Ҫ�ȴ�"+secondsLeft+"����ܼ������ͣ�");
                return false;
            }
        }
        World.Environment environment = player.getWorld().getEnvironment();
        switch (environment) {
            case NORMAL -> {
                if (Tpr.main.getConfig().getBoolean("WorldsEnabled.OverWorld")) {
                    Teleport(player.getWorld(), player);
                } else {
                    player.sendMessage("��ǰ���磨�����磩�޷�������ͣ�");
                }
            }
            case NETHER -> {
                if (Tpr.main.getConfig().getBoolean("WorldsEnabled.TheNether")) {
                    Teleport(player.getWorld(), player);
                } else {
                    player.sendMessage("��ǰ���磨�½磩�޷�������ͣ�");
                }
            }
            case THE_END -> {
                if (Tpr.main.getConfig().getBoolean("WorldsEnabled.TheEnd")) {
                    Teleport(player.getWorld(), player);
                } else {
                    player.sendMessage("��ǰ���磨ĩ�أ��޷�������ͣ�");
                }
            }
        }
        return false;
    }

    private void Teleport(World world, Player player) {
        Random random = new Random();
        Random Ran = new Random();
        int Rand = random.nextInt(Tpr.main.bounds*2);
        int Rand2 = Ran.nextInt(Tpr.main.bounds*2);
        int x = Rand - Tpr.main.bounds;
        int z = Rand2 - Tpr.main.bounds;
        int y = (world.getHighestBlockYAt(x,z))+1;
        Location location = new Location(world, x, y, z);
        player.teleport(location);
        player.sendMessage("������͵����꣨"+x+","+y+","+z+"��");
        Tpr.main.coolDown.put(player,System.currentTimeMillis());
    }
}
