package com.qks.mylibrary.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 文件读写删除解压等操作
 * Created by admin on 2016/3/30.
 */
public class FileUtils {


    /**
     * File转String字符串
     * @param file
     * @return
     * @throws IOException
     */
    public static String read2File(File file) throws IOException {
        String text = null;
        InputStream is =null;
        try {
            is = new FileInputStream(file);
            text = readInpuStream2String(is);
        }finally {
            if(is != null){
                is.close();
            }
        }
        return text;
    }


    /**
     * 读取输入流为字符串
     * @param is
     * @return
     * @throws IOException
     */
    public static String readInpuStream2String(InputStream is) throws IOException {
        StringBuffer strbuffer = new StringBuffer();
        String line;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is));
            while ((line = reader.readLine())!=null){
                strbuffer.append(line).append("\r\n");
            }
        } finally {
            if(reader!=null){
                reader.close();
            }
        }
        return strbuffer.toString();
    }

    /**
     * 将一个字符串写入到一个文件中
     * @param file
     * @param str
     * @throws IOException
     */
    public static void writeString2File(File file ,String str)throws IOException{
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(new FileOutputStream(file));
            out.write(str.getBytes("UTF-8"));
        }finally {
            if(out!=null){
                out.close();
            }
        }
    }

    /**
     * 解压
     * @param zipFilePath
     * @param destPath
     * @throws IOException
     */
    public static void unzip(String zipFilePath,String destPath)throws IOException{
        File destFile = new File(zipFilePath);
        if(destFile.exists()){
            destFile.mkdir();
        }
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry zipEntry;
        String zipEntryName;
        //循环遍历压缩文件中的所有文件(文件,文件夹)
        while((zipEntry = zipInputStream.getNextEntry())!= null){
            zipEntryName = zipEntry.getName();
            if(zipEntry.isDirectory()){
                File folder = new File(destPath+File.separator+zipEntryName);
                folder.mkdir();
            }else{
                File file = new File(destPath + File.separator+zipEntryName);
                if(file != null && !file.getParentFile().exists()){
                    file.getParentFile().mkdir();
                }
                file.createNewFile();
                FileOutputStream out = new FileOutputStream(file);
                int len;
                byte[] buffer = new byte[1024];
                while((len = zipInputStream.read(buffer))>0){
                    out.write(buffer,0,len);
                    out.flush();;
                }
                out.close();
            }
        }
        zipInputStream.close();
    }






    /**
     * 删除一个文件夹或者文件
     * @param directory 路径
     * @param keepRoot 如果删除目录,是否需要删除跟文件夹
     */
    public static void delete(File directory,boolean keepRoot){
        if(directory!= null && directory.exists()){
            if(directory.isDirectory()){
                //如果是目录
                for(File subDirectory : directory.listFiles()){
                    delete(subDirectory,false);
                }
            }

            if(!keepRoot){
                directory.delete();
            }
        }
    }

}
