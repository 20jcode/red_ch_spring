package com.redch.red_ch_spring.market.entity;

import com.redch.red_ch_spring.market.dto.MarketRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private String name;

    private String description;

    private String contractAddress;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime settlementDate;

    public void update(MarketRequest marketRequest) {
        this.name = marketRequest.getName();
        this.description = marketRequest.getDescription();
        this.contractAddress = marketRequest.getContractAddress();
        this.startDate = LocalDateTime.parse(marketRequest.getStartDate());
        this.endDate = LocalDateTime.parse(marketRequest.getEndDate());
        this.settlementDate = LocalDateTime.parse(marketRequest.getSettlementDate());
    }
}
