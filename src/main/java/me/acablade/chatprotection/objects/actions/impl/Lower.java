package me.acablade.chatprotection.objects.actions.impl;

import lombok.AllArgsConstructor;
import me.acablade.chatprotection.objects.actions.Action;
import net.md_5.bungee.api.event.ChatEvent;

import java.util.Locale;
import java.util.regex.Matcher;
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
        Matcher matcher = regex.matcher(message);
        StringBuilder sb = new StringBuilder();
        int last = 0;
        while(matcher.find()){
            sb.append(message, last, matcher.start());
            sb.append(matcher.group(0).toLowerCase(new Locale("tr","TR")));
            last = matcher.end();
        }
        sb.append(message.substring(last));
        message = sb.toString();
        event.setMessage(message);
    }
}
