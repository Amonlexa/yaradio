package com.amonlexasoftware.yaradio.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.amonlexasoftware.yaradio.R;
import com.amonlexasoftware.yaradio.databinding.FragmentInfoBinding;
import com.amonlexasoftware.yaradio.support.Config;

public class InfoFragment extends Fragment {

    FragmentInfoBinding binding;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInfoBinding.inflate(inflater, container, false);
        funButton();

        return binding.getRoot();
    }

    void funButton() {
        binding.btnInfoRadio.setOnClickListener(view -> callInfoRadioYkt());
        binding.btnInfoApp.setOnClickListener(view -> callDialogInfoApp());
        binding.btnCallDev.setOnClickListener(view -> callTelegram());
    }

    private void callInfoRadioYkt() {
        Dialog dialog;
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_info_radio);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnClose = dialog.findViewById(R.id.btnCloseDialog);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void callDialogInfoApp() {
        Dialog dialog;
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_info);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView imageView = dialog.findViewById(R.id.img1);
        Button btnClose = dialog.findViewById(R.id.btnCloseDialog);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void callTelegram() {
        Intent myintent;
        myintent = new Intent(Intent.ACTION_VIEW, Uri.parse(Config.telegram_dev()));
        startActivity(myintent);
        getActivity().finish();

    }
}
