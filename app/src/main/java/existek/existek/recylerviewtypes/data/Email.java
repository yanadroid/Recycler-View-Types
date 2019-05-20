package existek.existek.recylerviewtypes.data;


public class Email implements Type {

    private String email = "";
    private boolean error;
    private boolean typing;
    private boolean normal = true;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isTyping() {
        return typing;
    }

    public void setTyping(boolean typing) {
        this.typing = typing;
    }

    public boolean isNormal() {
        return normal;
    }

    public void setNormal(boolean normal) {
        this.normal = normal;
    }

    @Override
    public int getType() {
        return Type.TYPE_EMAIL;
    }
}
