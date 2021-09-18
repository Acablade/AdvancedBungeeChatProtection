package me.acablade.chatprotection.objects.actions.impl;

import lombok.AllArgsConstructor;
import me.acablade.chatprotection.objects.actions.Action;
import me.acablade.chatprotection.utils.Colorizer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.event.ChatEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class Deny extends Action {

    private final Pattern regex;

    @Override
    public String getName() {
        return "deny";
    }

    @Override
    public void execute(ChatEvent event) {

        String newMessage = Colorizer.colorize(event.getMessage());
        String rawMessage = ChatColor.stripColor(newMessage);
        if(regex!=null&&!regex.pattern().isEmpty()){
            Matcher matcher = regex.matcher(rawMessage);
            if(matcher.find()){
                event.setCancelled(true);
            }
            return;
        }
        event.setCancelled(true);
    }
}
