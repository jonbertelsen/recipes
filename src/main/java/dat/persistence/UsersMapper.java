package dat.persistence;

import dat.entities.Users;
import dat.persistence.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersMapper
{
    private DatabaseConnector databaseConnector;

    public UsersMapper(DatabaseConnector databaseConnector)
    {
        this.databaseConnector = databaseConnector;
    }

    public List<Users> getUsers()
    {
        String sql = "SELECT * FROM users";
        List<Users> usersList = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int userId = rs.getInt("user_id");
                    String userName = rs.getString("user_name");
                    String password = rs.getString("password");
                    String role = rs.getString("role");

                    Users newUser = new Users(userId, userName, password, role);
                    usersList.add(newUser);
                }

            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return usersList;
    }

    public Users getUsersById(int userId)
    {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        List<Users> usersList = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    String userName = rs.getString("user_name");
                    String password = rs.getString("password");
                    String role = rs.getString("role");

                    Users newUser = new Users(userId, userName, password, role);
                    return newUser;
                }

            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int createUser(Users users)
    {
        String sql = "INSERT INTO users (user_name, password, role) VALUES (?,?,?)";

        try (Connection connection = databaseConnector.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setString(1, users.getUserName());
                ps.setString(2, users.getPassword());
                ps.setString(3, users.getRole());

                ps.executeUpdate();
                ResultSet keySet = ps.getGeneratedKeys();
                if (keySet.next())
                {
                    return keySet.getInt(1);
                } else
                {
                    return -1;
                }
            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int updateUsers(Users users)
    {
        String sql = "UPDATE users SET user_name = ?, password = ?, role = ? WHERE user_id = ?";
        try (Connection connection = databaseConnector.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, users.getUserName());
                ps.setString(2, users.getPassword());
                ps.setString(3, users.getRole());
                ps.setInt(4, users.getUserId());

                int rowsAffected = ps.executeUpdate();
                return rowsAffected;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public int deleteUser(int userId)
    {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (Connection connection = databaseConnector.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, userId);
                int rowsAffected = ps.executeUpdate();
                return rowsAffected;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return 0;
    }



}
