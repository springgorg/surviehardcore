package fr.springg.surviehardcore.utils;

import fr.springg.surviehardcore.Main;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ShapedRecipe;

public class Recipes {

    public static void deathSword(){
        ShapedRecipe r = new ShapedRecipe(new ItemBuilder(Material.DIAMOND_SWORD).setName("§cEpée de la mort").addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1000).toItemStack());
        r.shape(" N ",
                " N ",
                " B ");
        r.setIngredient('N', Material.NETHER_STAR);
        r.setIngredient('B', Material.BLAZE_ROD);

        Main.getInstance().getServer().addRecipe(r);
    }

    public static void hammer(){
        ShapedRecipe r = new ShapedRecipe(new ItemBuilder(Material.DIAMOND_PICKAXE).setName("§cMarteau").toItemStack());
        r.shape("DDD",
                " B ",
                " B ");
        r.setIngredient('D', Material.DIAMOND_BLOCK);
        r.setIngredient('B', Material.BLAZE_ROD);

        Main.getInstance().getServer().addRecipe(r);
    }

    public static void sharingan(){
        ShapedRecipe r = new ShapedRecipe(new ItemBuilder(Material.CLAY_BALL).setName("§cSharingan").toItemStack());
        r.shape("RBR",
                "RER",
                "RRR");
        r.setIngredient('R', Material.REDSTONE_BLOCK);
        r.setIngredient('E', Material.ENDER_PEARL);
        r.setIngredient('B', Material.COAL_BLOCK);

        Main.getInstance().getServer().addRecipe(r);
    }

    public static void kamui(){
        ShapedRecipe r = new ShapedRecipe(new ItemBuilder(Material.CLAY_BALL).setName("§cKamui").toItemStack());
        r.shape("RBR",
                "BEB",
                "RBR");
        r.setIngredient('R', Material.REDSTONE_BLOCK);
        r.setIngredient('E', Material.ENDER_PEARL);
        r.setIngredient('B', Material.COAL_BLOCK);

        Main.getInstance().getServer().addRecipe(r);
    }

    public static void tsukuyomi(){
        ShapedRecipe r = new ShapedRecipe(new ItemBuilder(Material.CLAY_BALL).setName("§cTsukuyomi").toItemStack());
        r.shape("RBR",
                "BEB",
                "RRR");
        r.setIngredient('R', Material.REDSTONE_BLOCK);
        r.setIngredient('E', Material.ENDER_PEARL);
        r.setIngredient('B', Material.COAL_BLOCK);

        Main.getInstance().getServer().addRecipe(r);
    }

}