package com.dpt.TNetwork;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.dpt.TNetwork.net.util.NetClient;

/**
 * Created by dupengtao on 2014/6/17.
 */
public class NetImageActivity extends Activity {

    private ImageView iv1, iv2, iv3, iv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        iv4 = (ImageView) findViewById(R.id.iv4);
    }

    public void loadImages(View v) {
        NetClient.loadImage(imageUrls[0],iv1,R.drawable.loading,R.drawable.ic_launcher);
        //set image size
        NetClient.loadImage(imageUrls[1],iv2,R.drawable.loading,R.drawable.ic_launcher,100,100);
        //add admin
        NetClient.loadImageWithAnim(this,imageUrls[2],iv3,R.drawable.loading,R.drawable.ic_launcher,R.anim.fade_in);
        //no cache
        NetClient.loadImage(imageUrls[3],iv4,R.drawable.loading,R.drawable.ic_launcher,0,0,false, Bitmap.Config.ARGB_4444,"dpt");
    }

    private static String[] imageUrls = {
            "http://test.designer.c-launcher.com/resources/wallpaper/img/848/5397d1250cf267d0f0d15dd8/1402458405568/wallpaper_s.jpg",
            "http://test.designer.c-launcher.com/resources/wallpaper/img/391/5397eb860cf20c6436f7565f/1402465158841/wallpaper_s.jpg",
            "http://test.designer.c-launcher.com/resources/wallpaper/img/333/5397e5d50cf244d2003552cd/1402463701155/wallpaper_s.jpg",
            "http://test.designer.c-launcher.com/resources/wallpaper/img/246/5397d1310cf267d0f0d15dde/1402458417493/wallpaper_s.jpg"
    };
}