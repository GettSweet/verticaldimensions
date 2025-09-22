package getsweet.verticaldimensions.listeners;

import getsweet.verticaldimensions.units.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class VoidFallListener implements Listener {

    private final Config config;

    public VoidFallListener(Config config) {
        this.config = config;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        World world = player.getWorld();
        double y = location.getY();

        // Получение текущего вектора скорости
        Vector velocity = player.getVelocity();

        // Проверка условий для телепортации из энда в обычный мир
        if (world.getEnvironment() == World.Environment.THE_END && y < config.END_TO_OVERWORLD_Y) {
            Location newLocation = new Location(Bukkit.getWorld(config.OVERWORLD_NAME), location.getX(), 500, location.getZ());
            player.teleport(newLocation);
            player.setVelocity(velocity); // Установка сохранённой скорости
            applyEffects(player);
        }

        // Проверка условий для телепортации из ада в обычный мир
        if (world.getEnvironment() == World.Environment.NETHER && y > config.NETHER_TO_OVERWORLD_Y) {
            World overworld = Bukkit.getWorld(config.OVERWORLD_NAME);
            if (overworld != null) {
                Location newLocation = new Location(overworld, location.getX() * 8, -67, location.getZ() * 8);
                player.teleport(newLocation);
                player.setVelocity(velocity);
                applyEffects(player);
            }
        }
        // Проверка условий для телепортации из обычного мира в энд
        if (world.getEnvironment() == World.Environment.NORMAL && y < config.OVERWORLD_TO_NETHER_Y) {
            World nether = Bukkit.getWorld(config.NETHER_NAME);
            if (nether != null) {
                Location newLocation = new Location(nether, location.getX() / 8, 500, location.getZ() / 8);
                player.teleport(newLocation);
                player.setVelocity(velocity);
                applyEffects(player);
            }
        }

        // Проверка условий для телепортации в мир края, если игрок выше определенной высоты
        if (world.getEnvironment() == World.Environment.NORMAL && y > config.OVERWORLD_TO_END_Y) {
            World endWorld = Bukkit.getWorld(config.END_NAME);
            if (endWorld != null) {
                Location newLocation = new Location(endWorld, location.getX(), 70, location.getZ());
                player.teleport(newLocation);
                player.setVelocity(velocity);
                applyEffectsEND(player);
            }
        }
    }

    private void applyEffectsEND(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,
                config.END_SLOWNESS_DURATION, config.END_SLOWNESS_AMPLIFIER));
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,
                config.END_WEAKNESS_DURATION, config.END_WEAKNESS_AMPLIFIER));
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,
                config.END_BLINDNESS_DURATION, config.END_BLINDNESS_AMPLIFIER));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,
                config.END_SLOW_FALLING_DURATION, config.END_SLOW_FALLING_AMPLIFIER));
    }

    private void applyEffects(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,
                config.SLOWNESS_DURATION, config.SLOWNESS_AMPLIFIER));
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,
                config.WEAKNESS_DURATION, config.WEAKNESS_AMPLIFIER));
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,
                config.BLINDNESS_DURATION, config.BLINDNESS_AMPLIFIER));
        player.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA,
                config.NAUSEA_DURATION, config.NAUSEA_AMPLIFIER));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,
                config.SLOW_FALLING_DURATION, config.SLOW_FALLING_AMPLIFIER));
    }
}