package com.example.activity_maim;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;

import static com.example.activity_maim.R.id.photoselect;


public class fundin extends AppCompatActivity {

    private final int GET_GALLERY_IMAGE = 200;
    private ImageView imageview;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundin);
        imageview = (ImageView) findViewById(R.id.imageView);


        String strDate;
        TextView Datepick;
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm", java.util.Locale.getDefault());
        strDate = dateFormat.format(date);
        Datepick = (TextView) findViewById(R.id.date);
        Datepick.setText(strDate);

        EditText content;
        content = (EditText) findViewById(R.id.content);
        String contents = content.getText().toString();
        contents = contents.replace("'", "''");

        final TextView tv = (TextView) findViewById(R.id.textView1);
        Spinner s = (Spinner) findViewById(R.id.spinner);
        Spinner s1 = (Spinner) findViewById(R.id.spinner1);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                tv.setText("카테고리 : " +
                        parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        final TextView tv1 = (TextView) findViewById(R.id.textView2);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                tv1.setText("펀딩기간 : " +
                        parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ImageButton btnPhoto;
        btnPhoto = (ImageButton) findViewById(R.id.photoselect);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnIntent = new Intent(Intent.ACTION_PICK);
                btnIntent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(btnIntent, GET_GALLERY_IMAGE);
            }
        });

            }
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {

                if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

                    Uri selectedImageUri = data.getData();
                    imageview.setImageURI(selectedImageUri);
                }
            }
        }

