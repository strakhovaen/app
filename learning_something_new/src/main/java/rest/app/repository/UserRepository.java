package rest.app.repository;

import org.springframework.stereotype.Repository;
import rest.app.model.Role;
import rest.app.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class UserRepository{

    private List<User> userList;

    public UserRepository(){
        init();
    }

    public User getUserById(Integer id){
        return userList.get(id);
    }

    public void save(User user) {
        try {
            userList.set(user.getId(), user);
        } catch (Exception e) {
            userList.add(user);
        }
    }

    public List<User> getUserList(){
        return userList;
    }

    public User getUserByUsername(String username){
        for (User user : userList){
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new NoSuchElementException();
    }

    public User getUserByCookie(String cookie){
        for (User user : userList){
            if (user.getLastCookie().equals(cookie)) {
                return user;
            }
        }
        throw new NoSuchElementException();
    }

    private void init() {
        userList = new ArrayList<>();

        User user1 = User.builder()
                .id(userList.size())
                .username("admin")
                .password("123")
                .lastCookie("")
                .role(Role.ADMIN)
                .build();

        userList.add(user1);

        System.out.println("Users list");
        userList.forEach(user -> System.out.println("User:" + user.toString()));
    }

}
