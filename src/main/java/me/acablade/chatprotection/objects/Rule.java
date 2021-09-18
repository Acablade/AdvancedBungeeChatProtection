package me.acablade.chatprotection.objects;

import lombok.Getter;
import me.acablade.chatprotection.objects.actions.Action;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Rule {

    private final Pattern regex;
    private final Pattern ignore;
    private final List<Action> actionList;
    private final String permission;
    private final boolean needsPerm;

    public Rule(String regex, List<Action> actionList,String permission,String ignoreRegex){
        this.regex = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        if(ignoreRegex==null){
            this.ignore=null;
        } else {
            this.ignore = Pattern.compile(ignoreRegex,Pattern.CASE_INSENSITIVE);
        }
        this.actionList = actionList;
        this.needsPerm = !permission.trim().isEmpty();
        if(needsPerm){
            this.permission = permission;
        }else{
            this.permission = null;
        }

    }

    public String getStringRegex(){
        return regex.pattern();
    }

    public Matcher getMatcher(String message){
        return getRegex().matcher(message);
    }

    public boolean doesMessageContainRegex(String message){
        return getMatcher(message).find();
    }

    public void performActions(ChatEvent event){
        String message = event.getMessage();
        ProxiedPlayer player = (ProxiedPlayer) event.getSender();
        if(needsPerm && player.hasPermission(getPermission())) return;
        if(!ignore.pattern().isEmpty()){
            Matcher ig =Pattern.compile(ignore.pattern(),Pattern.CASE_INSENSITIVE).matcher(message);
            while(ig.find()){
                return;
            }
        }
        if(doesMessageContainRegex(event.getMessage())){
            for(Action action: getActionList()){
                action.execute(event);
            }
        }
    }


}
