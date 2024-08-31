package com.redch.red_ch_spring.market.dto;

import com.redch.red_ch_spring.market.entity.Market;
import com.redch.red_ch_spring.marketDetail.dto.MarketDetailRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MarketRequest {

    @Schema(description = "예측 마켓 이름", defaultValue = "고추참치 vs 그냥참치")
    private String name;
    @Schema(description = "예측 마켓 설명", defaultValue = "고추참치가 더 맛있을까요?")
    private String description;
    @Schema(description = "예측 마켓 이미지 URL", defaultValue = "https://example.com/image.jpg")
    private String imageUrl;
    @Schema(description = "예측 마켓 A 가격", defaultValue = "0.5")
    private Double priceA;
    @Schema(description = "예측 마켓 B 가격", defaultValue = "0.5")
    private Double priceB;
    @Schema(description = "예측 마켓 A 토큰 컨트렉트 주소", defaultValue = "0x1234")
    private String tokenAContractAddress;
    @Schema(description = "예측 마켓 B 토큰 컨트렉트 주소", defaultValue = "0x5678")
    private String tokenBContractAddress;
    @Schema(description = "예측 마켓 상세 정보", defaultValue = "{'description':'고추참치가 더 맛있을까요?','contractAddress':'0x1234','category':'음식','liquidity':100.0,'totalFees':100.0,'volume':100.0}")
    @NotNull
    private MarketDetailRequest marketDetailRequest;


    public Market toEntity() {
        return Market.builder().name(name).description(description).imageUrl(imageUrl)
            .priceA(priceA).priceB(priceB).tokenAContractAddress(tokenAContractAddress)
            .tokenBContractAddress(tokenBContractAddress)
            .marketDetail(marketDetailRequest.toEntity()).build();
    }
}
