package lk.ijse.task1.repository;

import lk.ijse.task1.dto.ItemDTO;
import lk.ijse.task1.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    @Query(value = "SELECT * FROM item WHERE (?1 IS NULL OR name LIKE %?1%)",
            nativeQuery = true)
    List<Item> filterItems(String itemName);

    @Query(value = "SELECT new lk.ijse.task1.dto.ItemDTO(i.id, i.name) " +
            "FROM Item i WHERE (?1 IS NULL OR i.name LIKE %?1%)")
    List<ItemDTO>filterItemsJPQL(String itemName);

    // 1 order - all items
    @Query("SELECT i FROM OrderItem oi " +
            "JOIN Item i ON oi.item = i " +
            "JOIN Order o ON oi.orders = o " +
            "WHERE o.id = ?1")
//    @Query("SELECT oi.item FROM OrderItem oi " +
//            "WHERE oi.orders.id = ?1")
    List<Item> findItemsByOrder(Long orderId);

    @Query("SELECT new lk.ijse.task1.dto.ItemDTO(i.id, i.name) " +
            "FROM OrderItem oi " +
            "JOIN Item i ON oi.item = i " +
            "JOIN Order o ON oi.orders = o " +
            "WHERE o.id = ?1")
    List<ItemDTO> findItemsByOrderDTO(Long orderId);
}
