/*
 * Copyright 2016 lizhaotailang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.marktony.zhihudaily.customtabs

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.preference.PreferenceManager
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.marktony.zhihudaily.R
import com.marktony.zhihudaily.util.KEY_CHROME_CUSTOM_TABS

/**
 * Created by lizhaotailang on 2017/5/23.
 *
 * A helper class of chrome custom tabs.
 * If the chrome custom tabs is available, then use it to open
 * links, otherwise use system browser instead.
 */

class CustomTabsHelper {

    companion object {
        fun openUrl(context: Context, url: String) {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

            if (sharedPreferences.getBoolean(KEY_CHROME_CUSTOM_TABS, true)) {
                val builder = CustomTabsIntent.Builder()
                builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
                builder.build().launchUrl(context, Uri.parse(url))
            } else {
                try {
                    context.startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)))
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(context, R.string.no_browser_found, Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

}
