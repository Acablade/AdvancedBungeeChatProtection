package me.acablade.chatprotection.listener;

import me.acablade.chatprotection.ChatProtectionPlugin;
import me.acablade.chatprotection.managers.RuleManager;
import me.acablade.chatprotection.objects.Rule;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;


public class ChatListener implements Listener {

    private final ChatProtectionPlugin plugin;

    public ChatListener(ChatProtectionPlugin chatProtection) {
        this.plugin = chatProtection;
    }
    @EventHandler(priority = -32)
    public void onChat(ChatEvent event){
        if(!(event.getSender() instanceof ProxiedPlayer)) return;
        if(event.isCommand() || event.isProxyCommand()) return;
        checkRules(event);

    }

    private void checkRules(ChatEvent event){
        for(Rule rule: RuleManager.getRuleList()){
            rule.performActions(event);
        }
    }

}
