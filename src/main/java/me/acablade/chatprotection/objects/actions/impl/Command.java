package me.acablade.chatprotection.objects.actions.impl;

import lombok.AllArgsConstructor;
import me.acablade.chatprotection.objects.actions.Action;
import me.acablade.chatprotection.utils.Colorizer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class Command extends Action {

    private final String command;
    private final CommandExecutor commandExecutor;
    private final Pattern regex;

    @Override
    public String getName() {
        return "command";
    }

    @Override
    public void execute(ChatEvent event) {

        String newMessage = Colorizer.colorize(event.getMessage());
        String rawMessage = ChatColor.stripColor(newMessage);
        if(regex!=null&&!regex.pattern().isEmpty()){
            Matcher matcher = regex.matcher(rawMessage);
            if(matcher.find()){
                if(commandExecutor==CommandExecutor.PLAYER){
                    ProxyServer.getInstance().getPluginManager().dispatchCommand((ProxiedPlayer)event.getSender(),command);
                }else if(commandExecutor==CommandExecutor.CONSOLE){
                    ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), command.replaceAll("\\{player}",((ProxiedPlayer)event.getSender()).getName()));
                }

            }
            return;
        }

        if(commandExecutor==CommandExecutor.PLAYER){
            ProxyServer.getInstance().getPluginManager().dispatchCommand((ProxiedPlayer)event.getSender(),command);
        }else if(commandExecutor==CommandExecutor.CONSOLE){
            ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), command);
        }


    }

    public enum CommandExecutor{
        PLAYER,
        CONSOLE
    }

}
