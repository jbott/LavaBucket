package com.jbott.lavabucket;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LavaBucket extends JavaPlugin implements Listener {
	Logger log = Logger.getLogger("MineCraft");
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		log.info("[" + getDescription().getName() + "] v" + getDescription().getVersion() + " disabled!"); //Message that is shown when the plugin gets disabled.
	}

	@Override
	public void onEnable() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(this, this);
		log.info("[" + getDescription().getName() + "] v" + getDescription().getVersion() + " enabled!"); //Message that is shown when the plugin gets enabled.
	}

	@EventHandler
	public void onFurnaceBurn(FurnaceBurnEvent e) {
		if (e.getFuel().getType() == Material.LAVA_BUCKET) {
			final Block furnace = e.getBlock();
			this.getServer().getScheduler()
					.scheduleSyncDelayedTask(this, new Runnable() {
						public void run() {
							Furnace furn = (Furnace) furnace.getState();
							furn.getInventory().setItem(1,
									new ItemStack(Material.BUCKET, 1));
						}
					});
		}
	}

}