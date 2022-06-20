package com.bayu.reservation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@JsonPropertyOrder({
        "code",
        "status",
        "message"
})
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;

    @JsonIgnore
    private HttpStatus httpStatus;

}
