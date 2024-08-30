package com.redch.red_ch_spring.market.entity;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {

    List<Market> findAll();

    Optional<Market> findById(Long aLong);


}
