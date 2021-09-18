package me.acablade.chatprotection.objects.actions;

import net.md_5.bungee.api.event.ChatEvent;

public abstract class Action {

    public abstract String getName();
    public abstract void execute(ChatEvent event);
    private final String actionType = getClass().getName();

}
