package com.fox.cradle.features.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController
{
    private final UserService _userService;

    public UserController(UserService userService)
    {
        this._userService = userService;
    }

    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable long id)
    {
        return _userService.getUserById(id);
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username)
    {
        return _userService.getUserByUsername(username);
    }

    @PostMapping
    public User addUser(@RequestBody User user)
    {
        return _userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable long id)
    {
        return _userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public User putUser(@PathVariable long id, @RequestBody User newUserData)
    {
        return _userService.putUser(id, newUserData);
    }
}
