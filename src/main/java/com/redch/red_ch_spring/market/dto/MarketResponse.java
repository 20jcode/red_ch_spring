package com.redch.red_ch_spring.market.dto;

import com.redch.red_ch_spring.market.entity.Market;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MarketResponse {

        private Long id;

        private String name;

        private String description;

        private String contractAddress;

        private String startDate;

        private String endDate;

        private String settlementDate;

        public static MarketResponse of(Market market) {
                return MarketResponse.builder()
                        .id(market.getId())
                        .name(market.getName())
                        .description(market.getDescription())
                        .contractAddress(market.getContractAddress())
                        .startDate(market.getStartDate().toString())
                        .endDate(market.getEndDate().toString())
                        .settlementDate(market.getSettlementDate().toString())
                        .build();
        }
}
