package existek.existek.recylerviewtypes.utils;

import android.text.TextUtils;

public class ValidUtility {

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
