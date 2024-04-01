package app.revanced.integrations.youtube.videoplayer;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import app.revanced.integrations.youtube.patches.AlwaysRepeatPatch;
import app.revanced.integrations.youtube.settings.Settings;
import app.revanced.integrations.shared.Logger;

public class AlwaysRepeatButton extends BottomControlButton {
    @Nullable
    private static AlwaysRepeatButton instance;

    public AlwaysRepeatButton(ViewGroup viewGroup) {
        super(
                viewGroup,
                "revanced_always_repeat_button",
                Settings.ALWAYS_REPEAT,
                view -> AlwaysRepeatPatch.alwaysrepeat(),
                null
        );
    }

    /**
     * Injection point.
     */
    public static void initializeButton(View view) {
        try {
            instance = new AlwaysRepeatButton((ViewGroup) view);
        } catch (Exception ex) {
            Logger.printException(() -> "initializeButton failure", ex);
        }
    }

    /**
     * Injection point.
     */
    public static void changeVisibility(boolean showing) {
        if (instance != null) instance.setVisibility(showing);
    }
}