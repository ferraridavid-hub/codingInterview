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

    @GetMapping
    public ResponseEntity<List<UserEntity>> getUserListByFilter(@RequestParam(required = false) String name,
                                                                @RequestParam(required = false) String lastName) {
        if (name != null && lastName != null) {
            return ResponseEntity.ofNullable(userRepository.findByNameAndLastName(name, lastName));
        }

        if (name != null) {
            return ResponseEntity.ofNullable(userRepository.findByName(name));
        }

        if (lastName != null) {
            return ResponseEntity.ofNullable(userRepository.findByLastName(lastName));
        }

        return ResponseEntity.ofNullable(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        return ResponseEntity.of(userRepository.findById(id));
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
                    user.setEmail(userEntity.getEmail());
                    return userRepository.save(user);
                })
                .orElseGet(() -> userRepository.save(userEntity));

        return ResponseEntity.ofNullable(userToBePersisted);
    }

}

