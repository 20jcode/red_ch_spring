package com.redch.red_ch_spring.market.dto;

import com.redch.red_ch_spring.market.entity.Market;
import com.redch.red_ch_spring.marketDetail.dto.MarketDetailResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MarketResponse {

        private Long id;

        private String name;

        private String description;

        private String imageUrl;

        private Double priceA;

        private Double priceB;

        private String tokenAContractAddress;

        private String tokenBContractAddress;

        private MarketDetailResponse marketDetailResponse;

        public static MarketResponse of(Market market) {
            return MarketResponse.builder()
                .id(market.getId())
                .name(market.getName())
                .description(market.getDescription())
                .imageUrl(market.getImageUrl())
                .priceA(market.getPriceA())
                .priceB(market.getPriceB())
                .tokenAContractAddress(market.getTokenAContractAddress())
                .tokenBContractAddress(market.getTokenBContractAddress())
                .marketDetailResponse(MarketDetailResponse.of(market.getMarketDetail()))
                .build();
        }
}
