package existek.existek.recylerviewtypes.views.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import existek.existek.recylerviewtypes.R;
import existek.existek.recylerviewtypes.data.Type;
import existek.existek.recylerviewtypes.data.User;
import existek.existek.recylerviewtypes.databinding.ActivityMainBinding;
import existek.existek.recylerviewtypes.utils.GenerateFakeRandomDataUtil;
import existek.existek.recylerviewtypes.views.base.BaseActivity;
import existek.existek.recylerviewtypes.views.callback.AppBarStateChangeListener;
import existek.existek.recylerviewtypes.views.callback.OnItemClickListener;
import existek.existek.recylerviewtypes.views.detail.DetailScreenActivity;

public class MainActivity extends BaseActivity implements OnItemClickListener {

    private ActivityMainBinding binding;

    public static void launch(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = (ActivityMainBinding) getBinding();
        binding.setUrl(GenerateFakeRandomDataUtil.IMAGE_URL);
        setRecycler();
        binding.appbar.addOnOffsetChangedListener(listener);
    }

    private void setRecycler() {
        MainAdapter adapter = new MainAdapter();
        adapter.setListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setData(GenerateFakeRandomDataUtil.generateData());
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setAdapter(adapter);
    }

    private AppBarStateChangeListener listener = new AppBarStateChangeListener() {
        @Override
        public void onStateChanged(AppBarLayout appBarLayout, State state) {
            binding.toolbar.setVisibility(state == State.COLLAPSED ? View.VISIBLE : View.INVISIBLE);
        }
    };

    @Override
    public int getRes() {
        return R.layout.activity_main;
    }

    @Override
    public Activity getViewContext() {
        return this;
    }

    @Override
    public void onClick(Type item) {
        DetailScreenActivity.launch(this, (User) item);
    }
}
