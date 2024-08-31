package com.redch.red_ch_spring.marketDetail.entity;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketDetailRepository extends JpaRepository<MarketDetail, Long> {

    Optional<MarketDetail> findById(Long id);

    void deleteById(Long id);

}
