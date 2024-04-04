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


public class AlwaysRepeatButton extends OverlayBottomControlButton {
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
        if (instance == null) {
            return;
        }

        // // Toggle sellected status
        instance.setSelected(selected);
        if (!Settings.ALWAYS_REPEAT.get())
        {

            Utils.showToastShort(str("revanced_overlay_button_always_repeat_enable"));
            Settings.ALWAYS_REPEAT.save(true);
        } else {
            Utils.showToastShort(str("revanced_overlay_button_always_repeat_disable"));
            
            Settings.ALWAYS_REPEAT.save(false);
        }
                // ? str("revanced_overlay_button_always_repeat_enable")
                // : str("revanced_overlay_button_always_repeat_disable"))
        // if (!onlyView) Settings.ALWAYS_REPEAT.save(selected);
    }
}

// public class AlwaysRepeatButton {
//     @Nullable
//     private static AlwaysRepeatButton instance;
//     private static final Animation fadeIn;
//     private static final Animation fadeOut;

//     private static final WeakReference<ImageView> buttonRef;
//     private final BooleanSetting setting;
//     protected boolean isVisible;

//     static {
//         // TODO: check if these durations are correct.
//         fadeIn = Utils.getResourceAnimation("fade_in");
//         fadeIn.setDuration(Utils.getResourceInteger("fade_duration_fast"));

//         fadeOut = Utils.getResourceAnimation("fade_out");
//         fadeOut.setDuration(Utils.getResourceInteger("fade_duration_scheduled"));
//     }

//     @NonNull
//     public static Animation getButtonFadeIn() {
//         return fadeIn;
//     }

//     @NonNull
//     public static Animation getButtonFadeOut() {
//         return fadeOut;
//     }

//     public AlwaysRepeatButton(@NonNull ViewGroup bottomControlsViewGroup, @NonNull String imageViewButtonId,
//                                @NonNull BooleanSetting booleanSetting, @NonNull View.OnClickListener onClickListener,
//                                @Nullable View.OnLongClickListener longClickListener) {
//         Logger.printDebug(() -> "Initializing button: " + imageViewButtonId);

//         setting = booleanSetting;

//         // Create the button.
//         ImageView imageView = Objects.requireNonNull(bottomControlsViewGroup.findViewById(
//                 Utils.getResourceIdentifier(imageViewButtonId, "id")
//         ));
//         imageView.setOnClickListener(onClickListener);
//         if (longClickListener != null) {
//             imageView.setOnLongClickListener(longClickListener);
//         }
//         imageView.setVisibility(View.GONE);

//         buttonRef = new WeakReference<>(imageView);
//     }

//     public void setVisibility(boolean visible) {
//         if (isVisible == visible) return;
//         isVisible = visible;

//         ImageView imageView = buttonRef.get();
//         if (imageView == null) {
//             return;
//         }

//         imageView.clearAnimation();
//         if (visible && setting.get()) {
//             imageView.startAnimation(fadeIn);
//             imageView.setVisibility(View.VISIBLE);
//         } else if (imageView.getVisibility() == View.VISIBLE) {
//             imageView.startAnimation(fadeOut);
//             imageView.setVisibility(View.GONE);
//         }
//     }

//     /**
//      * Injection point.
//      */
//     public static void initializeButton(View view) {
//         try {
//             instance = new AlwaysRepeatButton(
//                 (ViewGroup) view,
//                 "overlayalways_repeat_button",
//                 Settings.OVERLAY_BUTTON_ALWAYS_REPEAT,
//                 view -> AlwaysRepeatButton.changeSelected(!view.isSelected(), false),
//                 null);
//         } catch (Exception ex) {
//             Logger.printException(() -> "initializeButton failure", ex);
//         }
//     }

//     /**
//      * Injection point.
//      */
//     public static void changeVisibility(boolean showing) {
//         if (instance != null) instance.setVisibility(showing);
//     }

//     public static void changeSelected(boolean selected, boolean onlyView) {
//         ImageView imageView = buttonRef.get();
//         if (instance == null || imageView == null)
//             return;

//         // // Toggle sellected status
//         imageView.setSelected(selected);
//         if (!Settings.ALWAYS_REPEAT.get())
//         {

//             Utils.showToastShort(str("revanced_overlay_button_always_repeat_enable"));
//             Settings.ALWAYS_REPEAT.save(true);
//         } else {
//             Utils.showToastShort(str("revanced_overlay_button_always_repeat_disable"));
            
//             Settings.ALWAYS_REPEAT.save(false);
//         }
//                 // ? str("revanced_overlay_button_always_repeat_enable")
//                 // : str("revanced_overlay_button_always_repeat_disable"))
//         // if (!onlyView) Settings.ALWAYS_REPEAT.save(selected);
//     }
// }