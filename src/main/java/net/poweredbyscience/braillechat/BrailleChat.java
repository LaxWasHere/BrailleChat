package net.poweredbyscience.braillechat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by John on 1/27/2015.
 * <p>
 * Some people just wanna watch the world burn, I on the other hand wanna set it on fire.
 */
public class BrailleChat extends JavaPlugin implements Listener {

    private static HashMap<Character, String> letterSets = new HashMap<Character, String>();
    private ArrayList<UUID> braillers = new ArrayList<UUID>();

    private static String replaceChars(String sentence) {
        char[] normalChars = sentence.toLowerCase().toCharArray();
        StringBuilder brailled = new StringBuilder();
        for (char c : normalChars) {
            //System.out.println("Getting key for " + c);
            if (letterSets.containsKey(c)) {
                brailled.append(letterSets.get(c));
            } else {
                brailled.append(c);
            }
        }
        return brailled.toString();
    }

    //I'm sure there's a better way of doing this.
    private static void addBrailles() {
        letterSets.put(' ', "\u2800");
        letterSets.put('.', "\u2828");
        letterSets.put('a', "\u2801");
        letterSets.put('b', "\u2803");
        letterSets.put('c', "\u2809");
        letterSets.put('d', "\u2819");
        letterSets.put('e', "\u2811");
        letterSets.put('f', "\u280B");
        letterSets.put('g', "\u281B");
        letterSets.put('h', "\u2813");
        letterSets.put('i', "\u280A");
        letterSets.put('j', "\u281A");
        letterSets.put('k', "\u2805");
        letterSets.put('l', "\u2807");
        letterSets.put('m', "\u280D");
        letterSets.put('n', "\u281D");
        letterSets.put('o', "\u2815");
        letterSets.put('p', "\u280F");
        letterSets.put('q', "\u281F");
        letterSets.put('r', "\u2817");
        letterSets.put('s', "\u280E");
        letterSets.put('t', "\u281E");
        letterSets.put('u', "\u2825");
        letterSets.put('v', "\u2827");
        letterSets.put('w', "\u283A");
        letterSets.put('x', "\u282D");
        letterSets.put('y', "\u283D");
        letterSets.put('z', "\u2835");
    }

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        addBrailles();
        getLogger().info(replaceChars("Hello World!"));
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent ev) {
        for (Player player : ev.getRecipients()) {
            if (braillers.contains(player.getUniqueId())) {
                player.sendMessage(String.format(ev.getFormat(), ev.getPlayer().getDisplayName(), replaceChars(ev.getMessage())));
                ev.getRecipients().remove(player);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {// Also, I'm judging you, Lax
        if (command.getName().equalsIgnoreCase("brailled")) {
            if (sender instanceof Player) {
                UUID pid = ((Player) sender).getUniqueId();
                if (braillers.contains(pid))
                    braillers.remove(pid);
                else
                    braillers.add(((Player) sender).getUniqueId());
                sender.sendMessage(ChatColor.RED + "Got ya!");
            } else {
                sender.sendMessage("You MUST be a player to run this command");
            }
            return true;
        }
        return false;
    }

    /*
    Thanks to https://github.com/mlang/freedots/blob/master/src/freedots/Braille.java
    */

}