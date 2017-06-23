package com.dam.preferencia;


import com.dam.R;

import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
/**
 * Esta clase gestina la pantalla de preferencia del administrador
 * @author Javier
 * @version 1
 */
public class Preferencias extends PreferenceActivity {

	EditTextPreference e;
	SharedPreferences pref;
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    pref = getSharedPreferences("com.dam", Context.MODE_PRIVATE);
	    String usuario = pref.getString(getString(R.string.usuarioPref), null);
		addPreferencesFromResource(R.xml.preferencia);
		e = (EditTextPreference) findPreference(getString(R.string.usuarioPref));
		e.setSummary(usuario);
		e.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(Preference preference,
					Object newValue) {
				
				e.setSummary((String) newValue);
				modificarPref((String) newValue);

				return false;
			}
		});
	}
	/**
	 * Modificamos la preferencia ya creada
	 * @param usuario
	 */
	public void modificarPref(String usuario){
		SharedPreferences.Editor ed = pref.edit();
		ed.putString(getString(R.string.usuarioPref), usuario);
		ed.commit();
	}
}
