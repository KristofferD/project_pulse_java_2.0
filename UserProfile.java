public class UserProfile {
    private String name;
    private String email;
    private String mobile;

    public UserProfile(String name, String email, String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    // Getters och setters för användarprofilens egenskaper

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }
}
