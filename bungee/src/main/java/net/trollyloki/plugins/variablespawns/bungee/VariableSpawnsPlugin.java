package net.trollyloki.plugins.variablespawns.bungee;

import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;
import net.trollyloki.plugins.variablespawns.Constants;
import net.trollyloki.plugins.variablespawns.CookieUtils;

public class VariableSpawnsPlugin extends Plugin implements Listener {

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerListener(this, this);
    }

    @EventHandler
    public void onServerSwitch(ServerSwitchEvent event) {
        if (event.getFrom() == null) return;
        event.getPlayer().storeCookie(Constants.SERVER_COOKIE, CookieUtils.encodeString(event.getFrom().getName()));
    }

}
