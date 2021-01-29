package io.crypto.marketalerts.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class BadRequestException extends RuntimeException {
    private String message;
}
