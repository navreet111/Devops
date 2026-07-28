package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.dto.OperationType;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.dto.QuantityInputDTO;
import com.apps.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.repository.QuantityMeasurementRepository;
@RestController
@RequestMapping("/api/v1/quantities")
@Tag(
        name = "Quantity Measurements",
        description = "REST API for quantity measurement operations"
)
public class QuantityMeasurementController {

    private static final Logger logger =
            Logger.getLogger(
                    QuantityMeasurementController.class.getName());

    @Autowired
    private IQuantityMeasurementService service;
    @Autowired
    private QuantityMeasurementRepository repository;
    // =====================================================
    // JSON Examples
    // =====================================================

    private static final String EX_FEET_INCH =
            """
                    {
                      "thisQuantityDTO":{
                         "value":1.0,
                         "unit":"FEET",
                         "measurementType":"LengthUnit"
                      },
                      "thatQuantityDTO":{
                         "value":12.0,
                         "unit":"INCHES",
                         "measurementType":"LengthUnit"
                      }
                    }
                    """;

    private static final String EX_YARD_FEET =
            """
                    {
                      "thisQuantityDTO":{
                         "value":1.0,
                         "unit":"YARDS",
                         "measurementType":"LengthUnit"
                      },
                      "thatQuantityDTO":{
                         "value":3.0,
                         "unit":"FEET",
                         "measurementType":"LengthUnit"
                      }
                    }
                    """;

    private static final String EX_WITH_TARGET =
            """
                    {
                      "thisQuantityDTO":{
                         "value":1.0,
                         "unit":"FEET",
                         "measurementType":"LengthUnit"
                      },
                      "thatQuantityDTO":{
                         "value":12.0,
                         "unit":"INCHES",
                         "measurementType":"LengthUnit"
                      },
                      "targetQuantityDTO":{
                         "value":0.0,
                         "unit":"INCHES",
                         "measurementType":"LengthUnit"
                      }
                    }
                    """;
    // =====================================================
// Compare Quantities
// =====================================================

    @Operation(
            summary = "Compare Two Quantities",
            description = "Checks whether two quantities are equal."
    )
    @PostMapping("/compare")
    public ResponseEntity<QuantityMeasurementDTO> compare(
            @RequestBody QuantityInputDTO inputDTO) {

        if (inputDTO.getThatQuantityDTO() == null) {
            throw new IllegalArgumentException("Second quantity required");
        }

        boolean result =
                service.compare(
                        inputDTO.getThisQuantityDTO(),
                        inputDTO.getThatQuantityDTO());

        return ResponseEntity.ok(
                new QuantityMeasurementDTO(result)
        );
    }
    // =====================================================
// Convert Quantity
// =====================================================

    @Operation(
            summary = "Convert Quantity",
            description = "Converts one quantity into another unit."
    )
    @PostMapping("/convert")
    public ResponseEntity<QuantityMeasurementDTO>
    performConversion(

            @Valid
            @RequestBody
            QuantityInputDTO inputDTO) {

        logger.info(
                "Performing Quantity Conversion");

        QuantityDTO result =

                service.convert(

                        inputDTO.getThisQuantityDTO(),

                        inputDTO.getTargetQuantityDTO());

        QuantityMeasurementDTO response =

                new QuantityMeasurementDTO(

                        true,

                        "Conversion Successful",

                        result);

        return ResponseEntity.ok(
                response);
    }
    // =====================================================
// Add Quantities
// =====================================================
//
//    @Operation(
//            summary = "Add Two Quantities",
//            description = "Adds two quantities having same measurement type."
//    )
//    @PostMapping("/add")
//    public ResponseEntity<QuantityMeasurementDTO> add(
//            @RequestBody QuantityInputDTO inputDTO) {
//
//        if (inputDTO.getThatQuantityDTO() == null) {
//            throw new IllegalArgumentException("Second quantity required");
//        }
//
//        QuantityDTO result =
//                service.add(
//                        inputDTO.getThisQuantityDTO(),
//                        inputDTO.getThatQuantityDTO());
//
//        return ResponseEntity.ok(
//                new QuantityMeasurementDTO(result)
//        );
//    }
    // =====================================================
// Add Quantities With Target Unit
// =====================================================

    @Operation(
            summary = "Add Two Quantities In Target Unit",
            description = "Adds two quantities and returns the result in the specified target unit."
    )
    @PostMapping("/add")
    public ResponseEntity<QuantityMeasurementDTO> add(
            @RequestBody QuantityInputDTO inputDTO) {

        if (inputDTO.getThatQuantityDTO() == null) {
            throw new IllegalArgumentException("Second quantity required");
        }

        QuantityDTO result =
                service.add(
                        inputDTO.getThisQuantityDTO(),
                        inputDTO.getThatQuantityDTO());

        return ResponseEntity.ok(
                new QuantityMeasurementDTO(result)
        );
    }
    // =====================================================
// Subtract Quantities
// =====================================================

