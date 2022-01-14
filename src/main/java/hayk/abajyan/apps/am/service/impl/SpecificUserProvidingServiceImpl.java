package hayk.abajyan.apps.am.service.impl;

import hayk.abajyan.apps.am.exception.DataBaseException;
import hayk.abajyan.apps.am.model.User;
import hayk.abajyan.apps.am.repository.MessageRepository;
import hayk.abajyan.apps.am.repository.UserRepository;
import hayk.abajyan.apps.am.service.SpecificUserProvidingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpecificUserProvidingServiceImpl implements SpecificUserProvidingService {


    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Autowired
    public SpecificUserProvidingServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> get() throws DataBaseException {
        int id = messageRepository.getTheMostUserId();
        return userRepository.findById(id);
    }
}
