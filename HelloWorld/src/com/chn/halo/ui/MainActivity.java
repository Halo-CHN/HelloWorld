package com.chn.halo.ui;

import com.chn.halo.R;
import com.chn.halo.core.BaseButterKnifeActivity;
import com.chn.halo.util.ToastUtils;

public class MainActivity extends BaseButterKnifeActivity {

	@Override
	protected boolean supportBackKey() {
		return false;
	}

	@Override
	protected int getLayoutResId() {
		return R.layout.activity_main;
	}

	@Override
	protected void initializeAfterOnCreate() {
		ToastUtils.show(this, "Welcome To Halo's World.");
	}	
}