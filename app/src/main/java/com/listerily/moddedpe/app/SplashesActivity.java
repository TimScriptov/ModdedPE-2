/*
 * Copyright (C) 2018 - 2019 Тимашков Иван
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.listerily.moddedpe.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.listerily.mcdesign.app.DesignActivity;
import com.listerily.moddedpe.R;

import java.lang.ref.WeakReference;

public class SplashesActivity extends DesignActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashes_activity);

        new Thread()
        {
            @Override
            public void run()
            {
                super.run();

                try
                {
                    Thread.sleep(3000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                mUIHandler.sendEmptyMessage(0);
            }
        }.start();
    }

    private Handler mUIHandler = new UIHandler(this);


    static class UIHandler extends Handler
    {
        WeakReference<SplashesActivity> activity;

        UIHandler(SplashesActivity activity)
        {
            this.activity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);

            Intent intent = new Intent(this.activity.get(), MainActivity.class);
            this.activity.get().startActivity(intent);
            this.activity.get().finish();
        }
    }
}
