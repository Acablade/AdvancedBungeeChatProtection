package me.acablade.chatprotection.objects.actions.impl;

import lombok.AllArgsConstructor;
import me.acablade.chatprotection.objects.actions.Action;
import me.acablade.chatprotection.utils.Colorizer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class Kick extends Action {

    private final String reason;
    private final Pattern regex;

    @Override
    public String getName() {
        return "kick";
    }

    @Override
    public void execute(ChatEvent event) {

        String newMessage = Colorizer.colorize(event.getMessage());
        String rawMessage = ChatColor.stripColor(newMessage);
        if(regex!=null&&!regex.pattern().isEmpty()){
            Matcher matcher = regex.matcher(rawMessage);
            if(matcher.find()){
                ProxiedPlayer proxiedPlayer = (ProxiedPlayer) event.getSender();
                proxiedPlayer.disconnect(Colorizer.colorize(reason));

            }

            return;
        }

        ProxiedPlayer proxiedPlayer = (ProxiedPlayer) event.getSender();
        proxiedPlayer.disconnect(Colorizer.colorize(reason));
    }
}
