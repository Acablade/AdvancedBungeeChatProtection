package me.acablade.chatprotection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import me.acablade.chatprotection.commands.CPCommand;
import me.acablade.chatprotection.listener.ChatListener;
import me.acablade.chatprotection.managers.RuleManager;
import me.acablade.chatprotection.objects.Rule;
import me.acablade.chatprotection.objects.RuleDeserializer;
import me.acablade.chatprotection.objects.actions.Action;
import me.acablade.chatprotection.objects.actions.ActionDeserializer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public final class ChatProtectionPlugin extends Plugin {

    @Getter
    private Gson gson;

    @Override
    public void onLoad(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Rule.class,new RuleDeserializer());
        gsonBuilder.registerTypeAdapter(Action.class, new ActionDeserializer<Action>());
        gson = gsonBuilder.create();
    }

    @Override
    public void onEnable() {

        if(loadRules()){
            getProxy().getPluginManager().registerListener(this, new ChatListener(this));
        }
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new CPCommand(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    public boolean loadRules(){
        try {
            if (!getDataFolder().exists())
                getDataFolder().mkdir();

            File folder = new File(getDataFolder(), "rules");

            if(!folder.exists())
                folder.mkdir();

            File[] files = folder.listFiles();

            RuleManager.getRuleList().clear();

            for(File file: files){
                if(file.isFile()){
                    if(!file.getName().startsWith("-")&&getFileExtension(file).equalsIgnoreCase(".json")){
                        Scanner myReader = new Scanner(file);
                        String data = "";
                        while (myReader.hasNextLine()) {
                            data += myReader.nextLine();
                        }
                        RuleManager.getRuleList().add(gson.fromJson(data,Rule.class));
                        myReader.close();
                    }
                }
            }
            //configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /*public Configuration getConfig() {
        return configuration;
    }*/
}
