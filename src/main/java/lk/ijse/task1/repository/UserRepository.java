package lk.ijse.task1.repository;

import lk.ijse.task1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT u FROM User u WHERE u.role = 'ADMIN'")
    List<User> getAllCustomers();


    Optional<User> findByName(String username);

    Optional<User> findByNameAndPassword(String username, String password);
}
