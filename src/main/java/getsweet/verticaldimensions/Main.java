package getsweet.verticaldimensions;

import getsweet.verticaldimensions.command.CommandManager;
import getsweet.verticaldimensions.listeners.*;
import getsweet.verticaldimensions.units.Config;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private Config config;

    @Override
    public void onEnable() {
        long startTime = System.currentTimeMillis();

        printBanner();

        getLogger().info(ChatColor.YELLOW + "▸ Загрузка конфигурации...");
        config = new Config(this);

        getLogger().info(ChatColor.YELLOW + "▸ Регистрация событий...");
        getServer().getPluginManager().registerEvents(new VoidFallListener(config), this);
        getServer().getPluginManager().registerEvents(new DimensionPortalListener(config), this);

        getLogger().info(ChatColor.YELLOW + "▸ Регистрация команд...");
        getCommand("vd").setExecutor(new CommandManager(this, config));

        long loadTime = System.currentTimeMillis() - startTime;

        getLogger().info(ChatColor.GREEN + "✓ Плагин успешно загружен за " + loadTime + "ms");
        getLogger().info(ChatColor.GRAY + "▸ Версия: " + ChatColor.WHITE + getDescription().getVersion());
        getLogger().info(ChatColor.GRAY + "▸ Автор: " + ChatColor.WHITE + String.join(", ", getDescription().getAuthors()));
        getLogger().info(ChatColor.GRAY + "▸ Миры: " + ChatColor.WHITE +
                config.OVERWORLD_NAME + ChatColor.GRAY + ", " +
                config.NETHER_NAME + ChatColor.GRAY + ", " +
                config.END_NAME);
    }

    @Override
    public void onDisable() {
        printShutdownBanner();
        getLogger().info(ChatColor.YELLOW + "▸ Сохранение данных...");
        getLogger().info(ChatColor.GREEN + "✓ Плагин успешно выключен");
    }

    private void printBanner() {
        getLogger().info(ChatColor.BLUE + "╔═══════════════════════════════════════════════╗");
        getLogger().info(ChatColor.BLUE + "║                                               ║");
        getLogger().info(ChatColor.BLUE + "║   " + ChatColor.AQUA + "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓" + ChatColor.BLUE + "   ║");
        getLogger().info(ChatColor.BLUE + "║   " + ChatColor.AQUA + "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓" + ChatColor.BLUE + "   ║");
        getLogger().info(ChatColor.BLUE + "║   " + ChatColor.WHITE + "          Vertical Dimensions           " + ChatColor.BLUE + "   ║");
        getLogger().info(ChatColor.BLUE + "║   " + ChatColor.GRAY + "         Vertical measurement plugin    " + ChatColor.BLUE + "   ║");
        getLogger().info(ChatColor.BLUE + "║   " + ChatColor.AQUA + "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓" + ChatColor.BLUE + "   ║");
        getLogger().info(ChatColor.BLUE + "║   " + ChatColor.AQUA + "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓" + ChatColor.BLUE + "   ║");
        getLogger().info(ChatColor.BLUE + "║                                               ║");
        getLogger().info(ChatColor.BLUE + "╚═══════════════════════════════════════════════╝");
    }

    private void printShutdownBanner() {
        getLogger().info(ChatColor.RED + "╔═══════════════════════════════════════════════╗");
        getLogger().info(ChatColor.RED + "║                                               ║");
        getLogger().info(ChatColor.RED + "║   " + ChatColor.DARK_RED + "✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗" + ChatColor.RED + "   ║");
        getLogger().info(ChatColor.RED + "║   " + ChatColor.WHITE + "          Vertical Dimensions           " + ChatColor.RED + "   ║");
        getLogger().info(ChatColor.RED + "║   " + ChatColor.GRAY + "             The plugin is disabled             " + ChatColor.RED + "   ║");
        getLogger().info(ChatColor.RED + "║   " + ChatColor.DARK_RED + "✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗ ✗" + ChatColor.RED + "   ║");
        getLogger().info(ChatColor.RED + "║                                               ║");
        getLogger().info(ChatColor.RED + "╚═══════════════════════════════════════════════╝");
    }

    public Config getPluginConfig() {
        return config;
    }
}