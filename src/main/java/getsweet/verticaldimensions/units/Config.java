package getsweet.verticaldimensions.units;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config {
    private final JavaPlugin plugin;

    // Настройки миров
    public String OVERWORLD_NAME;
    public String NETHER_NAME;
    public String END_NAME;

    // Настройки высот
    public double END_TO_OVERWORLD_Y;
    public double NETHER_TO_OVERWORLD_Y;
    public double OVERWORLD_TO_NETHER_Y;
    public double OVERWORLD_TO_END_Y;

    // Настройки эффектов
    public int SLOWNESS_DURATION;
    public int SLOWNESS_AMPLIFIER;
    public int WEAKNESS_DURATION;
    public int WEAKNESS_AMPLIFIER;
    public int BLINDNESS_DURATION;
    public int BLINDNESS_AMPLIFIER;
    public int NAUSEA_DURATION;
    public int NAUSEA_AMPLIFIER;
    public int SLOW_FALLING_DURATION;
    public int SLOW_FALLING_AMPLIFIER;

    // Настройки эффектов для телепортации в Энд
    public int END_SLOWNESS_DURATION;
    public int END_SLOWNESS_AMPLIFIER;
    public int END_WEAKNESS_DURATION;
    public int END_WEAKNESS_AMPLIFIER;
    public int END_BLINDNESS_DURATION;
    public int END_BLINDNESS_AMPLIFIER;
    public int END_SLOW_FALLING_DURATION;
    public int END_SLOW_FALLING_AMPLIFIER;


    // Настройки порталов
    public boolean PREVENT_NETHER_PORTAL_ENTRY;
    public boolean PREVENT_END_PORTAL_ENTRY;
    public boolean PREVENT_NETHER_TO_END_PORTAL;
    public boolean PREVENT_END_TO_NETHER_PORTAL;
    public boolean PREVENT_ENDER_PEARL_NETHER;
    public boolean PREVENT_ENDER_PEARL_END;
    public boolean PREVENT_VEHICLE_NETHER;
    public boolean PREVENT_VEHICLE_END;

    // Настройки сообщений
    public boolean ENABLE_MESSAGES;
    public String PORTAL_BLOCKED_MESSAGE;
    public String END_PORTAL_BLOCKED_MESSAGE;
    public String NETHER_TO_END_BLOCKED_MESSAGE;
    public String END_TO_NETHER_BLOCKED_MESSAGE;
    public String VEHICLE_BLOCKED_MESSAGE;
    public String VEHICLE_END_BLOCKED_MESSAGE;

    public Config(JavaPlugin plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();

        plugin.getLogger().info(ChatColor.YELLOW + "▸ Создание конфигурационного файла...");

        // Устанавливаем значения по умолчанию
        config.addDefault("worlds.overworld", "world");
        config.addDefault("worlds.nether", "world_nether");
        config.addDefault("worlds.end", "world_the_end");

        config.addDefault("heights.end_to_overworld", -55.0);
        config.addDefault("heights.nether_to_overworld", 1000.0);
        config.addDefault("heights.overworld_to_nether", -90.0);
        config.addDefault("heights.overworld_to_end", 2000.0);

        config.addDefault("nether_portal.prevent_entry", true);
        config.addDefault("nether_portal.prevent_ender_pearl", true);
        config.addDefault("nether_portal.prevent_vehicle_with_player", true);

        config.addDefault("portal_restrictions.prevent_nether_entry", true);
        config.addDefault("portal_restrictions.prevent_end_entry", true);
        config.addDefault("portal_restrictions.prevent_nether_to_end", true);
        config.addDefault("portal_restrictions.prevent_end_to_nether", true);
        config.addDefault("portal_restrictions.prevent_ender_pearl_nether", true);
        config.addDefault("portal_restrictions.prevent_ender_pearl_end", true);
        config.addDefault("portal_restrictions.prevent_vehicle_nether", true);
        config.addDefault("portal_restrictions.prevent_vehicle_end", true);
        config.addDefault("portal_restrictions.enable_messages", true);

        // Эффекты для обычной телепортации
        config.addDefault("effects.slowness.duration", 2400);
        config.addDefault("effects.slowness.amplifier", 3);
        config.addDefault("effects.weakness.duration", 24000);
        config.addDefault("effects.weakness.amplifier", 9);
        config.addDefault("effects.blindness.duration", 3600);
        config.addDefault("effects.blindness.amplifier", 0);
        config.addDefault("effects.nausea.duration", 6000);
        config.addDefault("effects.nausea.amplifier", 0);
        config.addDefault("effects.slow_falling.duration", 1000);
        config.addDefault("effects.slow_falling.amplifier", 0);

        // Эффекты для телепортации в Энд
        config.addDefault("effects_end.slowness.duration", 2400);
        config.addDefault("effects_end.slowness.amplifier", 3);
        config.addDefault("effects_end.weakness.duration", 24000);
        config.addDefault("effects_end.weakness.amplifier", 9);
        config.addDefault("effects_end.blindness.duration", 1200);
        config.addDefault("effects_end.blindness.amplifier", 0);
        config.addDefault("effects_end.slow_falling.duration", 1000);
        config.addDefault("effects_end.slow_falling.amplifier", 0);

        // Настройки сообщений
        config.addDefault("messages.enable", true);
        config.addDefault("messages.portal_blocked", "§cПортал в ад заблокирован! Используйте вертикальную телепортацию.");
        config.addDefault("messages.end_portal_blocked", "§cПортал в Энд заблокирован! Используйте вертикальную телепортацию.");
        config.addDefault("messages.nether_to_end_blocked", "§cПортал из ада в Энд заблокирован!");
        config.addDefault("messages.end_to_nether_blocked", "§cПортал из Энда в ад заблокирован!");
        config.addDefault("messages.vehicle_blocked", "§cТранспорт не может пройти через портал в ад!");
        config.addDefault("messages.vehicle_end_blocked", "§cТранспорт не может пройти через портал в Энд!");

        config.options().copyDefaults(true);
        plugin.saveConfig();

        plugin.getLogger().info(ChatColor.GREEN + "✓ Конфигурационный файл создан/загружен");

        // Загружаем значения из конфига
        OVERWORLD_NAME = config.getString("worlds.overworld");
        NETHER_NAME = config.getString("worlds.nether");
        END_NAME = config.getString("worlds.end");

        END_TO_OVERWORLD_Y = config.getDouble("heights.end_to_overworld");
        NETHER_TO_OVERWORLD_Y = config.getDouble("heights.nether_to_overworld");
        OVERWORLD_TO_NETHER_Y = config.getDouble("heights.overworld_to_nether");
        OVERWORLD_TO_END_Y = config.getDouble("heights.overworld_to_end");

        PREVENT_NETHER_PORTAL_ENTRY = config.getBoolean("nether_portal.prevent_entry");
        PREVENT_ENDER_PEARL_NETHER = config.getBoolean("nether_portal.prevent_ender_pearl");
        PREVENT_VEHICLE_NETHER = config.getBoolean("nether_portal.prevent_vehicle_with_player");

        // Настройки порталов
        PREVENT_NETHER_PORTAL_ENTRY = config.getBoolean("portal_restrictions.prevent_nether_entry");
        PREVENT_END_PORTAL_ENTRY = config.getBoolean("portal_restrictions.prevent_end_entry");
        PREVENT_NETHER_TO_END_PORTAL = config.getBoolean("portal_restrictions.prevent_nether_to_end");
        PREVENT_END_TO_NETHER_PORTAL = config.getBoolean("portal_restrictions.prevent_end_to_nether");
        PREVENT_ENDER_PEARL_NETHER = config.getBoolean("portal_restrictions.prevent_ender_pearl_nether");
        PREVENT_ENDER_PEARL_END = config.getBoolean("portal_restrictions.prevent_ender_pearl_end");
        PREVENT_VEHICLE_NETHER = config.getBoolean("portal_restrictions.prevent_vehicle_nether");
        PREVENT_VEHICLE_END = config.getBoolean("portal_restrictions.prevent_vehicle_end");
        ENABLE_MESSAGES = config.getBoolean("portal_restrictions.enable_messages");

        // Эффекты
        SLOWNESS_DURATION = config.getInt("effects.slowness.duration");
        SLOWNESS_AMPLIFIER = config.getInt("effects.slowness.amplifier");
        WEAKNESS_DURATION = config.getInt("effects.weakness.duration");
        WEAKNESS_AMPLIFIER = config.getInt("effects.weakness.amplifier");
        BLINDNESS_DURATION = config.getInt("effects.blindness.duration");
        BLINDNESS_AMPLIFIER = config.getInt("effects.blindness.amplifier");
        NAUSEA_DURATION = config.getInt("effects.nausea.duration");
        NAUSEA_AMPLIFIER = config.getInt("effects.nausea.amplifier");
        SLOW_FALLING_DURATION = config.getInt("effects.slow_falling.duration");
        SLOW_FALLING_AMPLIFIER = config.getInt("effects.slow_falling.amplifier");

        // Эффекты для Энда
        END_SLOWNESS_DURATION = config.getInt("effects_end.slowness.duration");
        END_SLOWNESS_AMPLIFIER = config.getInt("effects_end.slowness.amplifier");
        END_WEAKNESS_DURATION = config.getInt("effects_end.weakness.duration");
        END_WEAKNESS_AMPLIFIER = config.getInt("effects_end.weakness.amplifier");
        END_BLINDNESS_DURATION = config.getInt("effects_end.blindness.duration");
        END_BLINDNESS_AMPLIFIER = config.getInt("effects_end.blindness.amplifier");
        END_SLOW_FALLING_DURATION = config.getInt("effects_end.slow_falling.duration");
        END_SLOW_FALLING_AMPLIFIER = config.getInt("effects_end.slow_falling.amplifier");

        // Сообщения
        ENABLE_MESSAGES = config.getBoolean("messages.enable");
        PORTAL_BLOCKED_MESSAGE = config.getString("messages.portal_blocked");
        END_PORTAL_BLOCKED_MESSAGE = config.getString("messages.end_portal_blocked");
        NETHER_TO_END_BLOCKED_MESSAGE = config.getString("messages.nether_to_end_blocked");
        END_TO_NETHER_BLOCKED_MESSAGE = config.getString("messages.end_to_nether_blocked");
        VEHICLE_BLOCKED_MESSAGE = config.getString("messages.vehicle_blocked");
        VEHICLE_END_BLOCKED_MESSAGE = config.getString("messages.vehicle_end_blocked");

        // Логируем загруженные настройки
        plugin.getLogger().info(ChatColor.GRAY + "  • Миры: " + ChatColor.WHITE +
                OVERWORLD_NAME + ChatColor.GRAY + ", " + NETHER_NAME + ChatColor.GRAY + ", " + END_NAME);
        plugin.getLogger().info(ChatColor.GRAY + "  • Высоты телепортации загружены");
        plugin.getLogger().info(ChatColor.GRAY + "  • Эффекты настроены");
        plugin.getLogger().info(ChatColor.GRAY + "  • Сообщения: " + (ENABLE_MESSAGES ? ChatColor.GREEN + "включены" : ChatColor.RED + "выключены"));
    }
}