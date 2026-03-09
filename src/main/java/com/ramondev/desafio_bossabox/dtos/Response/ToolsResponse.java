package com.ramondev.desafio_bossabox.dtos.Response;

import lombok.Builder;

import java.util.List;

@Builder
public record ToolsResponse(Long id, String title, String link, List<String> tags) {
}
