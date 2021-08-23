package com.koreait.day03.controller.api;

import com.koreait.day03.ifs.CrudInterface;
import com.koreait.day03.model.network.Header;
import com.koreait.day03.model.network.request.PartnerApiRequest;
import com.koreait.day03.model.network.response.PartnerApiResponse;
import com.koreait.day03.service.PartnerApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partner")
@RequiredArgsConstructor
public class PartnerApiController implements CrudInterface<PartnerApiRequest, PartnerApiResponse> {
    private final PartnerApiLogicService partnerApiLogicService;

    @Override
    @PostMapping
    public Header<PartnerApiResponse> create(@RequestBody Header<PartnerApiRequest> request) {
        return partnerApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<PartnerApiResponse> read(@PathVariable(name="id")Long id) {
        return partnerApiLogicService.read(id);
    }

    @Override
    @PutMapping
    public Header<PartnerApiResponse> update(@RequestBody Header<PartnerApiRequest> request) {
        return partnerApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<PartnerApiResponse> delete(@PathVariable Long id) {
        return partnerApiLogicService.delete(id);
    }
}
