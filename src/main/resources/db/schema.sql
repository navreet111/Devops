CREATE TABLE IF NOT EXISTS quantity_measurement (

    id INT AUTO_INCREMENT PRIMARY KEY,

    value1 DOUBLE,

    unit1 VARCHAR(50),

    measurement_type1 VARCHAR(50),

    value2 DOUBLE,

    unit2 VARCHAR(50),

    measurement_type2 VARCHAR(50),

    operation VARCHAR(50),

    result VARCHAR(255),

    error_message VARCHAR(255)

);