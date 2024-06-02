package com.cefet.godziny.infraestrutura.exceptions;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class RestDefaultErrorMessage {
    private HttpStatus status;
    private String message;
}
