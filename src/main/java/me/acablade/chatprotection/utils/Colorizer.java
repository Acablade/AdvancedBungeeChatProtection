package me.acablade.chatprotection.utils;

import net.md_5.bungee.api.ChatColor;

public class Colorizer {

    public static String colorize(String message){
        return ChatColor.translateAlternateColorCodes('&',message);
    }


}
