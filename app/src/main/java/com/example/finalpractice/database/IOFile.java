package com.example.finalpractice.database;

import android.content.Context;

import com.example.finalpractice.model.NhanVien;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class IOFile {
    public static  <T>ArrayList<T> doc(Context c, String filename){
        File myExternalFile = new File(c.getExternalFilesDir(null),filename);
        ArrayList<T> list = new ArrayList<>();
        try {
            if (!myExternalFile.exists ()) { myExternalFile.createNewFile();}
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(myExternalFile));
            list= (ArrayList<T>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }catch (EOFException e) {
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }
    public static <T> void ghi(Context c, String filename, ArrayList<T> t ){
        File myExternalFile = new File(c.getExternalFilesDir(null),filename);
        try {
            FileOutputStream fos = new FileOutputStream(myExternalFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(t);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
