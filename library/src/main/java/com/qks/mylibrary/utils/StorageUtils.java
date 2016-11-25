package com.qks.mylibrary.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 存储相关工具类
 * 获取手机内存,内置存储卡,
 * Created by admin on 2016/3/30.
 */
public class StorageUtils {


    /**
     * 手机存储
     */
    public static class Phone {

        public static long phoneTotal() {
            File path = Environment.getDataDirectory();
            return total(path);
        }

        public static long phoneUsed() {
            File path = Environment.getDataDirectory();
            return used(path);
        }

        public static long phoneFree() {
            File path = Environment.getDataDirectory();
            return free(path);
        }

        public static String phoneTotalString(Context context) {
            return Formatter.formatFileSize(context, phoneTotal());
        }

        public static String phoneUsedString(Context context) {
            return Formatter.formatFileSize(context, phoneUsed());
        }

        public static String phoneFreeString(Context context) {
            return Formatter.formatFileSize(context, phoneFree());
        }
    }

    /**
     * 内部存储
     * 1.如果没有内部存储,则为外部存储
     * 2.一般默认说的sdcard就是指的这个
     */
    public static class Sdcard {

        public static boolean isReady() {
            String state = Environment.getExternalStorageState();
            return Environment.MEDIA_MOUNTED.equals(state);
        }

        public static File sdcardPath() {
            return Environment.getExternalStorageDirectory();
        }

        public static long sdcardTotal() {
            File path = Environment.getExternalStorageDirectory();
            return total(path);
        }

        public static long sdcardFree() {
            File path = Environment.getExternalStorageDirectory();
            return free(path);
        }

        public static long sdcardUsed() {
            File path = Environment.getExternalStorageDirectory();
            return used(path);
        }


        public static String sdcardTotalString(Context context) {
            return Formatter.formatFileSize(context, sdcardTotal());
        }

        public static String sdcardFreeString(Context context) {
            return Formatter.formatFileSize(context, sdcardFree());
        }

        public static String sdcardUsedString(Context context) {
            return Formatter.formatFileSize(context, sdcardUsed());
        }
    }


    /**
     * 外部存储
     */
    public static class ExtSdcard {

        public static File extSdcardPath() {
            List<String> paths = new ArrayList<String>();
            String extFileStatus = Environment.getExternalStorageState();
            File extFile = Environment.getExternalStorageDirectory();
            if (extFileStatus.equals(Environment.MEDIA_MOUNTED)
                    && extFile.exists()
                    && extFile.isDirectory()
                    && extFile.canWrite()) {

                paths.add(extFile.getAbsolutePath());
            }
            try {
                Runtime runtime = Runtime.getRuntime();
                Process process = runtime.exec("mount");
                InputStream is = process.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line;
                int mountPathIndex = 1;
                while ((line = br.readLine()) != null) {
                    if ((!line.contains("fat") && !line.contains("fuse") && !line
                            .contains("storage"))
                            || line.contains("secure")
                            || line.contains("asec")
                            || line.contains("firmware")
                            || line.contains("shell")
                            || line.contains("obb")
                            || line.contains("legacy") || line.contains("data")) {
                        continue;
                    }
                    String[] parts = line.split(" ");
                    int length = parts.length;
                    if (mountPathIndex >= length) {
                        continue;
                    }
                    String mountPath = parts[mountPathIndex];
                    if (!mountPath.contains("/") || mountPath.contains("data") || mountPath.contains("Data")) {
                        continue;
                    }
                    File mountRoot = new File(mountPath);
                    if (!mountRoot.exists() || !mountRoot.isDirectory() || !mountRoot.canWrite()) {
                        continue;
                    }
                    boolean equalsToPrimarySD = mountPath.equals(extFile.getAbsolutePath());
                    if (equalsToPrimarySD) {
                        continue;
                    }
                    paths.add(mountPath);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (paths.size() > 1) {
                return new File(paths.get(1));
            }
            return null;
        }

        public static long extSdcardTotal() {
            File path = Environment.getExternalStorageDirectory();
            return total(path);
        }

        public static long extSdcardFree() {
            File path = Environment.getExternalStorageDirectory();
            return free(path);
        }

        public static long extSdcardUsed() {
            File path = Environment.getExternalStorageDirectory();
            return used(path);
        }


        public static String extSdcardTotalText(Context context) {
            return Formatter.formatFileSize(context, extSdcardTotal());
        }

        public static String extSdcardFreeText(Context context) {
            return Formatter.formatFileSize(context, extSdcardFree());
        }

        public static String extSdcardUsedText(Context context) {
            return Formatter.formatFileSize(context, extSdcardUsed());
        }
    }


    /**
     * 总大小
     *
     * @param path
     * @return
     */
    private static long total(File path) {
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return blockSize * totalBlocks;
    }

    /**
     * 可用大小
     *
     * @param path
     * @return
     */
    private static long free(File path) {
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long freeBlacks = stat.getAvailableBlocks();
        return blockSize * freeBlacks;
    }

    /**
     * 获取已经用的大小
     *
     * @param path
     * @return
     */
    private static long used(File path) {
        return total(path) - free(path);
    }


}
