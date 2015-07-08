package com.chn.halo.view.bottomtabbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 * @description 自定义 FragmentManager
 *
 * @author Halo-CHN
 *
 * @mail halo-chn@outlook.com
 *
 * @date 2015年7月8日
 *
 * @version 1.0
 *
 */
public class HaloFragmentManager {

	private static HaloFragmentManager instance;

	public static HaloFragmentManager getInstance(HaloViewPager pViewPager, FragmentManager pFragmentManager) {
		synchronized (HaloFragmentManager.class) {
			if (null == instance) {
				synchronized (HaloFragmentManager.class) {
					if (null == instance) {
						fragmentManager = pFragmentManager;
						viewPager = pViewPager;
						instance = new HaloFragmentManager();
					}
				}
			}
		}
		return instance;
	}

	@SuppressLint("UseSparseArrays")
	private HaloFragmentManager() {
		fragmetsMap = new HashMap<Integer, Fragment>();
		fragments = new ArrayList<Fragment>();
		viewPager.setSlipping(false);
		viewPager.setOffscreenPageLimit(5);
		haloPagerAdapter = new HaloPagerAdapter();
		viewPager.setAdapter(haloPagerAdapter);
	}

	private Map<Integer, Fragment> fragmetsMap;

	private static HaloViewPager viewPager;

	private static List<Fragment> fragments;

	private static FragmentManager fragmentManager;

	private HaloPagerAdapter haloPagerAdapter;

	public void addFragment(int key, Fragment valueFragment) {
		if (!fragmetsMap.containsKey(key)) {
			fragmetsMap.put(key, valueFragment);
			fragments.add(valueFragment);
			haloPagerAdapter.notifyDataSetChanged();
		}
	}

	public void clickToChangeFragment(int id) {
		viewPager.setCurrentItem(fragments.indexOf(fragmetsMap.get(id)), false);
	}

	public Map<Integer, Fragment> getFragmentMap() {
		return this.fragmetsMap;
	}

	public List<Fragment> getFragments() {
		return fragments;
	}

	private static class HaloPagerAdapter extends PagerAdapter {

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getCount() {
			return fragments.size();
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(fragments.get(position).getView());
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			Fragment fragment = fragments.get(position);
			if (!fragment.isAdded()) {
				FragmentTransaction ft = fragmentManager.beginTransaction();
				ft.add(fragment, fragment.getClass().getSimpleName());
				ft.commit();
				/**
				 * 在用FragmentTransaction.commit()方法提交FragmentTransaction对象后
				 * 会在进程的主线程中，用异步的方式来执行。 如果想要立即执行这个等待中的操作，就要调用这个方法（只能在主线程中调用）。
				 * 要注意的是，所有的回调和相关的行为都会在这个调用中被执行完成，因此要仔细确认这个方法的调用位置。
				 */
				fragmentManager.executePendingTransactions();
			}

			if (fragment.getView().getParent() == null) {
				container.addView(fragment.getView());
			}
			return fragment.getView();
		}
	}
}