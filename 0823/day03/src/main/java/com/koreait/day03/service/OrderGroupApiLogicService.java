package com.koreait.day03.service;

import com.koreait.day03.ifs.CrudInterface;
import com.koreait.day03.model.entity.Item;
import com.koreait.day03.model.entity.OrderGroup;
import com.koreait.day03.model.enumclass.ItemStatus;
import com.koreait.day03.model.enumclass.OrderType;
import com.koreait.day03.model.network.Header;
import com.koreait.day03.model.network.request.ItemApiRequest;
import com.koreait.day03.model.network.request.OrderGroupApiRequest;
import com.koreait.day03.model.network.response.OrderGroupApiResponse;
import com.koreait.day03.repository.OrderGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderGroupApiLogicService implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {
    private final OrderGroupRepository orderGroupRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest orderGroupApiRequest = request.getData();
        OrderGroup orderGroup = OrderGroup.builder()
                .orderType(OrderType.ALL)
                .status(orderGroupApiRequest.getStatus())
                .revAddress(orderGroupApiRequest.getRevAddress())
                .revName(orderGroupApiRequest.getRevName())
                .paymentType(orderGroupApiRequest.getPaymentType())
                .totalPrice(orderGroupApiRequest.getTotalPrice())
                .totalQuantity(orderGroupApiRequest.getTotalQuantity())
                .build();
        OrderGroup orderGroups = orderGroupRepository.save(orderGroup);
        return response(orderGroups);
    }

    @Override   // http://127.0.0.1:9090/api/order/21
    public Header<OrderGroupApiResponse> read(Long id) {
        return orderGroupRepository.findById(id)
                .map(orderGroup -> response(orderGroup))
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
                "id":"21",
                "status":"결제대기",
                "revAddress":"서울시 강남구",
                "revName":"이메론",
                "paymentType":"카드",
                "totalPrice":100000,
                "totalQuantity":"1"
            }
    }
 */
    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest orderGroupApiRequest = request.getData();
        Optional<OrderGroup> optional = orderGroupRepository.findById(orderGroupApiRequest.getId());
        return optional.map(orderGroup -> {
                    orderGroup.setStatus(orderGroupApiRequest.getStatus());
                    orderGroup.setRevAddress(orderGroupApiRequest.getRevAddress());
                    orderGroup.setRevName(orderGroupApiRequest.getRevName());
                    orderGroup.setPaymentType(orderGroupApiRequest.getPaymentType());
                    orderGroup.setTotalPrice(orderGroupApiRequest.getTotalPrice());
                    orderGroup.setTotalQuantity(orderGroupApiRequest.getTotalQuantity());
                    System.out.println(orderGroup);

                    return orderGroup;
                }).map(orderGroup -> orderGroupRepository.save(orderGroup))
                .map(orderGroup -> response(orderGroup))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<OrderGroup> optional = orderGroupRepository.findById(id);

        return optional.map(orderGroup -> {
            orderGroupRepository.delete(orderGroup);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<OrderGroupApiResponse> response(OrderGroup orderGroups) {
        OrderGroupApiResponse orderGroupApiResponse = OrderGroupApiResponse.builder()
                .id(orderGroups.getId())
                .orderType(orderGroups.getOrderType())
                .status(orderGroups.getStatus())
                .revAddress(orderGroups.getRevAddress())
                .revName(orderGroups.getRevName())
                .paymentType(orderGroups.getPaymentType())
                .totalPrice(orderGroups.getTotalPrice())
                .totalQuantity(orderGroups.getTotalQuantity())
                .orderAt(orderGroups.getOrderAt())
                .arrivalDate(orderGroups.getArrivalDate())
                .build();
        return Header.OK(orderGroupApiResponse);
    }
}
