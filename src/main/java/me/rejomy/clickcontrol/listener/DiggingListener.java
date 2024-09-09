package me.rejomy.clickcontrol.listener;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.packettype.PacketTypeCommon;
import com.github.retrooper.packetevents.protocol.player.DiggingAction;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerDigging;
import me.rejomy.clickcontrol.data.PlayerData;
import me.rejomy.clickcontrol.manager.DataManager;

import java.sql.Wrapper;

/**
 * This listener include DIGGING packet.
 */
public class DiggingListener implements PacketListener {

    private final DataManager dataManager;

    public DiggingListener(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        PacketTypeCommon packetType = event.getPacketType();

        if (packetType != PacketType.Play.Client.PLAYER_DIGGING) {
            return;
        }

        PlayerData data = dataManager.getByPlayer(event.getUser());
        WrapperPlayClientPlayerDigging wrapper = new WrapperPlayClientPlayerDigging(event);

        if (wrapper.getAction() == DiggingAction.START_DIGGING) {
            data.digging = true;
        } else if (wrapper.getAction() == DiggingAction.CANCELLED_DIGGING || wrapper.getAction() == DiggingAction.FINISHED_DIGGING) {
            data.digging = false;
        }
    }

}
