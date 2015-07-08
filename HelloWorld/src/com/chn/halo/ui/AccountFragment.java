package com.chn.halo.ui;

import butterknife.OnClick;

import com.chn.halo.R;
import com.chn.halo.core.BaseButterKnifeFragment;
import com.chn.halo.util.ToastUtils;

public class AccountFragment extends BaseButterKnifeFragment {

	@Override
	protected int getLayoutResId() {
		return R.layout.fragment_account;
	}

	@Override
	protected void initializeAfterOnCreate() {

	}

	@OnClick(R.id.main_btn_test)
	void Test() {
		ToastUtils.show(getThis(), "Account");
	}
}
