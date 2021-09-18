package me.acablade.chatprotection.objects.actions.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.acablade.chatprotection.objects.actions.Action;
import me.acablade.chatprotection.utils.Colorizer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class Message extends Action {

    @Getter
    private final String message;

    private final Pattern regex;

    @Override
    public String getName() {
        return "message";
    }

    @Override
    public void execute(ChatEvent event) {

        String newMessage = Colorizer.colorize(event.getMessage());
        String rawMessage = ChatColor.stripColor(newMessage);
        if(regex!=null&&!regex.pattern().isEmpty()){
            Matcher matcher = regex.matcher(rawMessage);
            if(matcher.find()){
                ProxiedPlayer proxiedPlayer = (ProxiedPlayer) event.getSender();
                proxiedPlayer.sendMessage(Colorizer.colorize(getMessage()));

            }
            return;
        }
        ProxiedPlayer proxiedPlayer = (ProxiedPlayer) event.getSender();
        proxiedPlayer.sendMessage(Colorizer.colorize(getMessage()));


    }
}
