package me.acablade.chatprotection.objects.actions.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.acablade.chatprotection.objects.actions.Action;
import net.md_5.bungee.api.event.ChatEvent;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@Getter
public class Replace extends Action {


    private final Pattern regex;
    private final List<String> replaceList;

    @Override
    public String getName() {
        return "replace";
    }

    @Override
    public void execute(ChatEvent event) {
        String message = event.getMessage();
        Random random = new Random();
        Matcher matcher = regex.matcher(message);
        StringBuilder sb = new StringBuilder();
        int last = 0;
        while(matcher.find()){
            int n = random.nextInt(replaceList.size());
            sb.append(message, last, matcher.start());
            sb.append(replaceList.get(n));
            last = matcher.end();
        }
        sb.append(message.substring(last));
        message = sb.toString();
        event.setMessage(message);
    }
}
