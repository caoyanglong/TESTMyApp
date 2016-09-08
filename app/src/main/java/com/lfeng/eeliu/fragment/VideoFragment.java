package com.lfeng.eeliu.fragment;

import android.media.MediaPlayer;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import com.lfeng.eeliu.R;
import com.lfeng.eeliu.base.BaseFragment;

/**
 * Created by CYL on 2016/4/2.
 * email:670654904@qq.com
 */
public class VideoFragment extends BaseFragment {
    private VideoView videoView;
    @Override
    public int setContentView() {
        return R.layout.video_fragment_layout;
    }

    @Override
    public void initView(View view) {
        videoView = (VideoView) getView(view,R.id.videoview);
    }

    @Override
    public void initData() {
        String videoPath = "http://cloud.gslb.letv.com/cdn/b446289e16ad377987bb1f3445b5874835a02bf4?mltag=1&t=1459594409&g=0&k=16d3558156fefd05e9035a1559a28ae6&pantoken=db46fd95ce0df5fcbd0e97e0ffece99b&panuid=pan140658175&platid=14&splatid=1400&fname=%E7%96%AF%E7%8B%82%E5%8A%A8%E7%89%A9%E5%9F%8EDVDScr%E5%9B%BD%E8%AF%AD%E9%A6%96%E5%8F%91.mp4&pantm=1459612409&fname_gbk=%E9%90%A4";
        videoView.setVideoPath(videoPath);
        videoView.start();
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(context,"onerror",Toast.LENGTH_LONG).show();
                return true;
            }
        });
        Toast.makeText(context,"-------",Toast.LENGTH_LONG).show();
    }

    @Override
    public void initListener() {

    }
}
