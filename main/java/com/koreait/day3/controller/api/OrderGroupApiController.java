package com.koreait.day3.controller.api;

import com.koreait.day3.ifs.CrudInterface;
import com.koreait.day3.model.entity.OrderGroup;
import com.koreait.day3.model.network.Header;
import com.koreait.day3.model.network.request.OrderGroupApiRequest;
import com.koreait.day3.model.network.request.UserApiRequest;
import com.koreait.day3.model.network.response.ItemApiResponse;
import com.koreait.day3.model.network.response.OrderGroupApiResponse;
import com.koreait.day3.model.network.response.UserApiResponse;
import com.koreait.day3.service.BaseService;
import com.koreait.day3.service.OrderGroupApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ordergroup")
@RequiredArgsConstructor
public class OrderGroupApiController extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {
    private final OrderGroupApiLogicService orderGroupApiLogicService;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
        return null;
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
        return null;
    }

    @Override
    public Header<OrderGroupApiResponse> delete(Long id) {
        return null;
    }

    @GetMapping("")
    public Header<List<OrderGroupApiResponse>> findAll(@PageableDefault(sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return orderGroupApiLogicService.search(pageable);
    }
}
