package com.redch.red_ch_spring.contract.controller;

import com.redch.red_ch_spring.contract.dto.PriceResponse;
import com.redch.red_ch_spring.contract.service.PriceService;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/markets/{marketId}/prices")
@Schema(description = "가격 API")
public class PriceController {

    //TODO : 컨트렉트에 따라 조회되도록 설정
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }
    @GetMapping("/yes")
    @Schema(description = "tokenA 전체 가격 조회")
    public ResponseEntity<List<PriceResponse>> getPricesA() {
        return ResponseEntity.ok(priceService.getPricesA());
    }

    @GetMapping("/no")
    @Schema(description = "tokenB 전체 가격 조회")
    public ResponseEntity<List<PriceResponse>> getPricesB() {
        return ResponseEntity.ok(priceService.getPricesB());
    }


}
