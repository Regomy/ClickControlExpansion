package me.rejomy.clickcontrol.listener;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.packettype.PacketTypeCommon;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import me.rejomy.clickcontrol.data.PlayerData;
import me.rejomy.clickcontrol.manager.DataManager;

/**
 * This listener include INTERACT_ENTITY packet.
 */
public class FightListener implements PacketListener {

    private final DataManager dataManager;

    public FightListener(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        PacketTypeCommon packetType = event.getPacketType();

        if (packetType != PacketType.Play.Client.INTERACT_ENTITY) {
            return;
        }

        PlayerData data = dataManager.getByPlayer(event.getUser());
        WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(event);

        if (wrapper.getAction() == WrapperPlayClientInteractEntity.InteractAction.ATTACK) {
            PlayerData victimData = dataManager.DATA.stream()
                    .filter(targetData -> ((User) targetData.player).getEntityId() == wrapper.getEntityId())
                    .findAny()
                    .orElse(null);

            if (victimData != null) {
                // If player is fight with victim, we should check that player hit only victim for control combo.
                if (data.opponent == victimData.player) {
                    data.combo++;

                    if (victimData.opponent == data.player) {
                        victimData.combo = -data.combo;
                    }
                } else {
                    data.combo = 0;
                    data.opponent = victimData.player;
                    data.opponentData = victimData;
                }

                // We set victim opponent if he is null when our player hit victim.
                if (victimData.opponent == null) {
                    victimData.combo = 0;
                    victimData.opponent = data.player;

                    data.opponentData = victimData;
                }
            }

        }
    }
}
