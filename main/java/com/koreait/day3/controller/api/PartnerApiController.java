package com.koreait.day3.controller.api;

import com.koreait.day3.controller.CrudController;
import com.koreait.day3.model.entity.Partner;
import com.koreait.day3.model.network.Header;
import com.koreait.day3.model.network.request.PartnerApiRequest;
import com.koreait.day3.model.network.response.PartnerApiResponse;
import com.koreait.day3.model.network.response.UserApiResponse;
import com.koreait.day3.service.PartnerApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/partner")
@RequiredArgsConstructor
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse, Partner> {

    private final PartnerApiLogicService partnerApiLogicService;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        return null;
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        return null;
    }

    @Override
    public Header<PartnerApiResponse> delete(Long id) {
        return null;
    }

    @GetMapping("") // /api/item
    public Header<java.util.List<PartnerApiResponse>> findAll(@PageableDefault(sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return partnerApiLogicService.search(pageable);
    }
}
