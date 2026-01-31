package com.shiva.stockfeed.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shiva.stockfeed.model.StockBarMessage;
import com.shiva.stockfeed.repository.StockBarRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bars")
@RequiredArgsConstructor
public class StockBarController {

    private final StockBarRepository stockBarRepository;

    @GetMapping
    public ResponseEntity<List<StockBarMessage>> getAllBars() {
        return ResponseEntity.ok(stockBarRepository.findAll());
    }

    @GetMapping("/symbol")
    public ResponseEntity<List<StockBarMessage>> getBarsBySymbol(@RequestParam String symbol) {
        return ResponseEntity.ok(stockBarRepository.findBySymbol(symbol));
    }

    @PostMapping
    public ResponseEntity<StockBarMessage> createBar(@RequestBody StockBarMessage bar) {
        return ResponseEntity.ok(stockBarRepository.save(bar));
    }
}
