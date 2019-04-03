package com.coahr.fanoftruck.Utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import com.coahr.fanoftruck.BuildConfig;
import com.coahr.fanoftruck.mvp.view.MyWebView.Fragment_myWebView;

import java.io.File;

import androidx.core.content.FileProvider;

public class PhotoUtil {

    /**
     * 选择相册照片
     */
    public static void chooseAlbumPic(Activity activity) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        activity.startActivityForResult(Intent.createChooser(i, "Image Chooser"), Fragment_myWebView.REQUEST_CODE_ALBUM);
    }

    public static String takePhoto(Activity activity) {
        File tempFile = null;
//        StringBuilder fileName = new StringBuilder();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String state = Environment.getExternalStorageState();
        if (state.equals("mounted")) {
//            fileName.append(UUID.randomUUID()).append("_upload.png");
//            tempFile = new File(activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName.toString());

            tempFile = new File(activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES), System.currentTimeMillis() + "_upload.png");

            /*File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            File tempFile = new File(path, System.currentTimeMillis() + ".jpg");
            if (!path.exists()) {
                path.mkdirs();
            }*/

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri uri = FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID + ".fileProvider", tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            } else {
                Uri uri = Uri.fromFile(tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            }
        }

        activity.startActivityForResult(intent, Fragment_myWebView.REQUEST_CODE_CAMERA);
        return tempFile.getAbsolutePath();
    }
}
