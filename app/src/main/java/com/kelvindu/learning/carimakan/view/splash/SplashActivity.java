package com.kelvindu.learning.carimakan.view.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kelvindu.learning.carimakan.base.BaseActivity;
import com.kelvindu.learning.carimakan.view.main.MainActivity;

/**
 * This activity handles the splash screen.
 *
 * Created by KelvinDu on 5/21/2017.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openNewActivity(MainActivity.class);
    }
}
