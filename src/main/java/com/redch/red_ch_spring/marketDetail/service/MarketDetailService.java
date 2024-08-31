package com.redch.red_ch_spring.marketDetail.service;

import com.redch.red_ch_spring.exception.RedNotFoundException;
import com.redch.red_ch_spring.marketDetail.dto.MarketDetailRequest;
import com.redch.red_ch_spring.marketDetail.dto.MarketDetailResponse;
import com.redch.red_ch_spring.marketDetail.entity.MarketDetail;
import com.redch.red_ch_spring.marketDetail.entity.MarketDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class MarketDetailService {

    private final MarketDetailRepository marketDetailRepository;

    public MarketDetailService(MarketDetailRepository marketDetailRepository) {
        this.marketDetailRepository = marketDetailRepository;
    }

    //특정 마켓의 상세 정보 조회
    public MarketDetailResponse getMarketDetail(Long marketId) {
        var marketDetail = marketDetailRepository.findById(marketId)
            .orElseThrow(()-> new RedNotFoundException("해당 ID의 마켓 상세 정보가 존재하지 않습니다."));
        return MarketDetailResponse.of(marketDetail);
    }

    //새로운 마켓 상세 정보 생성
    public MarketDetailResponse create(MarketDetailRequest marketDetailRequest) {
        MarketDetail marketDetail = marketDetailRepository.save(marketDetailRequest.toEntity());
        return MarketDetailResponse.of(marketDetail);
    }

    //기존 마켓 상세 정보 수정
    public MarketDetailResponse update(Long marketId, MarketDetailRequest marketDetailRequest) {
        MarketDetail marketDetail = marketDetailRepository.findById(marketId)
            .orElseThrow(()-> new RedNotFoundException("해당 ID의 마켓 상세 정보가 존재하지 않습니다."));

        marketDetail.update(marketDetailRequest);

        return MarketDetailResponse.of(marketDetail);
    }

    //마켓 상세 정보 삭제
    public void delete(Long marketId) {
        marketDetailRepository.deleteById(marketId);
    }

}
