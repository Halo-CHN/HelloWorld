package com.chn.halo.ui;

import butterknife.OnClick;

import com.chn.halo.R;
import com.chn.halo.core.BaseButterKnifeFragment;
import com.chn.halo.util.ToastUtils;

public class HomeFragment extends BaseButterKnifeFragment {

	@Override
	protected int getLayoutResId() {
		return R.layout.fragment_home;
	}

	@Override
	protected void initializeAfterOnCreate() {

	}

	@OnClick(R.id.main_btn_test)
	void Test() {
		ToastUtils.show(getThis(), "Home");
	}
}
