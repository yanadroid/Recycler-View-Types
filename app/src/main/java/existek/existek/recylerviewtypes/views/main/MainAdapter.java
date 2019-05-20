package existek.existek.recylerviewtypes.views.main;

import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import existek.existek.recylerviewtypes.R;
import existek.existek.recylerviewtypes.data.Email;
import existek.existek.recylerviewtypes.data.Progress;
import existek.existek.recylerviewtypes.data.Random;
import existek.existek.recylerviewtypes.data.Settings;
import existek.existek.recylerviewtypes.data.Type;
import existek.existek.recylerviewtypes.data.User;
import existek.existek.recylerviewtypes.databinding.ItemEmailBinding;
import existek.existek.recylerviewtypes.databinding.ItemProgressBinding;
import existek.existek.recylerviewtypes.databinding.ItemRandomBinding;
import existek.existek.recylerviewtypes.databinding.ItemSettingsBinding;
import existek.existek.recylerviewtypes.databinding.ItemUserBinding;
import existek.existek.recylerviewtypes.utils.UploadImageHelper;
import existek.existek.recylerviewtypes.views.base.BaseViewHolder;
import existek.existek.recylerviewtypes.views.callback.OnEmailChanged;
import existek.existek.recylerviewtypes.views.callback.OnExpandableChanged;
import existek.existek.recylerviewtypes.views.callback.OnImageChanged;
import existek.existek.recylerviewtypes.views.callback.OnItemClickListener;
import existek.existek.recylerviewtypes.views.callback.OnNotifyProgressChanged;
import existek.existek.recylerviewtypes.views.callback.OnUploadImageCallback;
import existek.existek.recylerviewtypes.views.custom.CustomButtonLayout;
import existek.existek.recylerviewtypes.views.custom.CustomEditText;
import existek.existek.recylerviewtypes.views.custom.ProgressBarAnimation;


