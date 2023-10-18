package sh.siava.AOSPMods.ui.activities;

import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;
import static sh.siava.AOSPMods.utils.AppUtils.installDoubleZip;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import com.topjohnwu.superuser.Shell;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

import sh.siava.AOSPMods.R;
import sh.siava.AOSPMods.databinding.ActivityUpdateBinding;
import sh.siava.AOSPMods.modpacks.utils.ModuleFolderOperations;
import sh.siava.AOSPMods.utils.PrefManager;

public class UpdateActivity extends AppCompatActivity {

	@SuppressWarnings("FieldCanBeLocal")
	private ActivityUpdateBinding binding;

	public static final String MOD_NAME = "PixelXpert";
	public static final String MAGISK_UPDATE_DIR = "/data/adb/modules_update";
	public static final String MAGISK_MODULES_DIR = "/data/adb/modules";
	private static final String updateRoot = String.format("%s/%s", MAGISK_UPDATE_DIR, MOD_NAME);

	@Override
	protected void attachBaseContext(Context newBase) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(newBase.createDeviceProtectedStorageContext());

		String localeCode = prefs.getString("appLanguage", "");
		Locale locale = (localeCode.length() > 0) ? Locale.forLanguageTag(localeCode) : Locale.getDefault();

		Resources res = newBase.getResources();
		Configuration configuration = res.getConfiguration();

		configuration.setLocale(locale);

		LocaleList localeList = new LocaleList(locale);
		LocaleList.setDefault(localeList);
		configuration.setLocales(localeList);

		super.attachBaseContext(newBase.createConfigurationContext(configuration));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.menu_updates));
		Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
		Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);

		if (getIntent().getBooleanExtra("updateTapped", false))
		{
			String downloadPath = getIntent().getStringExtra("filePath");

			installDoubleZip(downloadPath);
		}
		else if("true".equals(getIntent().getStringExtra("migratePrefs"))) //received intent from magisk, showing installation is done
		{
			exportPrefsForMigration();
			applyPrefsToUpdate();
		}

		binding = ActivityUpdateBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.color_surface_overlay));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		exportPrefsForMigration();
	}

	private void exportPrefsForMigration() {
		if(Shell.cmd("ls /data/adb/modules/AOSPMods").exec().getOut().size() > 0)
		{
			try {
				String savePath = getFilesDir().getPath() + "/PixelXpert.migrate";
				PrefManager.exportPrefs(getDefaultSharedPreferences(getApplicationContext().createDeviceProtectedStorageContext()), getContentResolver().openOutputStream(Uri.fromFile(new File(savePath))));
				Shell.cmd(String.format("mv %s /sdcard/Download/", savePath)).submit();
			} catch (Throwable ignored) {}
		}
	}

	private void applyPrefsToUpdate() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(createDeviceProtectedStorageContext());

		int volumeStps = prefs.getInt("volumeStps", 0);
		boolean customFontsEnabled = prefs.getBoolean("enableCustomFonts", false);
		boolean GSansOverrideEnabled = prefs.getBoolean("gsans_override", false);

		ModuleFolderOperations.applyVolumeSteps(volumeStps, updateRoot, true);
		ModuleFolderOperations.applyFontSettings(customFontsEnabled, GSansOverrideEnabled, updateRoot, true);
	}

	@Override
	public void onNewIntent(Intent i) {
		super.onNewIntent(i);
	}

	@Override
	public boolean onSupportNavigateUp() {
		onBackPressed();
		return true;
	}
}