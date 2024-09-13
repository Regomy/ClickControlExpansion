package me.rejomy.clickcontrol;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.rejomy.clickcontrol.data.PlayerData;
import me.rejomy.clickcontrol.manager.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class ClickControlExpansion extends PlaceholderExpansion {

    private final DataManager dataManager;

    public ClickControlExpansion() {
        ClickControl clickControl = new ClickControl();
        clickControl.init();

        dataManager = clickControl.dataManager;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "cc";
    }
    @Override
    public @NotNull String getAuthor() {
        return "rejomy";
    }
    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        int entityId = player.getEntityId();

        switch (params.toLowerCase(Locale.ENGLISH)) {
            case "lcps" -> {
                return "" + dataManager.getById(entityId).getLeftClicks();
            }

            case "rcps" -> {
                return "" + dataManager.getById(entityId).getRightClicks();
            }

            case "combo" -> {
                return "" + dataManager.getById(entityId).combo;
            }

            case "lcps_opponent" -> {
                PlayerData data = dataManager.getById(entityId);

                if (data.getOpponentData() != null) {
                    int cpsOpponent = data.getOpponentData().getLeftClicks();
                    return "" + cpsOpponent;
                }

                return "";
            }

            case "rcps_opponent" -> {
                PlayerData data = dataManager.getById(entityId);

                if (data.getOpponentData() != null) {
                    int cpsOpponent = data.getOpponentData().getRightClicks();
                    return "" + cpsOpponent;
                }

                return "";
            }
        }

        return params;
    }
}
