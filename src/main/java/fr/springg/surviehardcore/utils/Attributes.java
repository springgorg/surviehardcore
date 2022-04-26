package fr.springg.surviehardcore.utils;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagInt;
import net.minecraft.server.v1_8_R3.NBTTagList;
import net.minecraft.server.v1_8_R3.NBTTagString;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class Attributes {

    public static ItemStack addAttributes(ItemStack i){
        net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(i);
        NBTTagCompound compound = nmsStack.getTag();
        if (compound == null) {
            compound = new NBTTagCompound();
            nmsStack.setTag(compound);
            compound = nmsStack.getTag();
        }
        NBTTagList modifiers = new NBTTagList();
        NBTTagCompound movementSpeed = new NBTTagCompound();
        movementSpeed.set("AttributeName", new NBTTagString("generic.movementSpeed"));
        movementSpeed.set("Name", new NBTTagString("generic.movementSpeed"));
        movementSpeed.set("Amount", new NBTTagInt(2));
        movementSpeed.set("Operation", new NBTTagInt(1));
        movementSpeed.set("UUIDLeast", new NBTTagInt(122321));
        movementSpeed.set("UUIDMost", new NBTTagInt(66663));
        modifiers.add(movementSpeed);
        compound.set("AttributeModifiers", modifiers);
        nmsStack.setTag(compound);
        i = CraftItemStack.asBukkitCopy(nmsStack);
        return i;
    }

}
