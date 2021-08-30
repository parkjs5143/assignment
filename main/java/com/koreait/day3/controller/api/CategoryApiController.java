package com.koreait.day3.controller.api;

import com.koreait.day3.controller.CrudController;
import com.koreait.day3.model.entity.Category;
import com.koreait.day3.model.network.Header;
import com.koreait.day3.model.network.request.CategoryApiRequest;
import com.koreait.day3.model.network.response.CategoryApiResponse;
import com.koreait.day3.model.network.response.UserApiResponse;
import com.koreait.day3.service.CategoryApiLogigService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryApiController extends CrudController<CategoryApiRequest, CategoryApiResponse, Category> {

    private  final CategoryApiLogigService categoryApiLogigService;
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

    @GetMapping("")
    public Header<List<CategoryApiResponse>> findAll(@PageableDefault(sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return categoryApiLogigService.search(pageable);
    }
}
