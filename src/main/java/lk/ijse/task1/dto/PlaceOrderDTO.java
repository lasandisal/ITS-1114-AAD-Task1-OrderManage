package lk.ijse.task1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderDTO {
    private long customerId;
    private BigDecimal total;
    private List<Long> itemIds;

}
