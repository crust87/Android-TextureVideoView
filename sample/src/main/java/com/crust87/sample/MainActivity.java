/*
 * Android-TextureVideoView
 * https://github.com/crust87/Android-TextureVideoView
 *
 * Mabi
 * crust87@gmail.com
 * last modify 2016-02-10
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

package com.crust87.sample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.crust87.texturevideoview.widget.TextureVideoView;

public class MainActivity extends AppCompatActivity {

    // Constants
    private static final int REQUEST_VIDEO = 1000;

    private TextureVideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadGUI();
        init();
    }

    @Override
    public void onPause() {
        if (mVideoView != null) {
            mVideoView.stopPlayback();
        }

        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_open:
                openImage();
                return true;
            case R.id.action_matrix:
                mVideoView.setScaleType(TextureVideoView.ScaleType.MATRIX);
                return true;
            case R.id.action_fitXY:
                mVideoView.setScaleType(TextureVideoView.ScaleType.FIT_XY);
                return true;
            case R.id.action_fitStart:
                mVideoView.setScaleType(TextureVideoView.ScaleType.FIT_START);
                return true;
            case R.id.action_fitCenter:
                mVideoView.setScaleType(TextureVideoView.ScaleType.FIT_CENTER);
                return true;
            case R.id.action_fitEnd:
                mVideoView.setScaleType(TextureVideoView.ScaleType.FIT_END);
                return true;
            case R.id.action_center:
                mVideoView.setScaleType(TextureVideoView.ScaleType.CENTER);
                return true;
            case R.id.action_centerCrop:
                mVideoView.setScaleType(TextureVideoView.ScaleType.CENTER_CROP);
                return true;
            case R.id.action_centerInside:
                mVideoView.setScaleType(TextureVideoView.ScaleType.CENTER_INSIDE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_VIDEO && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();

            mVideoView.setVideoURI(videoUri);
            mVideoView.start();
        }
    }

    private void loadGUI() {
        setContentView(R.layout.activity_main);
        mVideoView = (TextureVideoView) findViewById(R.id.videoClip);
    }

    private void init() {
    }

    public void openImage() {
        Intent lIntent = new Intent(Intent.ACTION_PICK);
        lIntent.setType("video/*");
        lIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(lIntent, REQUEST_VIDEO);
    }
}
