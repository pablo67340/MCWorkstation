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
