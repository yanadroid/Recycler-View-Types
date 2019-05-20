package existek.existek.recylerviewtypes.views.base;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import existek.existek.recylerviewtypes.views.callback.BaseCallback;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    protected BaseCallback callback;

    public BaseViewHolder(@NonNull ViewDataBinding itemView) {
        super(itemView.getRoot());
    }

    public abstract void bind(T type);

    public void setCallback(BaseCallback callback) {
        this.callback = callback;
    }
}
