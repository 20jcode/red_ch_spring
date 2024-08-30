package com.redch.red_ch_spring.market.dto;

import com.redch.red_ch_spring.market.entity.Market;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MarketRequest {
    private String name;
    private String description;
    private String contractAddress;
    private String startDate;
    private String endDate;
    private String settlementDate;

    public Market toEntity() {
        return Market.builder()
            .name(name)
            .description(description)
            .contractAddress(contractAddress)
            .startDate(LocalDateTime.parse(startDate))
            .endDate(LocalDateTime.parse(endDate))
            .settlementDate(LocalDateTime.parse(settlementDate))
            .build();
    }
}
