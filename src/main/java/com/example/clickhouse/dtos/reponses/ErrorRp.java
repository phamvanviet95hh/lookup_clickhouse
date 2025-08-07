package com.example.clickhouse.dtos.reponses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@JsonDeserialize(builder = ErrorRp.ErrorBuilder.class)
public class ErrorRp implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class ErrorBuilder {
    }
}
