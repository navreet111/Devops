package com.apps.quantitymeasurement.model;

import java.io.Serializable;
import java.util.Objects;

public class QuantityMeasurementEntity
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private final QuantityDTO thisQuantity;

    private final QuantityDTO thatQuantity;

    private final String operation;

    private final Object result;

    private final String errorMessage;

    private final boolean error;

    // Success Constructor
    public QuantityMeasurementEntity(
            QuantityDTO thisQuantity,
            QuantityDTO thatQuantity,
            String operation,
            Object result) {

        this.thisQuantity = thisQuantity;
        this.thatQuantity = thatQuantity;
        this.operation = operation;
        this.result = result;
        this.errorMessage = null;
        this.error = false;
    }

    // Error Constructor
    public QuantityMeasurementEntity(
            QuantityDTO thisQuantity,
            QuantityDTO thatQuantity,
            String operation,
            String errorMessage) {

        this.thisQuantity = thisQuantity;
        this.thatQuantity = thatQuantity;
        this.operation = operation;
        this.result = null;
        this.errorMessage = errorMessage;
        this.error = true;
    }

    public QuantityDTO getThisQuantity() {
        return thisQuantity;
    }

    public QuantityDTO getThatQuantity() {
        return thatQuantity;
    }

    public String getOperation() {
        return operation;
    }

    public Object getResult() {
        return result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isError() {
        return error;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof QuantityMeasurementEntity)) {
            return false;
        }

        QuantityMeasurementEntity other =
                (QuantityMeasurementEntity) obj;

        return Objects.equals(
                thisQuantity,
                other.thisQuantity)

                && Objects.equals(
                thatQuantity,
                other.thatQuantity)

                && Objects.equals(
                operation,
                other.operation)

                && Objects.equals(
                result,
                other.result)

                && Objects.equals(
                errorMessage,
                other.errorMessage)

                && error == other.error;
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                thisQuantity,
                thatQuantity,
                operation,
                result,
                errorMessage,
                error);
    }

    @Override
    public String toString() {

        if (error) {

            return "QuantityMeasurementEntity{" +
                    "operation='" + operation + '\'' +
                    ", error='" + errorMessage + '\'' +
                    '}';
        }

        return "QuantityMeasurementEntity{" +
                "operation='" + operation + '\'' +
                ", result=" + result +
                '}';
    }
}