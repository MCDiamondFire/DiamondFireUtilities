package diamondfireutils.commands.itemcontrol.lore;

import static diamondfireutils.commands.MessageUtils.*;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.IClientCommand;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CommandLoreBase extends CommandBase implements IClientCommand {
    
    private final Minecraft minecraft = Minecraft.getMinecraft();
    
    public String getName() {
        return "lore";
    }
    
    public String getUsage(ICommandSender sender) {
        return "§c/lore add <lore> \n" +
                "§c/lore set <line> <lore> \n" +
                "§c/lore insert <line> <lore> \n" +
                "§c/lore remove <line> \n" +
                "§c/lore clear";
    }
    
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }
    
    public boolean allowUsageWithoutPrefix(ICommandSender sender, String message) {
        return false;
    }
    
    public void execute(MinecraftServer server, ICommandSender sender, String[] commandArgs) {
        
        //Checks if player should be able to execute command.
        if (!minecraft.player.isCreative()) {
            commandError("You need to be in build mode or dev mode to do this!");
            return;
        }
        
        if (commandArgs.length == 0) {
            commandError("Usage:\n" + getUsage(sender));
            return;
        }
        
        switch (commandArgs[0]) {
            case "add":
                CommandLoreAdd.executeAddLore(sender, commandArgs);
                return;
                
            case "set":
                CommandLoreSet.executeSetLore(sender, commandArgs);
                return;
            
            case "insert":
                CommandLoreInsert.executeInsertLore(sender, commandArgs);
                return;
                
            case "remove":
                CommandLoreRemove.executeRemoveLore(sender, commandArgs);
                return;
            
            case "clear":
                CommandLoreClear.executeClearLore(sender, commandArgs);
                return;
                
            default:
                commandError("Usage:\n" + getUsage(sender));
        }
    }
}
