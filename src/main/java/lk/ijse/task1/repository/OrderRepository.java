package lk.ijse.task1.repository;

import lk.ijse.task1.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.customer.name LIKE %:customerName%")
    List<Order> filterOrders(String customerName);

    @Query(value = "SELECT o FROM Order o " +
            "JOIN Customer c ON o.customer = c " +
            "WHERE (?1 IS NULL OR c.name LIKE %?1%)")
    List<Order> filterOrdersJPQL( String customerName);
}