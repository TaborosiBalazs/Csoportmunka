package com.example.f1;

import android.util.Log;
import android.os.Looper;
import android.os.Handler;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class RegisterUser
{
    public static void putDataToDB(User user)
    {
        if(!user.getEmail().equals("") && !user.getUsername().equals("") && !user.getPassword().equals(""))
        {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    String[] field = { "email", "username", "password" };
                    String[] data = { user.getEmail(), user.getUsername(), user.getPassword() };
                    PutData putData = new PutData("http://10.0.2.2/f1/signUp.php","POST", field, data);

                    if(putData.startPut())
                    {
                        if(putData.onComplete())
                        {
                            String result = putData.getResult();
                            Log.i("PutData",result);
                        }
                    }
                }
            });
        }
    }
}