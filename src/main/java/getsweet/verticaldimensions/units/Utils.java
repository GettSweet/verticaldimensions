package getsweet.verticaldimensions.units;

import org.bukkit.ChatColor;

public class Utils {

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String getHeader() {
        return color("&b&m⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯&r &3&lVertical&b&lDimensions &b&m⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
    }

    public static String getFooter() {
        return color("&b&m⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
    }

    public static String formatTime(int ticks) {
        int seconds = ticks / 20;
        int minutes = seconds / 60;
        int hours = minutes / 60;

        if (hours > 0) {
            return hours + "ч " + (minutes % 60) + "м";
        } else if (minutes > 0) {
            return minutes + "м " + (seconds % 60) + "с";
        } else {
            return seconds + "с";
        }
    }
}