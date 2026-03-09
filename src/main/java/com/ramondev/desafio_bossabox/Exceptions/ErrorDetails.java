package com.ramondev.desafio_bossabox.Exceptions;

import java.time.LocalDateTime;

public record ErrorDetails(
        LocalDateTime timesTamp,
        String message,
        String details

) {


}
