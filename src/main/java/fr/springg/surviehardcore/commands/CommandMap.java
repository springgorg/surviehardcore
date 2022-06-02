package fr.springg.surviehardcore.commands;

import fr.springg.surviehardcore.Main;
import fr.springg.surviehardcore.tasks.TaskRenderImage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMap implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(label.equalsIgnoreCase("map")){
            if(sender instanceof Player){
                if(sender.hasPermission("map.use")) {
                    if (args.length == 2) {
                        if (args[0].equalsIgnoreCase("render")) {
                            final Player p = (Player) sender;
                            final String path = args[1];

                            new TaskRenderImage(p, path).runTaskAsynchronously(Main.getInstance());
                        }
                    } else {
                        sender.sendMessage("§cManque d'arguments !");
                    }
                } else {
                    sender.sendMessage("§cNon");
                }
            }
        }

        return false;
    }
}
