package com.obbedcode.xplex.views;

import com.obbedcode.xplex.AppBarActivity;
import com.obbedcode.xplex.R;

public abstract class AppBarFragmentActivity extends AppBarActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.main_app_bar_fragment_activity;
    }
}