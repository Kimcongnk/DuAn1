package nhom1.fpoly.duan1.view.customer.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.view.customer.CustomerActivity;

public class SuccessOder extends AppCompatActivity {
private ImageView imageView;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplication(), CustomerActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan_ok);
         imageView = findViewById(R.id.imageView);
        Glide.with(this).asGif().load(R.drawable.git_oder_ok).into(imageView);
        Button tiepTuc = findViewById(R.id.button2);
        tiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), CustomerActivity.class));
            }
        });
    }

}