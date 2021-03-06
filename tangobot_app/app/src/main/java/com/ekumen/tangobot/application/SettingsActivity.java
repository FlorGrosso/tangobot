/*
 * Copyright 2017 Ekumen, Inc. All Rights Reserved.
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

package com.ekumen.tangobot.application;

import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;


public class SettingsActivity extends MasterChooserSettingsActivity {
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key == getString(R.string.pref_master_is_local_key) ||
                key == getString(R.string.pref_master_uri_key)) {

            if (settingsWereAppliedThisSession() && mSettingsPreferenceFragment.getView() != null) {
                Snackbar snackbar = Snackbar.make(mSettingsPreferenceFragment.getView(),
                        getString(R.string.snackbar_text_restart), Snackbar.LENGTH_INDEFINITE);
                View snackBarView = snackbar.getView();
                snackBarView.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
                snackbar.show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!settingsWereAppliedThisSession()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), R.string.welcome_text_first_run, Toast.LENGTH_LONG).show();
                }
            });

            Snackbar snackbar = Snackbar.make(mSettingsPreferenceFragment.getView(),
                    getString(R.string.snackbar_text_first_run), Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction(getString(R.string.snackbar_action_text_first_run), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
            snackbar.show();
        }
    }
}
