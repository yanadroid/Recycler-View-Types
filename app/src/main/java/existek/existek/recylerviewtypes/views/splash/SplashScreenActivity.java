package existek.existek.recylerviewtypes.views.splash;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import existek.existek.recylerviewtypes.R;
import existek.existek.recylerviewtypes.views.base.BaseActivity;
import existek.existek.recylerviewtypes.views.main.MainActivity;

public class SplashScreenActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(() -> MainActivity.launch(SplashScreenActivity.this), 3000);
    }

    @Override
    public int getRes() {
        return R.layout.activity_splash_screen;
    }

    @Override
    public Activity getViewContext() {
        return this;
    }
}
