package business.persistence;

import business.entities.User;
import business.persistence.Database;
import business.persistence.UserMapper;
import business.exceptions.UserException;

import java.util.List;

public class UserFacade
{
    UserMapper userMapper;

    public UserFacade(Database database)
    {
        userMapper = new UserMapper(database);
    }

    public User login(String email, String password) throws UserException
    {
        return userMapper.login(email, password);
    }

    public User createUser(String email, String password,String name, String phone) throws UserException
    {
        User user = new User(email, password, "customer");
        user.setPhone(phone);
        user.setName(name);
        userMapper.createUser(user);
        return user;
    }

    public List<User> getAllCustomers() throws UserException {
        return userMapper.getAllCustomers();
    }

    public User getUserById(int userID) throws UserException {
        return userMapper.getUserById(userID);
    }

}
