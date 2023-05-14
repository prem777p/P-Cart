package com.prem.p_cart.Activities.initial_Screen;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.prem.p_cart.Activities.Home_Screen.Side_Nev_Home_Activity;
import com.prem.p_cart.R;
import com.prem.p_cart.Utilities.SessionManager;
import com.prem.p_cart.databinding.FragmentRegistarBinding;



public class Registar_Frag extends Fragment {

    FragmentRegistarBinding binding;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registar, container, false);
        binding = FragmentRegistarBinding.bind(view);

        //  email formatting
        binding.emailAddressEdT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(Patterns.EMAIL_ADDRESS.matcher(binding.emailAddressEdT.getText().toString()).matches()){
                    // click on button
                    binding.signUpBtn.setOnClickListener(view1 -> {
                        // data base code ......................

                        if(TextUtils.isEmpty(binding.emailAddressEdT.getText().toString()) || TextUtils.isEmpty(binding.passwordEdT.getText().toString())){
                            Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            progressDialog = new ProgressDialog(getContext());
                            progressDialog.setMessage("Register...");
                            progressDialog.show();
                            progressDialog.setCancelable(false);
                            registerFirebase();
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        // =========================== open sign in screen =======================================
        binding.signInTvBtn.setOnClickListener(view1 ->
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.initial_act, new SignIn_Frag()).addToBackStack(null).commit()
        );
        return view;
    }

    private void registerFirebase(){

        String firstName = binding.userFnameEdT.getText().toString();
        String lastName = binding.userLnameEdT.getText().toString();
        String phone = binding.phoneNoEdT.getText().toString();
        String email = binding.emailAddressEdT.getText().toString();
        String password = binding.passwordEdT.getText().toString();
        String conformPassword = binding.conformPasswordEdT.getText().toString();

        if(TextUtils.isEmpty(firstName) ||
           TextUtils.isEmpty(lastName)  ||
           TextUtils.isEmpty(phone)     ||
           TextUtils.isEmpty(email)     ||
           TextUtils.isEmpty(password)  ||
           TextUtils.isEmpty(lastName)){
            Toast.makeText(getContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(conformPassword)) {
            Toast.makeText(getContext(), "Password not match", Toast.LENGTH_SHORT).show();
        } else {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(getContext(), "successfully", Toast.LENGTH_SHORT).show();

                    mAuth = FirebaseAuth.getInstance();
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                    progressDialog.hide();

                }
                else {
                    progressDialog.hide();
                    Log.e("kjgh", String.valueOf(task.getResult()));
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void updateUI(FirebaseUser user) {
        new SessionManager(getContext()).localStoreLoginDetail(user.getEmail());
        startActivity(new Intent(getContext(), Side_Nev_Home_Activity.class));
    }


}