package com.prem.p_cart.Activities.initial_Screen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.prem.p_cart.Activities.Home_Screen.Side_Nev_Home_Activity;
import com.prem.p_cart.R;
import com.prem.p_cart.Utilities.SessionManager;
import com.prem.p_cart.databinding.FragmentSignInBinding;


public class SignIn_Frag extends Fragment {

    FragmentSignInBinding binding;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.fragment_sign_in, container, false);
       binding = FragmentSignInBinding.bind(view);

        // =========================== Sign in User =======================================
        //  email formatting
        binding.userFnameEdT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(Patterns.EMAIL_ADDRESS.matcher(binding.userFnameEdT.getText().toString()).matches()){
                    // click on button
                    binding.signUpBtn.setOnClickListener(view1 -> {
                        // data base code ......................

                        if(TextUtils.isEmpty(binding.userFnameEdT.getText().toString()) || TextUtils.isEmpty(binding.passwordEdT.getText().toString())){
                            Toast.makeText(getContext(), "Email not Matc", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            progressDialog = new ProgressDialog(getContext());
                            progressDialog.setMessage("Login...");
                            progressDialog.show();
                            progressDialog.setCancelable(false);
                            signIn();
                        }
                    });
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {  } });


        // =========================== open Sign Up / Register screen =======================================
        binding.signUpTvBtn.setOnClickListener(view1 ->
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.initial_act, new Registar_Frag()).addToBackStack(null).commit()
        );

       return view;
    }

    private void signIn() {
        String email = binding.userFnameEdT.getText().toString();
        String password = binding.passwordEdT.getText().toString();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                new SessionManager(requireContext()).localStoreLoginDetail(email);
                startActivity( new Intent(getContext(), Side_Nev_Home_Activity.class));
                progressDialog.hide();
            }
            else {
                progressDialog.hide();
                Toast.makeText(getContext(), "Email And Password not wrong!! ", Toast.LENGTH_SHORT).show();
            }
        });
    }

}