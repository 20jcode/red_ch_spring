package com.redch.red_ch_spring.market.controller;

import com.redch.red_ch_spring.market.dto.MarketRequest;
import com.redch.red_ch_spring.market.dto.MarketResponse;
import com.redch.red_ch_spring.market.service.MarketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/v1")
@RestController
@Tag(name = "Market API", description = "예측 마켓 API")
public class MarketController {

    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    //전체 예측 마켓 조회
    @GetMapping("/markets")
    @Operation(summary = "예측 마켓 전체 조회", description = "전체 예측 마켓을 조회합니다.")
    public ResponseEntity<List<MarketResponse>> getMarkets() {
        return ResponseEntity.ok(marketService.getMarkets());
    }

    //단일 예측 마켓 조회
    @GetMapping("/markets/{id}")
    @Operation(summary = "예측 마켓 조회", description = "특정 예측 마켓을 조회합니다.")
    public ResponseEntity<MarketResponse> getMarket(Long id) {
        return ResponseEntity.ok(marketService.getMarket(id));
    }

    //새 마켓 등록
    @PostMapping("/markets")
    @Operation(summary = "예측 마켓 등록", description = "새로운 예측 마켓을 등록합니다.")
    public ResponseEntity<MarketResponse> addMarket(@RequestBody @Valid MarketRequest marketRequest) {
        var body = marketService.create(marketRequest);
        return ResponseEntity.created(URI.create("/api/v1/markets/" + body.getId())).body(body);
    }

    //마켓 정보 수정
    @PutMapping("/markets/{id}")
    @Operation(summary = "예측 마켓 수정", description = "특정 예측 마켓을 수정합니다.")
    public ResponseEntity<MarketResponse> updateMarket(@PathVariable Long id, @RequestBody @Valid MarketRequest marketRequest) {
        return ResponseEntity.ok(marketService.update(id, marketRequest));
    }

    //마켓 삭제
    @DeleteMapping("/markets/{id}")
    @Operation(summary = "예측 마켓 삭제", description = "특정 예측 마켓을 삭제합니다.")
    public ResponseEntity<Void> deleteMarket(@PathVariable Long id) {
        marketService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
