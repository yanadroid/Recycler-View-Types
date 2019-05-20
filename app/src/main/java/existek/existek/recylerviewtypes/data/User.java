package existek.existek.recylerviewtypes.data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class User implements Type, Parcelable {

    private String name;
    private String surname;
    private int position;
    private String url;
    private Bitmap drawable;

    public User(String name, String surname, int position, String url) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getPosition() {
        return position;
    }

    public Bitmap getDrawable() {
        return drawable;
    }

    public void setDrawable(Bitmap drawable) {
        this.drawable = drawable;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int getType() {
        return Type.TYPE_USER;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.surname);
        dest.writeInt(this.position);
        dest.writeString(this.url);
    }

    protected User(Parcel in) {
        this.name = in.readString();
        this.surname = in.readString();
        this.position = in.readInt();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
