package com.coahr.fanoftruck.mvp.view.RecorderVideo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Movie;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;


import com.coahr.fanoftruck.commom.Constants;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by ${xinGen} on 2017/10/20.
 */

public class FileUtils {
    /**
     * 相片格式
     */
    public static final String PICTURE_FORMAT = ".png";
    /**
     * 视频格式
     */
    public static final String VIDEO_FORMAT = ".mp4";

    /**
     * @return
     */
    public static String getVideoName() {
        return "VID_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + VIDEO_FORMAT;
    }

    public static String getImageName() {
        return "IMG_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + PICTURE_FORMAT;
    }

    /**
     * 创建视频文件保存路径
     */
    public static String getSDVideoPath(String videoName) {

        File eis = new File(Constants.SAVE_DIR_VIDEO);
        if (!eis.exists()) {
            eis.mkdir();
        }
        return eis.toString() + "/" + videoName;
    }

    /**
     * 创建视频压缩路径
     * @param videoName
     * @return
     */
    public static String getSDVideoZipPath(String videoName) {

        File eis = new File(Constants.SAVE_DIR_ZIPVIDEO);
        if (!eis.exists()) {
            eis.mkdir();
        }
        return eis.toString() + "/" + videoName;
    }

    /**
     * 创建图片文件保存路径
     */
    public static String getSDImagePath(String videoName) {

        File eis = new File(Constants.SAVE_DIR_PHOTO);
        if (!eis.exists()) {
            eis.mkdir();
        }
        return eis.toString() + "/" + videoName;
    }

    /**
     * 以当前时间，加MD5编码后的文件名
     *
     * @return
     */
    private static String createFileNameWithTimeAndMD5() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            String currentDate = createFileNameWithTime();
            mDigest.update(currentDate.getBytes("utf-8"));
            byte[] b = mDigest.digest();
            for (int i = 0; i < b.length; ++i) {
                String hex = Integer.toHexString(0xFF & b[i]);
                if (hex.length() == 1) {
                    stringBuilder.append('0');
                }
                stringBuilder.append(hex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileName = stringBuilder.toString();
        return fileName;

    }

    /**
     * 以当前时间，加MD5编码后的文件名
     *
     * @return
     */
    private static String createFileNameWithTime() {
        String currentDate = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return currentDate;
    }

    /**
     * 合并多个视频文件，成为一个，但会删除以前的旧文件。
     * <p>
     * 涉及到IO流操作，异步调用
     * <p>
     * 参考博客：
     * http://blog.csdn.net/thelastalien/article/details/51545323
     * <p>
     * http://blog.csdn.net/smile3670/article/details/41279749
     *
     * @param videoPath1
     * @param videoPath2
     * @return
     */
    public static String mergeMultipleVideoFile(Context context, String videoPath1, String videoPath2) {
        Log.d("文件", videoPath1 + "\n\t" + videoPath2);
        String currentFileName = getSDVideoPath(getVideoName());
       /* try {
            //将视频文件转成对应的Movie
            List<String> fileList = new ArrayList<>();
            List<Movie> movieList = new ArrayList<>();
            fileList.add(videoPath1);
            fileList.add(videoPath2);
            for (String filePath : fileList) {
                movieList.add(MovieCreator.build(filePath));
            }
            //将多个Movie转成对应的音频和视频的mp4
            List<Track> videoTracks = new LinkedList<>();
            List<Track> audioTracks = new LinkedList<>();
            for (Movie movie : movieList) {
                for (Track track : movie.getTracks()) {
                    if (track.getHandler().equals("soun")) {
                        audioTracks.add(track);
                    }
                    if (track.getHandler().equals("vide")) {
                        videoTracks.add(track);
                    }
                }
            }
            //创建一个具备全部音频和视频的Movie
            Movie result = new Movie();
            if (audioTracks.size() > 0) {
                result.addTrack(new AppendTrack(audioTracks.toArray(new Track[audioTracks.size()])));
            }
            if (videoTracks.size() > 0) {
                result.addTrack(new AppendTrack(videoTracks.toArray(new Track[videoTracks.size()])));
            }

            Container out = new DefaultMp4Builder().build(result);
            FileChannel fc = new RandomAccessFile(String.format(currentFileName), "rw").getChannel();
            out.writeContainer(fc);
            Log.d("合并", "appendVideo: " + "合并");
            fc.close();
            //写入新文件中，将数据
            // writeMergeNewFile(currentFileName, result);
            //删除旧文件
            deleteFile(videoPath1);
            deleteFile(videoPath2);
            //释放资源
            fileList.clear();
            movieList.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        return currentFileName;
    }

    /**
     * 删除文件
     * 1. 单个文件
     * 2. 文件夹下的子文件夹和文件
     *
     * @param filePath
     */
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file == null || !file.exists()) {
            return;
        }
        //删除文件夹
        if (file.isDirectory()) {
            for (File childFile : file.listFiles()) {
                if (childFile.isFile()) {
                    //删除文件
                    childFile.delete();
                } else if (file.isDirectory()) {
                    //递归删除子文件夹
                    deleteFile(childFile.getAbsolutePath());
                }
            }
            //删除文件夹本身
            file.delete();
        }//删除文件
        else {
            if (file.isFile()) {
                file.delete();
            }
        }
    }

    /**
     * 写入合并数据到新文件中。
     *
     * @param filePath 新文件路径
     * @param result
     */
    private static void writeMergeNewFile(String filePath, Movie result) {

    }

    /**
     * @param imageBit
     * @return
     */
    public static String saveImage(Bitmap imageBit) {

        String sdImagePath = getSDImagePath(getImageName());
        File file = new File(sdImagePath);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            imageBit.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return file.getAbsolutePath();
    }

}
