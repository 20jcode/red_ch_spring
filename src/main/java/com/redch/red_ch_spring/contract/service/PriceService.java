package com.redch.red_ch_spring.contract.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.redch.red_ch_spring.contract.dto.PriceResponse;
import com.redch.red_ch_spring.contract.entity.Price;
import com.redch.red_ch_spring.contract.entity.PriceRepository;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@EnableScheduling
public class PriceService {

    private final WebClient webClient;

    private final PriceRepository priceRepository;


    public PriceService(WebClient.Builder webClientBuilder, PriceRepository priceRepository) {
        this.webClient = webClientBuilder.baseUrl(
                "https://explorer.xrplevm.org/api/v2/addresses/0xe30DAc6546bA81128701B0c8Bd30B004A932719A/tokens?type=ERC-20")
            .build();
        this.priceRepository = priceRepository;

    }

    @Scheduled(fixedDelay = 60000)
    public void getTokenPrice() {

        List<Integer> totalSupplyList = new ArrayList<>();

        var data = webClient.get().retrieve().bodyToMono(JsonNode.class).block(Duration.ofSeconds(100));

        for (JsonNode item : Objects.requireNonNull(data).get("items")) {
            totalSupplyList.add(item.get("token").get("total_supply").asInt());
        }

        double tokenA = (double) totalSupplyList.get(0) / totalSupplyList.get(1);

        double tokenB = (double) totalSupplyList.get(1) / totalSupplyList.get(0);

        Price price = new Price(LocalDateTime.now(),tokenA, tokenB);

        priceRepository.save(price);
    }

    public List<PriceResponse> getPricesA() {
        List<Price> prices = priceRepository.findAll();
        List<PriceResponse> priceResponses = new ArrayList<>();
        for (Price price : prices) {
            priceResponses.add(PriceResponse.ofA(price));
        }
        return priceResponses;

    }

    public List<PriceResponse> getPricesB() {
        List<Price> prices = priceRepository.findAll();
        List<PriceResponse> priceResponses = new ArrayList<>();
        for (Price price : prices) {
            priceResponses.add(PriceResponse.ofB(price));
        }
        return priceResponses;
    }
}
