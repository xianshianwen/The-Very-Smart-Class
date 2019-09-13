package com.example.theverysmartclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.theverysmartclass.personal_center.PersonalActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private EditText AccountEdit;
    private EditText PswEdit;
    private Button Loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        Loginbtn = (Button) findViewById(R.id.Loginbtn);
        AccountEdit = (EditText) findViewById(R.id.AccountEdit);
        PswEdit = (EditText) findViewById(R.id.PswEdit);

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = AccountEdit.getText().toString();
                final String password = PswEdit.getText().toString();
                //服务端地址
                final String serverPath = "http://10.0.2.2:8080/TestService/servlet/LoginDateServlet";
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                URL url = new URL(serverPath + "?username=" + username + "&password=" + password);
                                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                                httpURLConnection.setRequestMethod("GET");
                                httpURLConnection.setConnectTimeout(5000);
                                int responseCode = httpURLConnection.getResponseCode();
                                Log.i("Get", "Get方法返回值:" + responseCode);
                                if (responseCode == 200) {
                                    InputStream inputStream = httpURLConnection.getInputStream();
                                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                                    String responseMsg = bufferedReader.readLine();
                                    JSONObject jsonObj = new JSONObject(responseMsg);
                                    final String code = jsonObj.getString("code");//获取json中返回代码值
                                    Log.i("Get", "返回内容" + responseMsg + "->" + jsonObj.getString("code"));
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (code.equals("-1")) {
                                                Toast.makeText(MainActivity.this, "登录成功！", Toast.LENGTH_LONG).show();

                                            } else {
                                                Toast.makeText(MainActivity.this, "登录失败！", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                    bufferedReader.close();
                                    httpURLConnection.disconnect();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                Intent intent = new Intent(MainActivity.this, PersonalActivity.class);
                startActivity(intent);
            }
        });
    }
}
