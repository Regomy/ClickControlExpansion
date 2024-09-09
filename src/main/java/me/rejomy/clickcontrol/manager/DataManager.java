package me.rejomy.clickcontrol.manager;

import com.github.retrooper.packetevents.protocol.player.User;
import me.rejomy.clickcontrol.data.PlayerData;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    public final List<PlayerData> DATA = new ArrayList<>();

    public PlayerData getByPlayer(Object uniqueValue) {
        return DATA.stream().filter(data -> data.player == uniqueValue).findAny().orElse(null);
    }

    public PlayerData getById(int entityId) {
        return DATA.stream().filter(data -> ((User) data.player).getEntityId() == entityId).findAny().orElse(null);
    }

    public void add(Object uniqueValue) {
        DATA.add(new PlayerData(uniqueValue));
    }

    public void remove(Object uniqueValue) {
        DATA.removeIf(data ->  data.player == uniqueValue);
    }
}
