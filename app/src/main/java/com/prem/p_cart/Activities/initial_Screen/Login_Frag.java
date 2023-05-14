package com.prem.p_cart.Activities.initial_Screen;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.prem.p_cart.Activities.Home_Screen.Side_Nev_Home_Activity;
import com.prem.p_cart.Activities.Profile.Profile_Activity;
import com.prem.p_cart.R;
import com.prem.p_cart.Utilities.SessionManager;
import com.prem.p_cart.databinding.FragmentLoginBinding;


public class Login_Frag extends Fragment {

    FragmentLoginBinding binding;


    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "GOOGLEAUTH";
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    Dialog dialog;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        binding = FragmentLoginBinding.bind(view);

        // =========================== open sign in screen =======================================
        binding.signInBtn.setOnClickListener(view1 ->
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.initial_act, new SignIn_Frag()).addToBackStack(null).commit()
        );


        // =========================== open Sign Up / Register screen =======================================
        binding.signUpBtn.setOnClickListener(view1 ->
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.initial_act, new Registar_Frag()).addToBackStack(null).commit()
        );


        // ============================ call the social media API =====================================
        // ----------- twitter -------------
        binding.twitterBtn.setOnClickListener(view1 -> {
            // code .....................
            Toast.makeText(getContext(), "GoogleAPI Implemented Only", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getContext(), Profile_Activity.class));

        });

        // ----------- google -------------
        binding.googleBtn.setOnClickListener(view1 -> {
            google();
        });

        // ----------- facebook -------------
        binding.fbBtn.setOnClickListener(view1 -> {
            // code .....................
            Toast.makeText(getContext(), "GoogleAPI Implemented Only", Toast.LENGTH_SHORT).show();

        });

        // ----------- Instagram -------------
        binding.instaBtn.setOnClickListener(view1 -> {
            // code .....................
            Toast.makeText(getContext(), "GoogleAPI Implemented Only", Toast.LENGTH_SHORT).show();
        });


        return view;
    }

    private void google() {
        mAuth = FirebaseAuth.getInstance();

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(),gso);
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);

        signIn();
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            dialog.show();
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                //Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                dialog.dismiss();
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            dialog.dismiss();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //  Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //  Snackbar.make(mBinding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            // updateUI(null);
                            dialog.dismiss();
                            Toast.makeText(getContext(),"Login failed",Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        new SessionManager(getContext()).localStoreLoginDetail(user.getEmail());
        startActivity(new Intent(getContext(), Side_Nev_Home_Activity.class));
    }
}
