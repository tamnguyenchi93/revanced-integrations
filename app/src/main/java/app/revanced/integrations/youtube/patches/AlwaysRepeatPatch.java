package app.revanced.integrations.youtube.patches;

import static app.revanced.integrations.shared.StringRef.str;

import android.os.Build;

import app.revanced.integrations.shared.Logger;
import app.revanced.integrations.shared.Utils;

public class AlwaysRepeatPatch {

    public static void alwaysrepeat() {
        try {
            Utils.showToastShort(str("revanced_always_repeat_success"));
        } catch (Exception e) {
            Logger.printException(() -> "Failed to generate video url", e);
        }
    }

}
