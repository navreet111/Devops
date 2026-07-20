package com.apps.quantitymeasurement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Response DTO for Quantity Measurement APIs"
)
public class QuantityMeasurementDTO {

    private boolean success;

    private String message;

    private Object result;

    public QuantityMeasurementDTO() {
    }

    public QuantityMeasurementDTO(
            boolean success,
            String message,
            Object result) {

        this.success = success;
        this.message = message;
        this.result = result;
    }

    public QuantityMeasurementDTO(boolean result) {
        this.success = true;
        this.message = "Comparison Successful";
        this.result = result;
    }

    public QuantityMeasurementDTO(QuantityDTO result) {
        this.success = true;
        this.message = "Operation Successful";
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(
            boolean success) {

        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(
            String message) {

        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(
            Object result) {

        this.result = result;
    }
}