package com.example.activity_maim;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private final ImageView gameImage;

    public DownloadImageTask(ImageView gameImage) {
        this.gameImage = gameImage;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String uri = urls[0];
        InputStream in = null;
        try {
            in = new URL(uri).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(in);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        gameImage.setImageBitmap(bitmap);
    }
}