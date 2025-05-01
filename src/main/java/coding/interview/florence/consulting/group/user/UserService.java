package coding.interview.florence.consulting.group.user;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserEntity> getUserListByFilter(String name, String lastName) {

        if (name != null && lastName != null) {
            return userRepository.findByNameAndLastName(name, lastName);
        }

        if (name != null) {
            return userRepository.findByName(name);
        }

        if (lastName != null) {
            return userRepository.findByLastName(lastName);
        }

        return userRepository.findAll();
    }


    public Optional<UserEntity> getUserById(Long id) {

        return userRepository.findById(id);
    }


    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }

        return false;
    }


    public UserEntity createUser(UserEntity userEntity) {

        return userRepository.save(userEntity);
    }

    public Optional<UserEntity> updateUser(UserEntity userEntity, Long id) {

        return userRepository.findById(id)
                .map(user -> {
                    user.setLastName(userEntity.getLastName());
                    user.setName(userEntity.getName());
                    user.setEmail(userEntity.getEmail());
                    return userRepository.save(user);
                });
    }
}
