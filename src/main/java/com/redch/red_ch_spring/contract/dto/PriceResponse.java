package com.redch.red_ch_spring.contract.dto;

import com.redch.red_ch_spring.contract.entity.Price;
import lombok.Getter;

@Getter
public class PriceResponse {
    private Long id;
    private String time;
    private Double tokenPrice;



    public PriceResponse(Long id, String time,Double tokenPrice) {
        this.id = id;
        this.time = time;
        this.tokenPrice = tokenPrice;
    }

    public static PriceResponse ofA(Price price) {
        return new PriceResponse(price.getId(), price.getTime().toString(), price.getTokenAPrice());
    }

    public static PriceResponse ofB(Price price) {
        return new PriceResponse(price.getId(), price.getTime().toString(),price.getTokenBPrice());
    }
}
