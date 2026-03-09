package com.ramondev.desafio_bossabox.dtos.Request;

import java.util.List;

public record ToolsRequest(String title, String link, List<String> tags) {
}
