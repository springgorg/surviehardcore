package fr.springg.surviehardcore.tasks;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class TaskSakashima extends BukkitRunnable {

    LivingEntity ent;

    public TaskSakashima(LivingEntity ent){
        this.ent = ent;
    }

    @Override
    public void run() {
        ArmorStand stand = (ArmorStand) ent.getWorld().spawn(ent.getLocation(), ArmorStand.class);
        stand.setVisible(false);
        stand.setGravity(false);
        stand.setSmall(true);
        stand.setCustomNameVisible(true);
        stand.setCustomName("§c"+ent.getHealth()+" ❤️");
        if(ent.getHealth() > 1){
            ent.damage(1.0);
            stand.teleport(ent.getLocation().add(0,1,0));
            stand.remove();
        } else if(ent.getHealth() == 1) {
            ent.damage(1.0);
            stand.teleport(ent.getLocation().add(0,1,0));
            stand.remove();
            this.cancel();
        }
    }
}
