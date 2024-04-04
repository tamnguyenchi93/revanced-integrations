package app.revanced.integrations.youtube.overplaybuttons;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.Objects;

import app.revanced.integrations.youtube.patches.AlwaysRepeatPatch;
import app.revanced.integrations.youtube.settings.Settings;
import app.revanced.integrations.shared.Logger;
import app.revanced.integrations.shared.Utils;
import static app.revanced.integrations.shared.StringRef.str;
import app.revanced.integrations.shared.settings.BooleanSetting;


public class SpeedDialogButton extends OverlayBottomControlButton {
    @Nullable
    private static SpeedDialogButton instance;

    public SpeedDialogButton(ViewGroup viewGroup) {
        super(
                viewGroup,
                "overlay_speed_dialog_button",
                Settings.OVERLAY_BUTTON_SPEED_DIALOG,
                view -> SpeedDialogButton.changeSelected(!view.isSelected(), false),
                null
        );
    }

    /**
     * Injection point.
     */
    public static void initializeButton(View view) {
        try {
            instance = new SpeedDialogButton((ViewGroup) view);
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

    public static void changeSelected(boolean selected, boolean onlyView) {
        if (instance == null) {
            return;
        }

        Utils.showToastShort(str("revanced_overlay_button_speed_dialog_summary"));
    }
}
