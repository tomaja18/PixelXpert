package sh.siava.pixelxpert.modpacks.utils;

import static sh.siava.pixelxpert.modpacks.XPrefs.Xprefs;

import android.annotation.SuppressLint;

import java.util.Calendar;

public class BootLoopProtector {
	public static final String LOAD_TIME_KEY_KEY = "packageLastLoad_";
	public static final String PACKAGE_STRIKE_KEY_KEY = "packageStrike_";

	@SuppressLint("ApplySharedPref")
	public static boolean isBootLooped(String packageName)
	{
		String loadTimeKey = String.format("%s%s", LOAD_TIME_KEY_KEY, packageName);
		String strikeKey = String.format("%s%s", PACKAGE_STRIKE_KEY_KEY, packageName);
		long currentTime = Calendar.getInstance().getTime().getTime();
		long lastLoadTime = Xprefs.getLong(loadTimeKey, 0);
		int strikeCount = Xprefs.getInt(strikeKey, 0);

		if (currentTime - lastLoadTime > 40000)
		{
			resetCounter(packageName);
		}
		else if(strikeCount >= 3)
		{
			return true;
		}
		else
		{
			Xprefs.edit().putInt(strikeKey, ++strikeCount).commit();
		}
		return false;
	}

	@SuppressLint("ApplySharedPref")
	public static void resetCounter(String packageName)
	{
		try
		{
			String loadTimeKey = String.format("%s%s", LOAD_TIME_KEY_KEY, packageName);
			String strikeKey = String.format("%s%s", PACKAGE_STRIKE_KEY_KEY, packageName);
			long currentTime = Calendar.getInstance().getTime().getTime();

			Xprefs.edit()
					.putLong(loadTimeKey, currentTime)
					.putInt(strikeKey, 0)
					.commit();
		}
		catch (Throwable ignored){}
	}
}
