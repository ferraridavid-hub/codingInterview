package coding.interview.florence.consulting.group.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        return ResponseEntity.of(userRepository.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return ResponseEntity.ofNullable(userRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        return ResponseEntity.ofNullable(userRepository.save(userEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> replaceUser(@RequestBody UserEntity userEntity, @PathVariable Long id) {
        UserEntity userToBePersisted = userRepository.findById(id)
                .map(user -> {
                    user.setLastName(userEntity.getLastName());
                    user.setName(userEntity.getName());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {return userRepository.save(userEntity);});

        return ResponseEntity.ofNullable(userToBePersisted);
    }
}

