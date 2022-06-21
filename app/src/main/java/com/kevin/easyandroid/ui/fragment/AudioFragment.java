package com.kevin.easyandroid.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kevin.easyandroid.R;

/**
 * @author : 王康
 * @date : 2022/6/21
 * @desc :
 */
public class AudioFragment extends Fragment {

    public static AudioFragment newInstance() {
        return new AudioFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audio, container, false);
        return view;
    }
}
