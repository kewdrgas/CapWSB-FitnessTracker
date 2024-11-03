package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    @GetMapping("/simple")
    public List<SimpleUserDto> getAllSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }
    @GetMapping("/{id}")
    public UserDto getSimpleUser(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @GetMapping("/email")
    public UserDto getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @GetMapping("/find-users-by-email")
    public Stream<SimpleUserDto> findUsersByEmail(@RequestParam String query){
        return userService.findUsersByEmailFragment(query)
                .stream()
                .map(userMapper::toSimpleDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId)  {
        userService.removeUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/older/{age}")
    public Stream<SimpleUserDto> getOlderUsers(@PathVariable int age){
        return userService.getOlderUsers(age)
                .stream()
                .map(userMapper::toSimpleDto);
    }


    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) throws InterruptedException {
        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");
        User user = userMapper.toEntity(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(userService.createUser(user)));

    }
}