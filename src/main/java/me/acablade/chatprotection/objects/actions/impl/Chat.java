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
@Getter
public class Chat extends Action {

    private final String message;
    private final Pattern regex;
    @Override
    public String getName() {
        return "chat";
    }

    @Override
    public void execute(ChatEvent event) {
        ProxiedPlayer player = (ProxiedPlayer) event.getSender();
        String newMessage = Colorizer.colorize(message);
        String rawMessage = ChatColor.stripColor(event.getMessage());
        if(regex!=null&&!regex.pattern().isEmpty()){
            Matcher matcher = regex.matcher(rawMessage);
            if(matcher.find()){
                player.chat(newMessage);
            }
            return;
        }

        player.chat(newMessage);


    }
}
