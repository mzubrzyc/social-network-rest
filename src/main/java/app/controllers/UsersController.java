package app.controllers;

import app.controllers.utils.ControllerDtoMapper;
import app.dto.UserDto;
import app.modules.user.UserFacade;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(path = "/users")
public class UsersController {

    private final UserFacade userFacade;

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long userId) throws Exception {
        userFacade.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<UserDto>> getAlUsers() {
        return new ResponseEntity<>(ControllerDtoMapper.toUserDto(userFacade.getAllUsers()), HttpStatus.OK);
    }

    @PostMapping(path = "/save", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpStatus> saveUser(@RequestBody UserDto userDto) throws Exception {
        userFacade.saveNewUser(ControllerDtoMapper.toUsr(userDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpStatus> updateUser(@RequestBody UserDto userDto) throws Exception {
        userFacade.updateUser(ControllerDtoMapper.toUsr(userDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
