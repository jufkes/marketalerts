package io.crypto.marketalerts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@AllArgsConstructor
@Document
@Builder
public class Alert {
    @Id
    private Interval id;
    private Set<String> emaAlerts;
}
