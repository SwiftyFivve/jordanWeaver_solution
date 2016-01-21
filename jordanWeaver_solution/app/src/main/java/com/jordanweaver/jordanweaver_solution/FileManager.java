package com.jordanweaver.jordanweaver_solution;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

/**
 * Created by jordanweaver on 1/20/16.
 */
public class FileManager {


    public ArrayList<ApiObject> loadData(String name, Context mContext){

        ArrayList<ApiObject> savedNews = null;

        try{
            FileInputStream fis = mContext.openFileInput(name);
            ObjectInputStream ois = new ObjectInputStream(fis);
            savedNews = (ArrayList<ApiObject>)ois.readObject();
            fis.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (OptionalDataException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(savedNews == null){
            return savedNews = new ArrayList<>();
        }

        return savedNews;
    }

    public void saveObject(ArrayList<ApiObject> news, String name, Context mContext){

        try {
            ArrayList<ApiObject> saveArray = news;
            FileOutputStream fos = mContext.openFileOutput(name, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(news);
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
