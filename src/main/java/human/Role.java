package human;

public enum Role {

    actor("actor"),
    director("director");

    String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
