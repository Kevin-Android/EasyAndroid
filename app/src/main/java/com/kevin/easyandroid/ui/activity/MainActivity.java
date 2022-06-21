package com.kevin.easyandroid.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.kevin.easyandroid.Injection;
import com.kevin.easyandroid.MainViewModel;
import com.kevin.easyandroid.MainViewModelFactory;
import com.kevin.easyandroid.R;
import com.kevin.easyandroid.ui.fragment.AudioFragment;
import com.kevin.easyandroid.ui.fragment.ImageFragment;
import com.kevin.easyandroid.ui.fragment.VideoFragment;
import com.kevin.easyandroid.ui.view.SlantedTextView;
import com.kevin.room_library.Group;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;


public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener {

    private MaterialToolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private AppBarLayout mAppBarLayout;
    private TabLayout mTabLayout;
    private MainViewModel mainViewModel;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private ArrayList<Group> arrayList;
    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabLayout = findViewById(R.id.home_tab_layout);
        mToolbar = findViewById(R.id.tool_bar);
        mAppBarLayout = findViewById(R.id.app_bar);
        mDrawerLayout = findViewById(R.id.home_drawer);
        mNavigationView = findViewById(R.id.home_nav_view);
        mFrameLayout = findViewById(R.id.home_frame_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, 0, 0);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);   //设置监听
        actionBarDrawerToggle.syncState(); //同步
        mDrawerLayout.addDrawerListener(this);
        SlantedTextView mSlantedTextView = mNavigationView.getHeaderView(0).findViewById(R.id.name_label);
        mSlantedTextView.setText(packageName(this));
        //设置navigationView中菜单的点击事件
        mNavigationView.setNavigationItemSelectedListener(item -> {
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
            mDrawerLayout.closeDrawers();
            return true;
        });
        TabLayout.Tab tab = mTabLayout.getTabAt(mTabLayout.getSelectedTabPosition());
        int whiteColor = ContextCompat.getColor(MainActivity.this, R.color.white);
        int purple200Color = ContextCompat.getColor(MainActivity.this, R.color.purple_200);
        if (tab != null && tab.getIcon() != null) {
            tab.getIcon().setColorFilter(whiteColor, PorterDuff.Mode.SRC_IN);
        }
        replaceFragment(ImageFragment.newInstance());
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getIcon() != null) {
                    tab.getIcon().setColorFilter(whiteColor, PorterDuff.Mode.SRC_IN);
                }
                if (tab.getPosition() == 0) {
                    replaceFragment(ImageFragment.newInstance());
                } else if (tab.getPosition() == 1) {
                    replaceFragment(AudioFragment.newInstance());
                } else if (tab.getPosition() == 2) {
                    replaceFragment(VideoFragment.newInstance());
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getIcon() != null) {
                    tab.getIcon().setColorFilter(purple200Color, PorterDuff.Mode.SRC_IN);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        MainViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
        mainViewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);

    }

    // 动态切换fragment
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.home_frame_layout, fragment);
        //添加这个表示用了栈
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private String packageName(Context context) {
        PackageManager manager = context.getPackageManager();
        String name;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            name = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "err";
        }
        return name;
    }


    /**
     * 当抽屉位置改变时调用。
     *
     * @param drawerView  被移动的子视图
     * @param slideOffset 此抽屉在其范围内的新偏移量，从 0-1
     */
    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

    }

    /**
     * 当抽屉处于完全打开状态时调用。
     * 此时抽屉是交互式的。
     *
     * @param drawerView 现在打开的抽屉视图
     */
    @Override
    public void onDrawerOpened(@NonNull View drawerView) {
        mAppBarLayout.setElevation(12);
    }

    /**
     * 当抽屉处于完全关闭状态时调用。
     *
     * @param drawerView 现在关闭的抽屉视图
     */
    @Override
    public void onDrawerClosed(@NonNull View drawerView) {
        mAppBarLayout.setElevation(0);
    }

    /**
     * 当抽屉运动状态改变时调用。
     *
     * @param newState 新的抽屉运动状态
     */
    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    protected void onStart() {
        super.onStart();
//        mDisposable.add(
//                mainViewModel.getAllGroup()
//                        .map(new Function<List<Group>, List<Group>>() {
//                            @Override
//                            public List<Group> apply(List<Group> groups) {
//                                Timber.e("apply %s", groups);
//                                return groups;
//                            }
//                        }).subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Consumer<List<Group>>() {
//                            @Override
//                            public void accept(List<Group> list) {
//                                Timber.e("accept : %s", list.toString());
//                            }
//                        })
//        );
    }

    @Override
    protected void onStop() {
        super.onStop();

        // clear all the subscriptions
        mDisposable.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDrawerLayout != null) {
            mDrawerLayout.removeDrawerListener(this);
        }
    }
}