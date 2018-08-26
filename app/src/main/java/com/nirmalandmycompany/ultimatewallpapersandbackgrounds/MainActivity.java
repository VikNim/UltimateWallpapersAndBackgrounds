package com.nirmalandmycompany.ultimatewallpapersandbackgrounds;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Integer[] WallpaperArray = {
            R.drawable.thumbs1,R.drawable.thumbs2,
            R.drawable.thumbs3,R.drawable.thumbs4,
            R.drawable.thumbs5,R.drawable.thumbs6
    };
    GridView mainGridView;
    ImageView currentWallpaper;
    Drawable imageDrawable;
    WallpaperManager wallManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGridView = findViewById(R.id.MainGridView);
        currentWallpaper = findViewById(R.id.CurrentWallpaper);

        mainGridView.setAdapter(new ImageAdapter(getApplicationContext()));

        UpdateWallpaper();

    }
    private void UpdateWallpaper () {
        wallManager = WallpaperManager.getInstance(getApplicationContext());
        imageDrawable = wallManager.getDrawable();
        currentWallpaper.setImageDrawable(imageDrawable);
    }


    public class ImageAdapter extends BaseAdapter {
        Context imageContext;

        public ImageAdapter(Context applicationContext) {
            imageContext = applicationContext;
        }


        @Override
        public int getCount() {
            return WallpaperArray.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            final ImageView GridImageView;

            if( view == null){
                GridImageView = new ImageView(imageContext);
                GridImageView.setLayoutParams(new GridView.LayoutParams(512,512));
                GridImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
            else {
                GridImageView = (ImageView) view;
            }

            GridImageView.setImageResource(WallpaperArray [i]);

            GridImageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return false;
                }
            });

            GridImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        wallManager.setResource(WallpaperArray [i]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    UpdateWallpaper();
                }
            });

            return GridImageView;
        }
    }
}
