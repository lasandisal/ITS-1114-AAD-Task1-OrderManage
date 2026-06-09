package lk.ijse.task1.dto;

import lk.ijse.task1.entity.Customer;
import lk.ijse.task1.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private Long total;
    private Date date;
    private Long customer_id;
    private List<ItemDTO> items;
}
