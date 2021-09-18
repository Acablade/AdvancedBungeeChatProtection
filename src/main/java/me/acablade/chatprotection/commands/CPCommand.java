package me.acablade.chatprotection.commands;

import me.acablade.chatprotection.ChatProtectionPlugin;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class CPCommand extends Command {

    private ChatProtectionPlugin chatProtectionPlugin;

    public CPCommand(ChatProtectionPlugin cp) {
        super("cp");
        this.chatProtectionPlugin = cp;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        //if(!sender.hasPermission("cp.reload")) return;
        chatProtectionPlugin.loadRules();
    }
}
