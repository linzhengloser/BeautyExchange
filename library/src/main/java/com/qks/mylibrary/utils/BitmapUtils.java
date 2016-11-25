package com.qks.mylibrary.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by admin on 2016/3/24.
 */
public class BitmapUtils {


    /**
     * 根据maxWidth和maxHeight计算出最合适的inSampleSize
     *
     * @param options
     * @param maxWidth
     * @param maxHeight
     * @return
     */
    public static int computeBestSampleSize(BitmapFactory.Options options, int maxWidth, int maxHeight) {
        int rawWidth = options.outWidth;
        int rawHeight = options.outHeight;
        int inSampleSize = 0;
        if (rawWidth > maxWidth || rawHeight > maxHeight) {
            float rationWidth = (float) rawWidth / maxWidth;
            float rationHeight = (float) rawHeight / maxHeight;
            inSampleSize = (int) Math.min(rationWidth, rationHeight);
        }
        inSampleSize = Math.max(1, inSampleSize);
        return inSampleSize;
    }


    /**
     * 高效获取raw中的Bitmap
     *
     * @return
     */
    public static Bitmap efficientGetRawInBitmap(Context context, int rawId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        InputStream is = context.getResources().openRawResource(rawId);
        return BitmapFactory.decodeStream(is, null, opt);
    }


    /**
     * 旋转一个Bitmap
     *
     * @param originBitmap 要旋转的Bitmap
     * @param angle        旋转角度
     * @param isRecycle    是否需要回收旋转前的Bitmap
     * @return 旋转后的Bitmap
     */
    public static Bitmap rotate(Bitmap originBitmap, int angle, boolean isRecycle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        Bitmap rotatedBitmap = Bitmap.createBitmap(originBitmap, 0, 0, originBitmap.getWidth(), originBitmap.getHeight(), matrix, true);
        isRecycler(originBitmap, rotatedBitmap, isRecycle);
        return rotatedBitmap;
    }


