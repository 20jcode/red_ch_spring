package com.redch.red_ch_spring.market.dto;

import com.redch.red_ch_spring.market.entity.Market;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MarketRequest {
    @Schema(description = "예측 마켓 이름", defaultValue = "고추참치 vs 그냥참치")
    private String name;
    @Schema(description = "예측 마켓 설명", defaultValue = "고추참치가 더 맛있을까요?")
    private String description;
    @Schema(description = "예측 마켓 스마트 컨트랙트 주소", defaultValue = "0x1234567890abcdef")
    private String contractAddress;
    @Schema(description = "예측 마켓 시작일", defaultValue = "2021-08-01T00:00:00")
    private String startDate;
    @Schema(description = "예측 마켓 종료일", defaultValue = "2021-08-31T23:59:59")
    private String endDate;
    @Schema(description = "예측 마켓 정산일", defaultValue = "2021-09-01T00:00:00")
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
