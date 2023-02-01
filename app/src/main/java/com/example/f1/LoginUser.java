package com.example.f1;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class LoginUser {
    public static void putDataToDB(User user, Context context) {
        if (!user.getEmail().equals("") && !user.getPassword().equals("")) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String[] field = {"username", "password"};
                    String[] data = {user.getUsername(), user.getPassword()};
                    PutData putData = new PutData("http://10.0.14.10/f1/login.php", "POST", field, data);

                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Login Success")) {
                                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            } else {
                                //
                            }
                            Log.i("PutData", result);
                        }
                    }
                }
            });
        }
    }
}
