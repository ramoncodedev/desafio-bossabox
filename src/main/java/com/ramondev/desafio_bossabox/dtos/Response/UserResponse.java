package com.ramondev.desafio_bossabox.dtos.Response;

import lombok.Builder;

@Builder
public record UserResponse(Long id,
                           String name,
                           String email){
}
