package com.comtempwallet.activity;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.comtempwallet.R;
import com.comtempwallet.http.UrlService;
import com.comtempwallet.util.UserInfo;
import com.example.skn.framework.base.BaseActivity;
import com.example.skn.framework.http.Api;
import com.example.skn.framework.http.BaseEntity;
import com.example.skn.framework.util.JsonUtil;
import com.example.skn.framework.util.SpUtil;

import rx.Subscriber;

public class StartAppActivity extends BaseActivity {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (!SpUtil.getBooleanData("firstUse")) {
                    MainActivity.startActivity(mActivity, 0);
                } else {
                    //首页
                    MainActivity.startActivity(mActivity, 0);
                }
                finish();
            }
        }
    };

    @Override
    protected void initVar() {
//        setFlagFullscreen();
        setContentView(R.layout.activity_start_app);
    }

    @Override
    protected void init() {
        if (UserInfo.isLogin()) {
//            getUserInfo();
            handler.sendEmptyMessageDelayed(1, 2000);
        } else {
            handler.sendEmptyMessageDelayed(1, 2000);
        }
    }

    @Override
    protected void initData() {

    }

    private void getUserInfo() {

        Api.getDefault(UrlService.class).queryUser1(UserInfo.loginToken).compose(Api.handlerResult()).
                subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                handler.sendEmptyMessageDelayed(1, 1500);
            }

            @Override
            public void onNext(String s) {
                BaseEntity baseEntity = JsonUtil.fromJson(s, BaseEntity.class);
                if (baseEntity != null && TextUtils.equals(baseEntity.getCode(), "444")) {
                    UserInfo.destroyUserInfo();
                }
                handler.sendEmptyMessageDelayed(1, 1500);
            }
        });

    }

}
