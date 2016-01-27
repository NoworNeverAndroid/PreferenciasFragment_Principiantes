package com.example.nowor_000.mispreferencias;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Created by nowor_000 on 27/01/2016.
 */
public class PreferenciasFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String KEY_NOMBRE_ED = "nombre_texto";
    private static final String KEY_CONTRASEÑA_ED = "contraseña_texto";
    private static final String KEY_NOMBRE_CHK = "recordar_nombre";
    private static final String KEY_CONTRASEÑA_CHK = "recordar_contraseña";
    private static final String KEY_IDIOMA = "idioma_list";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * CARGAR LAYOUT
         */

        addPreferencesFromResource(R.xml.preferencias);
    }

    @Override
    public void onResume() {
        super.onResume();

        /**
         * encender el escuchador de eventos por si alguna clave de preferencias cambia
         */
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        // apagar el escuchador de eventos de preferencias
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }


    /**
     * OBTENER Y MODIFICAR LAS PREFRENCIAS DESDE FUERA DEL FRAGMENT
     */

    /**
     * String Preferences
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, final String key) {
        SharedPreferences shaPref = PreferenceManager.getDefaultSharedPreferences(context);
        return shaPref.getString(key, "");
    }

    public static void setString(Context context, final String key, final String value) {
        SharedPreferences shaPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = shaPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Boolean Preferences
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(Context context, final String key, final boolean defaultValue) {

        SharedPreferences shaPref = PreferenceManager.getDefaultSharedPreferences(context);
        return shaPref.getBoolean(key, defaultValue);
    }

    public static void setBoolean(Context context, final String key, final boolean value) {

        SharedPreferences shaPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = shaPref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }


    /**
     * MOSTRAR PREFERENCIAS
     */

    public static void showUserSettings(Context ctx) {

        SharedPreferences shaPref = PreferenceManager.getDefaultSharedPreferences(ctx);
        /**
         * obtener pref
         */

        StringBuilder builder = new StringBuilder();

        builder.append("\n Nombre: "
                + shaPref.getString(KEY_NOMBRE_ED, "NULL"));

        builder.append("\n Recordar Nombre:"
                + shaPref.getBoolean(KEY_NOMBRE_CHK, false));

        builder.append("\n Contraseña: "
                + shaPref.getString(KEY_CONTRASEÑA_ED, "NULL"));

        builder.append("\n Recordar Contraseña:"
                + shaPref.getBoolean(KEY_CONTRASEÑA_CHK, false));

        builder.append("\n Idioma value: "
                + shaPref.getString(KEY_IDIOMA, "NULL"));


        Toast.makeText(ctx, builder.toString(), Toast.LENGTH_LONG).show();

    }


    /**
     * Get & set claves
     *
     * @return
     */
    public static String getKeyNombreEd() {
        return KEY_NOMBRE_ED;
    }

    public static String getKeyContraseñaEd() {
        return KEY_CONTRASEÑA_ED;
    }

    public static String getKeyNombreChk() {
        return KEY_NOMBRE_CHK;
    }

    public static String getKeyContraseñaChk() {
        return KEY_CONTRASEÑA_CHK;
    }

    public static String getKeyIdioma() {
        return KEY_IDIOMA;
    }


    /**
     * CUANDO SE CAMBIE ALGO DESDE EL MENU DE PREFERENCIAS
     *
     * @param shaPref
     * @param key
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences shaPref, String key) {

        shaPref = PreferenceManager.getDefaultSharedPreferences(getActivity());


        switch (key) {
            case KEY_NOMBRE_ED:
                String valor_nombre_ed = shaPref.getString(KEY_NOMBRE_ED, "NULL");
                Toast.makeText(getActivity().getApplicationContext(), "Valor nombre Texto:" + valor_nombre_ed, Toast.LENGTH_SHORT).show();
                break;

            case KEY_CONTRASEÑA_ED:
                String valor_contraseña_ed = shaPref.getString(KEY_CONTRASEÑA_ED, "NULL");
                Toast.makeText(getActivity().getApplicationContext(), "Valor contraseña Texto:" + valor_contraseña_ed, Toast.LENGTH_SHORT).show();
                break;

            case KEY_NOMBRE_CHK:
                String valor_nombre_chk = "" + shaPref.getBoolean(KEY_NOMBRE_CHK, false);
                Toast.makeText(getActivity().getApplicationContext(), "Valor nombre chk:" + valor_nombre_chk, Toast.LENGTH_SHORT).show();
                break;

            case KEY_CONTRASEÑA_CHK:
                String valor_contraseña_chk = "" + shaPref.getBoolean(KEY_CONTRASEÑA_CHK, false);
                Toast.makeText(getActivity().getApplicationContext(), "Valor contraseña chk:" + valor_contraseña_chk, Toast.LENGTH_SHORT).show();
                break;

            case KEY_IDIOMA:
                String valor_idioma = shaPref.getString(KEY_IDIOMA, "NULL");
                Toast.makeText(getActivity().getApplicationContext(), "Valor Idioma:" + valor_idioma, Toast.LENGTH_SHORT).show();


                break;

        }
    }


}

