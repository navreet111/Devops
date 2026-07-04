package com.apps.quantitymeasurement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Schema(
        description = "Input DTO for Quantity Measurement APIs"
)
public class QuantityInputDTO {

    @Valid
    @NotNull(message = "First quantity cannot be null")
    private QuantityDTO thisQuantityDTO;

    @Valid
    @NotNull(message = "Second quantity cannot be null")
    private QuantityDTO thatQuantityDTO;

    @Valid
    @Schema(nullable = true)
    private QuantityDTO targetQuantityDTO;

    public QuantityInputDTO() {
    }

    public QuantityDTO getThisQuantityDTO() {
        return thisQuantityDTO;
    }

    public void setThisQuantityDTO(
            QuantityDTO thisQuantityDTO) {

        this.thisQuantityDTO = thisQuantityDTO;
    }

    public QuantityDTO getThatQuantityDTO() {
        return thatQuantityDTO;
    }

    public void setThatQuantityDTO(
            QuantityDTO thatQuantityDTO) {

        this.thatQuantityDTO = thatQuantityDTO;
    }

    public QuantityDTO getTargetQuantityDTO() {
        return targetQuantityDTO;
    }

    public void setTargetQuantityDTO(
            QuantityDTO targetQuantityDTO) {

        this.targetQuantityDTO = targetQuantityDTO;
    }
}