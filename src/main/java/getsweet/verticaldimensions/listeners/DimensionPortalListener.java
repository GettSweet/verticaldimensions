package getsweet.verticaldimensions.listeners;

import getsweet.verticaldimensions.units.Config;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.player.PlayerPortalEvent;

public class DimensionPortalListener implements Listener {

    private final Config config;

    public DimensionPortalListener(Config config) {
        this.config = config;
    }

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        Player player = event.getPlayer();
        World fromWorld = event.getFrom().getWorld();
        World toWorld = event.getTo() != null ? event.getTo().getWorld() : null;

        if (fromWorld == null || toWorld == null) {
            return;
        }

        // Запрещаем вход в портал из обычного мира в ад
        if (config.PREVENT_NETHER_PORTAL_ENTRY &&
                fromWorld.getEnvironment() == World.Environment.NORMAL &&
                toWorld.getEnvironment() == World.Environment.NETHER) {

            event.setCancelled(true);
            if (config.ENABLE_MESSAGES) {
                player.sendMessage(config.PORTAL_BLOCKED_MESSAGE);
            }
            return;
        }

        // Запрещаем вход в портал из обычного мира в Энд
        if (config.PREVENT_END_PORTAL_ENTRY &&
                fromWorld.getEnvironment() == World.Environment.NORMAL &&
                toWorld.getEnvironment() == World.Environment.THE_END) {

            event.setCancelled(true);
            if (config.ENABLE_MESSAGES) {
                player.sendMessage(config.END_PORTAL_BLOCKED_MESSAGE);
            }
            return;
        }

        // Запрещаем вход в портал из ада в Энд
        if (config.PREVENT_NETHER_TO_END_PORTAL &&
                fromWorld.getEnvironment() == World.Environment.NETHER &&
                toWorld.getEnvironment() == World.Environment.THE_END) {

            event.setCancelled(true);
            if (config.ENABLE_MESSAGES) {
                player.sendMessage(config.NETHER_TO_END_BLOCKED_MESSAGE);
            }
            return;
        }

        // Запрещаем вход в портал из Энда в ад
        if (config.PREVENT_END_TO_NETHER_PORTAL &&
                fromWorld.getEnvironment() == World.Environment.THE_END &&
                toWorld.getEnvironment() == World.Environment.NETHER) {

            event.setCancelled(true);
            if (config.ENABLE_MESSAGES) {
                player.sendMessage(config.END_TO_NETHER_BLOCKED_MESSAGE);
            }
        }
    }

    @EventHandler
    public void onEntityPortal(EntityPortalEvent event) {
        Entity entity = event.getEntity();
        World fromWorld = event.getFrom().getWorld();
        World toWorld = event.getTo() != null ? event.getTo().getWorld() : null;

        if (fromWorld == null || toWorld == null) {
            return;
        }

        // Запрещаем эндерперлы из обычного мира в ад
        if (config.PREVENT_ENDER_PEARL_NETHER &&
                entity.getType() == EntityType.ENDER_PEARL &&
                fromWorld.getEnvironment() == World.Environment.NORMAL &&
                toWorld.getEnvironment() == World.Environment.NETHER) {

            event.setCancelled(true);
            return;
        }

        // Запрещаем эндерперлы из обычного мира в Энд
        if (config.PREVENT_ENDER_PEARL_END &&
                entity.getType() == EntityType.ENDER_PEARL &&
                fromWorld.getEnvironment() == World.Environment.NORMAL &&
                toWorld.getEnvironment() == World.Environment.THE_END) {

            event.setCancelled(true);
            return;
        }

        // Запрещаем транспорт с игроками из обычного мира в ад
        if (config.PREVENT_VEHICLE_NETHER &&
                fromWorld.getEnvironment() == World.Environment.NORMAL &&
                toWorld.getEnvironment() == World.Environment.NETHER) {

            if (isVehicleWithPlayer(entity)) {
                event.setCancelled(true);

                // Уведомляем игроков в транспорте
                if (config.ENABLE_MESSAGES) {
                    for (Entity passenger : entity.getPassengers()) {
                        if (passenger instanceof Player) {
                            passenger.sendMessage(config.VEHICLE_BLOCKED_MESSAGE);
                        }
                    }
                }
            }
        }

        // Запрещаем транспорт с игроками из обычного мира в Энд
        if (config.PREVENT_VEHICLE_END &&
                fromWorld.getEnvironment() == World.Environment.NORMAL &&
                toWorld.getEnvironment() == World.Environment.THE_END) {

            if (isVehicleWithPlayer(entity)) {
                event.setCancelled(true);

                // Уведомляем игроков в транспорте
                if (config.ENABLE_MESSAGES) {
                    for (Entity passenger : entity.getPassengers()) {
                        if (passenger instanceof Player) {
                            passenger.sendMessage(config.VEHICLE_END_BLOCKED_MESSAGE);
                        }
                    }
                }
            }
        }
    }

    private boolean isVehicleWithPlayer(Entity entity) {
        // Проверяем, является ли entity транспортным средством с игроком
        if (!entity.getPassengers().isEmpty()) {
            for (Entity passenger : entity.getPassengers()) {
                if (passenger instanceof Player) {
                    return isBlockedVehicleType(entity.getType());
                }
            }
        }
        return false;
    }

    private boolean isBlockedVehicleType(EntityType type) {
        // Проверяем все типы транспортных средств
        String typeName = type.name().toLowerCase();

        // Проверка на лодки (содержит "boat" в названии)
        if (typeName.contains("boat")) {
            return true;
        }

        // Проверка на вагонетки (содержит "minecart" в названии)
        if (typeName.contains("minecart")) {
            return true;
        }

        // Проверка конкретных типов транспорта
        switch (type) {
            case CAMEL:
            case HORSE:
            case DONKEY:
            case MULE:
            case LLAMA:
            case SKELETON_HORSE:
            case TRADER_LLAMA:
            case ZOMBIE_HORSE:
            case PIG:
            case STRIDER:
            case RAVAGER:
                return true;
            default:
                return false;
        }
    }
}