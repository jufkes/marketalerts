package io.crypto.marketalerts.controller;

import io.crypto.marketalerts.exception.BadRequestException;
import io.crypto.marketalerts.model.symbol.AddSymbolRequest;
import io.crypto.marketalerts.model.symbol.BulkAddSymbolsRequest;
import io.crypto.marketalerts.model.symbol.Symbol;
import io.crypto.marketalerts.service.SymbolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/symbols")
public class SymbolController {

    private final SymbolService symbolService;

    @GetMapping
    public ResponseEntity<List<String>> getSymbols() {
        List<String> symbols = symbolService.getSymbols();
        return ResponseEntity.ok(symbols);
    }

    @PostMapping
    public ResponseEntity<Symbol> addSymbol(@RequestBody AddSymbolRequest request) {
        try {
            return ResponseEntity.ok(symbolService.addSymbol(request));
        } catch (BadRequestException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/bulk-add")
    public ResponseEntity<List<Symbol>> addSymbols(@RequestBody BulkAddSymbolsRequest request) {
        try {
            return ResponseEntity.ok(symbolService.bulkAddSymbols(request));
        } catch (BadRequestException ex) {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{symbol}")
    public ResponseEntity<Void> deleteSymbol(@PathVariable String symbol) {
        symbolService.deleteSymbolAndAssociatedData(symbol.toUpperCase());
        return ResponseEntity.ok().build();
    }
}
