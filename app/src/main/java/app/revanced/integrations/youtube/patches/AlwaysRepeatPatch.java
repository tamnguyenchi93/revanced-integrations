package app.revanced.integrations.youtube.patches;

import static app.revanced.integrations.shared.StringRef.str;

import android.os.Build;

import app.revanced.integrations.shared.Logger;
import app.revanced.integrations.shared.Utils;
import app.revanced.integrations.youtube.settings.Settings;

public class AlwaysRepeatPatch {

    public static boolean enableAlwaysRepeat(boolean original) {
        return !Settings.ALWAYS_REPEAT.get() && original;
    }

}
