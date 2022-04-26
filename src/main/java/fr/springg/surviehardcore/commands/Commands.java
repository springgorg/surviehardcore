package fr.springg.surviehardcore.commands;

import fr.springg.surviehardcore.invs.CustomInv;
import fr.springg.surviehardcore.invs.WorldInv;
import fr.springg.surviehardcore.mobs.CustomEnderman;
import fr.springg.surviehardcore.mobs.CustomSkeleton;
import fr.springg.surviehardcore.mobs.CustomZombie;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(label.equalsIgnoreCase("custom")) {

            if (sender instanceof Player) {
                Player p = (Player) sender;

                if(p.hasPermission("custom.perm")) {
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
                    new CustomInv().open(p);
                } else {
                    p.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande !");
                    return false;
                }
            } else {
                sender.sendMessage("§cVous ne pouvez pas executer cette commande !");
                return false;
            }

        }

        return false;
    }
}
