package com.redch.red_ch_spring.marketDetail.controller;

import com.redch.red_ch_spring.marketDetail.dto.MarketDetailRequest;
import com.redch.red_ch_spring.marketDetail.dto.MarketDetailResponse;
import com.redch.red_ch_spring.marketDetail.service.MarketDetailService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/markets/{marketId}/market-details")
public class MarketDetailController {

    private final MarketDetailService marketDetailService;

    public MarketDetailController(MarketDetailService marketDetailService) {
        this.marketDetailService = marketDetailService;
    }

    //특정 마켓의 상세 정보 조회
    @GetMapping
    @Operation(summary = "마켓 상세 정보 조회", description = "특정 마켓의 상세 정보를 조회합니다.")
    public ResponseEntity<MarketDetailResponse> getMarketDetail(@PathVariable Long marketId) {
        return ResponseEntity.ok(marketDetailService.getMarketDetail(marketId));
    }

    //새로운 마켓 상세 정보 생성
    @PostMapping
    @Operation(summary = "마켓 상세 정보 생성", description = "새로운 마켓 상세 정보를 생성합니다.")
    public ResponseEntity<MarketDetailResponse> addMarketDetail(@PathVariable Long marketId, @RequestBody @Valid MarketDetailRequest marketDetailRequest) {
        var body = marketDetailService.create(marketDetailRequest);
        return ResponseEntity.created(
            URI.create("/api/v1/markets/" + marketId + "/market-details/" + body.getId())).body(body);
    }

    //마켓 상세 정보 수정
    @PutMapping
    @Operation(summary = "마켓 상세 정보 수정", description = "특정 마켓의 상세 정보를 수정합니다.")
    public ResponseEntity<MarketDetailResponse> updateMarketDetail(@PathVariable Long marketId, @RequestBody @Valid MarketDetailRequest marketDetailRequest) {
        return ResponseEntity.ok(marketDetailService.update(marketId, marketDetailRequest));
    }

    //마켓 상세 정보 삭제
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "마켓 상세 정보 삭제", description = "특정 마켓의 상세 정보를 삭제합니다.")
    public void deleteMarketDetail(@PathVariable Long marketId) {
        marketDetailService.delete(marketId);
    }

}
