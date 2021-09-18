package me.acablade.chatprotection.objects.actions.impl;

import lombok.AllArgsConstructor;
import me.acablade.chatprotection.objects.actions.Action;
import net.md_5.bungee.api.event.ChatEvent;

import java.util.Locale;
import java.util.regex.Pattern;


@AllArgsConstructor
public class Lower extends Action {

    private final Pattern regex;

    @Override
    public String getName() {
        return "lower";
    }

    @Override
    public void execute(ChatEvent event) {
        String message = event.getMessage();
        event.setMessage(message.toLowerCase(new Locale("tr","TR")));
    }
}
