package com.qks.mylibrary.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by admin on 2016/3/30.
 */
public class SerializeUtils {

    /**
     * 将一个对象序列化到本地
     * @param path
     * @param object
     * @param <T>
     */
    public static <T> void se(String path, T object) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(object);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 从本地反序列化
     * @param path
     * @param <T>
     * @return
     */
    public static <T>T de(String path){
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(path));
            Object obj = in.readObject();
            return (T)obj;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }


}
