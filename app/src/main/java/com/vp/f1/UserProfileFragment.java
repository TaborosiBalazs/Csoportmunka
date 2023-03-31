package com.vp.f1;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.android.gms.tasks.OnCompleteListener;

public class UserProfileFragment extends Fragment
{
    private FirebaseAuth firebaseAuth;
    private Button changePasswordButton;
    private EditText oldPasswordEditText;
    private EditText newPasswordEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_user_profile_fragment, container, false);

        TextView userEmailTextView = view.findViewById(R.id.user_email);
        Bundle bundle = getArguments();

        if (bundle != null)
        {
            String userEmail = bundle.getString("email", "");
            userEmailTextView.setText(userEmail);
        }

        firebaseAuth = FirebaseAuth.getInstance();
        oldPasswordEditText = view.findViewById(R.id.old_password);
        newPasswordEditText = view.findViewById(R.id.new_password);
        changePasswordButton = view.findViewById(R.id.change_password_button);

        changePasswordButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });

        return view;
    }

    private void changePassword()
    {
        String oldPassword = oldPasswordEditText.getText().toString();
        String newPassword = newPasswordEditText.getText().toString();

        if (TextUtils.isEmpty(oldPassword))
        { oldPasswordEditText.setError("OLD PASSWORD IS REQUIRED"); return; }
        if (TextUtils.isEmpty(newPassword))
        { newPasswordEditText.setError("NEW PASSWORD IS REQUIRED"); return; }
        if (newPassword.length() < 6)
        { newPasswordEditText.setError("PASSWORD MUST BE AT LEAST 6 CHARACTERS"); return; }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null)
        {
            AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), oldPassword);

            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>()
            {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    if (task.isSuccessful())
                    {
                        user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                if (task.isSuccessful()) Toast.makeText(getActivity(), "PASSWORD CHANGED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                                else Toast.makeText(getActivity(), "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else Toast.makeText(getActivity(), "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}