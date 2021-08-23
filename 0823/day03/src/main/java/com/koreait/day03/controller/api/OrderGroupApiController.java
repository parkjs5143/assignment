package com.koreait.day03.controller.api;

import com.koreait.day03.ifs.CrudInterface;
import com.koreait.day03.model.network.Header;
import com.koreait.day03.model.network.request.OrderGroupApiRequest;
import com.koreait.day03.model.network.response.OrderGroupApiResponse;
import com.koreait.day03.service.OrderGroupApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderGroupApiController implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {
    private final OrderGroupApiLogicService orderGroupApiLogicService;

    @Override
    @PostMapping    // http://127.0.0.1:9090/api/order (post)
    public Header<OrderGroupApiResponse> create(@RequestBody Header<OrderGroupApiRequest> request) {
        return orderGroupApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<OrderGroupApiResponse> read(@PathVariable(name="id")Long id) {
        return orderGroupApiLogicService.read(id);
    }

    @Override
    @PutMapping
    public Header<OrderGroupApiResponse> update(@RequestBody Header<OrderGroupApiRequest> request) {
        return orderGroupApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<OrderGroupApiResponse> delete(@PathVariable Long id) {
        return orderGroupApiLogicService.delete(id);
    }
}
