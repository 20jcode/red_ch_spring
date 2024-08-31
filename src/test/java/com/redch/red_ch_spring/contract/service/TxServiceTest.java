package com.redch.red_ch_spring.contract.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TxServiceTest {

    @Test
    void getLastBlock() {
        TxService txService = new TxService();
        txService.getLastBlock();
    }
}