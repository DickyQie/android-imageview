package com.zq.myinterfacepictureupload.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zq on 2016/6/11.
 */

public class UtilImags {
    public static final String SHOWFILEURL(Context context) throws IOException
    {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath =context.getCacheDir().getPath();
        }
        File file = new File(cachePath+"/thumb");
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e) {
            }
        }
        return cachePath+File.separator+"thumb";
    }

    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options =50;
        if ((baos.toByteArray().length/1024)>400)
        {
            options =10;
        }else if ((baos.toByteArray().length/1024)>300  && ((baos.toByteArray().length/1024)<=400))
        {
            options = 15;
        }
        else if ((baos.toByteArray().length/1024)>200  && ((baos.toByteArray().length/1024)<=300)) {
            options = 20;//90
        }
        else if ((baos.toByteArray().length/1024)>100  && ((baos.toByteArray().length/1024)<=200)) {
            options = 25;//90
        }
        else {
            options = 50;
        }
        while (baos.toByteArray().length / 1024 > 46) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }
    /**
     * 图片按比例大小压缩方法
     *
     * @param image （根据Bitmap图片压缩）
     * @return
     */
    public static Bitmap compressScale(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        // 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
        if (baos.toByteArray().length / 1024 > 1000) {
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, 15, baos);
        }
        else if ((baos.toByteArray().length / 1024) > 600  && (baos.toByteArray().length / 1024) <= 1000) {
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        }
        else if ((baos.toByteArray().length / 1024) > 400  && (baos.toByteArray().length / 1024) <= 600) {
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, 25, baos);
        }
        else if ((baos.toByteArray().length / 1024) > 100  && (baos.toByteArray().length / 1024) <= 400) {
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, 35, baos);
        }
        else {
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, 55, baos);
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        // float hh = 800f;// 这里设置高度为800f
        // float ww = 480f;// 这里设置宽度为480f
        float hh = 512f;
        float ww = 512f;
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) { // 如果高度高的话根据高度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be; // 设置缩放比例
        // newOpts.inPreferredConfig = Config.RGB_565;//降低图片从ARGB888到RGB565

        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);

        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩

    }







}
