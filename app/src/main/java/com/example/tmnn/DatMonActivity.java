package com.example.tmnn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DatMonActivity extends AppCompatActivity {
    private TextView txtMon=null;
    private EditText edtTenKH=null;
    private EditText edtDiaChi=null;
    private Button btnDat=null;
    private static final String LOG_TAG = "AndroidExample";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_mon);
        txtMon=(TextView)findViewById(R.id.txtMon);
        edtTenKH=(EditText)findViewById(R.id.edtTenKH);
        edtDiaChi=(EditText)findViewById(R.id.edtDiaChi);
        btnDat=(Button)findViewById(R.id.btnDat);
        Intent intent= getIntent();
        //String Mon=intent.getStringExtra("Mon");
        txtMon.setText(intent.getStringExtra("Mon"));
        btnDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "0938288724";
                String msgKH =  "Họ tên: "+edtTenKH.getText()+"\n";
                String msgDiaChi =  "Địa chỉ: "+edtDiaChi.getText()+"\n";
                String msgMon = "Món ăn đã đặt:"+"\n"+txtMon.getText();
                String message = msgKH+msgDiaChi+msgMon;
                Uri uri = Uri.parse("smsto:" + phoneNumber);

                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);
                // Add the message at the sms_body extra field
                smsIntent.putExtra("sms_body", message);
                try {
                    startActivity(smsIntent);
                } catch (Exception ex) {
                    Log.e(LOG_TAG, "Đăt hàng không thành công... " + ex.getMessage(), ex);
                    Toast.makeText(DatMonActivity.this, "Đặt hàng không thành công... " + ex.getMessage(),
                            Toast.LENGTH_LONG).show();
                    ex.printStackTrace();
                }
            }
        });
    }
}