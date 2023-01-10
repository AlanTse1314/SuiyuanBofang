package com.e4a.runtime.components.impl.android.随缘_智能播放器类库;


import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.e4a.runtime.android.mainActivity;
import com.e4a.runtime.components.ComponentContainer;
import com.e4a.runtime.components.impl.android.ViewComponent;
import com.sy.play.SYDataSource;
import com.sy.play.SYMediaExo;
import com.sy.play.SYplayStd;

//作者:874334395
public class 随缘_智能播放器Impl extends ViewComponent implements 随缘_智能播放器 {
    private ComponentContainer container;
    private SYplayStd sYplayStd;

    public 随缘_智能播放器Impl(ComponentContainer container) {
        super(container);
        this.container = container;
    }


    //E4A  初始化View
    @Override
    protected View createView() {
        LinearLayout linearLayout = new LinearLayout(mainActivity.getContext());
        sYplayStd = new SYplayStd(mainActivity.getContext());
        linearLayout.addView(sYplayStd, new ViewGroup.LayoutParams(-1, -1));
        return linearLayout;

        //设置视频 并播放
       //     sYplayStd.setUp(new SYDataSource("https://cctvwbndtxy.liveplay.myqcloud.com/cctvwbnd/jzcctv1_2/index.m3u8", "框架测试",
       //                true, null, false,false,0), SYMediaExo.class);
       //     sYplayStd.startVideo();
        //设置视频嗅探地址并解析播放
        //sYplayStd.setUp(new SYDataSource("https://www.bilibili.com/video/BV1GY4y177r9?spm_id_from=333.851.b_7265636f6d6d656e64.2", "框架测试",
        //       true, null, false,true,8), SYMediaExo.class);
        //sYplayStd.startVideo();

    }


}
