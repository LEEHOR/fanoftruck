package com.coahr.fanoftruck.mvp.view.RecorderVideo;

import android.content.Context;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


/**
 */

public class ObservableBuilder {
    /**
     * 合并多个视频文件，到一个新的视频中
     *
     * @param filePath1
     * @param filePath2
     * @return
     */
    public static Observable<String> createMergeMuiltFile(final Context context, final String filePath1, final String filePath2) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                String s = FileUtils.mergeMultipleVideoFile(context, filePath1, filePath2);
                e.onNext(s);
            }
        });

    }
}
