package lk.ijse.task1.repository;

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
}
