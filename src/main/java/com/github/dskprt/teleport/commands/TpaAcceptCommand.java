package com.github.dskprt.teleport.commands;

import com.github.dskprt.teleport.TeleportPlugin;
import com.github.dskprt.teleport.util.Teleport;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaAcceptCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 1) {
            return false;
        }

        if(sender instanceof Player) {
            Player player = (Player) sender;
            Teleport teleport = TeleportPlugin.REQUEST_MAP.get(args[0]);

            if(teleport == null) return true;

            if(teleport.target.getUniqueId().equals(player.getUniqueId())) {
                teleport.accept();
            }
        }

        return true;
    }
}
