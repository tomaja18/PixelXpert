package sh.siava.pixelxpert.modpacks;

import android.os.Build;

import java.util.ArrayList;

import sh.siava.pixelxpert.modpacks.allApps.HookTester;
import sh.siava.pixelxpert.modpacks.allApps.OverScrollDisabler;
import sh.siava.pixelxpert.modpacks.android.BrightnessRange;
import sh.siava.pixelxpert.modpacks.android.HotSpotController;
import sh.siava.pixelxpert.modpacks.android.PackageManager;
import sh.siava.pixelxpert.modpacks.android.PhoneWindowManager;
import sh.siava.pixelxpert.modpacks.android.ScreenOffKeys;
import sh.siava.pixelxpert.modpacks.android.ScreenRotation;
import sh.siava.pixelxpert.modpacks.android.StatusbarSize;
import sh.siava.pixelxpert.modpacks.android.SystemScreenRecord;
import sh.siava.pixelxpert.modpacks.dialer.RecordingMessage;
import sh.siava.pixelxpert.modpacks.launcher.PixelXpertIconUpdater;
import sh.siava.pixelxpert.modpacks.launcher.ClearAllButtonMod;
import sh.siava.pixelxpert.modpacks.launcher.CustomNavGestures;
import sh.siava.pixelxpert.modpacks.launcher.FeatureFlags;
import sh.siava.pixelxpert.modpacks.launcher.TaskbarActivator;
import sh.siava.pixelxpert.modpacks.settings.AppCloneEnabler;
import sh.siava.pixelxpert.modpacks.android.RingerVolSeperator;
import sh.siava.pixelxpert.modpacks.systemui.AOSPSettingsLauncher;
import sh.siava.pixelxpert.modpacks.systemui.BatteryStyleManager;
import sh.siava.pixelxpert.modpacks.systemui.BrightnessSlider;
import sh.siava.pixelxpert.modpacks.systemui.EasyUnlock;
import sh.siava.pixelxpert.modpacks.systemui.FeatureFlagsMods;
import sh.siava.pixelxpert.modpacks.systemui.FingerprintWhileDozing;
import sh.siava.pixelxpert.modpacks.systemui.FlashLightLevel;
import sh.siava.pixelxpert.modpacks.systemui.GestureNavbarManager;
import sh.siava.pixelxpert.modpacks.systemui.KeyGuardPinScrambler;
import sh.siava.pixelxpert.modpacks.systemui.KeyguardMods;
import sh.siava.pixelxpert.modpacks.systemui.MultiStatusbarRows;
import sh.siava.pixelxpert.modpacks.systemui.NotificationExpander;
import sh.siava.pixelxpert.modpacks.systemui.NotificationManager;
import sh.siava.pixelxpert.modpacks.systemui.QSFooterTextManager;
import sh.siava.pixelxpert.modpacks.systemui.StatusbarGestures;
import sh.siava.pixelxpert.modpacks.systemui.ThemeManager_13;
import sh.siava.pixelxpert.modpacks.systemui.QSTileGrid;
import sh.siava.pixelxpert.modpacks.systemui.ScreenGestures;
import sh.siava.pixelxpert.modpacks.systemui.ScreenRecord;
import sh.siava.pixelxpert.modpacks.systemui.ScreenshotManager;
import sh.siava.pixelxpert.modpacks.systemui.SettingsLibUtilsProvider;
import sh.siava.pixelxpert.modpacks.systemui.StatusbarMods;
import sh.siava.pixelxpert.modpacks.systemui.ThemeManager_14;
import sh.siava.pixelxpert.modpacks.systemui.ThermalProvider;
import sh.siava.pixelxpert.modpacks.systemui.ThreeButtonNavMods;
import sh.siava.pixelxpert.modpacks.systemui.UDFPSManager;
import sh.siava.pixelxpert.modpacks.systemui.VolumeTile;
import sh.siava.pixelxpert.modpacks.telecom.CallVibrator;
import sh.siava.pixelxpert.modpacks.utils.Helpers;


public class ModPacks {

	public static ArrayList<Class<? extends XposedModPack>> getMods(String packageName)
	{
		ArrayList<Class<? extends XposedModPack>> modPacks = new ArrayList<>();

		//Should be loaded before others
		modPacks.add(HealthHook.class);


		//All Apps
		return modPacks;
	}
}