package sh.siava.pixelxpert.modpacks;

import static de.robv.android.xposed.XposedBridge.hookAllMethods;
import static de.robv.android.xposed.XposedBridge.log;
import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.setObjectField;
import static sh.siava.pixelxpert.modpacks.utils.Helpers.dumpClass;

import android.content.Context;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HealthHook extends XposedModPack{
	public HealthHook(Context context) {
		super(context);
		log("init");

	}

	@Override
	public void updatePrefs(String... Key) {

	}

	@Override
	public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
		log("hi");
		Class<?> d = findClass("defpackage.anx", lpparam.classLoader);
		dumpClass(d);
		hookAllMethods(d, "aj", new XC_MethodHook() {
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable {
				setObjectField(this, "ao", true);
			}
		});
	}

	@Override
	public boolean listensTo(String packageName) {
		return true;
	}
}
