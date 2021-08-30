package com.koreait.day3.service;

import com.koreait.day3.ifs.CrudInterface;
import com.koreait.day3.model.entity.AdminUser;
import com.koreait.day3.model.network.Header;
import com.koreait.day3.model.network.Pagination;
import com.koreait.day3.model.network.request.AdminUserApiRequest;
import com.koreait.day3.model.network.request.UserApiRequest;
import com.koreait.day3.model.network.response.AdminUserApiResponse;
import com.koreait.day3.model.network.response.UserApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminApiLogicService extends BaseService<AdminUserApiRequest, AdminUserApiRequest, AdminUser> {

    @Override
    public Header<AdminUserApiRequest> create(Header<AdminUserApiRequest> request) {
        return null;
    }

    @Override
    public Header<AdminUserApiRequest> read(Long id) {
        return null;
    }

    @Override
    public Header<AdminUserApiRequest> update(Header<AdminUserApiRequest> request) {
        return null;
    }

    @Override
    public Header<AdminUserApiRequest> delete(Long id) {
        return null;
    }

    private AdminUserApiResponse response(AdminUser adminUser){
        AdminUserApiResponse adminUserApiResponse = AdminUserApiResponse.builder()
                .id(adminUser.getId())
                .userid(adminUser.getUserid())
                .name(adminUser.getName())
                .status(adminUser.getStatus())
                .regDate(adminUser.getRegDate())
                .build();
        return adminUserApiResponse;
    }

    public Header<List<AdminUserApiResponse>> search(Pageable pageable){
        Page<AdminUser> admin = baseRepository.findAll(pageable);
        List<AdminUserApiResponse> adminUserApiResponseList = admin.stream()
                .map(adminUser->response(adminUser))
                .collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(admin.getTotalPages())
                .totalElements(admin.getTotalElements())
                .currentPage(admin.getNumber())
                .currentElements(admin.getNumberOfElements())
                .build();
        return Header.OK(adminUserApiResponseList, pagination);
    }
}
