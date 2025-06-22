package com.amonlexasoftware.yaradio.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.amonlexasoftware.yaradio.databinding.FragmentHomeBinding;
import com.amonlexasoftware.yaradio.support.Streams;
import com.amonlexasoftware.yaradio.ui.adapter.RadioAdapter;
import com.amonlexasoftware.yaradio.models.RadioModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    ArrayList<RadioModel> radioList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);


        loadRadios();



        return binding.getRoot();
    }

    private void loadRadios() {
        Streams.getRadios(radioList);
        binding.rvMain.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvMain.setAdapter(new RadioAdapter(radioList,getContext()));
    }
}
