package io.crypto.marketalerts.model.symbol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BulkAddSymbolsRequest {
    private List<String> symbols;
}
