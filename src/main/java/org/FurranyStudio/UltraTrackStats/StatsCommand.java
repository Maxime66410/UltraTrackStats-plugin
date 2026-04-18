package org.FurranyStudio.UltraTrackStats;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StatsCommand implements CommandExecutor {

    private final UltraTrackStats main;

    public StatsCommand(UltraTrackStats main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("ultratackstats.reload")) {
                sender.sendMessage("§cVous n'avez pas la permission.");
                return true;
            }

            main.reload();
            sender.sendMessage("§a[UltraTrackStats] Plugin rechargé avec succès !");
            return true;
        }

        sender.sendMessage("§7Usage: §f/" + label + " reload");
        return true;
    }
}
