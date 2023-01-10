package com.e4a.runtime.components.impl.android.随缘_智能播放器类库;

import com.e4a.runtime.annotations.SimpleComponent;
import com.e4a.runtime.annotations.SimpleObject;
import com.e4a.runtime.annotations.UsesPermissions;
import com.e4a.runtime.components.VisibleComponent;

@SimpleComponent//必须保留
@SimpleObject//必须保留
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.WRITE_EXTERNAL_STORAGE,android.permission.ACCESS_NETWORK_STATE")
public interface 随缘_智能播放器 extends VisibleComponent {


}
