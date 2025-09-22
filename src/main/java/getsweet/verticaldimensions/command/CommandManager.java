package getsweet.verticaldimensions.command;

import getsweet.verticaldimensions.Main;
import getsweet.verticaldimensions.units.Config;
import getsweet.verticaldimensions.units.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandManager implements CommandExecutor {

    private final Main plugin;
    private final Config config;

    public CommandManager(Main plugin, Config config) {
        this.plugin = plugin;
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sendInfo(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                if (sender.hasPermission("verticaldimensions.reload")) {
                    config.loadConfig();
                    sender.sendMessage(Utils.color("&a✓ Конфигурация успешно перезагружена!"));
                } else {
                    sender.sendMessage(Utils.color("&c✗ У вас нет прав для этой команды!"));
                }
                return true;

            case "info":
                sendInfo(sender);
                return true;

            case "help":
                sendHelp(sender);
                return true;

            default:
                sender.sendMessage(Utils.color("&cНеизвестная команда. Используйте &6/vd help&c."));
                return true;
        }
    }

    private void sendInfo(CommandSender sender) {
        sender.sendMessage(Utils.getHeader());
        sender.sendMessage(Utils.color(" &7Версия: &b" + plugin.getDescription().getVersion()));
        sender.sendMessage(Utils.color(" &7Автор: &6" + String.join(", ", plugin.getDescription().getAuthors())));
        sender.sendMessage("");
        sender.sendMessage(Utils.color(" &b• &fМиры:"));
        sender.sendMessage(Utils.color("   &7Обычный: &a" + config.OVERWORLD_NAME));
        sender.sendMessage(Utils.color("   &7Незер: &c" + config.NETHER_NAME));
        sender.sendMessage(Utils.color("   &7Энд: &5" + config.END_NAME));
        sender.sendMessage("");
        sender.sendMessage(Utils.color(" &b• &fВысоты телепортации:"));
        sender.sendMessage(Utils.color("   &7Энд → Обычный: &e" + config.END_TO_OVERWORLD_Y));
        sender.sendMessage(Utils.color("   &7Незер → Обычный: &e" + config.NETHER_TO_OVERWORLD_Y));
        sender.sendMessage(Utils.color("   &7Обычный → Незер: &e" + config.OVERWORLD_TO_NETHER_Y));
        sender.sendMessage(Utils.color("   &7Обычный → Энд: &e" + config.OVERWORLD_TO_END_Y));
        sender.sendMessage(Utils.getFooter());
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage(Utils.getHeader());
        sender.sendMessage(Utils.color(" &b/vd &7- Основная информация о плагине"));
        sender.sendMessage(Utils.color(" &b/vd reload &7- Перезагрузить конфигурацию"));
        sender.sendMessage(Utils.color(" &b/vd info &7- Подробная информация"));
        sender.sendMessage(Utils.color(" &b/vd help &7- Помощь по командам"));
        sender.sendMessage(Utils.getFooter());
    }
}