    @Operation(
            summary = "Subtract Two Quantities",
            description = "Subtracts two quantities having same measurement type."
    )
    @PostMapping("/subtract")
    public ResponseEntity<QuantityMeasurementDTO>
    performSubtraction(

            @Valid
            @RequestBody
            QuantityInputDTO inputDTO) {

        logger.info(
                "Performing Quantity Subtraction");

        QuantityDTO result =
                service.subtract(

                        inputDTO.getThisQuantityDTO(),

                        inputDTO.getThatQuantityDTO());

        QuantityMeasurementDTO response =
                new QuantityMeasurementDTO(

                        true,

                        "Subtraction Successful",

                        result);

        return ResponseEntity.ok(
                response);
    }
    // =====================================================
// Subtract Quantities With Target Unit
// =====================================================

    @Operation(
            summary = "Subtract Two Quantities In Target Unit",
            description = "Subtracts two quantities and returns the result in the specified target unit."
    )
    @PostMapping("/subtract/target")
    public ResponseEntity<QuantityMeasurementDTO>
    performSubtractionWithTargetUnit(

            @Valid
            @RequestBody
            QuantityInputDTO inputDTO) {
        if (inputDTO.getThatQuantityDTO() == null) {
            throw new IllegalArgumentException("Second quantity required");
        }
        logger.info(
                "Performing Quantity Subtraction With Target Unit");

        QuantityDTO result =
                service.subtract(

                        inputDTO.getThisQuantityDTO(),

                        inputDTO.getThatQuantityDTO(),

                        inputDTO.getTargetQuantityDTO());

        QuantityMeasurementDTO response =
                new QuantityMeasurementDTO(

                        true,

                        "Subtraction Successful",

                        result);

        return ResponseEntity.ok(
                response);
    }

    // =====================================================
// Divide Quantities
// =====================================================

    @Operation(
            summary = "Divide Two Quantities",
            description = "Divides one quantity by another."
    )
    @PostMapping("/divide")
    public ResponseEntity<QuantityMeasurementDTO>
    performDivision(

            @Valid
            @RequestBody
            QuantityInputDTO inputDTO) {
        if (inputDTO.getThatQuantityDTO() == null) {
            throw new IllegalArgumentException("Second quantity required");
        }
        logger.info(
                "Performing Quantity Division");

        double result =
                service.divide(

                        inputDTO.getThisQuantityDTO(),

                        inputDTO.getThatQuantityDTO());

        QuantityMeasurementDTO response =
                new QuantityMeasurementDTO(

                        true,

                        "Division Successful",

                        result);

        return ResponseEntity.ok(
                response);
    }
    @PostMapping("/divide/target")
    public ResponseEntity<QuantityMeasurementDTO> performDivisionWithTarget(
            @Valid @RequestBody QuantityInputDTO inputDTO) {
        if (inputDTO.getThatQuantityDTO() == null) {
            throw new IllegalArgumentException("Second quantity required");
        }
        double result = service.divide(
                inputDTO.getThisQuantityDTO(),
                inputDTO.getThatQuantityDTO());

        return ResponseEntity.ok(
                new QuantityMeasurementDTO(
                        true,
                        "Division Successful",
                        result));
    }
    // =====================================================
// Get History By Operation
// =====================================================

    @Operation(
            summary = "Get History By Operation",
            description = "Returns all quantity measurements for the given operation."
    )
    @GetMapping("/history/operation/{operation}")
    public ResponseEntity<List<QuantityMeasurementEntity>>
    getHistoryByOperation(

            @PathVariable
            OperationType operation) {

        logger.info(
                "Fetching History By Operation");

        List<QuantityMeasurementEntity> history =

                repository.findByOperation(
                        operation.name());

        return ResponseEntity.ok(
                history);
    }
    @GetMapping("/history")
    public ResponseEntity<List<QuantityMeasurementEntity>> getHistory(){

        return ResponseEntity.ok(

                repository.findAll()

        );

    }
    // =====================================================
// Get History By Measurement Type
// =====================================================

    @Operation(
            summary = "Get History By Measurement Type",
            description = "Returns all measurements of a specific type."
    )
    @GetMapping("/history/type/{type}")
    public ResponseEntity<List<QuantityMeasurementEntity>>
    getHistoryByMeasurementType(

            @PathVariable
            String type) {

        logger.info(
                "Fetching History By Measurement Type");

        List<QuantityMeasurementEntity> history =

                repository.findByThisQuantityMeasurementType(
                        type);

        return ResponseEntity.ok(
                history);
    }
    // =====================================================
// Get Total Records
// =====================================================

    @Operation(
            summary = "Get Total Records",
            description = "Returns total number of stored measurements."
    )
    @GetMapping("/count")
    public ResponseEntity<Long>
    getTotalCount() {

        logger.info(
                "Fetching Total Count");

        return ResponseEntity.ok(
                repository.count());
    }
    // =====================================================
// Delete History
// =====================================================

    @Operation(
            summary = "Delete Complete History",
            description = "Deletes all quantity measurement history."
    )
    @DeleteMapping("/history")
    public ResponseEntity<String>
    deleteHistory() {

        logger.info(
                "Deleting History");

        repository.deleteAll();

        return ResponseEntity.ok(
                "History Deleted Successfully");
    }
    @GetMapping("/login-success")
    public String loginSuccess() {

        return "Google Login Successful";
    }

}