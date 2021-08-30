package com.koreait.day3.service;

import com.koreait.day3.model.entity.DtUser;
import com.koreait.day3.model.entity.OrderGroup;
import com.koreait.day3.model.network.Header;
import com.koreait.day3.model.network.Pagination;
import com.koreait.day3.model.network.request.UserApiRequest;
import com.koreait.day3.model.network.response.ItemApiResponse;
import com.koreait.day3.model.network.response.OrderGroupApiResponse;
import com.koreait.day3.model.network.response.UserApiResponse;
import com.koreait.day3.model.network.response.UserOrderInfoApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service    // 서비스레이어, 내부에서 자바로직을 처리함
@RequiredArgsConstructor
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, DtUser> {

    private final ItemApiLogicService itemApiLogicService;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        UserApiRequest userApiRequest = request.getData();

        DtUser dtUser = DtUser.builder()
                .userid(userApiRequest.getUserid())
                .userpw(userApiRequest.getUserpw())
                .hp(userApiRequest.getHp())
                .email(userApiRequest.getEmail())
                .status(userApiRequest.getStatus())
                .build();
        DtUser newDtUser = baseRepository.save(dtUser);
        return Header.OK(response(newDtUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(user -> response(user))    // 데이터가 있으면 실행
                .map(Header::OK)    // 데이터가 있으면 실행
                .orElseGet(     // 데이터가 없으면 실행
                        () -> Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();
        Optional<DtUser> optional = baseRepository.findById(userApiRequest.getId());
        return optional.map(user -> {

                    user.setUserid(userApiRequest.getUserid());
                    user.setUserpw(userApiRequest.getUserpw());
                    user.setHp(userApiRequest.getHp());
                    user.setEmail(userApiRequest.getEmail());
                    user.setStatus(userApiRequest.getStatus());

                    return user;
                }).map(user -> baseRepository.save(user))
                .map(user -> response(user))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<DtUser> optional = baseRepository.findById(id);

        return optional.map(user -> {
            baseRepository.delete(user);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

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
    */

    private UserApiResponse response(DtUser dtUser) {
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(dtUser.getId())
                .userid(dtUser.getUserid())
                .userpw(dtUser.getUserpw())
                .email(dtUser.getEmail())
                .hp(dtUser.getHp())
                .regDate(dtUser.getRegDate())
                .status(dtUser.getStatus())
                .build();

        return userApiResponse;
    }

//    public Header<UserOrderInfoApiResponse> orderInfo(Long id){
//        DtUser user = baseRepository.getById(id);
//        UserApiResponse userApiResponse = response(user);
//
//        List<OrderGroup> orderGroupList = user.getOrderGroupsList();
//        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream()
//                .map(orderGroup -> {
//                    OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.response(orderGroup).getData();
//                    List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
//                            .map(detail -> detail.getItem())
//                            .map(item -> itemApiLogicService.response(item).getData())
//                            .collect(Collectors.toList());  // collect는 stream의 데이터를 변형등의 처리를 하고 원하는 자료형으로 변환
//
//                        orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
//                        return orderGroupApiResponse;
//                })
//                .collect(Collectors.toList());  // 전체를 리스트 형태로 collect에 담는다.
//        userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);
//        UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
//                .userApiResponse(userApiResponse)
//                .build();
//        return Header.OK(userOrderInfoApiResponse);
//    }

    public Header<List<UserApiResponse>> search(Pageable pageable){
        Page<DtUser> user = baseRepository.findAll(pageable);
        List<UserApiResponse> userApiResponsesList = user.stream()
                .map(users -> response(users))
                .collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(user.getTotalPages())
                .totalElements(user.getTotalElements())
                .currentPage(user.getNumber())
                .currentElements(user.getNumberOfElements())
                .build();
        return Header.OK(userApiResponsesList, pagination);
    }
}
