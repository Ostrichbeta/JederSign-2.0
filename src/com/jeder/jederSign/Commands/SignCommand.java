package com.jeder.jederSign.Commands;
import com.jeder.jederSign.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SignCommand implements CommandExecutor {
    private final Main plugin;
    public SignCommand ( Main plugin ) {
        this.plugin = plugin ;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if ( cmd.getName().equalsIgnoreCase( "jederSign" ) ) {
            if ( args.length == 0 ) {
                sender.sendMessage( ChatColor.GOLD + "[JederSign] " + ChatColor.RED + "Usage: /jederSign reload" );
                return true ;
            }
            if ( args[0].equalsIgnoreCase( "reload" ) ) {
                //當輸入/jederSign reload時
                plugin.reloadConfig() ; //重載插件
                sender.sendMessage( ChatColor.GOLD + "[JederSign] " + ChatColor.RESET + plugin.getConfig().getString(  "Messages.reloadComplete" ) );
                return true ;
            }
            else {
                sender.sendMessage( ChatColor.GOLD + "[JederSign] " + ChatColor.RED + "Usage: /jederSign reload" );
                return true ;
            }
        }
        return true ;
    }
}
