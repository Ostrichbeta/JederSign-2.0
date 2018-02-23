package com.jeder.jederSign.Listeners;
import com.jeder.jederSign.Main;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SignEventListener implements Listener {
    public HashMap< String , Boolean > isEditing = new HashMap<> () ;
    public HashMap< String , Block > signLocation = new HashMap<> () ;
    public HashMap< String , Integer > lineNum = new HashMap<>() ;
    public HashMap< String , String > line1Text = new HashMap<>() ;
    public HashMap< String , String > line2Text = new HashMap<>() ;
    public HashMap< String , String > line3Text = new HashMap<>() ;
    public HashMap< String , String > line4Text = new HashMap<>() ;



    private final Main plugin ;
    public SignEventListener ( Main plugin ) {
        this.plugin = plugin ;
    }

    @EventHandler
    public void onPlace ( PlayerInteractEvent ev ) {
        System.out.println( "交互事件被執行了" );
        Player player = ev.getPlayer() ;
        String playerName = player.getName() ;
        ItemStack handing = ev.getItem() ;
        Block block = ev.getClickedBlock() ;
        System.out.println( playerName );
        System.out.println( handing );
        System.out.println( block.getX() );
        System.out.println( block.getY() );
        System.out.println( block.getZ() );
        System.out.println( block.getType() );
        if ( handing != null ) {
            return ; //如果玩家手上拿有物品，則取消判定。
        }
        if ( !block.getType().equals( Material.WALL_SIGN ) && !block.getType().equals( Material.SIGN_POST ) && !block.getType().equals( Material.SIGN ) ) {
            return ; //如果玩家不是與告示牌進行交互，那麼也取消判定
        }
        if ( !player.isSneaking() ) {
            return ; //如果玩家不是潛行狀態，也不判定
        }
//        設定玩家的一些叁數
        Integer x = block.getX() ; Integer y = block.getY() ; Integer z = block.getZ() ;
        isEditing.put( playerName , true ) ;
        signLocation.put( playerName , block ) ;
        Sign getSign = ( Sign ) block.getState() ;
        line1Text.put( playerName , getSign.getLine( 0 ) ) ;
        line2Text.put( playerName , getSign.getLine( 1 ) ) ;
        line3Text.put( playerName , getSign.getLine( 2 ) ) ;
        line4Text.put( playerName , getSign.getLine( 3 ) ) ;
        //獲取告示牌的位置，存進Hashmap
        String line1Tr = plugin.getConfig().getString( "Messages.showLineOne" ) ;
        String line2Tr = plugin.getConfig().getString( "Messages.showLineTwo" ) ;
        String line3Tr = plugin.getConfig().getString( "Messages.showLineThree" ) ;
        String line4Tr = plugin.getConfig().getString( "Messages.showLineFour" ) ;
        String editLine1Tr = plugin.getConfig().getString( "Messages.editLineOne" ) ;
        String editLine2Tr = plugin.getConfig().getString( "Messages.editLineTwo" ) ;
        String editLine3Tr = plugin.getConfig().getString( "Messages.editLineThree" ) ;
        String editLine4Tr = plugin.getConfig().getString( "Messages.editLineFour" ) ;
        String clearLine1Tr = plugin.getConfig().getString( "Messages.clearLineOne" ) ;
        String clearLine2Tr = plugin.getConfig().getString( "Messages.clearLineTwo" ) ;
        String clearLine3Tr = plugin.getConfig().getString( "Messages.clearLineThree" ) ;
        String clearLine4Tr = plugin.getConfig().getString( "Messages.clearLineFour" ) ;
        String title = plugin.getConfig().getString( "Messages.signGUITitle" ) ;
        String exitTr = plugin.getConfig().getString( "Messages.exit" ) ;

        Inventory signGUI = Bukkit.createInventory( null , InventoryType.CHEST , title ) ;

        ItemStack showLine1 = new ItemStack( Material.EYE_OF_ENDER , 1 ) ;
        ItemMeta showLine1Meta = showLine1.getItemMeta() ;
        List< String > showLine1Lore = new ArrayList< String >() ;
        showLine1Lore.add( ChatColor.RESET + line1Text.get( playerName ) ) ;
        showLine1Meta.setDisplayName( line1Tr );
        showLine1Meta.setLore( showLine1Lore );

        ItemStack showLine2 = new ItemStack( Material.EYE_OF_ENDER , 2 ) ;
        ItemMeta showLine2Meta = showLine2.getItemMeta() ;
        showLine2Meta.setDisplayName( line2Tr );
        List< String > showLine2Lore = new ArrayList< String >() ;
        showLine2Lore.add( ChatColor.RESET + line2Text.get( playerName ) ) ;
        showLine2Meta.setLore( showLine2Lore );

        ItemStack showLine3 = new ItemStack( Material.EYE_OF_ENDER , 3 ) ;
        ItemMeta showLine3Meta = showLine3.getItemMeta() ;
        showLine3Meta.setDisplayName( line3Tr );
        List< String > showLine3Lore = new ArrayList< String >() ;
        showLine3Lore.add( ChatColor.RESET + line3Text.get( playerName ) ) ;
        showLine3Meta.setLore( showLine3Lore );

        ItemStack showLine4 = new ItemStack( Material.EYE_OF_ENDER , 4 ) ;
        ItemMeta showLine4Meta = showLine4.getItemMeta() ;
        showLine4Meta.setDisplayName( line4Tr );
        List< String > showLine4Lore = new ArrayList< String >() ;
        showLine4Lore.add( ChatColor.RESET + line4Text.get( playerName ) ) ;
        showLine4Meta.setLore( showLine4Lore );

        //保存更改
        showLine1.setItemMeta( showLine1Meta ) ;
        showLine2.setItemMeta( showLine2Meta ) ;
        showLine3.setItemMeta( showLine3Meta ) ;
        showLine4.setItemMeta( showLine4Meta ) ;

        ItemStack editLine1 = new ItemStack( Material.BOOK_AND_QUILL , 1 ) ;
        ItemMeta editLine1Meta = editLine1.getItemMeta() ;
        editLine1Meta.setDisplayName( editLine1Tr );

        ItemStack editLine2 = new ItemStack( Material.BOOK_AND_QUILL , 2 ) ;
        ItemMeta editLine2Meta = editLine2.getItemMeta() ;
        editLine2Meta.setDisplayName( editLine2Tr );

        ItemStack editLine3 = new ItemStack( Material.BOOK_AND_QUILL , 3 ) ;
        ItemMeta editLine3Meta = editLine3.getItemMeta() ;
        editLine3Meta.setDisplayName( editLine3Tr );

        ItemStack editLine4 = new ItemStack( Material.BOOK_AND_QUILL , 4 ) ;
        ItemMeta editLine4Meta = editLine4.getItemMeta() ;
        editLine4Meta.setDisplayName( editLine4Tr );

        editLine1.setItemMeta( editLine1Meta ) ;
        editLine2.setItemMeta( editLine2Meta ) ;
        editLine3.setItemMeta( editLine3Meta ) ;
        editLine4.setItemMeta( editLine4Meta ) ;

        ItemStack clearLine1 = new ItemStack( Material.TNT , 1 ) ;
        ItemMeta cL1meta = clearLine1.getItemMeta() ;
        cL1meta.setDisplayName( clearLine1Tr );
        clearLine1.setItemMeta( cL1meta ) ;

        ItemStack clearLine2 = new ItemStack( Material.TNT , 2 ) ;
        ItemMeta cL2meta = clearLine2.getItemMeta() ;
        cL1meta.setDisplayName( clearLine2Tr );
        clearLine1.setItemMeta( cL2meta ) ;

        ItemStack clearLine3 = new ItemStack( Material.TNT , 3 ) ;
        ItemMeta cL3meta = clearLine3.getItemMeta() ;
        cL3meta.setDisplayName( clearLine3Tr );
        clearLine3.setItemMeta( cL3meta ) ;

        ItemStack clearLine4 = new ItemStack( Material.TNT , 4 ) ;
        ItemMeta cL4meta = clearLine4.getItemMeta() ;
        cL4meta.setDisplayName( clearLine4Tr );
        clearLine4.setItemMeta( cL4meta ) ;

        ItemStack exit = new ItemStack( Material.BARRIER , 1 ) ;
        ItemMeta exitMeta = exit.getItemMeta() ;
        exitMeta.setDisplayName( exitTr );
        exit.setItemMeta( exitMeta ) ;

        //設定物品
        /*
        * 正確的順序：
        * 第一行 s1    s2      s3      s4      e       e       e       e       e
        * 第二行 e1    e2      e3      e4      e       e       e       e       e
        * 第三行 c1    c2      c3      c4      e       e       e       e    exit
        *
        * */
        signGUI.setItem( 0 , showLine1 );
        signGUI.setItem( 1 , showLine2 );
        signGUI.setItem( 2 , showLine3 );
        signGUI.setItem( 3 , showLine4 );
        signGUI.setItem( 9 , editLine1 );
        signGUI.setItem( 10 , editLine2 );
        signGUI.setItem( 11 , editLine3 );
        signGUI.setItem( 12 , editLine4 );
        signGUI.setItem( 18 , clearLine1 );
        signGUI.setItem( 19 , clearLine2 );
        signGUI.setItem( 20 , clearLine3 );
        signGUI.setItem( 21 , clearLine4 );
        signGUI.setItem( 26 , exit );

//        給玩家顯示出GUI
        player.openInventory( signGUI ) ;
    }

    @EventHandler
    public void onClickInventory ( InventoryClickEvent ev ) {
        if ( ! (ev.getWhoClicked() instanceof Player ) ) {
            //判斷是否是玩家控制物品
            return ;
        }
        Player player = ( Player ) ev.getWhoClicked() ;
        String inventoryTitle = ev.getInventory().getTitle() ;
        String title = plugin.getConfig().getString( "Messages.signGUITitle" ) ;
        if ( ! inventoryTitle.equals( title ) ) {
            //標題不相符
            return ;
        }
        int getSlot = ev.getRawSlot() ;
        if ( ev.getCurrentItem().getType().equals( Material.AIR ) ) {
            //點到的是空氣
            return ;
        }

        //撤銷玩家的拿物品動作
        ev.setCancelled( true ) ;
        player.updateInventory() ;

        if ( getSlot == 0 || getSlot == 1 || getSlot == 2 || getSlot == 3 ) {
            //如果玩家點擊的是顯示行，也不做反應
            return ;
        }

        if ( getSlot == 26 ) {
            //點的是Exit按鈕，關閉GUI介面
            player.closeInventory() ;
        }

        switch ( getSlot ) {
            case 9 :
                //第一行
                player.closeInventory() ; //關閉GUI
                player.sendMessage( ChatColor.GOLD + "[JederSign] " + plugin.getConfig().getString( "Messages.editChatTip" ) ); //發送提示訊息
                lineNum.put( player.getName() , 0 ) ;
                break ;

            case 10 :
                //第二行
                player.closeInventory() ;
                player.sendMessage( ChatColor.GOLD + "[JederSign] " + plugin.getConfig().getString( "Messages.editChatTip" ) );
                lineNum.put( player.getName() , 1 ) ;
                break ;

            case 11 :
                //第三行
                player.closeInventory() ;
                player.sendMessage( ChatColor.GOLD + "[JederSign] " + plugin.getConfig().getString( "Messages.editChatTip" ) );
                lineNum.put( player.getName() , 2 ) ;
                break ;

            case 12 :
                //第四行
                player.closeInventory() ;
                player.sendMessage( ChatColor.GOLD + "[JederSign] " + plugin.getConfig().getString( "Messages.editChatTip" ) );
                lineNum.put( player.getName() , 3 ) ;
                break ;

            case 18 :
                //清空1
                player.closeInventory() ;
                if ( !signLocation.containsKey( player.getName() ) || !lineNum.containsKey( player.getName() ) ) {
                    //查無此鍵
                    player.sendMessage( ChatColor.GOLD + "[JederSign] " + ChatColor.RED + plugin.getConfig().getString( "Messages.clearError" ) ) ;
                    signLocation.remove( player.getName() ) ;
                    isEditing.remove( player.getName() ) ;
                    lineNum.remove( player.getName() ) ;
                    return ;
                }
                Block signBlock = signLocation.get( player.getName() ) ;
                Sign sign = ( Sign ) signBlock.getState() ;
                sign.setLine( 0 , "" ) ;
                sign.update() ;
                player.sendMessage( ChatColor.GOLD + "[JederSign] " + plugin.getConfig().getString( "Messages.cleared" ) );
                break ;

            case 19 :
                //清空2
                if ( !signLocation.containsKey( player.getName() ) || !lineNum.containsKey( player.getName() ) ) {
                    //查無此鍵
                    player.sendMessage( ChatColor.GOLD + "[JederSign] " + ChatColor.RED + plugin.getConfig().getString( "Messages.clearError" ) ) ;
                    signLocation.remove( player.getName() ) ;
                    isEditing.remove( player.getName() ) ;
                    lineNum.remove( player.getName() ) ;
                    return ;
                }
                Block signBlock2 = signLocation.get( player.getName() ) ;
                Sign sign2 = ( Sign ) signBlock2.getState() ;
                sign2.setLine( 1 , "" ) ;
                sign2.update() ;
                player.sendMessage( ChatColor.GOLD + "[JederSign] " + plugin.getConfig().getString( "Messages.cleared" ) );
                break ;

            case 20 :
                //清空3
                if ( !signLocation.containsKey( player.getName() ) || !lineNum.containsKey( player.getName() ) ) {
                    //查無此鍵
                    player.sendMessage( ChatColor.GOLD + "[JederSign] " + ChatColor.RED + plugin.getConfig().getString( "Messages.clearError" ) ) ;
                    signLocation.remove( player.getName() ) ;
                    isEditing.remove( player.getName() ) ;
                    lineNum.remove( player.getName() ) ;
                    return ;
                }
                Block signBlock3 = signLocation.get( player.getName() ) ;
                Sign sign3 = ( Sign ) signBlock3.getState() ;
                sign3.setLine( 1 , "" ) ;
                sign3.update() ;
                player.sendMessage( ChatColor.GOLD + "[JederSign] " + plugin.getConfig().getString( "Messages.cleared" ) );
                break ;

            case 21 :
                //清空4
                if ( !signLocation.containsKey( player.getName() ) || !lineNum.containsKey( player.getName() ) ) {
                    //查無此鍵
                    player.sendMessage( ChatColor.GOLD + "[JederSign] " + ChatColor.RED + plugin.getConfig().getString( "Messages.clearError" ) ) ;
                    signLocation.remove( player.getName() ) ;
                    isEditing.remove( player.getName() ) ;
                    lineNum.remove( player.getName() ) ;
                    return ;
                }
                Block signBlock4 = signLocation.get( player.getName() ) ;
                Sign sign4 = ( Sign ) signBlock4.getState() ;
                sign4.setLine( 1 , "" ) ;
                sign4.update() ;
                player.sendMessage( ChatColor.GOLD + "[JederSign] " + plugin.getConfig().getString( "Messages.cleared" ) );
                break ;
        }

        return ; //甚麼？！還能到達這裡？！反正也掐了吧 233
    }

    @EventHandler
    public void onChat ( PlayerChatEvent ev ) {
        Player player = ev.getPlayer() ;
        String message = ev.getMessage() ;
        String playerName = player.getName() ;
        System.out.println( message );

        Boolean isExist = isEditing.containsKey( playerName ) ;

        if ( !isExist ) {
            //玩家不在編輯狀態
            return ;
        }
        if ( !signLocation.containsKey( playerName ) || !lineNum.containsKey( playerName ) ) {
            //查無此鍵
            return ;
        }

        int getLineNum = lineNum.get( playerName ) ;
        if ( getLineNum > 3 || getLineNum < 0 ) {
            //行數大於三或者小於零
            return ;
        }

        message = ChatColor.translateAlternateColorCodes( '&' , message ) ;

       // try {
            Block signBlock = signLocation.get( playerName ) ;
            BlockState bs = signBlock.getState() ;
            Sign sign = ( Sign ) bs ;
            sign.setLine( getLineNum , message );
            sign.update() ; //更新塊狀態

        //} catch ( Exception e ) {
          //  System.err.println( "1" + e );
        //}

        //try {
            signLocation.remove( playerName ) ;
            isEditing.remove( playerName ) ;
            lineNum.remove( playerName ) ;
        //} catch ( Exception e ) {
        //    System.err.println( "2" + e );
        //}

        ev.setCancelled( true );
        //撤銷玩家發送訊息的事件

        return ; //最後還是要掐掉的

    }
}
