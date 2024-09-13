package me.rejomy.clickcontrol.listener;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.packettype.PacketTypeCommon;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import me.rejomy.clickcontrol.data.PlayerData;
import me.rejomy.clickcontrol.manager.DataManager;
import me.rejomy.clickcontrol.util.FightHandler;

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

        WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(event);

        if (wrapper.getAction() == WrapperPlayClientInteractEntity.InteractAction.ATTACK) {
            PlayerData data = dataManager.getByPlayer(event.getUser());
            PlayerData victimData = dataManager.getById(wrapper.getEntityId());
            FightHandler.handle(data, victimData);
        }
    }
}
