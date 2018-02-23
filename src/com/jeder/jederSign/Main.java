package com.jeder.jederSign;
import com.jeder.jederSign.Commands.SignCommand;
import com.jeder.jederSign.Listeners.SignEventListener;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable () {
        //插件載入時
        getLogger().info( ChatColor.GOLD + "插件載入中。如果您的伺服器安裝有舊版本的JederSign，請將其刪除。" ) ;
        getLogger().info( ChatColor.GOLD + "如果您需要翻譯該插件，請修改config.yml。" ) ;
        getLogger().info( ChatColor.GOLD + "The plugin is loading. Please delete the old version of the plugin if you have." ) ;
        getLogger().info( ChatColor.GOLD + "If you want to translate the plugin, edit the config.yml file." ) ;
        FileConfiguration fc = getConfig() ;
        fc.addDefault( "Messages.reloadComplete" , "配置文件重載成功。" );
        fc.addDefault( "Messages.signGUITitle" , "§0§l編輯告示牌" );
        fc.addDefault( "Messages.showLineOne" , "第一行文字" );
        fc.addDefault( "Messages.showLineTwo" , "第二行文字" );
        fc.addDefault( "Messages.showLineThree" , "第三行文字" );
        fc.addDefault( "Messages.showLineFour" , "第四行文字" ) ;
        fc.addDefault( "Messages.editLineOne" , "編輯第一行" );
        fc.addDefault( "Messages.editLineTwo" , "編輯第二行" );
        fc.addDefault( "Messages.editLineThree" , "編輯第三行" );
        fc.addDefault( "Messages.editLineFour" , "編輯第四行" );
        fc.addDefault( "Messages.clearLineOne" , "清空第一行" );
        fc.addDefault( "Messages.clearLineTwo" , "清空第二行" );
        fc.addDefault( "Messages.clearLineThree" , "清空第三行" );
        fc.addDefault( "Messages.clearLineFour" , "清空第四行" );
        fc.addDefault( "Messages.cleared" , "成功清除該行" );
        fc.addDefault( "Messages.clearError" , "清除該行時發生錯誤" );
        fc.addDefault( "Messages.exit" , "退出" );
        fc.addDefault( "Messages.editChatTip" , "將你想要編輯的文字直接輸入到聊天欄中，您所輸入的消息不會被顯示給其他的玩家。" );
        getConfig().options().copyDefaults( true ) ;
        saveConfig() ;
        getServer().getPluginManager().registerEvents( new SignEventListener( this ) , this );  //註冊偵聽器
        getCommand( "jederSign" ).setExecutor( new SignCommand( this ));

    }
}