public class MainAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Type> data;
    private OnItemClickListener listener;
    private static final long TIME_DURATION = TimeUnit.MINUTES.toMillis(5);

    public MainAdapter() {
        data = new LinkedList<>();
    }

    public void setData(List<Type> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (type) {
            case Type.TYPE_EMAIL:
                ItemEmailBinding emailBinding = ItemEmailBinding.inflate(inflater, viewGroup, false);
                return new EmailViewHolder(emailBinding);
            case Type.TYPE_PROGRESS:
                ItemProgressBinding progressBinding = ItemProgressBinding.inflate(inflater, viewGroup, false);
                return new ProgressViewHolder(progressBinding);
            case Type.TYPE_RANDOM:
                ItemRandomBinding randomBinding = ItemRandomBinding.inflate(inflater, viewGroup, false);
                return new RandomViewHolder(randomBinding);
            case Type.TYPE_SETTINGS:
                ItemSettingsBinding settingsBinding = ItemSettingsBinding.inflate(inflater, viewGroup, false);
                return new SettingsViewHolder(settingsBinding);
            case Type.TYPE_USER:
                ItemUserBinding userBinding = ItemUserBinding.inflate(inflater, viewGroup, false);
                return new UserViewHolder(userBinding);
            default:
                throw new IllegalArgumentException("Invalid view type");
         }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        Type item = data.get(position);
        holder.bind(item);
        holder.setIsRecyclable(false);

         switch (getItemViewType(position)) {
             case Type.TYPE_PROGRESS:
                 holder.setCallback((OnNotifyProgressChanged)(progress, pos, stop, start) -> {
                     Progress it = (Progress) getItem(pos);
                     it.setProgress(progress);
                     it.setStoped(stop);
                     it.setStarted(start);
                 });
                 break;
             case Type.TYPE_RANDOM:
                 holder.setCallback((OnExpandableChanged) (id, pos, current, expandable) -> {
                     Random it = (Random) getItem(pos);
                     it.setExpandable(expandable);
                     it.setId(id);
                     it.setCurrentPos(current);
                 });
                 break;
             case Type.TYPE_EMAIL:
                 holder.setCallback((OnEmailChanged) (pos, text, error, typing, normal) -> {
                     Email it = (Email) getItem(pos);
                     it.setEmail(text);
                     it.setError(error);
                     it.setNormal(normal);
                     it.setTyping(typing);
                 });
                 break;
             case Type.TYPE_USER:
                 ((UserViewHolder) holder).setListener(pos -> listener.onClick(pos));
                 holder.setCallback((OnImageChanged) (bitmap, pos) -> {
                     User it = (User) getItem(pos);
                     it.setDrawable(bitmap);
                 });
                 break;
         }
    }

    private Type getItem(int pos) {
        return data.get(pos);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }


    public static class EmailViewHolder extends BaseViewHolder<Email> implements CustomEditText.OnClickEventListener {

        private ItemEmailBinding binding;

        public EmailViewHolder(@NonNull ViewDataBinding itemView) {
            super(itemView);
            binding = (ItemEmailBinding) itemView;
            binding.etEmail.setListener(this);
        }

        @Override
        public void bind(Email email) {
            binding.etEmail.setText(email.getEmail());
            binding.tvError.setVisibility(email.isError() ? View.VISIBLE : View.GONE);
            binding.etEmail.changeTint(email.isError() ? R.color.colorAccent : (email.isNormal() ? R.color.colorGrey : R.color.colorGreen));
            binding.etEmail.showCloseIcon(email.isTyping() || email.isError());
        }

        @Override
        public void onEventClose() {
            binding.tvError.setVisibility(View.GONE);
        }

        @Override
        public void onEnterEvent(String value) {
            binding.tvError.setVisibility(View.GONE);
            ((OnEmailChanged)callback).notify(getAdapterPosition(), binding.etEmail.getEmail(), false, false, true);
        }

        @Override
        public void onEventError() {
            binding.tvError.setVisibility(View.VISIBLE);
            ((OnEmailChanged)callback).notify(getAdapterPosition(), binding.etEmail.getEmail(), true, false, false);
        }

        @Override
        public void onEventTyping() {
            binding.tvError.setVisibility(View.GONE);
            if (callback != null) {
                ((OnEmailChanged) callback).notify(getAdapterPosition(), binding.etEmail.getEmail(), false, true, false);
            }
        }
    }

    public static class ProgressViewHolder extends BaseViewHolder<Progress> implements View.OnClickListener {

        private ItemProgressBinding binding;
        private ProgressBarAnimation animation;
        private Progress progress;


        public ProgressViewHolder(@NonNull ViewDataBinding itemView) {
            super(itemView);
            binding = (ItemProgressBinding) itemView;
            binding.btClear.setOnClickListener(this);
            binding.btStart.setOnClickListener(this);
            binding.btStop.setOnClickListener(this);
            binding.progressBar.setMax((int) TIME_DURATION);
        }

        @Override
        public void bind(Progress progress) {
            this.progress = progress;

            binding.progressBar.setProgress(0);

            if (progress.isStarted()) {
                binding.progressBar.setProgress(progress.getProgress());
                startProgressAnimation(progress.getProgress());
            }

            if (progress.isStoped()) {
                binding.progressBar.setProgress(progress.getProgress());
            }
        }

        @Override
        public void onClick(View v) {
           switch (v.getId()) {
               case R.id.bt_clear:
                   clearProgressAnimation();
                   break;
               case R.id.bt_start:
                   startProgressAnimation(!progress.isStoped() ? 0 : progress.getProgress());
                   break;
               case R.id.bt_stop:
                   stopProgressAnimation();
                   break;
           }
        }


        private void startProgressAnimation(int progress) {
            clearProgressAnimation();
            animation = new ProgressBarAnimation(binding.progressBar);
            animation.start(progress, TIME_DURATION);
            animation.setDuration(TIME_DURATION);
            animation.setInterpolator(new DecelerateInterpolator());
            animation.setListener(value -> {
                ((OnNotifyProgressChanged)callback).notify(binding.progressBar.getProgress(), getAdapterPosition(), false, true);
                if (value == TIME_DURATION) {
                    clearProgressAnimation();
                }
            });
            binding.progressBar.startAnimation(animation);
        }

        private void stopProgressAnimation() {
            binding.progressBar.clearAnimation();
            ((OnNotifyProgressChanged)callback).notify(binding.progressBar.getProgress(), getAdapterPosition(), true, false);
        }

        private void clearProgressAnimation() {
            binding.progressBar.setProgress(0);

            if (callback != null) {
                ((OnNotifyProgressChanged)callback).notify(0, getAdapterPosition(), false, false);
            }

            if (animation != null) {
                animation.cancel();
                animation = null;
            }
        }
    }


    public static class RandomViewHolder extends BaseViewHolder<Random> implements CustomButtonLayout.OnPositionCallback {

        private ItemRandomBinding binding;
        private Random random;

        public RandomViewHolder(@NonNull ViewDataBinding itemView) {
            super(itemView);
            binding = (ItemRandomBinding) itemView;
            binding.llContainer.setCallback(this);
        }

        @Override
        public void bind(Random random) {
            this.random = random;
            binding.llContainer.setMax(random.getSize(), getAdapterPosition());

            if (random.isExpandable()) {
                binding.tvDescription.setVisibility(View.VISIBLE);
                binding.llContainer.setCurrentView(random.getId());
                binding.llContainer.setCurrentId(random.getId());
                binding.llContainer.setClicked(random.isExpandable());
                binding.tvDescription.setText(random.getDescriptions().get(random.getCurrentPos()).getText());
            }
        }

        @Override
        public void onPosition(int id, int position) {
            ((OnExpandableChanged)callback).notify(id, getAdapterPosition(), position, true);
            binding.tvDescription.setText(random.getDescriptions().get(position).getText());
        }

        @Override
        public void expandableOpen() {
            binding.tvDescription.setVisibility(View.VISIBLE);
            ((OnExpandableChanged)callback).notify(binding.llContainer.getCurrentId(),  getAdapterPosition(), 0, true);
        }

        @Override
        public void expandableClose() {
            binding.tvDescription.setVisibility(View.GONE);
            ((OnExpandableChanged)callback).notify(binding.llContainer.getCurrentId(), getAdapterPosition(), 0, false);
        }
    }

    public static class SettingsViewHolder extends BaseViewHolder<Settings> {

        private ItemSettingsBinding binding;

        public SettingsViewHolder(@NonNull ViewDataBinding itemView) {
            super(itemView);
            binding = (ItemSettingsBinding) itemView;
        }

        @Override
        public void bind(Settings settings) {
            binding.tvText.setText(settings.getText());
        }
    }

    public static class UserViewHolder extends BaseViewHolder<User> implements OnUploadImageCallback, View.OnClickListener {

        private ItemUserBinding binding;
        private UploadImageHelper upload;
        private User user;
        private OnItemClickListener listener;

        public UserViewHolder(@NonNull ViewDataBinding itemView) {
            super(itemView);
            binding = (ItemUserBinding) itemView;
            upload = new UploadImageHelper();
            upload.setDrawableCallback(this);
            itemView.getRoot().setOnClickListener(this);
        }

        @Override
        public void bind(User user) {
            this.user = user;
            binding.setUser(user);

            if (user.getDrawable() != null) {
                binding.ivPhoto.setImageBitmap(user.getDrawable());
            } else {
                upload.uploadImage(user.getUrl());
            }
        }

        @Override
        public void setImage(Bitmap bitmap) {
            binding.ivPhoto.setImageBitmap(bitmap);
            ((OnImageChanged) callback).notify(bitmap, getAdapterPosition());
        }

        public void setListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            listener.onClick(user);
        }
    }
}
