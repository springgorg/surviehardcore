package fr.springg.surviehardcore.tasks;

import fr.springg.surviehardcore.particles.TestParticle;
import fr.springg.surviehardcore.utils.Title;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class TaskSakanade extends BukkitRunnable {

    Player p;

    public TaskSakanade(Player p){
        this.p = p;
    }

    private int timer = 2;

    @Override
    public void run() {
        if(timer > 0){
            TestParticle.sendParticles(EnumParticle.CLOUD, 3, p);
            p.setVelocity(new Vector(0,10,0));
        }

        if(timer == 0){
            p.teleport(new Location(Bukkit.getWorld("UpsideDown_Challenge"), -1172.374, 1.000, -1879.597, 95.7f, 11.1f));
            p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 9999*20,2*20));
            p.playSound(p.getLocation(), Sound.PORTAL_TRAVEL,1,2);
            new Title("§5Welcome to the Inverted World !", "",20,100,20).send(p);
        }
        timer--;
    }

}
