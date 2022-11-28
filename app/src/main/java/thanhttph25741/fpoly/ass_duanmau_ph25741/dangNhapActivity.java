package thanhttph25741.fpoly.ass_duanmau_ph25741;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import thanhttph25741.fpoly.ass_duanmau_ph25741.dao.ThuThuDAO;

public class dangNhapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        EditText edtUser = findViewById(R.id.edUser);
        EditText edtPass = findViewById(R.id.edPass);
        Button btnDangNhap = findViewById(R.id.btnDangNhap);

        ThuThuDAO thuThuDAO = new ThuThuDAO(this);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                if(thuThuDAO.checkdangNhap(user,pass)){
                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("matt", user);
                    editor.commit();

                    startActivity(new Intent(dangNhapActivity.this, MainActivity.class));
                }else {
                    Toast.makeText(dangNhapActivity.this, "Username và Mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}