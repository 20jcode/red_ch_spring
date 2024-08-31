package com.redch.red_ch_spring.market.entity;

import com.redch.red_ch_spring.market.dto.MarketRequest;
import com.redch.red_ch_spring.marketDetail.entity.MarketDetail;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String imageUrl;

    private Double priceA;

    private Double priceB;

    private String tokenAContractAddress;

    private String tokenBContractAddress;

    @OneToOne(cascade = jakarta.persistence.CascadeType.ALL)
    @JoinColumn(name = "market_detail_id")
    private MarketDetail marketDetail;

    public void update(MarketRequest marketRequest) {
        this.name = marketRequest.getName();
        this.description = marketRequest.getDescription();
        this.priceA = marketRequest.getPriceA();
        this.priceB = marketRequest.getPriceB();
        this.imageUrl = marketRequest.getImageUrl();
    }
}
