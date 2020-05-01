package justforyou.dillahapp.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static justforyou.dillahapp.Utils.UtilPreferences.AGAMA;
import static justforyou.dillahapp.Utils.UtilPreferences.DS;
import static justforyou.dillahapp.Utils.UtilPreferences.EMAIL;
import static justforyou.dillahapp.Utils.UtilPreferences.FOTO;
import static justforyou.dillahapp.Utils.UtilPreferences.INGAT;
import static justforyou.dillahapp.Utils.UtilPreferences.JK;
import static justforyou.dillahapp.Utils.UtilPreferences.LOKASI;
import static justforyou.dillahapp.Utils.UtilPreferences.NAMA;
import static justforyou.dillahapp.Utils.UtilPreferences.NIM;
import static justforyou.dillahapp.Utils.UtilPreferences.PIN;
import static justforyou.dillahapp.Utils.UtilPreferences.TELP;
import static justforyou.dillahapp.Utils.UtilPreferences.TOKEN;
import static justforyou.dillahapp.Utils.UtilPreferences.TTL;

public class SaveSharedPreference {
    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Set the Login Status
     *
     * @param context
     * @param nim
     */

    public static void setNim(Context context, String nim) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(NIM, nim);
        editor.apply();
    }

    public static void setNama(Context context, String nama) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(NAMA, nama);
        editor.apply();
    }

    public static void setToken(Context context, String token) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

    public static void setFoto(Context context, String foto) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(FOTO, foto);
        editor.apply();
    }

    public static void setPin(Context context, String pin) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(PIN, pin);
        editor.apply();
    }

    public static void setIngat(Context context, boolean ingat) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(INGAT, ingat);
        editor.apply();
    }

    public static void setLokasi(Context context, String lokasi) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(LOKASI, lokasi);
        editor.apply();
    }

    public static void setEmail(Context context, String email) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public static void setJk(Context context, String jk) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(JK, jk);
        editor.apply();
    }

    public static void setTtl(Context context, String ttl) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(TTL, ttl);
        editor.apply();
    }

    public static void setAgama(Context context, String agama) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(AGAMA, agama);
        editor.apply();
    }

    public static void setTelp(Context context, String telp) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(TELP, telp);
        editor.apply();
    }

    public static void setDs(Context context, String ds) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(DS, ds);
        editor.apply();
    }

    public static String getNim(Context context) {
        return getPreferences(context).getString(NIM, null);
    }

    public static String getNama(Context context) {
        return getPreferences(context).getString(NAMA, null);
    }

    public static String getToken(Context context) {
        return getPreferences(context).getString(TOKEN, null);
    }

    public static String getFoto(Context context) {
        return getPreferences(context).getString(FOTO, null);
    }

    public static String getPin(Context context) {
        return getPreferences(context).getString(PIN, null);
    }

    public static boolean getIngat(Context context) {
        return getPreferences(context).getBoolean(INGAT, false);
    }

    public static String getLokasi(Context context) {
        return getPreferences(context).getString(LOKASI, null);
    }

    public static String getEmail(Context context) {
        return getPreferences(context).getString(EMAIL, null);
    }

    public static String getJk(Context context) {
        return getPreferences(context).getString(JK, null);
    }

    public static String getTtl(Context context) {
        return getPreferences(context).getString(TTL, null);
    }

    public static String getAgama(Context context) {
        return getPreferences(context).getString(AGAMA, null);
    }

    public static String getTelp(Context context) {
        return getPreferences(context).getString(TELP, null);
    }

    public static String getDs(Context context) {
        return getPreferences(context).getString(DS, null);
    }
}
