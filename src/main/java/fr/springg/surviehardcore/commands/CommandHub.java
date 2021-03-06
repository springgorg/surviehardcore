package fr.springg.surviehardcore.commands;

import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.utils.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHub implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(label.equalsIgnoreCase("hub")){

            if(sender instanceof Player){
                Player p =(Player) sender;

                p.teleport(new Location(Bukkit.getWorld("Japan-with-barrier"), -39.440, 18.000, -20.406, -44, 4));
                p.setHealth(20);
                p.setFoodLevel(20);
                p.setGameMode(GameMode.ADVENTURE);
                p.getActivePotionEffects().clear();

                if(!Main.getInstance().boards.containsKey(p)) {
                    FastBoard board = new FastBoard(p);
                    board.updateTitle("§c§lSURVIE HARDCORE");
                    Main.getInstance().boards.put(p.getName(), board);
                }
            } else {
                sender.sendMessage("§cNon");
                return false;
            }

        }

        return false;
    }
}
