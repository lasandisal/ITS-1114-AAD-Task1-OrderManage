package lk.ijse.task1.service.impl;

import lk.ijse.task1.dto.ItemDTO;
import lk.ijse.task1.entity.Item;
import lk.ijse.task1.repository.ItemRepository;
import lk.ijse.task1.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void saveItem(ItemDTO itemDTO) {
        log.info("Saving item into DB");
        try {
            if (itemDTO.getName() == null) {
                throw new RuntimeException("item name is null");
            }

            Item item = new Item();
            item.setName(itemDTO.getName());
            itemRepository.save(item);
        } catch (Exception e) {
            log.error("Error saving item: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ItemDTO> getItems() {
        log.info("getItems");
        try {
            List<Item> items = itemRepository.findAll();
            List<ItemDTO> itemDTOS = new ArrayList<>();
            for (Item item : items) {
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setName(item.getName());
                itemDTO.setId(item.getId());
                itemDTOS.add(itemDTO);
            }
            return itemDTOS;
        } catch (Exception e) {
            log.error("Error fetching items: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void updateItem(ItemDTO itemDTO) {
        log.info("updateItem");
        try {
            if (itemDTO.getId() == null) {
                throw new RuntimeException("item id is null");
            }
            if (itemDTO.getName() == null) {
                throw new RuntimeException("item name is null");
            }
            Optional<Item> itemOptional = itemRepository.findById(itemDTO.getId());
            if (!itemOptional.isPresent()) {
                throw new RuntimeException("Item not found");
            }
            Item item = itemOptional.get();
            item.setName(itemDTO.getName());
            itemRepository.save(item);
        } catch (Exception e) {
            log.error("Error updating item: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ItemDTO> getItemsByName(String itemName) {
        log.info("getItemsByName");
        try {
            List<ItemDTO> itemDTOS = new ArrayList<>();
            List<Item> items = itemRepository.filterItems(itemName);
            for (Item item : items) {
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setName(item.getName());
                itemDTO.setId(item.getId());
                itemDTOS.add(itemDTO);
            }
            return itemDTOS;
        } catch (Exception e) {
            log.error("Error filtering items: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}