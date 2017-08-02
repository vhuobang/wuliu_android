package com.arkui.fz_tools.utils;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by 任少东 on 2016/07/28 15:13
 */
public class FileUtil {

    public static void save(Context context, String fileName, Object object) {
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object read(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean delete(Context context,String fileName){
        return context.deleteFile(fileName);
    }

    public static String readAssets(Context context, String fileName) {
        InputStream inputStream = null;
        StringBuffer buffer = new StringBuffer();
        try {
            inputStream = context.getResources().getAssets().open(fileName);
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            byte[] bytes = new byte[10240];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                buffer.append(new String(bytes, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer.toString();
    }
}
