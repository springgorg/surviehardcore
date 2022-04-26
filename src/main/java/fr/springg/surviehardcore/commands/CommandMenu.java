package fr.springg.surviehardcore.commands;

import fr.springg.surviehardcore.invs.NPCInv;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMenu implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(label.equalsIgnoreCase("menu")){

            if(sender instanceof Player) {
                Player p = (Player) sender;

                p.sendMessage("§aAssalamu Aleykoum");
                p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 2);
                new NPCInv().open(p);
            } else {
                sender.sendMessage("§cVous ne pouvez pas executer cette commande !");
                return false;
            }
        }

        return false;
    }
}
