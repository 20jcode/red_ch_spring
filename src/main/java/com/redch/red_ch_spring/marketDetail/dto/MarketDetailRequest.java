package com.redch.red_ch_spring.marketDetail.dto;

import com.redch.red_ch_spring.marketDetail.entity.MarketDetail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MarketDetailRequest {

        @Schema(description = "마켓 상세 정보 설명", defaultValue = "마켓 상세 정보 설명")
        @NotNull
        private final String description;
        @Schema(description = "마켓 상세 정보 contractAddress", defaultValue = "0x123")
        @NotNull
        private final String contractAddress;
        @Schema(description = "마켓 상세 정보 카테고리", defaultValue = "카테고리")
        @NotNull
        private final String category;
        @Schema(description = "마켓 상세 정보 유동성공급", defaultValue = "1234.123")
        private final Double liquidity;
        @Schema(description = "마켓 상세 정보 총 수수료", defaultValue = "0.2323")
        private final Double totalFees;
        @Schema(description = "마켓 상세 정보 거래량", defaultValue = "1000.4444")
        private final Double volume;

        public MarketDetailRequest(String description, String contractAddress, String category,
            Double liquidity, Double totalFees, Double volume) {
            this.description = description;
            this.contractAddress = contractAddress;
            this.category = category;
            this.liquidity = liquidity;
            this.totalFees = totalFees;
            this.volume = volume;
        }

    public MarketDetail toEntity() {
        return MarketDetail.builder()
            .description(description)
            .contractAddress(contractAddress)
            .category(category)
            .liquidity(liquidity)
            .totalFees(totalFees)
            .volume(volume)
            .build();
    }
}
