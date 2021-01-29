package io.crypto.marketalerts.service;

import io.crypto.marketalerts.exception.BadRequestException;
import io.crypto.marketalerts.model.symbol.AddSymbolRequest;
import io.crypto.marketalerts.model.symbol.BulkAddSymbolsRequest;
import io.crypto.marketalerts.model.symbol.Symbol;
import io.crypto.marketalerts.repository.SymbolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SymbolService {

    private final SymbolRepository symbolRepository;
    private final TokenRecordRepositoryService tokenRecordRepositoryService;

    public List<String> getSymbols() {
        return symbolRepository.findAll().stream().map(Symbol::getId).collect(Collectors.toList());
    }

    public Symbol addSymbol(AddSymbolRequest request) {
        if (request.getName() == null || request.getName().trim().equals("")) {
            throw new BadRequestException("Symbol name cannot be blank");
        }
        String symbolName = request.getName().toUpperCase();
        Optional<Symbol> byId = symbolRepository.findById(symbolName);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            Symbol newSymbol = Symbol.builder().id(symbolName).build();
            return symbolRepository.save(newSymbol);
        }
    }

    public List<Symbol> bulkAddSymbols(BulkAddSymbolsRequest request) {
        if (CollectionUtils.isEmpty(request.getSymbols())) throw new BadRequestException("Symbols cannot be empty");

        return request.getSymbols().stream().map(symbol -> {
            return addSymbol(AddSymbolRequest.builder().name(symbol).build());
        }).collect(Collectors.toList());
    }

    public void deleteSymbolAndAssociatedData(String name) {
        Optional<Symbol> symbol = symbolRepository.findById(name);
        symbol.ifPresent(value -> symbolRepository.deleteById(value.getId()));
        tokenRecordRepositoryService.deleteRecordsForSymbol(name);
    }
}
