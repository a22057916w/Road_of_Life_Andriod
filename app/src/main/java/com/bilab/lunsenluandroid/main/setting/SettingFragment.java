package com.bilab.lunsenluandroid.main.setting;

import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bilab.lunsenluandroid.R;

public class SettingFragment extends Fragment {
    LinearLayout health_passport,doctor_auth,info,account,private_policy;

    TextView logout;
    ImageView test;


    private SettingViewModel settingViewModel;

    Intent intent;

    String email;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingViewModel =
                ViewModelProviders.of(this).get(SettingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        /*final TextView textView = root.findViewById(R.id.text_notifications);
        settingViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        intent = getActivity().getIntent();
        email = (intent != null ) ? intent.getStringExtra("email") : "";

        health_passport = root.findViewById(R.id.setting_health_passport);
        doctor_auth = root.findViewById(R.id.setting_doctor_auth);
        info = root.findViewById(R.id.setting_info);
        account = root.findViewById(R.id.setting_account);
        private_policy = root.findViewById(R.id.setting_private_policy);
        logout = root.findViewById(R.id.setting_log_out);
        test = root.findViewById(R.id.setting_health_passport_right);





        health_passport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), HealthPassActivity.class);
                startActivity(intent);
            }
        });
        doctor_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), DoctorAuthActivity.class);
                startActivity(intent);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //asd.setText("123456789");
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), AccountPasswordActivity.class);
                startActivity(intent);
            }
        });

        private_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //asd.setText("123456789");
            }
        });

        return root;
    }

}