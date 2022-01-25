package com.sebastiani.datapersonalsebastiani;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;

public class CommunityFragment extends Fragment {

    @Override
    public View onCreateView(

        @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.community_fragment,container,false);

        MaterialButton backButton = view.findViewById(R.id.btn_Back_comu);

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ((NavigationHost)getActivity()).navigateTo(new SettingFragment(), false);
            }
        });


        return view;
    }
}
