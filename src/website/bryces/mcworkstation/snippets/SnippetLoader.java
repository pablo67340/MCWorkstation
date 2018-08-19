/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package website.bryces.mcworkstation.snippets;

import javafx.collections.ObservableList;
import website.bryces.mcworkstation.main.FXMLDocumentController;

/**
 *
 * @author Bryce
 */
public class SnippetLoader {

    FXMLDocumentController mainInstance;

    public SnippetLoader() {
        mainInstance = FXMLDocumentController.getInstance();
        ObservableList<String> list = mainInstance.lstCat.getItems();
        
        Category spigot = new Category("Spigot/Bukkit");
        Category yml = new Category("YML");
        mainInstance.categories.put(spigot.getName(), spigot);
        mainInstance.categories.put(yml.getName(), yml);

        Snippet defaultCMD = new Snippet.Builder()
                .withName("Command Executor")
                .withDescription("This method will fire when a command is typed if successfuly registered in plugin.")
                .withReturns("true/false - If the command executed successfully.")
                .withSnippet("@Override\n"
                        + "public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {\n"
                        + "  return false;\n"
                        + "}")
                .build();
        spigot.addSnippet(defaultCMD);
        
        
        
        Snippet delayedScheduler = new Snippet.Builder()
                .withName("Delayed BukkitScheduler")
                .withDescription("This method schedules an anonymous Runnable with the BukkitScheduler to run after 20 ticks, once.")
                .withReturns("N/A")
                .withSnippet("BukkitScheduler scheduler = getServer().getScheduler();\n" +
"        scheduler.scheduleSyncDelayedTask(this, new Runnable() {\n" +
"            @Override\n" +
"            public void run() {\n" +
"                // Do something\n" +
"            }\n" +
"        }, 20L);")
                .build();
        spigot.addSnippet(delayedScheduler);
        
       Snippet repeatingScheduler = new Snippet.Builder()
                .withName("Repeating BukkitScheduler")
                .withDescription("This method will schedule an anonymous Runnable with the BukkitScheduler with an intial delay of 0 ticks, to run every twenty ticks, forever, starting from when you schedule it.")
                .withReturns("N/A")
                .withSnippet("BukkitScheduler scheduler = getServer().getScheduler();\n" +
"        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {\n" +
"            @Override\n" +
"            public void run() {\n" +
"                // Do something\n" +
"            }\n" +
"        }, 0L, 20L);")
                .build();
        spigot.addSnippet(repeatingScheduler);
        
               Snippet eventListener = new Snippet.Builder()
                .withName("Generic EventListener")
                .withDescription("This method will fire when the specified event happens. You can also toggle the EventPriority so the event will fire after or before other events. Lastly, you can toggle IgnoreCancelled so your specific event wont be cancelled by other plugins/forces. NOTE* The class containing your method MUST implement Listener! \n\nReplace InventoryClickEvent with an event of your choice!")
                .withReturns("N/A")
                .withSnippet("@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = false)\n" +
"	public void onCommand(InventoryClickEvent event) {\n" +
"		// Do stuff here\n" +
"	}")
                .build();
        spigot.addSnippet(eventListener);
        
                       Snippet basicPlugin = new Snippet.Builder()
                .withName("Basic Plugin Class")
                .withDescription("This is a basic class for a base plugin. It will save the config (if there's one in the jar) & register the class as a listener. \n\nIt is recommended to keep your Event Listeners in a seperate class rather than melded into one.")
                .withReturns("N/A")
                .withSnippet("import org.bukkit.event.EventHandler;\n" +
"import org.bukkit.event.EventPriority;\n" +
"import org.bukkit.event.Listener;\n" +
"import org.bukkit.event.inventory.InventoryClickEvent;\n" +
"import org.bukkit.plugin.java.JavaPlugin;\n" +
"\n" +
"public class Main extends JavaPlugin implements Listener{\n" +
"	\n" +
"	private static Main INSTANCE;\n" +
"	\n" +
"	@Override\n" +
"	public void onEnable() {\n" +
"		INSTANCE = this;\n" +
"		saveDefaultConfig();\n" +
"		this.getServer().getPluginManager().registerEvents(this, this);\n" +
"	}\n" +
"	\n" +
"	@Override\n" +
"	public void onDisable() {\n" +
"		\n" +
"	}\n" +
"	\n" +
"	@EventHandler(priority = EventPriority.HIGHEST)\n" +
"	public void onInventoryClick(InventoryClickEvent event) {\n" +
"		event.getWhoClicked().sendMessage(\"boop!\");\n" +
"	}\n" +
"	\n" +
"	// This will allow you to get the instance from any other class.\n" +
"	public static Main getInstance() {\n" +
"		return INSTANCE;\n" +
"	}\n" +
"\n" +
"}")
                .build();
        spigot.addSnippet(basicPlugin);
        
        
        
                               Snippet cooldowns = new Snippet.Builder()
                .withName("Cooldowns Class")
                .withDescription("This class can be used to add cooldowns to just about anything. You can create a cooldown with a unique key, and later, check if the cooldown has expired or not.")
                .withReturns("N/A")
                .withSnippet("import org.bukkit.entity.Player;\n" +
"\n" +
"import com.google.common.collect.HashBasedTable;\n" +
"import com.google.common.collect.Table;\n" +
"\n" +
"public class Cooldowns {\n" +
"    private static Table<String, String, Long> cooldowns = HashBasedTable.create();\n" +
"    \n" +
"    /**\n" +
"     * Retrieve the number of milliseconds left until a given cooldown expires.\n" +
"     * <p>\n" +
"     * Check for a negative value to determine if a given cooldown has expired. <br>\n" +
"     * Cooldowns that have never been defined will return {@link Long#MIN_VALUE}.\n" +
"     * @param player - the player.\n" +
"     * @param key - cooldown to locate.\n" +
"     * @return Number of milliseconds until the cooldown expires.\n" +
"     */\n" +
"    public static long getCooldown(Player player, String key) {\n" +
"        return calculateRemainder(cooldowns.get(player.getName(), key));\n" +
"    }\n" +
"    \n" +
"    /**\n" +
"     * Update a cooldown for the specified player.\n" +
"     * @param player - the player.\n" +
"     * @param key - cooldown to update.\n" +
"     * @param delay - number of milliseconds until the cooldown will expire again.\n" +
"     * @return The previous number of milliseconds until expiration.\n" +
"     */\n" +
"    public static long setCooldown(Player player, String key, long delay) {\n" +
"        return calculateRemainder(\n" +
"                cooldowns.put(player.getName(), key, System.currentTimeMillis() + delay));\n" +
"    }\n" +
"    \n" +
"    /**\n" +
"     * Determine if a given cooldown has expired. If it has, refresh the cooldown. If not, do nothing.\n" +
"     * @param player - the player.\n" +
"     * @param key - cooldown to update. \n" +
"     * @param delay - number of milliseconds until the cooldown will expire again.\n" +
"     * @return TRUE if the cooldown was expired/unset and has now been reset, FALSE otherwise.\n" +
"     */\n" +
"    public static boolean tryCooldown(Player player, String key, long delay) {\n" +
"        if (getCooldown(player, key) <= 0) {\n" +
"            setCooldown(player, key, delay);\n" +
"            return true;\n" +
"        }\n" +
"        return false;\n" +
"    }\n" +
"    \n" +
"    \n" +
"    private static long calculateRemainder(Long expireTime) {\n" +
"        return expireTime != null ? expireTime - System.currentTimeMillis() : Long.MIN_VALUE;\n" +
"    }\n" +
"}")
                .build();
        spigot.addSnippet(cooldowns);
        
        
        Snippet matLib = new Snippet.Builder()
                .withName("Material -> ItemID Class")
                .withDescription("This class can aid you in converting material names to ItemID, as they're being phased out. This is up to date with 1.13")
                .withReturns("N/A")
                .withSnippet("import java.util.HashMap;\n" +
"import java.util.Map;\n" +
"\n" +
"/**\n" +
" *\n" +
" * @author Bryce\n" +
" */\n" +
"public final class MatLib {\n" +
"\n" +
"    public static final Map<String, String> MAP = new HashMap<>();\n" +
"\n" +
"    public MatLib() {\n" +
"\n" +
"    }\n" +
"\n" +
"    static {\n" +
"        MAP.put(\"0:0\", \"AIR\");\n" +
"        MAP.put(\"1:0\", \"STONE\");\n" +
"        MAP.put(\"1:1\", \"GRANITE\");\n" +
"        MAP.put(\"1:2\", \"POLISHED_GRANITE\");\n" +
"        MAP.put(\"1:3\", \"DIORITE\");\n" +
"        MAP.put(\"1:4\", \"POLISHED_DIORITE\");\n" +
"        MAP.put(\"1:5\", \"ANDESITE\");\n" +
"        MAP.put(\"1:6\", \"POLISHED_ANDESITE\");\n" +
"        MAP.put(\"2:0\", \"GRASS\");\n" +
"        MAP.put(\"3:0\", \"DIRT\");\n" +
"        MAP.put(\"3:1\", \"COARSE_DIRT\");\n" +
"        MAP.put(\"3:2\", \"PODZOL\");\n" +
"        MAP.put(\"4:0\", \"COBBLESTONE\");\n" +
"        MAP.put(\"5:0\", \"OAK_PLANKS\");\n" +
"        MAP.put(\"5:1\", \"SPRUCE_PLANKS\");\n" +
"        MAP.put(\"5:2\", \"BIRCH_PLANKS\");\n" +
"        MAP.put(\"5:3\", \"JUNGLE_PLANKS\");\n" +
"        MAP.put(\"5:4\", \"ACACIA_PLANKS\");\n" +
"        MAP.put(\"5:5\", \"DARK_OAK_PLANKS\");\n" +
"        MAP.put(\"6:0\", \"OAK_SAPLING\");\n" +
"        MAP.put(\"6:1\", \"SPRUCE_SAPLING\");\n" +
"        MAP.put(\"6:2\", \"BIRCH_SAPLING\");\n" +
"        MAP.put(\"6:3\", \"JUNGLE_SAPLING\");\n" +
"        MAP.put(\"6:4\", \"ACACIA_SAPLING\");\n" +
"        MAP.put(\"6:5\", \"DARK_OAK_SAPLING\");\n" +
"        MAP.put(\"7:0\", \"BEDROCK\");\n" +
"        MAP.put(\"8:0\", \"WATER\");\n" +
"        MAP.put(\"9:0\", \"WATER\");\n" +
"        MAP.put(\"10:0\", \"LAVA\");\n" +
"        MAP.put(\"11:0\", \"LAVA\");\n" +
"        MAP.put(\"12:0\", \"SAND\");\n" +
"        MAP.put(\"12:1\", \"RED_SAND\");\n" +
"        MAP.put(\"13:0\", \"GRAVEL\");\n" +
"        MAP.put(\"14:0\", \"GOLD_ORE\");\n" +
"        MAP.put(\"15:0\", \"IRON_ORE\");\n" +
"        MAP.put(\"16:0\", \"COAL_ORE\");\n" +
"        MAP.put(\"17:0\", \"OAK_WOOD\");\n" +
"        MAP.put(\"17:1\", \"SPRUCE_WOOD\");\n" +
"        MAP.put(\"17:2\", \"BIRCH_WOOD\");\n" +
"        MAP.put(\"17:3\", \"JUNGLE_WOOD\");\n" +
"        MAP.put(\"18:0\", \"OAK_LEAVES\");\n" +
"        MAP.put(\"18:1\", \"SPRUCE_LEAVES\");\n" +
"        MAP.put(\"18:2\", \"BIRCH_LEAVES\");\n" +
"        MAP.put(\"18:3\", \"JUNGLE_LEAVES\");\n" +
"        MAP.put(\"19:0\", \"SPONGE\");\n" +
"        MAP.put(\"19:1\", \"WET_SPONGE\");\n" +
"        MAP.put(\"20:0\", \"GLASS\");\n" +
"        MAP.put(\"21:0\", \"LAPIS_ORE\");\n" +
"        MAP.put(\"22:0\", \"LAPIS_BLOCK\");\n" +
"        MAP.put(\"23:0\", \"DISPENSER\");\n" +
"        MAP.put(\"24:0\", \"SANDSTONE\");\n" +
"        MAP.put(\"24:1\", \"CHISELED_SANDSTONE\");\n" +
"        MAP.put(\"24:2\", \"SMOOTH_SANDSTONE\");\n" +
"        MAP.put(\"25:0\", \"NOTE_BLOCK\");\n" +
"        MAP.put(\"26:0\", \"BED\");\n" +
"        MAP.put(\"27:0\", \"POWERED_RAIL\");\n" +
"        MAP.put(\"28:0\", \"DETECTOR_RAIL\");\n" +
"        MAP.put(\"29:0\", \"STICKY_PISTON\");\n" +
"        MAP.put(\"30:0\", \"COBWEB\");\n" +
"        MAP.put(\"31:0\", \"TALL_GRASS\");\n" +
"        MAP.put(\"31:1\", \"GRASS\");\n" +
"        MAP.put(\"31:2\", \"FERN\");\n" +
"        MAP.put(\"32:0\", \"DEAD_BUSH\");\n" +
"        MAP.put(\"33:0\", \"PISTON\");\n" +
"        MAP.put(\"34:0\", \"PISTON_HEAD\");\n" +
"        MAP.put(\"35:0\", \"WHITE_WOOL\");\n" +
"        MAP.put(\"35:1\", \"ORANGE_WOOL\");\n" +
"        MAP.put(\"35:2\", \"MAGENTA_WOOL\");\n" +
"        MAP.put(\"35:3\", \"LIGHT_BLUE_WOOL\");\n" +
"        MAP.put(\"35:4\", \"YELLOW_WOOL\");\n" +
"        MAP.put(\"35:5\", \"LIME_WOOL\");\n" +
"        MAP.put(\"35:6\", \"PINK_WOOL\");\n" +
"        MAP.put(\"35:7\", \"GRAY_WOOL\");\n" +
"        MAP.put(\"35:8\", \"LIGHT_GRAY_WOOL\");\n" +
"        MAP.put(\"35:9\", \"CYAN_WOOL\");\n" +
"        MAP.put(\"35:10\", \"PURPLE_WOOL\");\n" +
"        MAP.put(\"35:11\", \"BLUE_WOOL\");\n" +
"        MAP.put(\"35:12\", \"BROWN_WOOL\");\n" +
"        MAP.put(\"35:13\", \"GREEN_WOOL\");\n" +
"        MAP.put(\"35:14\", \"RED_WOOL\");\n" +
"        MAP.put(\"35:15\", \"BLACK_WOOL\");\n" +
"        MAP.put(\"37:0\", \"DANDELION\");\n" +
"        MAP.put(\"38:0\", \"POPPY\");\n" +
"        MAP.put(\"38:1\", \"BLUE_ORCHID\");\n" +
"        MAP.put(\"38:2\", \"ALLIUM\");\n" +
"        MAP.put(\"38:3\", \"AZURE_BLUET\");\n" +
"        MAP.put(\"38:4\", \"RED_TULIP\");\n" +
"        MAP.put(\"38:5\", \"ORANGE_TULIP\");\n" +
"        MAP.put(\"38:6\", \"WHITE_TULIP\");\n" +
"        MAP.put(\"38:7\", \"PINK_TULIP\");\n" +
"        MAP.put(\"38:8\", \"OXEYE_DAISY\");\n" +
"        MAP.put(\"39:0\", \"BROWN_MUSHROOM\");\n" +
"        MAP.put(\"40:0\", \"RED_MUSHROOM\");\n" +
"        MAP.put(\"41:0\", \"GOLD_BLOCK\");\n" +
"        MAP.put(\"42:0\", \"IRON_BLOCK\");\n" +
"        MAP.put(\"43:0\", \"STONE_STAIRS\");\n" +
"        MAP.put(\"43:1\", \"SANDSTONE_STAIRS\");\n" +
"        MAP.put(\"43:2\", \"OAK_STAIRS\");\n" +
"        MAP.put(\"43:3\", \"COBBLESTONE_STAIRS\");\n" +
"        MAP.put(\"43:4\", \"BRICK_STAIRS\");\n" +
"        MAP.put(\"43:5\", \"STONE_BRICK_STAIRS\");\n" +
"        MAP.put(\"43:6\", \"NETHER_BRICK_STAIRS\");\n" +
"        MAP.put(\"43:7\", \"QUARTZ_STAIRS\");\n" +
"        MAP.put(\"44:0\", \"STONE_SLAB\");\n" +
"        MAP.put(\"44:1\", \"SANDSTONE_SLAB\");\n" +
"        MAP.put(\"44:2\", \"OAK_STAIRS\");\n" +
"        MAP.put(\"44:3\", \"COBBLESTONE_SLAB\");\n" +
"        MAP.put(\"44:4\", \"BRICK_SLAB\");\n" +
"        MAP.put(\"44:5\", \"STONE_BRICK_SLAB\");\n" +
"        MAP.put(\"44:6\", \"NETHER_BRICK_SLAB\");\n" +
"        MAP.put(\"44:7\", \"QUARTZ_SLAB\");\n" +
"        MAP.put(\"45:0\", \"BRICKS\");\n" +
"        MAP.put(\"46:0\", \"TNT\");\n" +
"        MAP.put(\"47:0\", \"BOOKSHELF\");\n" +
"        MAP.put(\"48:0\", \"MOSSY_COBBLESTONE\");\n" +
"        MAP.put(\"49:0\", \"OBSIDIAN\");\n" +
"        MAP.put(\"50:0\", \"TORCH\");\n" +
"        MAP.put(\"51:0\", \"FIRE\");\n" +
"        MAP.put(\"52:0\", \"SPAWNER\");\n" +
"        MAP.put(\"53:0\", \"OAK_STAIRS\");\n" +
"        MAP.put(\"54:0\", \"CHEST\");\n" +
"        MAP.put(\"55:0\", \"REDSTONE_WIRE\");\n" +
"        MAP.put(\"56:0\", \"DIAMOND_ORE\");\n" +
"        MAP.put(\"57:0\", \"DIAMOND_BLOCK\");\n" +
"        MAP.put(\"58:0\", \"CRAFTING_TABLE\");\n" +
"        MAP.put(\"59:0\", \"WHEAT\");\n" +
"        MAP.put(\"60:0\", \"FARMLAND\");\n" +
"        MAP.put(\"61:0\", \"FURNACE\");\n" +
"        MAP.put(\"62:0\", \"FURNACE\");\n" +
"        MAP.put(\"63:0\", \"SIGN\");\n" +
"        MAP.put(\"64:0\", \"OAK_DOOR\");\n" +
"        MAP.put(\"65:0\", \"LADDER\");\n" +
"        MAP.put(\"66:0\", \"RAIL\");\n" +
"        MAP.put(\"67:0\", \"COBBLESTONE_STAIRS\");\n" +
"        MAP.put(\"68:0\", \"SIGN\");\n" +
"        MAP.put(\"69:0\", \"LEVER\");\n" +
"        MAP.put(\"70:0\", \"STONE_PRESSURE_PLATE\");\n" +
"        MAP.put(\"71:0\", \"IRON_DOOR\");\n" +
"        MAP.put(\"72:0\", \"OAK_PRESSURE_PLATE\");\n" +
"        MAP.put(\"73:0\", \"REDSTONE_ORE\");\n" +
"        MAP.put(\"74:0\", \"REDSTONE_ORE\");\n" +
"        MAP.put(\"75:0\", \"REDSTONE_TORCH\");\n" +
"        MAP.put(\"76:0\", \"REDSTONE_TORCH\");\n" +
"        MAP.put(\"77:0\", \"STONE_BUTTON\");\n" +
"        MAP.put(\"78:0\", \"SNOW\");\n" +
"        MAP.put(\"79:0\", \"ICE\");\n" +
"        MAP.put(\"80:0\", \"SNOW_BLOCK\");\n" +
"        MAP.put(\"81:0\", \"CACTUS\");\n" +
"        MAP.put(\"82:0\", \"CLAY\");\n" +
"        MAP.put(\"83:0\", \"SUGAR_CANE\");\n" +
"        MAP.put(\"84:0\", \"JUKEBOX\");\n" +
"        MAP.put(\"85:0\", \"OAK_FENCE\");\n" +
"        MAP.put(\"86:0\", \"PUMPKIN\");\n" +
"        MAP.put(\"87:0\", \"NETHERRACK\");\n" +
"        MAP.put(\"88:0\", \"SOUL_SAND\");\n" +
"        MAP.put(\"89:0\", \"GLOWSTONE\");\n" +
"        MAP.put(\"90:0\", \"NETHER_PORTAL\");\n" +
"        MAP.put(\"91:0\", \"JACK_O_LANTERN\");\n" +
"        MAP.put(\"92:0\", \"CAKE\");\n" +
"        MAP.put(\"93:0\", \"REPEATER\");\n" +
"        MAP.put(\"94:0\", \"REPEATER\");\n" +
"        MAP.put(\"95:0\", \"WHITE_STAINED_GLASS\");\n" +
"        MAP.put(\"95:1\", \"ORANGE_STAINED_GLASS\");\n" +
"        MAP.put(\"95:2\", \"MAGENTA_STAINED_GLASS\");\n" +
"        MAP.put(\"95:3\", \"LIGHT_BLUE_STAINED_GLASS\");\n" +
"        MAP.put(\"95:4\", \"YELLOW_STAINED_GLASS\");\n" +
"        MAP.put(\"95:5\", \"LIME_STAINED_GLASS\");\n" +
"        MAP.put(\"95:6\", \"PINK_STAINED_GLASS\");\n" +
"        MAP.put(\"95:7\", \"GRAY_STAINED_GLASS\");\n" +
"        MAP.put(\"95:8\", \"LIGHT_GRAY_STAINED_GLASS\");\n" +
"        MAP.put(\"95:9\", \"CYAN_STAINED_GLASS\");\n" +
"        MAP.put(\"95:10\", \"PURPLE_STAINED_GLASS\");\n" +
"        MAP.put(\"95:11\", \"BLUE_STAINED_GLASS\");\n" +
"        MAP.put(\"95:12\", \"BROWN_STAINED_GLASS\");\n" +
"        MAP.put(\"95:13\", \"GREEN_STAINED_GLASS\");\n" +
"        MAP.put(\"95:14\", \"RED_STAINED_GLASS\");\n" +
"        MAP.put(\"95:15\", \"BLACK_STAINED_GLASS\");\n" +
"        MAP.put(\"96:0\", \"TRAP_DOOR\");\n" +
"        MAP.put(\"97:0\", \"INFESTED_STONE\");\n" +
"        MAP.put(\"97:1\", \"INFESTED_COBBLESTONE\");\n" +
"        MAP.put(\"97:2\", \"INFESTED_STONE_BRICKS\");\n" +
"        MAP.put(\"97:3\", \"INFESTED_MOSSY_STONE_BRICKS\");\n" +
"        MAP.put(\"97:4\", \"INFESTED_CRACKED_STONE_BRICKS\");\n" +
"        MAP.put(\"97:5\", \"CHISELED_STONE_BRICKS\");\n" +
"        MAP.put(\"98:0\", \"STONE_BRICKS\");\n" +
"        MAP.put(\"98:1\", \"MOSSY_STONE_BRICKS\");\n" +
"        MAP.put(\"98:2\", \"CRACKED_STONE_BRICKS\");\n" +
"        MAP.put(\"98:3\", \"CHISELED_STONE_BRICKS\");\n" +
"        MAP.put(\"99:0\", \"BROWN_MUSHROOM_BLOCK\");\n" +
"        MAP.put(\"100:0\", \"RED_MUSHROOM_BLOCK\");\n" +
"        MAP.put(\"101:0\", \"IRON_BARS\");\n" +
"        MAP.put(\"102:0\", \"GLASS_PANE\");\n" +
"        MAP.put(\"103:0\", \"MELON\");\n" +
"        MAP.put(\"104:0\", \"PUMPKIN_STEM\");\n" +
"        MAP.put(\"105:0\", \"MELON_STEM\");\n" +
"        MAP.put(\"106:0\", \"MELON\");\n" +
"        MAP.put(\"107:0\", \"OAK_FENCE_GATE\");\n" +
"        MAP.put(\"108:0\", \"BRICK_STAIRS\");\n" +
"        MAP.put(\"109:0\", \"STONE_BRICK_STAIRS\");\n" +
"        MAP.put(\"110:0\", \"MYCELIUM\");\n" +
"        MAP.put(\"111:0\", \"LILY_PAD\");\n" +
"        MAP.put(\"112:0\", \"NETHER_BRICK\");\n" +
"        MAP.put(\"113:0\", \"NETHER_BRICK_FENCE\");\n" +
"        MAP.put(\"114:0\", \"NETHER_BRICK_STAIRS\");\n" +
"        MAP.put(\"115:0\", \"NETHER_WART\");\n" +
"        MAP.put(\"116:0\", \"ENCHANTING_TABLE\");\n" +
"        MAP.put(\"117:0\", \"BREWING_STAND\");\n" +
"        MAP.put(\"118:0\", \"CAULDRON\");\n" +
"        MAP.put(\"119:0\", \"END_PORTAL\");\n" +
"        MAP.put(\"120:0\", \"END_PORTAL_FRAME\");\n" +
"        MAP.put(\"121:0\", \"END_STONE\");\n" +
"        MAP.put(\"122:0\", \"DRAGON_EGG\");\n" +
"        MAP.put(\"123:0\", \"ENCHANTING_TABLE\");\n" +
"        MAP.put(\"124:0\", \"REDSTONE_LAMP\");\n" +
"        MAP.put(\"125:0\", \"OAK_SLAB\");\n" +
"        MAP.put(\"125:1\", \"SPRUCE_SLAB\");\n" +
"        MAP.put(\"125:2\", \"BIRCH_SLAB\");\n" +
"        MAP.put(\"125:3\", \"JUNGLE_SLAB\");\n" +
"        MAP.put(\"125:4\", \"ACACIA_SLAB\");\n" +
"        MAP.put(\"125:5\", \"DARK_OAK_SLAB\");\n" +
"        MAP.put(\"126:0\", \"OAK_SLAB\");\n" +
"        MAP.put(\"126:1\", \"SPRUCE_SLAB\");\n" +
"        MAP.put(\"126:2\", \"BIRCH_SLAB\");\n" +
"        MAP.put(\"126:3\", \"JUNGLE_SLAB\");\n" +
"        MAP.put(\"126:4\", \"ACACIA_SLAB\");\n" +
"        MAP.put(\"126:5\", \"DARK_OAK_SLAB\");\n" +
"        MAP.put(\"127:0\", \"COCOA\");\n" +
"        MAP.put(\"128:0\", \"SANDSTONE_STAIRS\");\n" +
"        MAP.put(\"129:0\", \"EMERALD_ORE\");\n" +
"        MAP.put(\"130:0\", \"ENDER_CHEST\");\n" +
"        MAP.put(\"131:0\", \"TRIPWIRE_HOOK\");\n" +
"        MAP.put(\"132:0\", \"TRIPWIRE\");\n" +
"        MAP.put(\"133:0\", \"EMERALD_BLOCK\");\n" +
"        MAP.put(\"134:0\", \"SPRUCE_STAIRS\");\n" +
"        MAP.put(\"135:0\", \"BITCH_STAIRS\");\n" +
"        MAP.put(\"136:0\", \"JUNGLE_STAIRS\");\n" +
"        MAP.put(\"137:0\", \"COMMAND_BLOCK\");\n" +
"        MAP.put(\"138:0\", \"BEACON\");\n" +
"        MAP.put(\"139:0\", \"COBBLESTONE_WALL\");\n" +
"        MAP.put(\"139:1\", \"MOSSY_COBBLESTONE_WALL\");\n" +
"        MAP.put(\"140:0\", \"FLOWER_POT\");\n" +
"        MAP.put(\"141:0\", \"CARROTS\");\n" +
"        MAP.put(\"142:0\", \"POTATOES\");\n" +
"        MAP.put(\"143:0\", \"OAK_BUTTON\");\n" +
"        MAP.put(\"144:0\", \"SKULL\");\n" +
"        MAP.put(\"145:0\", \"ANVIL\");\n" +
"        MAP.put(\"146:0\", \"TRAPPED_CHEST\");\n" +
"        MAP.put(\"147:0\", \"LIGHT_WEIGHTED_PRESSURE_PLATE\");\n" +
"        MAP.put(\"148:0\", \"HEAVY_WEIGHTED_PRESSURE_PLATE\");\n" +
"        MAP.put(\"149:0\", \"COMPARATOR\");\n" +
"        MAP.put(\"150:0\", \"COMPARATOR\");\n" +
"        MAP.put(\"151:0\", \"DAYLIGHT_DETECTOR\");\n" +
"        MAP.put(\"152:0\", \"REDSTONE_BLOCK\");\n" +
"        MAP.put(\"153:0\", \"NETHER_QUARTZ_ORE\");\n" +
"        MAP.put(\"154:0\", \"HOPPER\");\n" +
"        MAP.put(\"155:0\", \"QUARTZ_BLOCK\");\n" +
"        MAP.put(\"155:1\", \"CHISELED_QUARTZ_BLOCK\");\n" +
"        MAP.put(\"155:2\", \"QUARTZ_PILLAR\");\n" +
"        MAP.put(\"156:0\", \"QUARTZ_STAIRS\");\n" +
"        MAP.put(\"157:0\", \"ACTIVATOR_RAIL\");\n" +
"        MAP.put(\"158:0\", \"DROPPER\");\n" +
"        MAP.put(\"159:0\", \"CONCRETE\");\n" +
"        MAP.put(\"159:1\", \"ORANGE_CONCRETE\");\n" +
"        MAP.put(\"159:2\", \"MAGENTA_CONCRETE\");\n" +
"        MAP.put(\"159:3\", \"LIGHT_BLUE_CONCRETE\");\n" +
"        MAP.put(\"159:4\", \"YELLOW_CONCRETE\");\n" +
"        MAP.put(\"159:5\", \"LIME_CONCRETE\");\n" +
"        MAP.put(\"159:6\", \"PINK_CONCRETE\");\n" +
"        MAP.put(\"159:7\", \"GRAY_CONCRETE\");\n" +
"        MAP.put(\"159:8\", \"LIGHT_GRAY_CONCRETE\");\n" +
"        MAP.put(\"159:9\", \"CYAN_CONCRETE\");\n" +
"        MAP.put(\"159:10\", \"PURPLE_CONCRETE\");\n" +
"        MAP.put(\"159:11\", \"BLUE_CONCRETE\");\n" +
"        MAP.put(\"159:12\", \"BROWN_CONCRETE\");\n" +
"        MAP.put(\"159:13\", \"GREEN_CONCRETE\");\n" +
"        MAP.put(\"159:14\", \"RED_CONCRETE\");\n" +
"        MAP.put(\"159:15\", \"BLACK_CONCRETE\");\n" +
"        MAP.put(\"160:0\", \"WHITE_STAINED_GLASS_PANE\");\n" +
"        MAP.put(\"160:1\", \"ORANGE_STAINED_GLASS_PANE\");\n" +
"        MAP.put(\"160:2\", \"MAGENTA_STAINED_GLASS_PANE\");\n" +
"        MAP.put(\"160:3\", \"LIGHT_BLUE_STAINED_GLASS_PANE\");\n" +
"        MAP.put(\"160:4\", \"YELLOW_STAINED_GLASS_PANE\");\n" +
"        MAP.put(\"160:5\", \"LIME_STAINED_GLASS_PANE\");\n" +
"        MAP.put(\"160:6\", \"PINK_STAINED_GLASS_PANE\");\n" +
"        MAP.put(\"160:7\", \"GRAY_STAINED_GLASS_PANE\");\n" +
"        MAP.put(\"160:8\", \"LIGHT_GRAY_STAINED_GLASS_PANE\");\n" +
"        MAP.put(\"160:9\", \"CYAN_STAINED_GLASS_PANE\");\n" +
"        MAP.put(\"160:10\", \"PURPLE_STAINED_GLASS_PANE\");\n" +
"        MAP.put(\"160:11\", \"BLUE_STAINED_GLASS_PANE\");\n" +
"        MAP.put(\"160:12\", \"BROWN_STAINED_GLASS_PANE\");\n" +
"        MAP.put(\"160:13\", \"GREEN_STAINED_GLASS_PANE\");\n" +
"        MAP.put(\"160:14\", \"RED_STAINED_GLASS_PANE\");\n" +
"        MAP.put(\"160:15\", \"BLACK_STAINED_GLASS_PANE\");\n" +
"        MAP.put(\"161:0\", \"ACACIA_LEAVES\");\n" +
"        MAP.put(\"161:1\", \"DARK_OAK_LEAVES\");\n" +
"        MAP.put(\"162:0\", \"ACACIA_WOOD\");\n" +
"        MAP.put(\"162:1\", \"DARK_OAK_WOOD\");\n" +
"        MAP.put(\"163:0\", \"ACACIA_STAIRS\");\n" +
"        MAP.put(\"164:0\", \"DARK_OAK_STAIRS\");\n" +
"        MAP.put(\"165:0\", \"SLIME_BLOCK\");\n" +
"        MAP.put(\"166:0\", \"BARRIER\");\n" +
"        MAP.put(\"167:0\", \"IRON_TRAPDOOR\");\n" +
"        MAP.put(\"168:0\", \"PRISMARINE\");\n" +
"        MAP.put(\"168:1\", \"PRISMARINE_BRICKS\");\n" +
"        MAP.put(\"168:2\", \"DARK_PRISMARINE\");\n" +
"        MAP.put(\"169:0\", \"SEA_LANTERN\");\n" +
"        MAP.put(\"170:0\", \"HAY_BLOCK\");\n" +
"        MAP.put(\"171:0\", \"WHITE_CARPET\");\n" +
"        MAP.put(\"171:1\", \"ORANGE_CARPET\");\n" +
"        MAP.put(\"171:2\", \"MAGENTA_CARPET\");\n" +
"        MAP.put(\"171:3\", \"LIGHT_BLUE_CARPET\");\n" +
"        MAP.put(\"171:4\", \"YELLOW_CARPET\");\n" +
"        MAP.put(\"171:5\", \"LIME_CARPET\");\n" +
"        MAP.put(\"171:6\", \"PINK_CARPET\");\n" +
"        MAP.put(\"171:7\", \"GRAY_CARPET\");\n" +
"        MAP.put(\"171:8\", \"LIGHT_GRAY_CARPET\");\n" +
"        MAP.put(\"171:9\", \"CYAN_CARPET\");\n" +
"        MAP.put(\"171:10\", \"PURPLE_CARPET\");\n" +
"        MAP.put(\"171:11\", \"BLUE_CARPET\");\n" +
"        MAP.put(\"171:12\", \"BROWN_CARPET\");\n" +
"        MAP.put(\"171:13\", \"GREEN_CARPET\");\n" +
"        MAP.put(\"171:14\", \"RED_CARPET\");\n" +
"        MAP.put(\"171:15\", \"BLACK_CARPET\");\n" +
"        MAP.put(\"172:0\", \"CONCRETE\");\n" +
"        MAP.put(\"173:0\", \"COAL_BLOCK\");\n" +
"        MAP.put(\"174:0\", \"PACKED_ICE\");\n" +
"        MAP.put(\"175:0\", \"SUNFLOWER\");\n" +
"        MAP.put(\"175:1\", \"LILAC\");\n" +
"        MAP.put(\"175:2\", \"TALL_GRASS\");\n" +
"        MAP.put(\"175:3\", \"LARGE_FERN\");\n" +
"        MAP.put(\"175:4\", \"ROSE_BUSH\");\n" +
"        MAP.put(\"175:5\", \"PEONY\");\n" +
"        MAP.put(\"176:0\", \"WHITE_BANNER\");\n" +
"        MAP.put(\"177:0\", \"WHITE_WALL_BANNER\");\n" +
"        MAP.put(\"178:0\", \"DAYLIGHT_DETECTOR\");\n" +
"        MAP.put(\"179:0\", \"RED_SANDSTONE\");\n" +
"        MAP.put(\"179:1\", \"CHISELED_RED_SANDSTONE\");\n" +
"        MAP.put(\"179:2\", \"SMOOTH_RED_SANDSTONE\");\n" +
"        MAP.put(\"180:0\", \"RED_SANDSTONE_STAIRS\");\n" +
"        MAP.put(\"181:0\", \"RED_SANDSTONE_SLAB\");\n" +
"        MAP.put(\"182:0\", \"RED_SANDSTONE_SLAB\");\n" +
"        MAP.put(\"183:0\", \"SPRUCE_FENCE_GATE\");\n" +
"        MAP.put(\"184:0\", \"BIRCH_FENCE_GATE\");\n" +
"        MAP.put(\"185:0\", \"JUNGLE_FENCE_GATE\");\n" +
"        MAP.put(\"186:0\", \"DARK_OAK_FENCE_GATE\");\n" +
"        MAP.put(\"187:0\", \"ACACIA_FENCE_GATE\");\n" +
"        MAP.put(\"188:0\", \"SPRUCE_FENCE\");\n" +
"        MAP.put(\"189:0\", \"BIRCH_FENCE\");\n" +
"        MAP.put(\"190:0\", \"JUNGLE_FENCE\");\n" +
"        MAP.put(\"191:0\", \"DARK_OAK_FENCE\");\n" +
"        MAP.put(\"192:0\", \"ACACIA_FENCE\");\n" +
"        MAP.put(\"193:0\", \"SPRUCE_DOOR\");\n" +
"        MAP.put(\"194:0\", \"BIRCH_DOOR\");\n" +
"        MAP.put(\"195:0\", \"JUNGLE_DOOR\");\n" +
"        MAP.put(\"196:0\", \"ACACIA_DOOR\");\n" +
"        MAP.put(\"197:0\", \"DARK_OAK_DOOR\");\n" +
"        MAP.put(\"198:0\", \"END_ROD\");\n" +
"        MAP.put(\"199:0\", \"CHORUS_PLANT\");\n" +
"        MAP.put(\"200:0\", \"CHORUS_FLOWER\");\n" +
"        MAP.put(\"201:0\", \"PURPUR_BLOCK\");\n" +
"        MAP.put(\"202:0\", \"PURPUR_PILLAR\");\n" +
"        MAP.put(\"203:0\", \"PURPUR_STAIRS\");\n" +
"        MAP.put(\"204:0\", \"PURPUR_SLAB\");\n" +
"        MAP.put(\"205:0\", \"PURPUR_SLAB\");\n" +
"        MAP.put(\"206:0\", \"END_STONE_BRICKS\");\n" +
"        MAP.put(\"207:0\", \"BEETROOT\");\n" +
"        MAP.put(\"208:0\", \"GRASS_PATH\");\n" +
"        MAP.put(\"209:0\", \"END_GATEWAY\");\n" +
"        MAP.put(\"210:0\", \"REPEATING_COMMAND_BLOCK\");\n" +
"        MAP.put(\"211:0\", \"CHAIN_COMMAND_BLOCK\");\n" +
"        MAP.put(\"212:0\", \"FROSTED_ICE\");\n" +
"        MAP.put(\"213:0\", \"MAGMA_BLOCK\");\n" +
"        MAP.put(\"214:0\", \"NETHER_WART_BLOCK\");\n" +
"        MAP.put(\"215:0\", \"RED_NETHER_BRICKS\");\n" +
"        MAP.put(\"216:0\", \"BONE_BLOCK\");\n" +
"        MAP.put(\"217:0\", \"STRUCTURE_VOID\");\n" +
"        MAP.put(\"218:0\", \"OBSERVER\");\n" +
"        MAP.put(\"219:0\", \"WHITE_SHULKER_BOX\");\n" +
"        MAP.put(\"220:0\", \"ORANGE_SHULKER_BOX\");\n" +
"        MAP.put(\"221:0\", \"MAGENTA_SHULKER_BOX\");\n" +
"        MAP.put(\"222:0\", \"LIGHT_BLUE_SHULKER_BOX\");\n" +
"        MAP.put(\"223:0\", \"YELLOW_SHULKER_BOX\");\n" +
"        MAP.put(\"224:0\", \"LIME_SHULKER_BOX\");\n" +
"        MAP.put(\"225:0\", \"PINK_SHULKER_BOX\");\n" +
"        MAP.put(\"226:0\", \"GRAY_SHULKER_BOX\");\n" +
"        MAP.put(\"227:0\", \"LIGHT_GRAY_SHULKER_BOX\");\n" +
"        MAP.put(\"228:0\", \"CYAN_SHULKER_BOX\");\n" +
"        MAP.put(\"229:0\", \"PURPLE_SHULKER_BOX\");\n" +
"        MAP.put(\"230:0\", \"BLUE_SHULKER_BOX\");\n" +
"        MAP.put(\"231:0\", \"BROWN_SHULKER_BOX\");\n" +
"        MAP.put(\"232:0\", \"GREEN_SHULKER_BOX\");\n" +
"        MAP.put(\"233:0\", \"RED_SHULKER_BOX\");\n" +
"        MAP.put(\"234:0\", \"BLACK_SHULKER_BOX\");\n" +
"        MAP.put(\"235:0\", \"WHITE_GLAZED_TERRACOTTA\");\n" +
"        MAP.put(\"236:0\", \"ORANGE_GLAZED_TERRACOTTA\");\n" +
"        MAP.put(\"237:0\", \"MAGENTA_GLAZED_TERRACOTTA\");\n" +
"        MAP.put(\"238:0\", \"LIGHT_BLUE_GLAZED_TERRACOTTA\");\n" +
"        MAP.put(\"239:0\", \"YELLOW_GLAZED_TERRACOTTA\");\n" +
"        MAP.put(\"240:0\", \"LIME_GLAZED_TERRACOTTA\");\n" +
"        MAP.put(\"241:0\", \"PINK_GLAZED_TERRACOTTA\");\n" +
"        MAP.put(\"242:0\", \"GRAY_GLAZED_TERRACOTTA\");\n" +
"        MAP.put(\"243:0\", \"LIGHT_GRAY_GLAZED_TERRACOTTA\");\n" +
"        MAP.put(\"244:0\", \"CYAN_GLAZED_TERRACOTTA\");\n" +
"        MAP.put(\"245:0\", \"PURPLE_GLAZED_TERRACOTTA\");\n" +
"        MAP.put(\"246:0\", \"BLUE_GLAZED_TERRACOTTA\");\n" +
"        MAP.put(\"247:0\", \"BROWN_GLAZED_TERRACOTTA\");\n" +
"        MAP.put(\"248:0\", \"GREEN_GLAZED_TERRACOTTA\");\n" +
"        MAP.put(\"249:0\", \"RED_GLAZED_TERRACOTTA\");\n" +
"        MAP.put(\"250:0\", \"BLACK_GLAZED_TERRACOTTA\");\n" +
"        MAP.put(\"251:0\", \"WHITE_CONCRETE\");\n" +
"        MAP.put(\"251:1\", \"ORANGE_CONCRETE\");\n" +
"        MAP.put(\"251:2\", \"MAGENTA_CONCRETE\");\n" +
"        MAP.put(\"251:3\", \"LIGHT_BLUE_CONCRETE\");\n" +
"        MAP.put(\"251:4\", \"YELLOW_CONCRETE\");\n" +
"        MAP.put(\"251:5\", \"LIME_CONCRETE\");\n" +
"        MAP.put(\"251:6\", \"PINK_CONCRETE\");\n" +
"        MAP.put(\"251:7\", \"GRAY_CONCRETE\");\n" +
"        MAP.put(\"251:8\", \"LIGHT_GRAY_CONCRETE\");\n" +
"        MAP.put(\"251:9\", \"CYAN_CONCRETE\");\n" +
"        MAP.put(\"251:10\", \"PURPLE_CONCRETE\");\n" +
"        MAP.put(\"251:11\", \"BLUE_CONCRETE\");\n" +
"        MAP.put(\"251:12\", \"BROWN_CONCRETE\");\n" +
"        MAP.put(\"251:13\", \"GREEN_CONCRETE\");\n" +
"        MAP.put(\"251:14\", \"RED_CONCRETE\");\n" +
"        MAP.put(\"251:15\", \"BLACK_CONCRETE\");\n" +
"        MAP.put(\"252:0\", \"WHITE_CONCRETE_POWDER\");\n" +
"        MAP.put(\"252:1\", \"ORANGE_CONCRETE_POWDER\");\n" +
"        MAP.put(\"252:2\", \"MAGENTA_CONCRETE_POWDER\");\n" +
"        MAP.put(\"252:3\", \"LIGHT_BLUE_CONCRETE_POWDER\");\n" +
"        MAP.put(\"252:4\", \"YELLOW_CONCRETE_POWDER\");\n" +
"        MAP.put(\"252:5\", \"LIME_CONCRETE_POWDER\");\n" +
"        MAP.put(\"252:6\", \"PINK_CONCRETE_POWDER\");\n" +
"        MAP.put(\"252:7\", \"GRAY_CONCRETE_POWDER\");\n" +
"        MAP.put(\"252:8\", \"LIGHT_GRAY_CONCRETE_POWDER\");\n" +
"        MAP.put(\"252:9\", \"CYAN_CONCRETE_POWDER\");\n" +
"        MAP.put(\"252:10\", \"PURPLE_CONCRETE_POWDER\");\n" +
"        MAP.put(\"252:11\", \"BLUE_CONCRETE_POWDER\");\n" +
"        MAP.put(\"252:12\", \"BROWN_CONCRETE_POWDER\");\n" +
"        MAP.put(\"252:13\", \"GREEN_CONCRETE_POWDER\");\n" +
"        MAP.put(\"252:14\", \"RED_CONCRETE_POWDER\");\n" +
"        MAP.put(\"252:15\", \"BLACK_CONCRETE_POWDER\");\n" +
"        MAP.put(\"255:0\", \"STRUCTURE_BLOCK\");\n" +
"        MAP.put(\"256:0\", \"IRON_SHOVEL\");\n" +
"        MAP.put(\"257:0\", \"IRON_PICKAXE\");\n" +
"        MAP.put(\"258:0\", \"IRON_AXE\");\n" +
"        MAP.put(\"259:0\", \"FLINT_AND_STEEL\");\n" +
"        MAP.put(\"260:0\", \"APPLE\");\n" +
"        MAP.put(\"261:0\", \"BOW\");\n" +
"        MAP.put(\"262:0\", \"ARROW\");\n" +
"        MAP.put(\"263:0\", \"COAL\");\n" +
"        MAP.put(\"263:1\", \"CHARCOAL\");\n" +
"        MAP.put(\"264:0\", \"DIAMOND\");\n" +
"        MAP.put(\"265:0\", \"IRON_INGOT\");\n" +
"        MAP.put(\"266:0\", \"GOLD_INGOT\");\n" +
"        MAP.put(\"267:0\", \"IRON_SWORD\");\n" +
"        MAP.put(\"268:0\", \"WOODEN_SWORD\");\n" +
"        MAP.put(\"269:0\", \"WOODEN_SHOVEL\");\n" +
"        MAP.put(\"270:0\", \"WOODEN_PICKAXE\");\n" +
"        MAP.put(\"271:0\", \"WOODEN_AXE\");\n" +
"        MAP.put(\"272:0\", \"STONE_SWORD\");\n" +
"        MAP.put(\"273:0\", \"STONE_SHOVEL\");\n" +
"        MAP.put(\"274:0\", \"STONE_PICKAXE\");\n" +
"        MAP.put(\"275:0\", \"STONE_AXE\");\n" +
"        MAP.put(\"276:0\", \"DIAMOND_SWORD\");\n" +
"        MAP.put(\"277:0\", \"DIAMOND_SHOVEL\");\n" +
"        MAP.put(\"278:0\", \"DIAMOND_PICKAXE\");\n" +
"        MAP.put(\"279:0\", \"DIAMOND_AXE\");\n" +
"        MAP.put(\"280:0\", \"STICK\");\n" +
"        MAP.put(\"281:0\", \"BOWL\");\n" +
"        MAP.put(\"282:0\", \"MUSHROOM_STEW\");\n" +
"        MAP.put(\"283:0\", \"GOLDEN_SWORD\");\n" +
"        MAP.put(\"284:0\", \"GOLDEN_SHOVEL\");\n" +
"        MAP.put(\"285:0\", \"GOLDEN_PICKAXE\");\n" +
"        MAP.put(\"286:0\", \"GOLDEN_AXE\");\n" +
"        MAP.put(\"287:0\", \"STRING\");\n" +
"        MAP.put(\"288:0\", \"FEATHER\");\n" +
"        MAP.put(\"289:0\", \"GUNPOWDER\");\n" +
"        MAP.put(\"290:0\", \"WOODEN_HOE\");\n" +
"        MAP.put(\"291:0\", \"STONE_HOE\");\n" +
"        MAP.put(\"292:0\", \"IRON_HOE\");\n" +
"        MAP.put(\"293:0\", \"DIAMOND_HOE\");\n" +
"        MAP.put(\"294:0\", \"GOLDEN_HOE\");\n" +
"        MAP.put(\"295:0\", \"WHEAT_SEEDS\");\n" +
"        MAP.put(\"296:0\", \"WHEAT\");\n" +
"        MAP.put(\"297:0\", \"BREAD\");\n" +
"        MAP.put(\"298:0\", \"LEATHER_HELMET\");\n" +
"        MAP.put(\"299:0\", \"LEATHER_CHESTPLATE\");\n" +
"        MAP.put(\"300:0\", \"LEATHER_LEGGINGS\");\n" +
"        MAP.put(\"301:0\", \"LEATHER_BOOTS\");\n" +
"        MAP.put(\"302:0\", \"CHAINMAIL_HELMET\");\n" +
"        MAP.put(\"303:0\", \"CHAINMAIL_CHESTPLATE\");\n" +
"        MAP.put(\"304:0\", \"CHAINMAIL_LEGGINGS\");\n" +
"        MAP.put(\"305:0\", \"CHAINMAIL_BOOTS\");\n" +
"        MAP.put(\"306:0\", \"IRON_HELMET\");\n" +
"        MAP.put(\"307:0\", \"IRON_CHESTPLATE\");\n" +
"        MAP.put(\"308:0\", \"IRON_LEGGINGS\");\n" +
"        MAP.put(\"309:0\", \"IRON_BOOTS\");\n" +
"        MAP.put(\"310:0\", \"DIAMOND_HELMET\");\n" +
"        MAP.put(\"311:0\", \"DIAMOND_CHESTPLATE\");\n" +
"        MAP.put(\"312:0\", \"DIAMOND_LEGGINGS\");\n" +
"        MAP.put(\"313:0\", \"DIAMOND_BOOTS\");\n" +
"        MAP.put(\"314:0\", \"GOLDEN_HELMET\");\n" +
"        MAP.put(\"315:0\", \"GOLDEN_CHESTPLATE\");\n" +
"        MAP.put(\"316:0\", \"GOLDEN_LEGGINGS\");\n" +
"        MAP.put(\"317:0\", \"GOLDEN_BOOTS\");\n" +
"        MAP.put(\"318:0\", \"FLINT\");\n" +
"        MAP.put(\"319:0\", \"PORKCHOP\");\n" +
"        MAP.put(\"320:0\", \"COOKED_PORKCHOP\");\n" +
"        MAP.put(\"321:0\", \"PAINTING\");\n" +
"        MAP.put(\"322:0\", \"GOLDEN_APPLE\");\n" +
"        MAP.put(\"322:1\", \"ENCHANTED_GOLDEN_APPLE\");\n" +
"        MAP.put(\"323:0\", \"SIGN\");\n" +
"        MAP.put(\"324:0\", \"OAK_DOOR\");\n" +
"        MAP.put(\"325:0\", \"BUCKET\");\n" +
"        MAP.put(\"326:0\", \"WATER_BUCKET\");\n" +
"        MAP.put(\"327:0\", \"LAVA_BUCKET\");\n" +
"        MAP.put(\"328:0\", \"MINECART\");\n" +
"        MAP.put(\"329:0\", \"SADDLE\");\n" +
"        MAP.put(\"330:0\", \"IRON_DOOR\");\n" +
"        MAP.put(\"331:0\", \"REDSTONE\");\n" +
"        MAP.put(\"332:0\", \"SNOWBALL\");\n" +
"        MAP.put(\"333:0\", \"OAK_BOAT\");\n" +
"        MAP.put(\"334:0\", \"LEATHER\");\n" +
"        MAP.put(\"335:0\", \"MILK_BUCKET\");\n" +
"        MAP.put(\"336:0\", \"BRICK\");\n" +
"        MAP.put(\"337:0\", \"CLAY\");\n" +
"        MAP.put(\"338:0\", \"SUGAR_CANE\");\n" +
"        MAP.put(\"339:0\", \"PAPER\");\n" +
"        MAP.put(\"340:0\", \"BOOK\");\n" +
"        MAP.put(\"341:0\", \"SLIME_BALL\");\n" +
"        MAP.put(\"342:0\", \"CHEST_MINECART\");\n" +
"        MAP.put(\"343:0\", \"FURNACE_MINECART\");\n" +
"        MAP.put(\"344:0\", \"EGG\");\n" +
"        MAP.put(\"345:0\", \"COMPASS\");\n" +
"        MAP.put(\"346:0\", \"FISHING_ROD\");\n" +
"        MAP.put(\"347:0\", \"CLOCK\");\n" +
"        MAP.put(\"348:0\", \"GLOWSTONE_DUST\");\n" +
"        MAP.put(\"349:0\", \"RAW_FISH\");\n" +
"        MAP.put(\"349:1\", \"RAW_SALMON\");\n" +
"        MAP.put(\"349:2\", \"TROPICAL_FISH\");\n" +
"        MAP.put(\"349:3\", \"PUFFERFISH\");\n" +
"        MAP.put(\"350:0\", \"COOKED_FISH\");\n" +
"        MAP.put(\"350:1\", \"COOKED_SALMON\");\n" +
"        MAP.put(\"351:0\", \"INK_SAC\");\n" +
"        MAP.put(\"351:1\", \"ROSE_RED\");\n" +
"        MAP.put(\"351:2\", \"CACTUS_GREEN\");\n" +
"        MAP.put(\"351:3\", \"COCOA_BEANS\");\n" +
"        MAP.put(\"351:4\", \"LAPIS_LAZULI\");\n" +
"        MAP.put(\"351:5\", \"PURPLE_DYE\");\n" +
"        MAP.put(\"351:6\", \"CYAN_DYE\");\n" +
"        MAP.put(\"351:7\", \"LIGHT_GRAY_DYE\");\n" +
"        MAP.put(\"351:8\", \"GRAY_DYE\");\n" +
"        MAP.put(\"351:9\", \"PINK_DYE\");\n" +
"        MAP.put(\"351:10\", \"LIME_DYE\");\n" +
"        MAP.put(\"351:11\", \"DANDELION_YELLOW\");\n" +
"        MAP.put(\"351:12\", \"LIGHT_BLUE_DYE\");\n" +
"        MAP.put(\"351:13\", \"MAGENTA_DYE\");\n" +
"        MAP.put(\"351:14\", \"ORANGE_DYE\");\n" +
"        MAP.put(\"351:15\", \"BONE_MEAL\");\n" +
"        MAP.put(\"352:0\", \"BONE\");\n" +
"        MAP.put(\"353:0\", \"SUGAR\");\n" +
"        MAP.put(\"354:0\", \"CAKE\");\n" +
"        MAP.put(\"355:0\", \"BED\");\n" +
"        MAP.put(\"356:0\", \"REPEATER\");\n" +
"        MAP.put(\"357:0\", \"COOKIE\");\n" +
"        MAP.put(\"358:0\", \"MAP\");\n" +
"        MAP.put(\"359:0\", \"SHEARS\");\n" +
"        MAP.put(\"360:0\", \"MELON\");\n" +
"        MAP.put(\"361:0\", \"PUMPKIN_SEEDS\");\n" +
"        MAP.put(\"362:0\", \"MELON_SEEDS\");\n" +
"        MAP.put(\"363:0\", \"BEEF\");\n" +
"        MAP.put(\"364:0\", \"COOKED_BEEF\");\n" +
"        MAP.put(\"365:0\", \"RAW_CHICKEN\");\n" +
"        MAP.put(\"366:0\", \"COOKED_CHICKEN\");\n" +
"        MAP.put(\"367:0\", \"ROTTEN_FLESH\");\n" +
"        MAP.put(\"368:0\", \"ENDER_PEARL\");\n" +
"        MAP.put(\"369:0\", \"BLAZE_ROD\");\n" +
"        MAP.put(\"370:0\", \"GHAST_TEAR\");\n" +
"        MAP.put(\"371:0\", \"GOLD_NUGGET\");\n" +
"        MAP.put(\"372:0\", \"NETHER_WART\");\n" +
"        MAP.put(\"373:0\", \"POTION\");\n" +
"        MAP.put(\"374:0\", \"GLASS_BOTTLE\");\n" +
"        MAP.put(\"375:0\", \"SPIDER_EYE\");\n" +
"        MAP.put(\"376:0\", \"FERMENTED_SPIDER_EYE\");\n" +
"        MAP.put(\"377:0\", \"BLAZE_POWDER\");\n" +
"        MAP.put(\"378:0\", \"MAGMA_CREAM\");\n" +
"        MAP.put(\"379:0\", \"BREWING_STAND\");\n" +
"        MAP.put(\"380:0\", \"CAULDRON\");\n" +
"        MAP.put(\"381:0\", \"ENDER_EYE\");\n" +
"        MAP.put(\"382:0\", \"GLISTERING_MELON_SLICE\");\n" +
"        MAP.put(\"383:4\", \"ELDER_GUARDIAN_SPAWN_EGG\");\n" +
"        MAP.put(\"383:5\", \"WITHER_SPAWN_EGG\");\n" +
"        MAP.put(\"383:6\", \"STRAY_SPAWN_EGG\");\n" +
"        MAP.put(\"383:23\", \"HUSK_SPAWN_EGG\");\n" +
"        MAP.put(\"383:27\", \"ZOMBIE_VILLAGER_SPAWN_EGG\");\n" +
"        MAP.put(\"383:28\", \"SKELETON_HORSE_SPAWN_EGG\");\n" +
"        MAP.put(\"383:29\", \"ZOMBIE_HORSE_SPAWN_EGG\");\n" +
"        MAP.put(\"383:31\", \"DONKEY_SPAWN_EGG\");\n" +
"        MAP.put(\"383:32\", \"MULE_SPAWN_EGG\");\n" +
"        MAP.put(\"383:34\", \"ENVOKER_SPAWN_EGG\");\n" +
"        MAP.put(\"383:35\", \"VEX_SPAWN_EGG\");\n" +
"        MAP.put(\"383:36\", \"VINDICATOR_SPAWN_EGG\");\n" +
"        MAP.put(\"383:50\", \"CREEPER_SPAWN_EGG\");\n" +
"        MAP.put(\"383:51\", \"SKELETON_SPAWN_EGG\");\n" +
"        MAP.put(\"383:52\", \"SPIDER_SPAWN_EGG\");\n" +
"        MAP.put(\"383:54\", \"ZOMBIE_SPAWN_EGG\");\n" +
"        MAP.put(\"383:55\", \"SLIME_SPAWN_EGG\");\n" +
"        MAP.put(\"383:56\", \"GHAST_SPAWN_EGG\");\n" +
"        MAP.put(\"383:57\", \"ZOMBIE_PIGMAN_SPAWN_EGG\");\n" +
"        MAP.put(\"383:58\", \"ENDERMAN_SPAWN_EGG\");\n" +
"        MAP.put(\"383:59\", \"CAVE_SPIDER_SPAWN_EGG\");\n" +
"        MAP.put(\"383:60\", \"SILVERFISH_SPAWN_EGG\");\n" +
"        MAP.put(\"383:61\", \"BLAZE_SPAWN_EGG\");\n" +
"        MAP.put(\"383:62\", \"MAGMA_CUBE_SPAWN_EGG\");\n" +
"        MAP.put(\"383:65\", \"BAT_SPAWN_EGG\");\n" +
"        MAP.put(\"383:66\", \"WITCH_SPAWN_EGG\");\n" +
"        MAP.put(\"383:67\", \"ENDERMITE_SPAWN_EGG\");\n" +
"        MAP.put(\"383:68\", \"GUARDIAN_SPAWN_EGG\");\n" +
"        MAP.put(\"383:69\", \"SHULKER_SPAWN_EGG\");\n" +
"        MAP.put(\"383:90\", \"PIG_SPAWN_EGG\");\n" +
"        MAP.put(\"383:91\", \"SHEEP_SPAWN_EGG\");\n" +
"        MAP.put(\"383:92\", \"COW_SPAWN_EGG\");\n" +
"        MAP.put(\"383:93\", \"CHICKEN_SPAWN_EGG\");\n" +
"        MAP.put(\"383:94\", \"SQUID_SPAWN_EGG\");\n" +
"        MAP.put(\"383:95\", \"WOLF_SPAWN_EGG\");\n" +
"        MAP.put(\"383:96\", \"MOOSHROOM_SPAWN_EGG\");\n" +
"        MAP.put(\"383:98\", \"OCELOT_SPAWN_EGG\");\n" +
"        MAP.put(\"383:100\", \"HORSE_SPAWN_EGG\");\n" +
"        MAP.put(\"383:101\", \"RABBIT_SPAWN_EGG\");\n" +
"        MAP.put(\"383:102\", \"POLAR_BEAR_SPAWN_EGG\");\n" +
"        MAP.put(\"383:103\", \"LLAMA_SPAWN_EGG\");\n" +
"        MAP.put(\"383:105\", \"PARROT_SPAWN_EGG\");\n" +
"        MAP.put(\"383:120\", \"VILLAGER_SPAWN_EGG\");\n" +
"        MAP.put(\"384:0\", \"EXPERIENCE_BOTTLE\");\n" +
"        MAP.put(\"385:0\", \"FIRE_CHARGE\");\n" +
"        MAP.put(\"386:0\", \"KNOWLEGE_BOOK\");\n" +
"        MAP.put(\"387:0\", \"WRITTEN_BOOK\");\n" +
"        MAP.put(\"388:0\", \"EMERALD\");\n" +
"        MAP.put(\"389:0\", \"ITEM_FRAME\");\n" +
"        MAP.put(\"390:0\", \"FLOWER_POT\");\n" +
"        MAP.put(\"391:0\", \"CARROT\");\n" +
"        MAP.put(\"392:0\", \"POTATO\");\n" +
"        MAP.put(\"393:0\", \"BAKED_POTATO\");\n" +
"        MAP.put(\"394:0\", \"POISONOUS_POTATO\");\n" +
"        MAP.put(\"395:0\", \"MAP\");\n" +
"        MAP.put(\"396:0\", \"GOLDEN_CARROT\");\n" +
"        MAP.put(\"397:0\", \"SKELETON_HEAD\");\n" +
"        MAP.put(\"397:1\", \"WITHER_HEAD\");\n" +
"        MAP.put(\"397:2\", \"ZOMBIE_HEAD\");\n" +
"        MAP.put(\"397:3\", \"HUMAN_HEAD\");\n" +
"        MAP.put(\"397:4\", \"CREEPER_HEAD\");\n" +
"        MAP.put(\"397:5\", \"DRAGON_HEAD\");\n" +
"        MAP.put(\"398:0\", \"CARROT_ON_A_STICK\");\n" +
"        MAP.put(\"399:0\", \"NETHER_STAR\");\n" +
"        MAP.put(\"400:0\", \"PUMPKIN_PIE\");\n" +
"        MAP.put(\"401:0\", \"FIREWORK_ROCKET\");\n" +
"        MAP.put(\"402:0\", \"FIREWORK_STAR\");\n" +
"        MAP.put(\"403:0\", \"ENCHANTED_BOOK\");\n" +
"        MAP.put(\"404:0\", \"COMPARATOR\");\n" +
"        MAP.put(\"405:0\", \"NETHER_BRICK\");\n" +
"        MAP.put(\"406:0\", \"QUARTZ\");\n" +
"        MAP.put(\"407:0\", \"TNT_MINECART\");\n" +
"        MAP.put(\"408:0\", \"HOPPER_MINECART\");\n" +
"        MAP.put(\"409:0\", \"PRISMARINE_SHARD\");\n" +
"        MAP.put(\"410:0\", \"PRISMARINE_CRYSTALS\");\n" +
"        MAP.put(\"411:0\", \"RABBIT\");\n" +
"        MAP.put(\"412:0\", \"COOKED_RABBIT\");\n" +
"        MAP.put(\"413:0\", \"RABBIT_STEW\");\n" +
"        MAP.put(\"414:0\", \"RABBIT_FOOT\");\n" +
"        MAP.put(\"415:0\", \"RABBIT_HIDE\");\n" +
"        MAP.put(\"416:0\", \"ARMOR_STAND\");\n" +
"        MAP.put(\"417:0\", \"IRON_HORSE_ARMOR\");\n" +
"        MAP.put(\"418:0\", \"GOLDEN_HORSE_ARMOR\");\n" +
"        MAP.put(\"419:0\", \"DIAMOND_HORSE_ARMOR\");\n" +
"        MAP.put(\"420:0\", \"LEAD\");\n" +
"        MAP.put(\"421:0\", \"NAME_TAG\");\n" +
"        MAP.put(\"422:0\", \"COMMAND_BLOCK_MINECART\");\n" +
"        MAP.put(\"423:0\", \"MUTTON\");\n" +
"        MAP.put(\"424:0\", \"COOKED_MUTTON\");\n" +
"        MAP.put(\"425:0\", \"WHITE_BANNER\");\n" +
"        MAP.put(\"426:0\", \"END_CRYSTAL\");\n" +
"        MAP.put(\"427:0\", \"SPRUCE_DOOR\");\n" +
"        MAP.put(\"428:0\", \"BIRCH_DOOR\");\n" +
"        MAP.put(\"429:0\", \"JUNGLE_DOOR\");\n" +
"        MAP.put(\"430:0\", \"ACACIA_DOOR\");\n" +
"        MAP.put(\"431:0\", \"DARK_OAK_DOOR\");\n" +
"        MAP.put(\"432:0\", \"CHORUS_FRUIT\");\n" +
"        MAP.put(\"433:0\", \"POPPED_CHORUS_FRUIT\");\n" +
"        MAP.put(\"434:0\", \"BEETROOT\");\n" +
"        MAP.put(\"435:0\", \"BEETROOT_SEEDS\");\n" +
"        MAP.put(\"436:0\", \"BEETROOT_SOUP\");\n" +
"        MAP.put(\"437:0\", \"DRAGON_BREATH\");\n" +
"        MAP.put(\"438:0\", \"SPLASH_POTION\");\n" +
"        MAP.put(\"439:0\", \"SPECTRAL_ARROW\");\n" +
"        MAP.put(\"440:0\", \"TIPPED_ARROW\");\n" +
"        MAP.put(\"441:0\", \"LINGERING_POTION\");\n" +
"        MAP.put(\"442:0\", \"SHIELD\");\n" +
"        MAP.put(\"443:0\", \"ELYTRA\");\n" +
"        MAP.put(\"444:0\", \"SPRUCE_BOAT\");\n" +
"        MAP.put(\"445:0\", \"BIRCH_BOAT\");\n" +
"        MAP.put(\"446:0\", \"JUNGLE_BOAT\");\n" +
"        MAP.put(\"447:0\", \"ACACIA_BOAT\");\n" +
"        MAP.put(\"448:0\", \"DARK_OAK_BOAT\");\n" +
"        MAP.put(\"449:0\", \"TOTEM_OF_UNDYING\");\n" +
"        MAP.put(\"450:0\", \"SHULKER_SHELL\");\n" +
"        MAP.put(\"452:0\", \"IRON_NUGGET\");\n" +
"        MAP.put(\"453:0\", \"KNOWLEDGE_BOOK\");\n" +
"        MAP.put(\"2256:0\", \"MUSIC_DISK_13\");\n" +
"        MAP.put(\"2257:0\", \"MUSIC_DISK_CAT\");\n" +
"        MAP.put(\"2258:0\", \"MUSIC_DISK_BLOCKS\");\n" +
"        MAP.put(\"2259:0\", \"MUSIC_DISK_CHIRP\");\n" +
"        MAP.put(\"2260:0\", \"MUSIC_DISK_FAR\");\n" +
"        MAP.put(\"2261:0\", \"MUSIC_DISK_MALL\");\n" +
"        MAP.put(\"2262:0\", \"MUSIC_DISK_MELLOHI\");\n" +
"        MAP.put(\"2263:0\", \"MUSIC_DISK_STAL\");\n" +
"        MAP.put(\"2264:0\", \"MUSIC_DISK_STRAD\");\n" +
"        MAP.put(\"2265:0\", \"MUSIC_DISK_WARD\");\n" +
"        MAP.put(\"2266:0\", \"MUSIC_DISK_11\");\n" +
"        MAP.put(\"2267:0\", \"MUSIC_DISK_WAIT\");\n" +
"    }\n" +
"\n" +
"}\n" +
"")
                .build();
        spigot.addSnippet(matLib);
        
        
        
        
        
        
        // YML SNIPPETS
        
               Snippet defaultPlugin = new Snippet.Builder()
                .withName("Default plugin.yml")
                .withDescription("When Bukkit loads a plugin, it needs to know some basic information about it. It reads this information from a YAML file, 'plugin.yml'. This file consists of a set of attributes, each defined on a new line and with no indentation.")
                .withReturns("N/A")
                .withSnippet("name: ExamplePlugin\n" +
"main: path.to.your.main.class.Main\n" +
"version: 1.0\n" +
"author: Author name here\n" +
"description: An example description here.\n" +
"depend: Dependency\n" +
"softdepend: [Depedency1, Dependency2]\n" +
"permissions:\n" +
"    example.perm:\n" +
"        default: false\n" +
"        description: Grants access to ExamplePerm\n" +
"command:\n" +
"    exampleCommand:\n" +
"        description: Opens the exampleCommand GUI")
                .build();
        yml.addSnippet(defaultPlugin);

        

        mainInstance.categories.values().forEach((cat) -> {
            list.add(cat.getName());
        });
        mainInstance.lstCat.setItems(list);
    }

}
