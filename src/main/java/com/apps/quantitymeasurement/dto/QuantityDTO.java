package com.apps.quantitymeasurement.dto;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class QuantityDTO
        implements Serializable {

    private double value;

    private String unit;

    private String measurementType;

    public QuantityDTO() {
    }

    public QuantityDTO(
            double value,
            String unit,
            String measurementType) {

        this.value = value;
        this.unit = unit;
        this.measurementType = measurementType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(
            double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(
            String unit) {
        this.unit = unit;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(
            String measurementType) {
        this.measurementType = measurementType;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof QuantityDTO))
            return false;

        QuantityDTO other =
                (QuantityDTO) obj;

        return Double.compare(
                value,
                other.value) == 0

                && Objects.equals(
                unit,
                other.unit)

                && Objects.equals(
                measurementType,
                other.measurementType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                value,
                unit,
                measurementType);
    }

    @Override
    public String toString() {

        return "QuantityDTO{" +
                "value=" + value +
                ", unit='" + unit + '\'' +
                ", measurementType='" + measurementType + '\'' +
                '}';
    }
}