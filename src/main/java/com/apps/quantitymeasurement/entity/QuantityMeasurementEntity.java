package com.apps.quantitymeasurement.entity;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "quantity_measurements")
public class QuantityMeasurementEntity
        implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "value",
                    column = @Column(name = "first_value")
            ),
            @AttributeOverride(
                    name = "unit",
                    column = @Column(name = "first_unit")
            ),
            @AttributeOverride(
                    name = "measurementType",
                    column = @Column(name = "first_measurement_type")
            )
    })
    private QuantityDTO thisQuantity;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "value",
                    column = @Column(name = "second_value")
            ),
            @AttributeOverride(
                    name = "unit",
                    column = @Column(name = "second_unit")
            ),
            @AttributeOverride(
                    name = "measurementType",
                    column = @Column(name = "second_measurement_type")
            )
    })
    private QuantityDTO thatQuantity;

    @Column(name = "operation")
    private String operation;

    @Column(name = "result")
    private String result;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "is_error")
    private boolean error;

    public QuantityMeasurementEntity() {
    }

    // Success Constructor
    public QuantityMeasurementEntity(
            QuantityDTO thisQuantity,
            QuantityDTO thatQuantity,
            String operation,
            Object result) {

        this.thisQuantity = thisQuantity;
        this.thatQuantity = thatQuantity;
        this.operation = operation;
        this.result =
                result == null
                        ? null
                        : result.toString();
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

    public Integer getId() {
        return id;
    }

    public void setId(
            Integer id) {
        this.id = id;
    }

    public QuantityDTO getThisQuantity() {
        return thisQuantity;
    }

    public void setThisQuantity(
            QuantityDTO thisQuantity) {
        this.thisQuantity = thisQuantity;
    }

    public QuantityDTO getThatQuantity() {
        return thatQuantity;
    }

    public void setThatQuantity(
            QuantityDTO thatQuantity) {
        this.thatQuantity = thatQuantity;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(
            String operation) {
        this.operation = operation;
    }

    public String getResult() {
        return result;
    }

    public void setResult(
            String result) {
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(
            String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isError() {
        return error;
    }

    public void setError(
            boolean error) {
        this.error = error;
    }
    private LocalDateTime createdAt;
    @Override
    public boolean equals(
            Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof QuantityMeasurementEntity)) {
            return false;
        }

        QuantityMeasurementEntity other =
                (QuantityMeasurementEntity) obj;

        return Objects.equals(id, other.id)
                && Objects.equals(thisQuantity, other.thisQuantity)
                && Objects.equals(thatQuantity, other.thatQuantity)
                && Objects.equals(operation, other.operation)
                && Objects.equals(result, other.result)
                && Objects.equals(errorMessage, other.errorMessage)
                && error == other.error;
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                id,
                thisQuantity,
                thatQuantity,
                operation,
                result,
                errorMessage,
                error);
    }

    @Override
    public String toString() {

        return "QuantityMeasurementEntity{" +
                "id=" + id +
                ", operation='" + operation + '\'' +
                ", result='" + result + '\'' +
                ", error=" + error +
                '}';
    }
}