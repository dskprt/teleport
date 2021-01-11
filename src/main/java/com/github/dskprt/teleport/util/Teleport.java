package com.github.dskprt.teleport.util;

import com.github.dskprt.teleport.TeleportPlugin;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.atomic.AtomicInteger;

public class Teleport {

    public String id;

    public Player sender;
    public Player target;

    public Teleport(String id, Player sender, Player target) {
        this.id = id;

        this.sender = sender;
        this.target = target;
    }

    public void sendRequestMessage() {
        TextComponent title = new TextComponent(sender.getDisplayName() + " has send you a teleportation request.");

        TextComponent accept = new TextComponent("[ACCEPT]");
        accept.setBold(true);
        accept.setColor(ChatColor.DARK_GREEN);
        accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaaccept " + id));

        TextComponent empty = new TextComponent(" ");
        accept.addExtra(empty);

        TextComponent deny = new TextComponent("[DENY]");
        deny.setBold(true);
        deny.setColor(ChatColor.DARK_RED);
        deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpadeny " + id));
        accept.addExtra(deny);

//        target.sendRawMessage(String.format(
//                "[\"\",{\"text\":\"%s has sent you a teleportation request.\\n\"},{\"text\":\"[ACCEPT]\",\"bold\":true" +
//                ",\"color\":\"dark_green\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/tpaaccept %s\"}} " +
//                ",{\"text\":\" \"},{\"text\":\"[DENY]\",\"bold\":true,\"color\":\"dark_red\",\"clickEvent\":" +
//                "{\"action\":\"run_command\",\"value\":\"/tpadeny %s\"}}]", sender.getDisplayName(), id, id));
        target.sendMessage(title);
        target.sendMessage(accept);
    }

    public void accept() {
        TeleportPlugin.REQUEST_MAP.remove(this.id);
        AtomicInteger i = new AtomicInteger(5);

        new BukkitRunnable() {

            @Override
            public void run() {
                if(i.get() == 0) {
                    sender.teleport(target);
                    this.cancel();
                    return;
                }

                sender.sendTitle(i.toString(), target.getDisplayName() + " has accepted your teleportation request.", 0, 20, 0);
                i.getAndDecrement();
            }

        }.runTaskTimer(TeleportPlugin.getProvidingPlugin(TeleportPlugin.class), 0, 20);
    }

    public void deny() {
        TeleportPlugin.REQUEST_MAP.remove(this.id);
        sender.sendRawMessage("Your teleportation request has been denied.");
    }
}
