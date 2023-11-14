package com.example.dhd_cinema_duan1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    private TextView tv;
    private String ul;
    private RecyclerView rcv;
    private Adapteranh adapter;
    private ArrayList<anhmodel> list;
    private anhdao anhdao;
    private Button add;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.img);
        tv = findViewById(R.id.hh);
        rcv = findViewById(R.id.rcv);
        add = findViewById(R.id.them);

        anhdao = new anhdao(MainActivity.this);
        list = anhdao.getAllGhe();

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rcv.setLayoutManager(layoutManager);
        adapter = new Adapteranh(MainActivity.this, list);
        rcv.setAdapter(adapter);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                imagePickerLauncher.launch(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ul != null && !ul.isEmpty()) {
                    anhmodel anhmodel = new anhmodel();
                    anhmodel.setAnh(ul);

                    if (anhdao.addanh(anhmodel)) {
                        Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(anhdao.getAllGhe());
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Chưa chọn ảnh", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private final ActivityResultLauncher<Intent> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri imageUri = result.getData().getData();
                    if (imageUri != null) {
                        ul = getPathFromUri(imageUri);
                        img.setImageURI(imageUri);
                        tv.setText(ul);
                    }
                }
            }
    );

    private String getPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            try {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                return cursor.getString(columnIndex);
            } finally {
                cursor.close();
            }
        }
        return uri.getPath();
    }
}
