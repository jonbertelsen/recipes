package dat.entities;

public class Users
{
    private int userId;
    private String userName;
    private String password;
    private String role;
    public static int count = 10;

    public Users(String userName, String password, String role)
    {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public Users(int userId, String userName, String password, String role)
    {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public int getUserId()
    {
        return userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getPassword()
    {
        return password;
    }

    public String getRole()
    {
        return role;
    }

    @Override
    public String toString()
    {
        return "Users{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
