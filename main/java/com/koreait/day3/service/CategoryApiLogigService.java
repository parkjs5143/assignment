package com.koreait.day3.service;

import com.koreait.day3.model.entity.Category;
import com.koreait.day3.model.network.Header;
import com.koreait.day3.model.network.Pagination;
import com.koreait.day3.model.network.request.CategoryApiRequest;
import com.koreait.day3.model.network.response.CategoryApiResponse;
import com.koreait.day3.model.network.response.UserApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryApiLogigService extends BaseService<CategoryApiRequest, CategoryApiResponse, Category> {
    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {
        return null;
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {
        return null;
    }

    @Override
    public Header<CategoryApiResponse> delete(Long id) {
        return null;
    }

    private CategoryApiResponse response(Category category){
        CategoryApiResponse categoryApiResponse = CategoryApiResponse.builder()
                .id(category.getId())
                .type(category.getType())
                .title(category.getTitle())
                .regDate(category.getRegDate())
                .updateDate(category.getUpdateDate())
                .updateBy(category.getUpdateBy())
                .build();
        return categoryApiResponse;
    }

    public Header<List<CategoryApiResponse>> search(Pageable pageable){
        Page<Category> categories = baseRepository.findAll(pageable);
        List<CategoryApiResponse> categoryApiResponseList = categories.stream()
                .map(category->response(category))
                .collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(categories.getTotalPages())
                .totalElements(categories.getTotalElements())
                .currentPage(categories.getNumber())
                .currentElements(categories.getNumberOfElements())
                .build();
        return Header.OK(categoryApiResponseList, pagination);
    }
}
