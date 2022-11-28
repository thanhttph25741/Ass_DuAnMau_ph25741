package thanhttph25741.fpoly.ass_duanmau_ph25741;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import thanhttph25741.fpoly.ass_duanmau_ph25741.dao.SachDao;
import thanhttph25741.fpoly.ass_duanmau_ph25741.dao.ThuThuDAO;
import thanhttph25741.fpoly.ass_duanmau_ph25741.fragment.QLLoaiSachFragment;
import thanhttph25741.fpoly.ass_duanmau_ph25741.fragment.QLPhieuMuonFragment;
import thanhttph25741.fpoly.ass_duanmau_ph25741.fragment.QLSachFragment;
import thanhttph25741.fpoly.ass_duanmau_ph25741.fragment.QLThanhVienfragment;
import thanhttph25741.fpoly.ass_duanmau_ph25741.fragment.ThongkeDoanhThuFragment;
import thanhttph25741.fpoly.ass_duanmau_ph25741.fragment.ThongkeTop10Fragment;

public class MainActivity extends AppCompatActivity {
 DrawerLayout  drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SachDao dao = new SachDao(this);
        dao.getDSDauSach();

        Toolbar toolbar = findViewById(R.id.toolBar);
        NavigationView navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerlayout);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.mQLPhieuMuon:
                       fragment = new QLPhieuMuonFragment();
                       break;
                    case R.id.mQLLoaiSach:
                       fragment = new QLLoaiSachFragment();
                       break;
                    case R.id.mThoat:
                        Intent intent = new Intent(MainActivity.this, dangNhapActivity.class);
                      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                      startActivity(intent);
                    case R.id.mDoiMatKhau:
                        showDiaLogDoiMk();
                        break;
                    case R.id.mTop10:
                        fragment = new ThongkeTop10Fragment();
                        break;
                    case R.id.mDoanhThu:
                        fragment = new ThongkeDoanhThuFragment();
                        break;
                    case R.id.mQLThanhVien:
                        fragment = new QLThanhVienfragment();
                        break;
                    case R.id.mQLSach:
                        fragment = new QLSachFragment();
                        break;
                    default:
                        fragment = new QLPhieuMuonFragment();
                        break;
                }
                if(fragment !=  null){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.framelayout,fragment)
                            .commit();
                    toolbar.setTitle(item.getTitle());
                }


                drawerLayout.closeDrawer(GravityCompat.START);

                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if(item.getItemId() == android.R.id.home){
          drawerLayout.openDrawer(GravityCompat.START);
       }
        return super.onOptionsItemSelected(item);
    }
    private void showDiaLogDoiMk(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this)
                .setNegativeButton("Cap Nhat", null)
                .setPositiveButton("Huy", null);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doimk, null);
        EditText edtoldpass = view.findViewById(R.id.edtpassold);
        EditText edtnewpass = view.findViewById(R.id.edtpassnew);
        EditText edtrenewpass = view.findViewById(R.id.edtrenewpass);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldpass = edtoldpass.getText().toString();
                String newPass = edtnewpass.getText().toString();
                String renewPass = edtrenewpass.getText().toString();
                if(oldpass.equals("")|| newPass.equals("")|| renewPass.equals("")){
                    Toast.makeText(MainActivity.this, "Vui long nhap day du thong tin", Toast.LENGTH_SHORT).show();
                }
                if(newPass.equals(renewPass)){
                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
                    String matt = sharedPreferences.getString("matt","");

                    ThuThuDAO thuThuDAO = new ThuThuDAO(MainActivity.this);
                    int check = thuThuDAO.capNhatMatKhau(matt, oldpass, newPass);
                    if(check == 1 ){
                        Toast.makeText(MainActivity.this, "Cap nhat mat khau thanh cong", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, dangNhapActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else if(check==0){
                        Toast.makeText(MainActivity.this, "Mat khau cu khong dung", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "that bai", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, " Mat khau khong trung nhau", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}