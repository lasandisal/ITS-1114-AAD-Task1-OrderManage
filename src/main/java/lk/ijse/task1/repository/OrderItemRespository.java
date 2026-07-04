package lk.ijse.task1.repository;

import lk.ijse.task1.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRespository extends JpaRepository<OrderItem,Long> {
}
