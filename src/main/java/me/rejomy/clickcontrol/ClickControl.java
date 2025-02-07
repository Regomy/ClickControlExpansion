package me.rejomy.clickcontrol;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.protocol.player.User;
import me.rejomy.clickcontrol.listener.ClickListener;
import me.rejomy.clickcontrol.listener.ConnectionListener;
import me.rejomy.clickcontrol.listener.DiggingListener;
import me.rejomy.clickcontrol.listener.FightListener;
import me.rejomy.clickcontrol.manager.DataManager;

import java.util.Arrays;

public class ClickControl {

    DataManager dataManager;

    public void init() {
        dataManager = new DataManager();

        // Add online users from packet events to data manager.
        for (User user : PacketEvents.getAPI().getProtocolManager().getUsers()) {
            dataManager.add(user);
        }

        // Register listeners
        register(new ConnectionListener(dataManager));
        register(new ClickListener(dataManager));
        register(new FightListener(dataManager));
        register(new DiggingListener(dataManager));
    }

    private void register(PacketListener... listeners) {
        Arrays.stream(listeners).forEach(listener -> PacketEvents.getAPI().getEventManager()
                .registerListener(listener, PacketListenerPriority.LOWEST));
    }
}
