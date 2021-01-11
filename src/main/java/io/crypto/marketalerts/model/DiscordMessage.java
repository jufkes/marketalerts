package io.crypto.marketalerts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.awt.*;
import java.util.List;

@Data
@Builder
public class DiscordMessage {

    private String content;
    public static final String USERNAME = "BananaPants";
    public static final String AVATAR_URL = "https://cdn.arstechnica.net/wp-content/uploads/2019/11/6bb83d91-bd51-49c7-b054-550f5af8dc53.jpg";
    public static final boolean TTS = true;
    private List<EmbedObject> embeds;

    @Data
    @Builder
    public static class EmbedObject {
        private String title;
        private String description;
        private String url;
        private Color color;

        private Footer footer;
        private Image image;
        private Author author;
        private List<Field> fields;

        @Data
        @AllArgsConstructor
        public static class Footer {
            private String text;
            private String iconUrl;
        }

        @Data
        @AllArgsConstructor
        public static class Image {
            private String url;
        }

        @Data
        @AllArgsConstructor
        public static class Author {
            private String name;
            private String url;
            private String iconUrl;
        }

        @Data
        @AllArgsConstructor
        public static class Field {
            private String name;
            private String value;
            private boolean inline;
        }
    }

}
