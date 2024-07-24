package org.gravitytwog.controllerAdvices;

import java.time.LocalDate;

public record ErrorMessage(int statusCode, LocalDate timestamp, String message, String description) {
}
