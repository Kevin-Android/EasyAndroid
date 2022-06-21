package com.kevin.easyandroid.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.kevin.easyandroid.ImageViewModel;
import com.kevin.easyandroid.ImageViewModelFactory;
import com.kevin.easyandroid.Injection;
import com.kevin.easyandroid.MainViewModel;
import com.kevin.easyandroid.MainViewModelFactory;
import com.kevin.easyandroid.R;
import com.kevin.room_library.Group;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

/**
 * @author : 王康
 * @date : 2022/6/21
 * @desc :
 */
public class ImageFragment extends Fragment {

    private ImageViewModel imageViewModel;
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public static ImageFragment newInstance() {
        return new ImageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageViewModelFactory imageViewModelFactory = Injection.provideImageViewModelFactory(getContext());
        imageViewModel = new ViewModelProvider(this, imageViewModelFactory).get(ImageViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        mDisposable.add(
                imageViewModel.getAllGroup()
                        .map(new Function<List<Group>, List<Group>>() {
                            @Override
                            public List<Group> apply(List<Group> groups) {
                                Timber.e("apply %s", groups);
                                return groups;
                            }
                        }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<Group>>() {
                            @Override
                            public void accept(List<Group> list) {
                                Timber.e("accept : %s", list.toString());
                            }
                        })
        );
    }


    @Override
    public void onStop() {
        super.onStop();
        mDisposable.clear();
    }
}
