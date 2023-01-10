package com.example.suiyuanbofang;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.e4a.runtime.android.mainActivity;
import com.e4a.runtime.对话框类;
import com.e4a.runtime.系统相关类;
import com.suiyuan.activity.SuiYuanActivity;
import com.sy.play.SYDataSource;
import com.sy.play.SYMediaExo;
import com.sy.play.SYMediaIjk;
import com.sy.play.SYplayStd;
import com.sy.play.util.LogUtility;
import com.sy.play.util.ObjectUtil;
import com.sy.sniffer.VideoSniffer;

import java.util.Map;

/**
 * 创建日期： 2021/5/14
 *
 * @author 随缘_QQ:874334395
 * @version 1.0
 * @since JDK 1.8.0_79
 * <p>
 * 类说明：   模拟E4A应用程序!此类为真实调试E4A类库 1比1还原E4A环境
 * 类说明：   SuiYuanActivity 为E4A的窗口类, 具体需要自行研究!
 */

public class 播放测试 extends SuiYuanActivity {
    SYplayStd sYplayStd;
    String url = "https://upos-sz-mirror08ct.bilivideo.com/upgcxcode/99/64/769146499/769146499_nb3-1-16.mp4?e=ig8euxZM2rNcNbRVhwdVhwdlhWdVhwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=&uipk=5&nbs=1&deadline=1670715709&gen=playurlv2&os=08ctbv&oi=0&trid=26671a4b7d964d09a072dd4a43527facT&mid=0&platform=html5&upsig=da960cabec8478522d8fc7fecac2ab11&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,mid,platform&bvc=vod&nettype=0&bw=38274&orderid=0,1&logo=80000000";
    private int 屏宽, 屏高;

    @SuppressLint("ResourceAsColor")
    @Override
    public void onCreate() {
        背景颜色(Color.parseColor("#0B1022"));
//        系统相关类.隐藏状态栏();
        屏宽 = 系统相关类.取屏幕宽度();
        屏高 = 系统相关类.取屏幕高度();
        //SuiYuanActivity 是相对布局窗口 ,你可以使用他封装E4A窗口程序
        //***************************************************************
//        hx = new 随缘_V7剧集详情列表Impl(this);
//        addView(hx, 屏宽 - dpPx(60), 屏高 - dpPx(60), dpPx(30), dpPx(15));

        try {
            LinearLayout linearLayout = new LinearLayout(mainActivity.getContext());
            sYplayStd = new SYplayStd(mainActivity.getContext());
            linearLayout.addView(sYplayStd);
            addView(linearLayout, -1, dpPx(240), 0, 0);


        } catch (Throwable t) {
            t.printStackTrace();
        }
        initUi();

    }

    private void initUi() {
        ViewGroup viewGroup = (ViewGroup) relativeLayouts.getView();
        viewGroup.setClipChildren(false);
        viewGroup.setClipToPadding(false);
        LinearLayout linearLayout = new LinearLayout(mainActivity.getContext());
        linearLayout.setBackgroundColor(Color.TRANSPARENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(newText("设置直播", -1, dpPx(30), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sYplayStd.setUp(new SYDataSource("https://cctvwbndtxy.liveplay.myqcloud.com/cctvwbnd/jzcctv1_2/index.m3u8", "框架测试",
                        true, null, false,false,0), SYMediaExo.class);
                sYplayStd.startVideo();
            }
        }));
        linearLayout.addView(newText("设置视频", -1, dpPx(30), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity.getContext());
                对话框类.InputBox ib = new 对话框类.InputBox();
                ib.showDialog(builder, "设置视频", "不输入则使用默认测试地址", new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        if (ObjectUtil.notNull(ib.getDialogResult())) {
                            sYplayStd.setUp(new SYDataSource(ib.getDialogResult(), "框架测试", true, null, false,false,0), SYMediaIjk.class);
                            sYplayStd.startVideo();
                        } else {
                            sYplayStd.setUp(new SYDataSource("https://media.w3.org/2010/05/sintel/trailer.mp4", "框架测试",
                                    true, null, false,false,0), SYMediaExo.class);
                            sYplayStd.startVideo();
                        }
                    }
                });
            }
        }));
        linearLayout.addView(newText("停止", -1, dpPx(30), v -> sYplayStd.stopPlay()));
        linearLayout.addView(newText("嗅探", -1, dpPx(30), v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity.getContext());
            对话框类.InputBox ib = new 对话框类.InputBox();
            ib.showDialog(builder, "设置嗅探", "输入需要嗅探的地址", new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    if (ObjectUtil.notNull(ib.getDialogResult())) {
                        sYplayStd.setUp(new SYDataSource(ib.getDialogResult(), "框架测试", true, null, false,true,8), SYMediaExo.class);
                        sYplayStd.startVideo();
                    } else {
                        sYplayStd.setUp(new SYDataSource("https://www.bilibili.com/video/BV1GY4y177r9?spm_id_from=333.851.b_7265636f6d6d656e64.2", "框架测试",
                                true, null, false,true,8), SYMediaExo.class);
                        sYplayStd.startVideo();
                    }
                }
            });
        }));

        linearLayout.addView(newText("作者: 随缘 QQ:874334395 \n框架开源 请移步QQ群:476412098", -1, -2, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }));
        linearLayout.addView(newText("框架简介:\n1.支持多内核拓展\n2.支持多视频同时播放\n3.内置手势放大缩小\n4.内置悬浮窗\n5.支持UI多拓展\n6.框架未使用任何E4A依赖,解耦度极高", -1, -2, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }));
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-2, -2);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        linearLayout.setLayoutParams(lp);
        addView(linearLayout);

    }

    private TextView newText(String title, int width, int height, View.OnClickListener onClickListener) {
        TextView textView = new TextView(mainActivity.getContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        textView.setBackground(getBackground(GradientDrawable.Orientation.LEFT_RIGHT));
        textView.setGravity(Gravity.CENTER);
        textView.setFocusable(true);
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        textView.setText(title);
        textView.setOnClickListener(onClickListener);
        return textView;
    }

    public Drawable getBackground(GradientDrawable.Orientation orientation) {
        //圆角设置
        float[] radii = new float[]{
                dpPx(5), dpPx(5),
                dpPx(5), dpPx(5),
                dpPx(5), dpPx(5),
                dpPx(5), dpPx(5)
        };
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColors(new int[]{Color.parseColor("#FF5444"), Color.parseColor("#FF7643")});
        gradientDrawable.setOrientation(orientation);
        gradientDrawable.setStroke(0, Color.TRANSPARENT);
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setColors(new int[]{Color.TRANSPARENT, Color.TRANSPARENT});
        gradientDrawable2.setStroke(0, Color.TRANSPARENT);
        gradientDrawable.setCornerRadii(radii);
        gradientDrawable2.setCornerRadii(radii);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, gradientDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_focused}, gradientDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_selected}, gradientDrawable);
        stateListDrawable.addState(new int[]{-android.R.attr.state_enabled}, gradientDrawable2);
        return stateListDrawable;
    }


}
