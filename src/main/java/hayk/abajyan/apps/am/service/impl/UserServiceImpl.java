package hayk.abajyan.apps.am.service.impl;

import hayk.abajyan.apps.am.exception.DataBaseException;
import hayk.abajyan.apps.am.model.Message;
import hayk.abajyan.apps.am.model.User;
import hayk.abajyan.apps.am.repository.UserRepository;
import hayk.abajyan.apps.am.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User add(User user)  {
        return userRepository.save(user);
    }


    @Override
    public Optional<User> get(String email, String password) throws DataBaseException {
        return userRepository.getByEmailAndPassword(email, password);
    }

    @Override
    public List<User> getAll() throws DataBaseException {
        return userRepository.findAll();
    }

    @Override
    public User userExist(String email)  {
        return userRepository.findByEmail(email);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }


}
