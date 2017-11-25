package com.dreamwallet.activity;

import com.dreamwallet.R;
import com.dreamwallet.fragment.BaseLoansFragment;
import com.example.skn.framework.base.BaseActivity;

/**
 * Created by Administrator on 2017/11/25 0025.
 *
 */

public class LoansActivity extends BaseActivity {

    @Override
    protected void initVar() {
        setFlagTranslucentStatus();
        setContentView(R.layout.activity_loans);
    }

    @Override
    protected void init() {
        getSupportFragmentManager().beginTransaction().add(R.id.layout_loan, BaseLoansFragment.getInstance()).commit();
    }

    @Override
    protected void initData() {

    }
}
