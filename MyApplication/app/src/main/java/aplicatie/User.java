package aplicatie;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String username;
    private String parola;
    private String tip;

    public User(String name, String username, String parola, String tip, int id){
        this.name = name;
        this.parola=parola;
        this.username = username;
        this.tip = tip;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameUser() { return name; }

    public void setNameUser(String name) { this.name = name; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getParola() { return parola; }

    public void setParola(String parola) { this.parola = parola; }

    public String getTip() { return tip; }

    public void setTip(String tip) { this.tip = tip; }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
