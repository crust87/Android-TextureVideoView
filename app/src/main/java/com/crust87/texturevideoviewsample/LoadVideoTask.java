/*
 * Android-CenterCropVideoView
 * https://github.com/crust87/Android-FFmpegExecutor
 *
 * Mabi
 * crust87@gmail.com
 * last modify 2016-01-15
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.crust87.texturevideoviewsample;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.AsyncTask;

import com.crust87.texturevideoview.widget.TextureVideoView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LoadVideoTask extends AsyncTask<String, Void, String> {
    private TextureVideoView mVideoView;
    private String mVideo;
    private Context mContext;

    public LoadVideoTask(TextureVideoView videoView, String video, Context context) {
		mVideoView = videoView;
		mVideo = video;
        mContext = context;
		mVideoView.setPlayable(false);
    }

	protected String doInBackground(String... urls) {
        String name = mVideo.substring(mVideo.lastIndexOf('/') + 1);
        
        if(mVideo == null || mVideo.equals("null") || mVideo.equals("")) {
        	return null;
        } 
        
        File lPictureFile = new File(mContext.getCacheDir() + "/" + name);
        
		if (!lPictureFile.exists()) {
			try {
				InputStream inputStream = new java.net.URL(mVideo).openStream();
				OutputStream outputStream = new FileOutputStream(lPictureFile);
				BufferedInputStream bin = new BufferedInputStream(inputStream);
				BufferedOutputStream bout = new BufferedOutputStream(outputStream);

				int bytesRead = 0;
				byte[] buffer = new byte[1024];

				while ((bytesRead = bin.read(buffer, 0, 1024)) != -1) {
					bout.write(buffer, 0, bytesRead);
				}

				bout.close();
				bin.close();
				outputStream.close();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
        
       return lPictureFile.getAbsolutePath().toString();
    }
    
    protected void onPostExecute(String pClip) {
		mVideoView.setVideoPath(pClip);

		mVideoView.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				mVideoView.setPlayable(true);
			}
		});

		mVideoView.setOnErrorListener(new OnErrorListener() {

			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				return false;
			}
		});
    }

}
