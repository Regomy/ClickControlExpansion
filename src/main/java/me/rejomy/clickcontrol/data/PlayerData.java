package me.rejomy.clickcontrol.data;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
public class PlayerData {

    private final static int MILLIS_IN_SECOND = 1000;

    @NotNull
    public final Object player;
    public Object opponent;

    /**
     * Use this value only with opponent != null, because we dont delete opponentData when opponent is leave from the server.
     */
    public PlayerData opponentData;

    public final List<Long> rightClicks = new ArrayList<>();
    public final List<Long> leftClicks = new ArrayList<>();

    public int combo = 0;

    /**
     * Check if player breaking block, because we handle swing animation as left clicks per second
     *  and when player start to break block, he is send this animation.
     */
    public boolean digging;

    public int getLeftClicks() {
        filterClicks(leftClicks);

        return leftClicks.size();
    }

    public int getRightClicks() {
        filterClicks(rightClicks);

        return rightClicks.size();
    }

    /**
     * Delete clicks made longer than a second ago.
     * @param clicksTimeStamps clicks timestamps list.
     */
    private void filterClicks(List<Long> clicksTimeStamps) {
        Iterator<Long> iterator = clicksTimeStamps.iterator();

        while (iterator.hasNext()) {
            long time = iterator.next();

            // If click time large than second we delete this value.
            if (System.currentTimeMillis() - time > MILLIS_IN_SECOND) {
                iterator.remove();
            } else {
                // I use break because our list has order, so... if this click do small than second ago, next click will be too.
                break;
            }
        }
    }
}
