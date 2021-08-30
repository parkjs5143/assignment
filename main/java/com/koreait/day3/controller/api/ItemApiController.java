package com.koreait.day3.controller.api;

import com.koreait.day3.model.entity.Item;
import com.koreait.day3.model.network.Header;
import com.koreait.day3.model.network.Pagination;
import com.koreait.day3.model.network.request.ItemApiRequest;
import com.koreait.day3.model.network.response.CategoryApiResponse;
import com.koreait.day3.model.network.response.ItemApiResponse;
import com.koreait.day3.service.BaseService;
import com.koreait.day3.service.ItemApiLogicService;
import com.koreait.day3.service.PartnerApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemApiController extends BaseService<ItemApiRequest, ItemApiResponse, Item> {

    private final ItemApiLogicService itemApiLogicService;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        return null;
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        return null;
    }

    @Override
    public Header<ItemApiResponse> delete(Long id) {
        return null;
    }

    @GetMapping("")
    public Header<List<ItemApiResponse>> findAll(@PageableDefault(sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return itemApiLogicService.search(pageable);
    }
}
