package com.github.dskprt.teleport.commands;

import com.github.dskprt.teleport.TeleportPlugin;
import com.github.dskprt.teleport.util.Teleport;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TpaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 1) {
            return false;
        }

        if(sender instanceof Player) {
            Player player = (Player) sender;
            Player target = player.getServer().getPlayer(args[0]);

            if(target == null) {
                player.sendRawMessage("Player not found!");
                return true;
            }

            String uuid = UUID.randomUUID().toString();
            Teleport teleport = new Teleport(uuid, player, target);

            TeleportPlugin.REQUEST_MAP.put(uuid, teleport);
            teleport.sendRequestMessage();
        }

        return true;
    }
}
