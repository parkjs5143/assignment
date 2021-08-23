package com.koreait.day03.controller.api;

import com.koreait.day03.ifs.CrudInterface;
import com.koreait.day03.model.network.Header;
import com.koreait.day03.model.network.request.ItemApiRequest;
import com.koreait.day03.model.network.response.ItemApiResponse;
import com.koreait.day03.service.ItemApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {
    private final ItemApiLogicService itemApiLogicService;
/*
{
    "transaction_time":"2021-08-23",
        "resultCode":"OK",
        "description":"OK",
        "data":{
            "userid":"cherry",
            "userpw":"1234",
            "email":"cherry@cherry.com",
            "hp":"010-1234-1234"
        }
}
*/
    @Override
    @PostMapping    // http://127.0.0.1:9090/api/item (post)
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
        return itemApiLogicService.create(request);
    }

    // http://127.0.0.1:9090/api/item/3
    @Override
    @GetMapping("{id}")
    public Header<ItemApiResponse> read(@PathVariable(name="id") Long id) {
        return itemApiLogicService.read(id);
    }

    @Override
    @PutMapping
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
        return itemApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<ItemApiResponse> delete(@PathVariable Long id) {
        return itemApiLogicService.delete(id);
    }
}
