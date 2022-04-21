package fr.springg.surviehardcore.commands;

import fr.springg.surviehardcore.mobs.CustomZombie;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            if(label.equalsIgnoreCase("caca")){
                CustomZombie customZombie = new CustomZombie();
                customZombie.custom(p);
            }
        } else {
            Bukkit.getConsoleSender().sendMessage("§cNon");
            return false;
        }

        return false;
    }
}
