package com.dybcatering.live4teach.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dybcatering.live4teach.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.PrincipalActivity;
import com.dybcatering.live4teach.R;

public class PreLoginActivity extends AppCompatActivity {

    TextView textView;

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);
       // new CheckInternetConnection(this).checkConnection();
            textView = findViewById(R.id.text_registro);
            button = findViewById(R.id.btn_loginestu2);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PreLoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PreLoginActivity.this, PrincipalActivity.class);
                    startActivity(intent);
                }
            });

    }
}
