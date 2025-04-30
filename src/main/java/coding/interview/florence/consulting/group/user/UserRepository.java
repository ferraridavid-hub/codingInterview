package coding.interview.florence.consulting.group.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByName(String name);
    List<UserEntity> findByLastName(String lastName);
    List<UserEntity> findByNameAndLastName(String name, String lastName);
}
