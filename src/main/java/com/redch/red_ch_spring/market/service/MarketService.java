package com.redch.red_ch_spring.market.service;

import static java.util.stream.Collectors.toList;

import com.redch.red_ch_spring.exception.RedNotFoundException;
import com.redch.red_ch_spring.market.dto.MarketRequest;
import com.redch.red_ch_spring.market.dto.MarketResponse;
import com.redch.red_ch_spring.market.entity.Market;
import com.redch.red_ch_spring.market.entity.MarketRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarketService {

    private final MarketRepository marketRepository;

    //전체 마켓 조회
    public List<MarketResponse> getMarkets() {
        return marketRepository.findAll().stream().map(MarketResponse::of).collect(toList());
    }

    //단일 마켓 조회
    public MarketResponse getMarket(Long id) {
        var market = marketRepository.findById(id)
            .orElseThrow(()-> new RedNotFoundException("해당 ID의 마켓이 존재하지 않습니다."));
        return MarketResponse.of(market);
    }

    //새로운 마켓 생성
    public MarketResponse create(MarketRequest marketRequest) {
        Market market = marketRepository.save(marketRequest.toEntity());
        return MarketResponse.of(market);
    }

    //기존 마켓 수정
    public MarketResponse update(Long id, MarketRequest marketRequest) {
        Market market = marketRepository.findById(id)
            .orElseThrow(()-> new RedNotFoundException("해당 ID의 마켓이 존재하지 않습니다."));

        market.update(marketRequest);

        return MarketResponse.of(market);
    }

    //마켓 삭제
    public void delete(Long id) {
        marketRepository.deleteById(id);
    }
}
