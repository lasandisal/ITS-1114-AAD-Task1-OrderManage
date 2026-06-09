package lk.ijse.task1.controller;

import lk.ijse.task1.constant.CommonResponse;
import lk.ijse.task1.dto.ItemDTO;
import lk.ijse.task1.service.ItemService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.task1.constant.ResponseMessage.SUCCESS_MESSAGE;
import static lk.ijse.task1.constant.ResponseStatusCode.OPERATION_SUCCESS;

@RestController
@RequestMapping(value = "v1/items")
public class ItemController {

    private final ItemService itemService;
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveItem(@RequestBody ItemDTO itemDTO){
        itemService.saveItem(itemDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getItems(){
        List<ItemDTO> itemDTOList = itemService.getItems();
        return new CommonResponse(OPERATION_SUCCESS,itemDTOList,SUCCESS_MESSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateItem(@RequestBody ItemDTO itemDTO){
        itemService.updateItem(itemDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getItemsByName(@RequestParam String itemName){
        List<ItemDTO> itemDTOList = itemService.getItemsByName(itemName);
        return new CommonResponse(OPERATION_SUCCESS,itemDTOList,SUCCESS_MESSAGE);
    }
}
