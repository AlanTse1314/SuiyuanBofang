package com.e4a.runtime.components.impl.android.随缘_智能播放器类库;


import android.view.View;
import android.widget.ImageView;

import com.e4a.runtime.android.mainActivity;
import com.e4a.runtime.components.ComponentContainer;
import com.e4a.runtime.components.impl.android.ViewComponent;
import com.e4a.runtime.components.impl.android.n4.图片框Impl;

//作者:874334395
public class 随缘_智能播放器Impl extends ViewComponent implements 随缘_智能播放器 {
    private ComponentContainer container;

    public 随缘_智能播放器Impl(ComponentContainer container) {
        super(container);
        this.container = container;
    }

    @Override
    protected View createView() {
         //注意:请尽量不要再类中使用系统相关类.取资源索引()!容易导致R资源智能检测失效!
         //当然你也可以使用, 如果使用系统相关类.取资源索引() 则内置R资源检测失效
        try {
            //直接使用E4A 的可视控件,如果你想使用E4A控件的事件,那么你必须继承控件
            图片框Impl imageView = new 图片框Impl(container);
        } catch (Exception e) {
            System.out.println("不是E4A可视控件");
        }

        ImageView imageView = new ImageView(mainActivity.getContext());
        //imageView.setBackgroundResource(R.drawable.a);
        return imageView;
    }

    @Override
    public void 设置图片(int 资源id) {
        getView().setBackgroundResource(资源id);
    }
}
