package com.example.activity_maim;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
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
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundin);



        String strDate;
        TextView Datepick;
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm", java.util.Locale.getDefault());
        strDate = dateFormat.format(date);
        Datepick = (TextView) findViewById(R.id.date);
        Datepick.setText(strDate);

        EditText content;
        content = (EditText)findViewById(R.id.content);
        String contents = content.getText().toString();
        contents = contents.replace("'","''");

        final TextView tv = (TextView)findViewById(R.id.textView1);
        Spinner s = (Spinner)findViewById(R.id.spinner);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                tv.setText("카테고리 : " +
                        parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        /*ImageButton btnPhoto;
        btnPhoto = (ImageButton) findViewById(R.id.photoselect);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case (R.id.photoselect):

                        Intent intent3 = new Intent(Intent.ACTION_PICK);
                        intent3.setType(MediaStore.Images.Media.CONTENT_TYPE);
                        startActivityForResult(intent3, PICK_FROM_ALBUM);

                        break;
                }
            }
        };
    }
    ImageView simpleview;
    Bitmap image_bitmap;
    simpleview = (ImageView) findViewByld(R.id.Simpleview);
    int width;
    width = getWindowManager(),getDefaultDisplay(),getWidth();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICKALBUM){
            if (requestCode == RESULT_OK){
                try {
                    Uri mlmageUri = data.getData();
                    String realpaths = getRealImagepath(mlmageUri);

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 2;
                    image_bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(mlmageUri), null,options);
                    int resizeWidth = width;
                    int resizeHeight = width * image_bitmap.getHeight()/image_bitmap.getWidth();
                    image_bitmap = Bitmap.createScaledBitmap(image_bitmap, resizeWidth, resizeHeight, true);*/
}}

