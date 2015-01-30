package net.poweredbyscience.braillechat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

/**
 * Created by John on 1/27/2015.
 *
 * Some people just wanna watch the world burn, I on the other hand wanna set it on fire.
 */
public class BrailleChat extends JavaPlugin implements Listener {

    public static HashMap<Character, String> letterSets = new HashMap<Character, String>();
    public ArrayList<UUID> braillers = new ArrayList<UUID>();

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        addBrailles();
        System.out.println(replaceChars("Hello World!"));
    }

    public static String replaceChars(String sentence) {
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

    @EventHandler
    public void onChat(AsyncPlayerChatEvent ev) {
        if(braillers.contains(ev.getPlayer().getUniqueId())) {
            ev.setMessage(replaceChars(ev.getMessage()));
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent ev) { //Don't judge me
        UUID pid = ev.getPlayer().getUniqueId();
        if (ev.getMessage().equalsIgnoreCase("/brailled")) {
           if (braillers.contains(pid)) {
               braillers.remove(pid);
               ev.getPlayer().sendMessage(ChatColor.RED + "Got ya!");
           } else {
               braillers.add(pid);
               ev.getPlayer().sendMessage(ChatColor.RED + "Got ya!");
           }
            ev.setCancelled(true);
        }

    }

    //I'm sure there's a better way of doing this.
    public static void addBrailles() {
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

    public static void main(String[] args) {
        addBrailles();
        Scanner r =  new Scanner(System.in);
        System.out.println(replaceChars(r.nextLine()));
        //System.out.println(replaceChars("My milkshakes brings all the boys to the yaaaard."));
    }

    /*
    Thanks to https://github.com/mlang/freedots/blob/master/src/freedots/Braille.java
    */

}
