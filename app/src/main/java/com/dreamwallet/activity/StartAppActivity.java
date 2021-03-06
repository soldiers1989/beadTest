package com.dreamwallet.activity;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.dreamwallet.R;
import com.dreamwallet.util.Global;
import com.dreamwallet.util.UrlService;
import com.dreamwallet.util.UserInfo;
import com.example.skn.framework.base.BaseActivity;
import com.example.skn.framework.http.Api;
import com.example.skn.framework.http.BaseEntity;
import com.example.skn.framework.http.RequestCallBack;
import com.example.skn.framework.util.AppUtil;
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
                    MainActivity.startActivity(mActivity, 0);
                }
                finish();
            }else if(msg.what == 2){
                checkLogin();
            }
        }
    };

    @Override
    protected void initVar() {
        setFlagFullscreen();
        setContentView(R.layout.activity_start_app);
    }

    @Override
    protected void init() {

        getDreamSwift();

    }

    private void checkLogin(){
        if (UserInfo.isLogin()) {
            getUserInfo();
        } else {
            handler.sendEmptyMessageDelayed(1, 2000);
        }
    }

    @Override
    protected void initData() {

    }

    private void getDreamSwift(){

        Api.getDefault(UrlService.class).getSwift(AppUtil.getVersionName(), "Android").compose(Api.handlerResult())
                .subscribe(new RequestCallBack<String>(mActivity, false) {
                    @Override
                    public void onSuccess(String s) {
                        Global.hideLoans = Integer.valueOf(s);
//                        Global.hideLoans = 1;
                        handler.sendEmptyMessage(2);
                    }

                    @Override
                    public void onFailure(String code, String errorMsg) {
                        handler.sendEmptyMessage(2);
                    }
                });



    }

    private void getUserInfo() {
        Api.getDefault(UrlService.class).queryUser1(UserInfo.loginToken, "android").compose(Api.handlerResult())
                .subscribe(new Subscriber<String>() {
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
