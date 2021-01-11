package io.crypto.marketalerts.controller;

import io.crypto.marketalerts.model.DiscordMessage;
import io.crypto.marketalerts.service.DiscordAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class DiscordTestController {

    private final DiscordAlertService discordAlertService;

    @GetMapping("/discord")
    public ResponseEntity sendMessage() throws IOException {
        // Build message - content or embeds is required
        DiscordMessage message = DiscordMessage.builder()
                .content("Test")
                .embeds(List.of(DiscordMessage.EmbedObject.builder()
                        .title("A test")
                        .description("Something happened")
                        .color(Color.BLUE)
                        .fields(List.of(new DiscordMessage.EmbedObject.Field("Here's stuff", "Inline", true)))
                        .build()))
                .build();

        discordAlertService.sendMessage(message);

        return ResponseEntity.ok().build();
    }
}
