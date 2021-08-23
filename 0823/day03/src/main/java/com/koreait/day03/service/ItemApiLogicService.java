package com.koreait.day03.service;

import com.koreait.day03.ifs.CrudInterface;
import com.koreait.day03.model.entity.Item;
import com.koreait.day03.model.enumclass.ItemStatus;
import com.koreait.day03.model.network.Header;
import com.koreait.day03.model.network.request.ItemApiRequest;
import com.koreait.day03.model.network.response.ItemApiResponse;
import com.koreait.day03.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {
    private final ItemRepository itemRepository;
/*
    {
        "transaction_time":"2021-08-23",
            "resultCode":"OK",
            "description":"OK",
            "data":{
        "name":"닌텐도",
                "title":"닌텐도 스위치",
                "content":"노우 재밌어요",
                "price":300000,
                "creatBy":"순정잉"
            }
    }

 */
    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        ItemApiRequest itemApiRequest = request.getData();
        Item item = Item.builder()
                .name(itemApiRequest.getName())
                .status(ItemStatus.REGISTERED)
                .title(itemApiRequest.getTitle())
                .content(itemApiRequest.getContent())
                .price(itemApiRequest.getPrice())
                .createBy(itemApiRequest.getCreateBy())
                .build();
        Item items = itemRepository.save(item);
        return response(items);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        return itemRepository.findById(id)
                .map(item -> response(item))
                .orElseGet(
                        () -> Header.ERROR("노 데이터")
                );
    }
/*
    {
        "transaction_time":"2021-08-23",
            "resultCode":"OK",
            "description":"OK",
            "data":{
        "id":21,
                "name":"닌텐도",
                "title":"닌텐도 스위치",
                "content":"노우 재밌어요",
                "price":300000,
                "creatBy":"순정잉"
    }
    }

 */
    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        ItemApiRequest itemApiRequest = request.getData();
        Optional<Item> optional = itemRepository.findById(itemApiRequest.getId());
        return optional.map(item -> {
            item.setName(itemApiRequest.getName());
            item.setStatus(ItemStatus.REGISTERED);
            item.setTitle(itemApiRequest.getTitle());
            item.setContent(itemApiRequest.getContent());
            item.setPrice(itemApiRequest.getPrice());
            item.setUpdateBy(itemApiRequest.getUpdateBy());
            System.out.println(item);

            return item;
        }).map(item -> itemRepository.save(item))
                .map(item -> response(item))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Item> optional = itemRepository.findById(id);

        return optional.map(item -> {
            itemRepository.delete(item);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public Header<ItemApiResponse> response(Item item){
        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .status(item.getStatus())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .regDate(item.getRegDate())
                .build();
        return Header.OK(itemApiResponse);
    }
}
