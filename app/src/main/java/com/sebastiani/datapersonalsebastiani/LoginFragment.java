package com.sebastiani.datapersonalsebastiani;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment {


    private DataBaseHelper dataBaseHelper;
    private Context context;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedinstanceState){
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        //
        final TextInputLayout usernameTextInput = view.findViewById(R.id.username_text_input);
        final TextInputEditText usernameTextEditText = view.findViewById(R.id.username_edit_text);


        //Login
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText passwordTextEditText = view.findViewById(R.id.password_edit_text);

        //boton login
        MaterialButton loginButton = view.findViewById(R.id.btn_login);
        //boton register
        MaterialButton registerButton = view.findViewById(R.id.btn_register);

        //instanciar la BD
        dataBaseHelper = new DataBaseHelper(this.getContext());

        //Registro
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getUsername = usernameTextEditText.getText().toString();
                String getPassword = passwordTextEditText.getText().toString();
                dataBaseHelper.registerUser(new Data(getUsername, getPassword));
            }
        });

        //LOGIN
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getUsername = usernameTextEditText.getText().toString();
                String getPassword = passwordTextEditText.getText().toString();
                dataBaseHelper.loginUser(new Data(getUsername, getPassword));

                if(!isPasswordValid(passwordTextEditText.getText())){
                    passwordTextInput.setError(getString(R.string.error_password));
                }else{
                    passwordTextInput.setError(null);
                    ((NavigationHost) getActivity()).navigateTo(new SettingFragment(), false);
                }
            }
        });

        passwordTextEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(isPasswordValid(passwordTextEditText.getText())){
                    passwordTextInput.setError(null);
                }
                return false;
            }
        });

        return  view;
    }



    private boolean isPasswordValid(@NonNull Editable text){
        return text != null && text.length()>=4;
    }
}
