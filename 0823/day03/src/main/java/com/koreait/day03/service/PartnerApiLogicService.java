package com.koreait.day03.service;

import com.koreait.day03.ifs.CrudInterface;
import com.koreait.day03.model.entity.Item;
import com.koreait.day03.model.entity.Partner;
import com.koreait.day03.model.enumclass.ItemStatus;
import com.koreait.day03.model.network.Header;
import com.koreait.day03.model.network.request.ItemApiRequest;
import com.koreait.day03.model.network.request.PartnerApiRequest;
import com.koreait.day03.model.network.response.ItemApiResponse;
import com.koreait.day03.model.network.response.PartnerApiResponse;
import com.koreait.day03.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PartnerApiLogicService implements CrudInterface<PartnerApiRequest, PartnerApiResponse> {
    private final PartnerRepository partnerRepository;
    private PartnerApiRequest partnerApiRequest;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        PartnerApiRequest partnerApiRequest = request.getData();
        Partner partner = Partner.builder()
                .name(partnerApiRequest.getName())
                .status(partnerApiRequest.getStatus())
                .address(partnerApiRequest.getAddress())
                .callCenter(partnerApiRequest.getCallCenter())
                .businessNumber(partnerApiRequest.getBusinessNumber())
                .ceoName(partnerApiRequest.getCeoName())
                .build();
        Partner partners = partnerRepository.save(partner);
        return response(partners);
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return partnerRepository.findById(id)
                .map(partner -> response(partner))
                .orElseGet(
                        () -> Header.ERROR("노 데이터")
                );
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        PartnerApiRequest partnerApiRequest = request.getData();
        Optional<Partner> optional = partnerRepository.findById(partnerApiRequest.getId());
        return optional.map(partner -> {
            partner.setName(partnerApiRequest.getName());
            partner.setStatus(partnerApiRequest.getStatus());
            partner.setAddress(partnerApiRequest.getAddress());
            partner.setCallCenter(partnerApiRequest.getCallCenter());
            partner.setBusinessNumber(partnerApiRequest.getBusinessNumber());
            partner.setCeoName(partnerApiRequest.getCeoName());
            System.out.println(partner);

            return partner;
        }).map(partner -> partnerRepository.save(partner))
                .map(partner -> response(partner))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Partner> optional = partnerRepository.findById(id);

        return optional.map(partner -> {
            partnerRepository.delete(partner);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public Header<PartnerApiResponse> response(Partner partner){
        PartnerApiResponse partnerApiResponse = PartnerApiResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .regDate(partner.getRegDate())
                .updateDate(partner.getUpdateDate())
                .build();
        return Header.OK(partnerApiResponse);
    }
}
