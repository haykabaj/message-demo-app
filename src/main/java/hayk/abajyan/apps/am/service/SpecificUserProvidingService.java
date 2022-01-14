package hayk.abajyan.apps.am.service;

import hayk.abajyan.apps.am.exception.DataBaseException;
import hayk.abajyan.apps.am.model.User;

import java.util.Optional;

public interface SpecificUserProvidingService {
    Optional<User> get() throws DataBaseException;
}
