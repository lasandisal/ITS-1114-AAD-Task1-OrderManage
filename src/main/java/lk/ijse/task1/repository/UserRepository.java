package lk.ijse.task1.repository;

import lk.ijse.task1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
