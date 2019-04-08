package com.coahr.fanoftruck.Utils.imageLoader;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;


/**
 * Created by Leehor
 * on 2018/11/6
 * on 11:23
 * 基础配置
 */

public class Imageloader {
 private  static  ObjectAnimator anim;
    private static Bitmap bitmap=null;

    public static void loadImage(String path, final ImageView targetImage) {

        Glide.with(BaseApplication.mContext).load(path).crossFade().diskCacheStrategy(DiskCacheStrategy.RESULT).thumbnail(0.1f).placeholder(R.mipmap.loading).error(R.mipmap.loadimage_err).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {

                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {

                return false;
            }
        }).into(targetImage);
    }

    /**
     *  服务器返回url，通过url去获取视频的第一帧
     *  Android 原生给我们提供了一个MediaMetadataRetriever类
     *  提供了获取url视频第一帧的方法,返回Bitmap对象
     *
     *  @param videoUrl
     *  @return
     */
    public static Bitmap getNetVideoBitmap(String videoUrl) {
        Bitmap bitmap = null;

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            if (videoUrl.startsWith("http")) {  //网络视频
                //根据url获取缩略图
                retriever.setDataSource(videoUrl, new HashMap());
            }else {                               //本地视频
                retriever.setDataSource(videoUrl);
            }
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static Bitmap createVideoThumbnail(String url, int width, int height) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        int kind = MediaStore.Video.Thumbnails.MINI_KIND;
        try {
            if (url.startsWith("http")) {
                retriever.setDataSource(url, new HashMap<String, String>());
            } else {
                retriever.setDataSource(url);
            }
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                // Ignore failures while cleaning up.
            }
        }
        if (kind == MediaStore.Images.Thumbnails.MICRO_KIND && bitmap != null) {
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                    ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        }
        return bitmap;
    }

    public static void loadGif(int resId, final ImageView targetImage) {
        Glide.with(BaseApplication.mContext).load(resId).asGif().crossFade().diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.mipmap.loading).error(R.mipmap.loadimage_err).into(targetImage);
    }

    public static void loadImage(Uri path, final ImageView targetImage) {

        Glide.with(BaseApplication.mContext).load(path).crossFade().diskCacheStrategy(DiskCacheStrategy.RESULT).thumbnail(0.1f).placeholder(R.mipmap.loading).error(R.mipmap.loadimage_err).listener(new RequestListener<Uri, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, Uri model, Target<GlideDrawable> target, boolean isFirstResource) {

                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, Uri model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {

                return false;
            }
        }).into(targetImage);

    }

    /**
     * 加载圆形图片
     * @param path
     * @param targetImage
     */
    public static void loadCircularImage(Uri path, final ImageView targetImage) {
        Glide.with(BaseApplication.mContext).load(path).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).thumbnail(0.1f).placeholder(R.mipmap.loading).error(R.mipmap.loadimage_err).into(new BitmapImageViewTarget(targetImage){
            @Override
            protected void setResource(Bitmap resource) {
                super.setResource(resource);
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(BaseApplication.mContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                targetImage.setImageDrawable(circularBitmapDrawable);

            }
        });
    }
    /**
     * 加载圆形图片
     * @param path
     * @param targetImage
     */
    public static void loadCircularImage(String path, final ImageView targetImage) {
        Glide.with(BaseApplication.mContext).load(path).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).thumbnail(0.1f).placeholder(R.mipmap.loading).error(R.mipmap.default_head).into(new BitmapImageViewTarget(targetImage){
            @Override
            protected void setResource(Bitmap resource) {
                super.setResource(resource);
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(BaseApplication.mContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                targetImage.setImageDrawable(circularBitmapDrawable);

            }
        });
    }
    /**
     * 根据图片的url路径获得Bitmap对象
     */
    public static Bitmap getBitMap(Uri path) {
        Glide.with(BaseApplication.mContext).load(path).asBitmap().override(500, 500).diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.mipmap.loading).error(R.mipmap.loadimage_err).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                bitmap = resource;
            }

        });

        return bitmap;
    }

    /**
     * 根据图片的url路径获得Bitmap对象
     */
    public static Bitmap getBitMap(String path) {

        Glide.with(BaseApplication.mContext).load(path).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                //final Bitmap bitmap1=resource;
                bitmap = resource;
            }

        });
        return bitmap;
    }

    public static Bitmap getBitMap(int res) {
        Glide.with(BaseApplication.mContext).load(res).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.mipmap.loading).error(R.mipmap.loadimage_err).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                //final Bitmap bitmap1=resource;
                bitmap = resource;
            }

        });
        return bitmap;
    }

    public static void loadBitMap(Bitmap bitmap,ImageView imageView){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes=baos.toByteArray();
        Glide.with(BaseApplication.mContext).load(bytes).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.mipmap.loading).into(imageView);
    }

    /**
     * 加载动画
     * @param img
     */
    private static void setAnimator(ImageView img){
        anim = ObjectAnimator.ofInt(img, "ImageLevel", 0,100);
        anim.setDuration(800);
        anim.setRepeatCount(ObjectAnimator.INFINITE);
        anim.start();
    }

    private static void stopAnim(ImageView img){
        anim.cancel();

    }

    /**
     * 大图加载
     * @param path
     * @param targetImage
     */
    public static void loadImage_larger(String path, final ImageView targetImage) {
        setAnimator(targetImage);
        Glide.with(BaseApplication.mContext).load(path).crossFade().diskCacheStrategy(DiskCacheStrategy.RESULT).thumbnail(0.1f).placeholder(R.mipmap.loading).error(R.mipmap.loadimage_err).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        }).into(targetImage);
    }

    public static void loadImage_larger(Uri path, final ImageView targetImage) {
        setAnimator(targetImage);
        Glide.with(BaseApplication.mContext).load(path).crossFade().diskCacheStrategy(DiskCacheStrategy.RESULT).thumbnail(0.1f).placeholder(R.mipmap.loading).error(R.mipmap.loadimage_err).listener(new RequestListener<Uri, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, Uri model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, Uri model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        }).into(targetImage);
    }

    /**
     * 设置控件大小
     * @param view
     * @param imageWidth
     * @param imageHeight
     */
    public static void setViewSize(View view, int imageWidth, int imageHeight, ImageView.ScaleType type){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height=imageHeight;
        params.width =imageWidth;
        view.setLayoutParams(params);
        if (view instanceof ImageView){
            if (type !=null){
                ((ImageView) view).setScaleType(type);
            }
        }
    }

}
