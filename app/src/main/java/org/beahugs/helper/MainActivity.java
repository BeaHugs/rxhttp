package org.beahugs.helper;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import org.beahugs.common.weiget.btmbar.Anim;
import org.beahugs.common.weiget.btmbar.RxNavigationBar;
import org.beahugs.common.weiget.menu.MenuView;
import org.beahugs.helper.activity.LoginActivity;
import org.beahugs.helper.fragment.HomeFragment;
import org.beahugs.helper.presenter.MainPresenter;
import org.beahugs.libs.base.BaseMvpActivity;

import java.util.ArrayList;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MenuView.MenuItemOnClickImpl {


    private String[] tabText = {"首页", "", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.tab_home_normal,  R.mipmap.add_image, R.mipmap.tab_me_normal};
    //选中时icon
    private int[] selectIcon = {R.mipmap.tab_home_selected,  R.mipmap.add_image, R.mipmap.tab_me_selected};



    private ArrayList<Fragment> fragmentArrayList;
    private RxNavigationBar rx_nav_bar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void getIntent(Intent intent) {
        mPresenter.dataInfo();
    }

    @Override
    protected void initView() {


        rx_nav_bar = findViewById(R.id.rx_nav_bar);


        fragmentArrayList = new ArrayList<>();

        HomeFragment homeFragment = new HomeFragment();
        HomeFragment homeFragment1 = new HomeFragment();
        HomeFragment homeFragment2 = new HomeFragment();


        fragmentArrayList.add(homeFragment);
        fragmentArrayList.add(homeFragment1);
        fragmentArrayList.add(homeFragment2);
        final MenuView menuView = new MenuView(rx_nav_bar,this);
        rx_nav_bar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragmentArrayList)
                .fragmentManager(getSupportFragmentManager())
               // .addLayoutRule(RxNavigationBar.RULE_BOTTOM)
               // .addLayoutBottom(100)
                .onTabClickListener(new RxNavigationBar.OnTabClickListener() {
                    @Override
                    public boolean onTabClickEvent(View view, int position) {
                        if (position == 4) {
                            Toast.makeText(MainActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                            //return true则拦截事件、不进行页面切换
                            return true;
                        } else if (position == 1) {
                            //跳转页面（全民K歌）   或者   弹出菜单（微博）
                            menuView.showMunu();

                           // startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        }
                        return false;
                    }
                })
                .mode(RxNavigationBar.MODE_ADD)
                .anim(Anim.ZoomIn)
                .build();

        menuView.setMenuItemOnClick(this);

        rx_nav_bar.setAddViewLayout(menuView.createWeiboView());



    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onRetry() {
        mPresenter.dataInfo();
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void showError(String msg) {

    }


    @Override
    public void onMenuItemClick(String path) {

    }
}
