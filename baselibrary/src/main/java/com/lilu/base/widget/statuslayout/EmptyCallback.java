package com.lilu.base.widget.statuslayout;

import com.kingja.loadsir.callback.Callback;
import com.lilu.base.R;

/**
 * Description:
 *
 * @author lilu on 2021/1/12
 * No one knows this better than me
 */
public class EmptyCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_empty;
    }
}
