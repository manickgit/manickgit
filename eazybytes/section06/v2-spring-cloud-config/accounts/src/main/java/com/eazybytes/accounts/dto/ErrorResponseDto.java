package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "Error Response",
        description = "Schema to hold Error Response information"
)
public class ErrorResponseDto {

    @Schema(
            description = "API Path invoked by client"
    )
    private String apiPath;

    @Schema(
            description = "Error code representing the error happened"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message due to the runtime exception"
    )
    private String errorMessage;

    @Schema(
            description = "Time representing when the error occurred"
    )
    private LocalDateTime errorTime;

}
