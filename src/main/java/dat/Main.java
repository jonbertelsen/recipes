package dat;

import dat.entities.Users;
import dat.persistence.DatabaseConnector;
import dat.persistence.UsersMapper;

import java.util.List;

public class Main
{
    private static final String URL = "jdbc:postgresql://localhost:5432/sportsclub";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static DatabaseConnector databaseConnector = new DatabaseConnector(URL, USERNAME, PASSWORD);
    private static UsersMapper usersMapper = new UsersMapper(databaseConnector);

    public static void main(String[] args)
    {
        System.out.println("Hello DB world");

        List<Users> usersList = usersMapper.getUsers();

        // Print out userslist
        for (Users users : usersList)
        {
            System.out.println(users);
        }

        Users singleUser = usersMapper.getUsersById(1);
        System.out.println("SingleUser: " + singleUser);

        Users updateUser = new Users(singleUser.getUserId(),
                                     singleUser.getUserName(),
                                     "5678",
                                     singleUser.getRole());

        int updateResult = usersMapper.updateUsers(updateUser);

        System.out.println("Create new user");
        Users newUser = new Users("billybob", "1234", "user");
        int newId = usersMapper.createUser(newUser);
        System.out.println("Du har oprettet en ny bruger med userId = " + newId);

        int deleteResult = usersMapper.deleteUser(3);
        System.out.println("Du har slettet " + deleteResult + " bruger(e)");


    }


}