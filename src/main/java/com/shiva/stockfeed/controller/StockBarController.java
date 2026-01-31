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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bars")
@RequiredArgsConstructor
@Tag(name = "Stock Bars", description = "Stock bar data operations")
public class StockBarController {

    private final StockBarRepository stockBarRepository;

    @GetMapping
    @Operation(summary = "Get all bars", description = "Retrieve all stock bar records")
    public ResponseEntity<List<StockBarMessage>> getAllBars() {
        return ResponseEntity.ok(stockBarRepository.findAll());
    }

    @GetMapping("/symbol")
    @Operation(summary = "Get bars by symbol", description = "Retrieve stock bars filtered by ticker symbol")
    public ResponseEntity<List<StockBarMessage>> getBarsBySymbol(
            @Parameter(description = "Stock ticker symbol (e.g., AAPL)") @RequestParam String symbol) {
        return ResponseEntity.ok(stockBarRepository.findBySymbol(symbol));
    }

    @PostMapping
    @Operation(summary = "Create bar", description = "Insert a new stock bar record. Use T=b for bar type.")
    public ResponseEntity<StockBarMessage> createBar(@RequestBody StockBarMessage bar) {
        return ResponseEntity.ok(stockBarRepository.save(bar));
    }
}
