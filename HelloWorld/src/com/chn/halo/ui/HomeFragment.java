package com.chn.halo.ui;

import butterknife.OnClick;

import com.chn.halo.GlobalConfig;
import com.chn.halo.R;
import com.chn.halo.core.BaseButterKnifeFragment;
import com.chn.halo.net.BaiDuApiStoreClientProvider;
import com.chn.halo.net.HaloAsyncHttpResponseHandler;
import com.chn.halo.util.ToastUtils;

/**
 *
 * @description 主页
 *
 * @author Halo-CHN
 *
 * @mail halo-chn@outlook.com
 *
 * @date 2015年7月13日
 *
 * @version 1.0
 *
 */
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
		BaiDuApiStoreClientProvider.getInstance().get(getThis(), GlobalConfig.baiDuTrafficEventUrl,
				BaiDuApiStoreClientProvider.getInstance().getTafficEventParams("北京"), new HaloAsyncHttpResponseHandler() {

					@Override
					public void callback(String response) {
						ToastUtils.show(getThis(), response);
					}
				});
	}
}
