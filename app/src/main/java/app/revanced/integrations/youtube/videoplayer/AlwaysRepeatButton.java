package app.revanced.integrations.youtube.videoplayer;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import app.revanced.integrations.youtube.patches.AlwaysRepeatPatch;
import app.revanced.integrations.youtube.settings.Settings;
import app.revanced.integrations.shared.Logger;
import app.revanced.integrations.shared.Utils;
import static app.revanced.integrations.shared.StringRef.str;

public class AlwaysRepeatButton extends BottomControlButton {
    @Nullable
    private static AlwaysRepeatButton instance;

    public AlwaysRepeatButton(ViewGroup viewGroup) {
        super(
                viewGroup,
                "overlayalways_repeat_button",
                Settings.OVERLAY_BUTTON_ALWAYS_REPEAT,
                view -> AlwaysRepeatButton.changeSelected(!view.isSelected(), false),
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

    public static void changeSelected(boolean selected, boolean onlyView) {
        // ImageView imageView = buttonRef.get();
        // if (constraintLayout == null || imageView == null)
        //     return;

        // // Toggle sellected status
        // imageView.setSelected(selected);
        Utils.showToastShort(selected
                ? str("revanced_overlay_button_always_repeat_enable")
                : str("revanced_overlay_button_always_repeat_disable"));
        if (!onlyView) Settings.ALWAYS_REPEAT.save(selected);
    }
}