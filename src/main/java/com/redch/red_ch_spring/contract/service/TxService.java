package com.redch.red_ch_spring.contract.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TxService {

    public void getLastBlock(){
        WebClient webClient = WebClient.create("https://explorer.xrplevm.org/api/v2/main-page/blocks");
        webClient.get().retrieve().bodyToMono(String.class).subscribe(System.out::println);
    }

}
