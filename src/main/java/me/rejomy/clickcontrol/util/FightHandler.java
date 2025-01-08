package me.rejomy.clickcontrol.util;

import lombok.experimental.UtilityClass;
import me.rejomy.clickcontrol.data.PlayerData;

@UtilityClass
public class FightHandler {

    public void handle(PlayerData attackerData, PlayerData victimData) {
        // Prevent hits on air and other mistakes.
        if (attackerData == null || victimData == null) {
            return;
        }

        // If our opponent is victim, we can increase combat data.
        if (attackerData.getOpponentData() == victimData) {
            // Increase attacker combo state.
            attackerData.combo++;

            // If our victim is fight with attacker, we should set his combo state to zero.
            if (victimData.getOpponentData() == attackerData) {
                victimData.combo = 0;
            }
        }
        // If this player is new our opponent we should set
        else {
            attackerData.setOpponent(victimData);

            /* If victim dont has an opponent we can set this to our attacker.
                Dont do this if its false, because else our victim can pvp with other player and we just loose his combo state.
             */
            if (victimData.getOpponentData() == null) {
                victimData.setOpponent(attackerData);
            }
        }
    }

}
