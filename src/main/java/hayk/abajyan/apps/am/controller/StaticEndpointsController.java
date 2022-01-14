package hayk.abajyan.apps.am.controller;

import hayk.abajyan.apps.am.exception.DataBaseException;
import hayk.abajyan.apps.am.model.User;
import hayk.abajyan.apps.am.service.SpecificUserProvidingService;
import hayk.abajyan.apps.am.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/api/v1")
public class StaticEndpointsController {

    private final SpecificUserProvidingService specificUserProvidingService;
    private static final Logger logger = LoggerFactory.getLogger(StaticEndpointsController.class);


    @Autowired
    public StaticEndpointsController(SpecificUserProvidingService specificUserProvidingService) {
        this.specificUserProvidingService = specificUserProvidingService;
    }

    /** endpoint to get the most active user */
    @GetMapping("/active_user")
    public ResponseEntity<User> getTheMostActiveUser(){
        try {
            Optional<User> userEntity = this.specificUserProvidingService.get();
            return userEntity.map(ResponseEntity::ok).orElseGet(()
                    -> ResponseEntity.notFound().build());
        } catch (DataBaseException e){
            e.printStackTrace();
            logger.error("error", e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
