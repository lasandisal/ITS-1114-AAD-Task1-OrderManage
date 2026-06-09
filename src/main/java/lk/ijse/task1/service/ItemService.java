package lk.ijse.task1.service;

import lk.ijse.task1.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    List<ItemDTO> getItems();
    void updateItem(ItemDTO itemDTO);
    List<ItemDTO> getItemsByName(String itemName);
}
