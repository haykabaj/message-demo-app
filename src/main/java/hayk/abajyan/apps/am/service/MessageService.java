package hayk.abajyan.apps.am.service;

import hayk.abajyan.apps.am.exception.DataBaseException;
import hayk.abajyan.apps.am.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    Message add(Message message)throws DataBaseException;


    List<Message> getAll(int senderId, int receiverId) throws DataBaseException;
}
