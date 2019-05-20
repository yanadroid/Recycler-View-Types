package existek.existek.recylerviewtypes.views.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Objects;

import existek.existek.recylerviewtypes.R;
import existek.existek.recylerviewtypes.data.User;
import existek.existek.recylerviewtypes.databinding.ActivityDetailScreenBinding;
import existek.existek.recylerviewtypes.views.base.BaseActivity;

public class DetailScreenActivity extends BaseActivity {

    public static final String KEY_USER = "key_user";
    private  ActivityDetailScreenBinding binding;

    public static void launch(Context context, User user) {
        Intent intent = new Intent(context, DetailScreenActivity.class);
        intent.putExtra(KEY_USER, user);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = (ActivityDetailScreenBinding) getBinding();
        binding.setUser(getIntent().getParcelableExtra(KEY_USER));
        setSupportActionBar();
    }

    private void setSupportActionBar() {
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public int getRes() {
        return R.layout.activity_detail_screen;
    }

    @Override
    public Activity getViewContext() {
        return this;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
