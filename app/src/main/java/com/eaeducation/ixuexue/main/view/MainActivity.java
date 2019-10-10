package com.eaeducation.ixuexue.main.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.eaeducation.ixuexue.R;
import com.eaeducation.ixuexue.base.BaseActivity;
import com.eaeducation.ixuexue.utils.DataGenerator;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_tabLayout)
    TabLayout mTabLayout;
    private Fragment[] mFragmensts;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //取消Tablayout下划线
        mTabLayout.setSelectedTabIndicatorHeight(0);
        mFragmensts = DataGenerator.getFragments();
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabItemSelected(tab.getPosition());
                // Tab 选中之后，改变各个Tab的状态
                for (int i = 0; i < mTabLayout.getTabCount(); i++) {
                    View view = mTabLayout.getTabAt(i).getCustomView();
                    ImageView icon = (ImageView) view.findViewById(R.id.tab_content_image);
                    TextView text = (TextView) view.findViewById(R.id.tab_content_text);
                    if (i == tab.getPosition()) { // 选中状态
                        icon.setImageResource(DataGenerator.mTabResPressed[i]);
                        text.setTextColor(getResources().getColor(android.R.color.black));
                    } else {// 未选中状态
                        icon.setImageResource(DataGenerator.mTabRes[i]);
                        text.setTextColor(getResources().getColor(android.R.color.darker_gray));
                    }
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initData() {
        // 提供自定义的布局添加Tab
        for (int i = 0; i < 4; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(DataGenerator.getTabView(this, i)));
        }
    }

    private void onTabItemSelected(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = mFragmensts[0];
                break;
            case 1:
                fragment = mFragmensts[1];
                break;

            case 2:
                fragment = mFragmensts[2];
                break;
            case 3:
                fragment = mFragmensts[3];
                break;
        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment).commit();
        }
    }
}