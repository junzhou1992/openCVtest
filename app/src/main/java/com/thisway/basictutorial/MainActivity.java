package com.thisway.basictutorial;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String TAG = "cvsample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLoadOpenCVlibs();//初始化openCV
        Button btn = (Button)findViewById(R.id.envTestButton);
        btn.setOnClickListener(this);
        
    }

    //初始化openCV
    private void initLoadOpenCVlibs() {
       boolean success = OpenCVLoader.initDebug();
        if (success) {
            Log.i(TAG, "initLoadOpenCVlibs...");
        }
    }

    @Override
    public void onClick(View view) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.test,options);  //获取到了bitmap

        //opencv  接收这种mat对象  借助util中的bitmapToMat
        Mat src = new Mat();
        Mat dst = new Mat();

        Utils.bitmapToMat(bitmap,src);
        Imgproc.cvtColor(src,dst,Imgproc.COLOR_BGRA2GRAY);  //颜色转换    四通道变成变成灰度空间
        Utils.matToBitmap(dst,bitmap);//重新变成bitmap图像
        ImageView imgview = (ImageView)this.findViewById(R.id.imageView);
        imgview.setImageBitmap(bitmap);

    }
}
