package com.redch.red_ch_spring.marketDetail.entity;

import com.redch.red_ch_spring.market.entity.Market;
import com.redch.red_ch_spring.marketDetail.dto.MarketDetailRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class MarketDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "market_detail_id")
    private Long id;

    private String description;

    private String contractAddress;

    private String category;

    private Double liquidity;

    private Double totalFees;

    private Double volume;

    @OneToOne(mappedBy = "marketDetail")
    private Market market;

    public void update(MarketDetailRequest marketDetailRequest) {
        this.description = marketDetailRequest.getDescription();
        this.contractAddress = marketDetailRequest.getContractAddress();
        this.category = marketDetailRequest.getCategory();
        this.liquidity = marketDetailRequest.getLiquidity();
        this.totalFees = marketDetailRequest.getTotalFees();
        this.volume = marketDetailRequest.getVolume();
    }
}
