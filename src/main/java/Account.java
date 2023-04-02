import java.util.UUID;

public abstract class Account {
    private UUID id;
    private String username;
    private String password;

    public Account(String username, String password) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean editUserName(String newUsername){
        if (newUsername.equals(this.getUsername())){
            return false;
        } else {
            setUsername(newUsername);
            return true;
        }
    }

    public boolean editPassword(String newPassword){
        if (newPassword.equals(this.getPassword())){
            return false;
        } else {
            setPassword(newPassword);
            return true;
        }
    }
}
