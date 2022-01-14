package hayk.abajyan.apps.am.service;

import hayk.abajyan.apps.am.exception.DataBaseException;
import hayk.abajyan.apps.am.model.Message;
import hayk.abajyan.apps.am.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User add(User user);


    Optional<User> get(String email, String password) throws DataBaseException;

    List<User> getAll() throws DataBaseException;

    User userExist(String email);


    void delete(int id);


}
