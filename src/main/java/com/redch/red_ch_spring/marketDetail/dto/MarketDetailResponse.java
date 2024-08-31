package com.redch.red_ch_spring.marketDetail.dto;


import com.redch.red_ch_spring.marketDetail.entity.MarketDetail;
import lombok.Getter;

@Getter

public class MarketDetailResponse {

    private Long id;

    private String description;

    private String contractAddress;

    private String category;

    private Double liquidity;

    private Double totalFees;

    private Double volume;

    public MarketDetailResponse(Long id, String description, String contractAddress,
        String category, Double liquidity, Double totalFees, Double volume) {
        this.id = id;
        this.description = description;
        this.contractAddress = contractAddress;
        this.category = category;
        this.liquidity = liquidity;
        this.totalFees = totalFees;
        this.volume = volume;
    }

    public static MarketDetailResponse of(MarketDetail marketDetail) {
        return new MarketDetailResponse(marketDetail.getId(), marketDetail.getDescription(),
            marketDetail.getContractAddress(), marketDetail.getCategory(),
            marketDetail.getLiquidity(), marketDetail.getTotalFees(), marketDetail.getVolume());
    }
}
