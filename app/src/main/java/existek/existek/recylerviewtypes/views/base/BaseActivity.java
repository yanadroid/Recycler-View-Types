package existek.existek.recylerviewtypes.views.base;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    private ViewDataBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(getViewContext(), getRes());

    }

    public ViewDataBinding getBinding() {
        return binding;
    }

    public abstract int getRes();
    public abstract Activity getViewContext();
}
