package com.bilab.lunsenluandroid.main.setting;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bilab.lunsenluandroid.PrivacyPolicyActivity;
import com.bilab.lunsenluandroid.R;
import com.bilab.lunsenluandroid.conf.Constant;

public class SettingFragment extends Fragment {
    LinearLayout health_passport,info,account,private_policy, dna_methylation, survey;

    ImageView test;


    private SettingViewModel settingViewModel;

    Intent intent;

    String email;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingViewModel =
                ViewModelProviders.of(this).get(SettingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_setting, container, false);

        intent = getActivity().getIntent();
        email = (intent != null ) ? intent.getStringExtra("email") : "";

        health_passport = root.findViewById(R.id.setting_health_passport);
        info = root.findViewById(R.id.setting_info);
        account = root.findViewById(R.id.setting_account);
        private_policy = root.findViewById(R.id.setting_private_policy);
        test = root.findViewById(R.id.setting_health_passport_right);

        dna_methylation = root.findViewById(R.id.dna_methylation);
        survey = root.findViewById(R.id.survey);



        health_passport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), HealthPassActivity.class);
                startActivity(intent);
            }
        });
        dna_methylation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), GeneRegisterActivity.class);
                startActivity(intent);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "目前暫無公告", Toast.LENGTH_SHORT).show();
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), PersonDataActivity.class);
                startActivity(intent);
            }
        });

        private_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPrivacyPolicyIntent = new Intent(getActivity(), PrivacyPolicyActivity.class);
                openPrivacyPolicyIntent.putExtra(Constant.EXTRA_STARTER_ACTIVITY_NAME, getActivity().getClass().getName()); // only works for activity
                startActivity(openPrivacyPolicyIntent);
            }
        });

        survey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/1RXnjQdirYI9rL0-4JvErdhp-5w_sDW3I6SeUnX6NiDU/edit"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return root;
    }

}