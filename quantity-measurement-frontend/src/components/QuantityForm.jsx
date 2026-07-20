import { UNITS_BY_TYPE } from "../services/quantityService";

/**
 * Reusable form: measurement type cards + operation buttons + value/unit
 * inputs. All state is controlled by the parent (Home.jsx) so this stays
 * a pure presentational component.
 */
export default function QuantityForm({
  currentType,
  onTypeChange,
  currentOperation,
  onOperationChange,
  values,
  onValueChange,
  onCalculate,
  loading,
}) {
  const units = UNITS_BY_TYPE[currentType];

  const showValue2 = currentOperation !== "convert";
  const showTarget = currentOperation !== "compare" && currentOperation !== "divide";

  const operations =
    currentType === "TEMPERATURE"
      ? ["compare", "convert"]
      : ["compare", "convert", "add", "subtract", "divide"];

  return (
    <>
      {/* Measurement type cards */}
      <section>
        <h2>Select Measurement Type</h2>
        <div className="cards">
          {Object.keys(UNITS_BY_TYPE).map((type) => (
            <div
              key={type}
              className={`card ${currentType === type ? "active" : ""}`}
              onClick={() => onTypeChange(type)}
            >
              {type === "LENGTH" && "📏"}
              {type === "WEIGHT" && "⚖️"}
              {type === "TEMPERATURE" && "🌡️"}
              {type === "VOLUME" && "🧪"}
              <h3>{type}</h3>
            </div>
          ))}
        </div>
      </section>

      {/* Operation buttons */}
      <section>
        <h2>Select Operation</h2>
        <div className="operations">
          {operations.map((op) => (
            <button
              key={op}
              className={currentOperation === op ? "active" : ""}
              onClick={() => onOperationChange(op)}
            >
              {op.charAt(0).toUpperCase() + op.slice(1)}
            </button>
          ))}
        </div>
      </section>

      {/* Inputs */}
      <section className="inputs">
        <div>
          <label>Value 1</label>
          <input
            type="number"
            value={values.value1}
            onChange={(e) => onValueChange("value1", e.target.value)}
          />
          <select value={values.unit1} onChange={(e) => onValueChange("unit1", e.target.value)}>
            {units.map((u) => (
              <option key={u} value={u}>
                {u}
              </option>
            ))}
          </select>
        </div>

        {showValue2 && (
          <div>
            <label>Value 2</label>
            <input
              type="number"
              value={values.value2}
              onChange={(e) => onValueChange("value2", e.target.value)}
            />
            <select value={values.unit2} onChange={(e) => onValueChange("unit2", e.target.value)}>
              {units.map((u) => (
                <option key={u} value={u}>
                  {u}
                </option>
              ))}
            </select>
          </div>
        )}

        {showTarget && (
          <div>
            <label>Target Unit</label>
            <select
              value={values.targetUnit}
              onChange={(e) => onValueChange("targetUnit", e.target.value)}
            >
              {units.map((u) => (
                <option key={u} value={u}>
                  {u}
                </option>
              ))}
            </select>
          </div>
        )}
      </section>

      <button className="calculate-btn" onClick={onCalculate} disabled={loading}>
        {loading ? "Calculating..." : "Calculate"}
      </button>
    </>
  );
}