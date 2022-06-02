package fr.springg.surviehardcore.particles;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class GenryusaiEffect {

    public void sendCircleParticle(Location loc, EnumParticle particle, float r, Player p){
        for (double t = 0; t < 100; t+=0.5) {
            float x = (float)(r*Math.cos(t));
            float z = (float)(r*Math.sin(t));
            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(particle, true, (float) loc.getX() + x, (float)loc.getY(), (float)loc.getZ()+z,0,0,0,0,1);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
        }
    }

}
