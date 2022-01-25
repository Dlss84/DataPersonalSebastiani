package com.sebastiani.datapersonalsebastiani;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class SettingFragment extends Fragment {

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.setting_fragment,container,false);


        MaterialButton btnSettingButton = view.findViewById(R.id.mayor_personal_data);
        btnSettingButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //((NavigationHost)getActivity()).navigateTo(new PersonalDataFragment(), false);
            }
        });


        MaterialButton btnCommuButton = view.findViewById(R.id.mayor_community);
        btnCommuButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ((NavigationHost)getActivity()).navigateTo(new CommunityFragment(), false);
            }
        });

        MaterialButton exitButton = view.findViewById(R.id.exit);
        exitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ((NavigationHost)getActivity()).navigateTo(new LoginFragment(), false);
            }
        });



        return view;
    }
}