    /**
     * 按倍数缩放Bitmap
     *
     * @param originBitmap 需要缩放的Bitmap
     * @param scaleX       x的缩放比例
     * @param scaleY       y的缩放比例
     * @param isRecycle    是否需要回收掉缩放前的Bitmap
     * @return 缩放后的Bitmap
     */
    public static Bitmap scale(Bitmap originBitmap, float scaleX, float scaleY, boolean isRecycle) {
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY);
        Bitmap scaledBitmap = Bitmap.createBitmap(originBitmap, 0, 0, originBitmap.getWidth(), originBitmap.getHeight(), matrix, true);
        isRecycler(originBitmap, scaledBitmap, isRecycle);
        return scaledBitmap;
    }

    /**
     * 将Bitmap缩放到指定宽高
     *
     * @param originBitmap 需要缩放的Bitmap
     * @param dstWidth     需要缩放的指定宽度
     * @param dstHeight    需要缩放的指定高度
     * @param isRecycler   是需要回收掉缩放前的Bitmap
     * @return 缩放后的Bitmap
     */
    public static Bitmap scale(Bitmap originBitmap, int dstWidth, int dstHeight, boolean isRecycler) {
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originBitmap, dstWidth, dstHeight, true);
        isRecycler(originBitmap, scaledBitmap, isRecycler);
        return scaledBitmap;
    }

    /**
     * 获取缩略图
     *
     * @param path       路径
     * @param maxWidth   最大宽
     * @param maxHeight  最大高
     * @param autoRotate 是否需要旋转
     * @return 缩略图
     */
    public static Bitmap thumbnail(String path, int maxWidth, int maxHeight, boolean autoRotate) {
        int angle = 0;
        if (autoRotate) {
            angle = ImageUtils.getImageExifRotateAngle(path);
        }
        BitmapFactory.Options ops = new BitmapFactory.Options();
        ops.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(path, ops);
        //计算最合适的SampleSize
        int sampleSize = computeBestSampleSize(ops, maxWidth, maxHeight);
        ops.inSampleSize = sampleSize;
        ops.inPreferredConfig = Bitmap.Config.RGB_565;
        ops.inPurgeable = true;
        ops.inInputShareable = true;
        //回收bitmap
        if (null != bitmap && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        //是否需要旋转
        if (autoRotate && angle != 0) {
            bitmap = rotate(bitmap, angle, true);
        }
        return bitmap;
    }


    /**
     * 保存Bitmap到本地 默认路径/mnt/sdcard/[package]/save/
     *
     * @param bitmap
     * @param format
     * @param quality
     * @param context
     * @return
     */
    public static String saveBitmap2Loacl(Bitmap bitmap, Bitmap.CompressFormat format, int quality, Context context) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        File dir = new File(Environment.getExternalStorageDirectory() + File.separator + context.getPackageName() + File.separator + "save" + File.separator);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File destFile = new File(dir, UUID.randomUUID().toString());
        return saveBitmap2Local(bitmap, format, quality, destFile);
    }


    /**
     * 保存Bitmap到指定路径
     *
     * @param bitmap   要保存的Bitmap
     * @param format   保存格式 JPG PNG
     * @param quality  压缩的质量
     * @param destFile 保存的路径
     * @return
     */
    public static String saveBitmap2Local(Bitmap bitmap, Bitmap.CompressFormat format, int quality, File destFile) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(destFile);
            bitmap.compress(format, quality, out);
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
            return destFile.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 获取圆角Bitmap
     *
     * @param originBitmap 需要圆角的Bitmap
     * @param radius       半径
     * @param isRecycle    是否需要回收
     * @return 圆角bitmap
     */
    public static Bitmap getRoundBitmap(Bitmap originBitmap, int radius, boolean isRecycle) {
        //创建画笔对象
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        //准备裁剪的矩阵
        Rect rect = new Rect(0, 0, originBitmap.getWidth(), originBitmap.getHeight());
        RectF rectF = new RectF(new Rect(0, 0, originBitmap.getWidth(), originBitmap.getHeight()));
        Bitmap roundBitmap = Bitmap.createBitmap(originBitmap.getWidth(), originBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(roundBitmap);
        canvas.drawRoundRect(rectF, radius, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(originBitmap, rect, rect, paint);
        //是否回收Bitmap
        isRecycler(originBitmap, roundBitmap, isRecycle);
        return roundBitmap;
    }


    /**
     * 居中裁剪
     * @param originBitmap 需要居中裁剪的Bitmap
     * @param isRecycle 是否需要回收掉裁剪前的Bitmap
     * @return 裁剪后的Bitmap
     */
    public static Bitmap getCircleBitmap(Bitmap originBitmap, boolean isRecycle) {
        //求Bitmap是宽长好是高长
        int min = originBitmap.getWidth()>originBitmap.getHeight()?originBitmap.getHeight():originBitmap.getWidth();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap circleBitmap = Bitmap.createBitmap(min,min,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(circleBitmap);
        canvas.drawCircle(min/2,min/2,min/2,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        //居中显示
        int left = -(originBitmap.getWidth() - min)/2;
        int top = -(originBitmap.getHeight()-min)/2;
        canvas.drawBitmap(originBitmap,left,top,paint);
        isRecycler(originBitmap,circleBitmap,isRecycle);
        return circleBitmap;
    }


    /**
     * 灰阶效果
     * @param originBitmap 原Bitmap
     * @param isRecycle 是否需要回收原bitmap
     * @return 灰阶后的Bitmap
     */
    public static Bitmap getGray(Bitmap originBitmap,boolean isRecycle){
        Bitmap grayBitmap = Bitmap.createBitmap(originBitmap.getWidth(),
                originBitmap.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(grayBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter colorMatrixColorFilter =
                new ColorMatrixColorFilter(colorMatrix);
        paint.setColorFilter(colorMatrixColorFilter);
        canvas.drawBitmap(originBitmap, 0, 0, paint);
        isRecycler(originBitmap,grayBitmap,isRecycle);
        return grayBitmap;
    }


    /**
     * 判断一个Bitmap是否需要回收
     *
     * @param originBitmap 操作前的Bitmap
     * @param overBitmap   操作后的Bitmap
     * @param isRecycler   是否需要回收
     */
    private static void isRecycler(Bitmap originBitmap, Bitmap overBitmap, boolean isRecycler) {
        if (isRecycler && overBitmap != null && !originBitmap.isRecycled()) {
            originBitmap.recycle();
        }
    }


}
