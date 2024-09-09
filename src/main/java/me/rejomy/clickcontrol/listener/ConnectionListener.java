package me.rejomy.clickcontrol.listener;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.UserDisconnectEvent;
import com.github.retrooper.packetevents.event.UserLoginEvent;
import me.rejomy.clickcontrol.manager.DataManager;

public class ConnectionListener implements PacketListener {

    private final DataManager dataManager;

    public ConnectionListener(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void onUserLogin(UserLoginEvent event) {
        dataManager.add(event.getUser());
    }

    @Override
    public void onUserDisconnect(UserDisconnectEvent event) {
        dataManager.remove(event.getUser());
    }
}
