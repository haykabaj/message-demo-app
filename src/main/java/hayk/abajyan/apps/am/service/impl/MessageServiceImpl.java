package hayk.abajyan.apps.am.service.impl;

import hayk.abajyan.apps.am.exception.DataBaseException;
import hayk.abajyan.apps.am.model.Message;
import hayk.abajyan.apps.am.repository.MessageRepository;
import hayk.abajyan.apps.am.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, MessageRepository messageRepository1) {
        this.messageRepository = messageRepository1;
    }

    @Override
    public Message add(Message message) throws DataBaseException {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getAll(int senderId, int receiverId) throws DataBaseException{
        return messageRepository.getAll(senderId, receiverId);
    }
}
