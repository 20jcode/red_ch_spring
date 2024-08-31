package com.redch.red_ch_spring.contract.entity;

import com.redch.red_ch_spring.marketDetail.entity.MarketDetail;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime time;

    private Double tokenAPrice;

    private Double tokenBPrice;

    public Price(LocalDateTime time, Double tokenAPrice, Double tokenBPrice) {
        this.time = time;
        this.tokenAPrice = tokenAPrice;
        this.tokenBPrice = tokenBPrice;
    }
}
