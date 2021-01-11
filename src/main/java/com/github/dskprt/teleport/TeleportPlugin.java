package com.github.dskprt.teleport;

import com.github.dskprt.teleport.commands.TpaAcceptCommand;
import com.github.dskprt.teleport.commands.TpaCommand;
import com.github.dskprt.teleport.commands.TpaDenyCommand;
import com.github.dskprt.teleport.util.Teleport;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class TeleportPlugin extends JavaPlugin {

    public static Map<String, Teleport> REQUEST_MAP = new HashMap<>();

    @Override
    public void onEnable() {
        getCommand("tpa").setExecutor(new TpaCommand());
        getCommand("tpaaccept").setExecutor(new TpaAcceptCommand());
        getCommand("tpadeny").setExecutor(new TpaDenyCommand());
    }

    @Override
    public void onDisable() { }
}
