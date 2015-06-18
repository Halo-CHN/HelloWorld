package com.chn.halo.core;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 *
 * @description 两次确认退出应用程序
 *
 * @author Wiken
 *
 * @mail root_wiken@163.com
 *
 * @date 2015年4月15日
 *
 * @version 1.0
 *
 */
public class TwiceConfirmExitManager {

	private static TwiceConfirmExitManager instance;

	private TwiceConfirmExitManager() {
	}

	public static TwiceConfirmExitManager getInstance() {
		if (null == instance) {
			synchronized (TwiceConfirmExitManager.class) {
				if (null == instance) {
					instance = new TwiceConfirmExitManager();
				}
			}
		}
		return instance;
	}

	/* 程序确认退出时间间隔 */
	private final int FINALSECOND = 3;

	private int time = 0;

	Timer timer = new Timer();

	Toast toast = null;

	/**
	 * 
	 * @param context
	 *            当前页
	 * @param activities
	 *            当前应用中已经打开的Activity
	 */
	public void showToast(Context context, Stack<? extends Activity> activities) {
		if (time == 0) {
			toast = Toast.makeText(context, "再按一次返回将退出程序", FINALSECOND * 1000);
			toast.show();
			timeGos();
		} else {
			timer = null;
			toast.cancel();
			if (null != activities) {
				for (Activity ac : activities) {
					if (null != ac && !ac.isFinishing())
						ac.finish();
				}
			}
			System.exit(0);
		}
	}

	/**
	 * 计时器运行
	 */
	private void timeGos() {
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				time++;
				if (time == FINALSECOND) {
					time = 0;
					this.cancel();
				}
			}
		}, 0, 1000);
	}
}
