package com.koreait.day3.controller.api;

import com.koreait.day3.controller.CrudController;
import com.koreait.day3.model.entity.AdminUser;
import com.koreait.day3.model.network.Header;
import com.koreait.day3.model.network.request.AdminUserApiRequest;
import com.koreait.day3.model.network.response.AdminUserApiResponse;
import com.koreait.day3.model.network.response.UserApiResponse;
import com.koreait.day3.service.AdminApiLogicService;
import com.koreait.day3.service.AdminMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/adminuser")
@RequiredArgsConstructor
public class AdminUserApiController extends CrudController<AdminUserApiRequest, AdminUserApiResponse, AdminUser> {
    private final AdminApiLogicService adminApiLogicService;
    @Override
    public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {
        return null;
    }

    @Override
    public Header<AdminUserApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {
        return null;
    }

    @Override
    public Header<AdminUserApiResponse> delete(Long id) {
        return null;
    }

    @GetMapping("")
    public Header<List<AdminUserApiResponse>> findAll(@PageableDefault(sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return adminApiLogicService.search(pageable);
    }
}
