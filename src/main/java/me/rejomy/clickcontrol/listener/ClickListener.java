package me.rejomy.clickcontrol.listener;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.packettype.PacketTypeCommon;
import me.rejomy.clickcontrol.data.PlayerData;
import me.rejomy.clickcontrol.manager.DataManager;

/**
 * This listener include SWING_ITEM and BLOCK_PLACE packets.
 */
public class ClickListener implements PacketListener {

    private final DataManager dataManager;

    public ClickListener(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        PacketTypeCommon packetType = event.getPacketType();

        boolean isRightClick = packetType == PacketType.Play.Client.PLAYER_BLOCK_PLACEMENT;
        boolean isLeftClick = packetType == PacketType.Play.Client.ANIMATION;

        if (!isLeftClick && !isRightClick) {
            return;
        }

        PlayerData data = dataManager.getByPlayer(event.getUser());

        if (isRightClick) {
            data.rightClicks.add(event.getTimestamp());
        } else {
            // Check if player send animation while breaking block.
            if (data.digging) {
                return;
            }

            data.leftClicks.add(event.getTimestamp());
        }
    }
}
