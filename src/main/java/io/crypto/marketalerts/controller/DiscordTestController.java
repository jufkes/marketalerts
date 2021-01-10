package io.crypto.marketalerts.controller;

import io.crypto.marketalerts.service.DiscordAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Validated
public class DiscordTestController {

    private final DiscordAlertService discordAlertService;

    @GetMapping("/discord")
    public ResponseEntity sendMessage() throws IOException {
        discordAlertService.setContent("Test");
        discordAlertService.setAvatarUrl("https://cdn.arstechnica.net/wp-content/uploads/2019/11/6bb83d91-bd51-49c7-b054-550f5af8dc53.jpg");
        discordAlertService.setUsername("BananaPants");
        discordAlertService.setTts(true);
        discordAlertService.addEmbed(new DiscordAlertService.EmbedObject()
                .setTitle("A test")
                .setDescription("Something happened")
                .setColor(Color.BLUE)
                .addField("Heres stuff", "Inline", true)
                .setThumbnail("https://www.brandeps.com/logo-download/E/Ethereum-logo-vector-01.svg")
        );
        discordAlertService.execute();
        return ResponseEntity.ok().build();
    }
}
