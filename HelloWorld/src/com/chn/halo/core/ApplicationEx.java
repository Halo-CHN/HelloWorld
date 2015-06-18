package com.chn.halo.core;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;

/**
 *
 * @description Application扩展
 *
 * @author Halo-CHN
 *
 * @mail halo-chn@outlook.com
 *
 * @date 2015年6月18日
 *
 * @version 1.0
 *
 */
public class ApplicationEx extends Application {

	/* 单例 */
	private static ApplicationEx instance;

	private ApplicationEx() {
		instance = this;
	}

	public static ApplicationEx getInstance() {
		return instance;
	}

	/**
	 * 程序是否在前台运行
	 * 
	 * @return
	 */
	public boolean isAppOnForeground() {

		ActivityManager activityManager = (ActivityManager) getApplicationContext()
				.getSystemService(Context.ACTIVITY_SERVICE);
		String packageName = getApplicationContext().getPackageName();

		List<RunningAppProcessInfo> appProcesses = activityManager
				.getRunningAppProcesses();
		if (appProcesses == null)
			return false;

		for (RunningAppProcessInfo appProcess : appProcesses) {
			if (appProcess.processName.equals(packageName)
					&& appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 取得DaoMaster
	 * 
	 * @param context
	 * @return 全局唯一DaoMaster
	 */
	// public DaoMaster getDaoMaster(Context context) {
	// if (daoMaster == null) {
	// OpenHelper helper = new DaoMaster.DevOpenHelper(context, dbName,
	// null);
	// daoMaster = new DaoMaster(helper.getWritableDatabase());
	// }
	// return daoMaster;
	// }

	/**
	 * 取得DaoSession
	 * 
	 * @param context
	 * @return 全局唯一DaoSession
	 */
	// public DaoSession getDaoSession(Context context) {
	// if (daoSession == null) {
	// if (daoMaster == null) {
	// daoMaster = getDaoMaster(context);
	// }
	// daoSession = daoMaster.newSession();
	// }
	// return daoSession;
	// }

	// private static DaoMaster daoMaster;
	// private static DaoSession daoSession;
}
