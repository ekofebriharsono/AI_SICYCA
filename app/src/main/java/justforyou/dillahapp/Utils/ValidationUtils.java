package justforyou.dillahapp.Utils;

import android.widget.EditText;

public class ValidationUtils {
    public static boolean isEmpty(EditText editText) {
        String text = editText.getText().toString();
        return text.trim().isEmpty();
    }
}
