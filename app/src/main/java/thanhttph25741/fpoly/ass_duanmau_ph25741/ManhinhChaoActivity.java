package thanhttph25741.fpoly.ass_duanmau_ph25741;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ManhinhChaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinh_chao);

        ImageView ivlogo = findViewById(R.id.ivLogo);

        Glide.with(this)
                .load(R.mipmap.bookk)
                .into(ivlogo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
startActivity(new Intent(ManhinhChaoActivity.this, dangNhapActivity.class));
            }
        },3000);
    }
}