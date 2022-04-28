package fr.springg.surviehardcore;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import fr.mrmicky.fastinv.FastInvManager;
import fr.springg.surviehardcore.enchants.EnchantsManager;
import fr.springg.surviehardcore.holograms.HologramManager;
import fr.springg.surviehardcore.managers.Managers;
import fr.springg.surviehardcore.npc.MenuTrait;
import fr.springg.surviehardcore.npc.NPCTrait;
import fr.springg.surviehardcore.utils.FastBoard;
import fr.springg.surviehardcore.utils.Recipes;
import fr.springg.surviehardcore.worlds.CustomChunk;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.CitizensEnableEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.TraitInfo;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class Main extends JavaPlugin {

    private static Main instance;
    public ProtocolManager protocolManager;
    public final Map<String, FastBoard> boards = new HashMap<>();
    public final Map<String, Long> cooldowns = new HashMap<>();
    public int cooldowntime = 3600;
    public EnchantsManager enchantsManager;
    public HologramManager hologramManager;

    @Override
    public void onEnable() {
        instance = this;
        protocolManager = ProtocolLibrary.getProtocolManager();
        enchantsManager = new EnchantsManager();
        hologramManager = new HologramManager();

        Bukkit.getConsoleSender().sendMessage("§a[SURVIE HARDCORE]");

        // Managers
        Managers.registerCommands();
        Managers.registerListeners();

        // Scoreboard
        getServer().getScheduler().runTaskTimer(this, () -> {
            for(FastBoard board : boards.values()){
                updateBoard(board);
            }
        }, 0, 20);

        // FastInv
        FastInvManager.register(this);

        // Recipes
        Recipes.deathSword();
        Recipes.hammer();
        Recipes.sharingan();
        Recipes.kamui();
        Recipes.tsukuyomi();

        // Traits
        if(getServer().getPluginManager().getPlugin("Citizens") == null || !getServer().getPluginManager().getPlugin("Citizens").isEnabled()) {
            getLogger().log(Level.SEVERE, "Citizens 2.0 not found or not enabled");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(NPCTrait.class));
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(MenuTrait.class));

        // Enchants
        enchantsManager.init();
    }

    @Override
    public void onDisable() {
        EnchantsManager.enchants.forEach(enchantment -> {
           enchantsManager.disable(enchantment);
        });
    }

    private void updateBoard(FastBoard board){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        board.updateLines("§8" + dateFormat.format(date),
                "",
                "§aJoueurs§7: §f§l" + Bukkit.getOnlinePlayers().size(),
                "",
                "§aMode§7: §f§lSurvieHardCore",
                "",
                "§6onii-san.minesr.com"
        );
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new CustomChunk();
    }

    public static Main getInstance() {
        return instance;
    }
}
