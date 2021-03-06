package com.dreamwallet.util;

import android.content.Context;
import android.util.Log;
import android.webkit.WebView;

import com.example.skn.framework.http.Api;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;

/**
 * Created by DOY on 2017/9/5.
 */
public class StatisticsUtil {
    public static final String platform = "platformId";//产品
    public static final String starPlatform = "starPlatformId";//明星产品
    public static final String banner = "bannerId";//首页banner
    public static final String BorrowClick = "buttonId";//综合排序（我要借款）
    public static final int INFORMATION = 1;//资讯
    public static final int FORUM = 2;//论坛

    /**
     *
     *  首页资讯统计
     */
    public static void homeInformationCount(String informationId){
        Api.getDefault(UrlService.class).informationCount(informationId,"android").compose(Api.handlerResult())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("beadwallet", "insertInformationVisit---error--->" + e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("beadwallet", "insertInformationVisit---success--->" + s);
                    }
                });

    }

    /**
     * 统计资讯与论坛
     */
    public static void statistics(Context context, int type, int id) {

        Api.getDefault(UrlService.class).insertInformationVisit(UserInfo.loginToken, type, id, "android").compose(Api.handlerResult())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("beadwallet", "insertInformationVisit---error--->" + e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("beadwallet", "insertInformationVisit---success--->" + s);
                    }
                });

    }

    /**
     * 统计浏览量
     */
    public static void visitCount(Context context, String type, String id) {
        Map<String, Object> map = new HashMap<>();
        map.put(type, id);
        Api.getDefault(UrlService.class).visitCount(UserInfo.loginToken, map, "android").compose(Api.handlerResult())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("beadwallet", "visitCount---error--->" + e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("beadwallet", "visitCount---success--->" + s);
                    }
                });
    }

    /**
     * 统计借款页面
     */
    public static void clickCount(Context context, String id) {
        Api.getDefault(UrlService.class).clickCount(UserInfo.loginToken, id, "android").compose(Api.handlerResult())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("beadwallet", "clickCount---error--->" + e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("beadwallet", "clickCount---success--->" + s);
                    }
                });
    }

    /**
     * 统计首页浏览量
     */
    public static void homePage(Context context) {
        Api.getDefault(UrlService.class).homePage("android").compose(Api.handlerResult())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("beadwallet", "homePage---error--->" + e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("beadwallet", "homePage---success--->" + s);
                    }
                });
    }

    public static void setWebData(WebView wv, String msg){
        String html = "<html><head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head><body>"+ msg + "</body></html>";

        wv.loadDataWithBaseURL("file:///android_asset", html, "text/html", "UTF-8", "");
    }

}
