import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import QuantityForm from "../components/QuantityForm";
import HistoryTable from "../components/HistoryTable";
import {
  compareQuantities,
  convertQuantity,
  addQuantities,
  subtractQuantities,
  divideQuantities,
  getHistory,
  UNITS_BY_TYPE,
} from "../services/quantityService";

export default function Home() {
  const [currentType, setCurrentType] = useState("LENGTH");
  const [currentOperation, setCurrentOperation] = useState("compare");
  const [values, setValues] = useState({
    value1: "",
    unit1: "FEET",
    value2: "",
    unit2: "FEET",
    targetUnit: "FEET",
  });
  const [result, setResult] = useState("Waiting...");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [history, setHistory] = useState([]);
  const [historyLoading, setHistoryLoading] = useState(false);

  useEffect(() => {
    loadHistory();
  }, []);

  // Reset unit dropdowns whenever the measurement type changes.
  useEffect(() => {
    const firstUnit = UNITS_BY_TYPE[currentType][0];
    setValues((prev) => ({ ...prev, unit1: firstUnit, unit2: firstUnit, targetUnit: firstUnit }));
  }, [currentType]);

  const handleTypeChange = (type) => {
    setCurrentType(type);
    setResult("Waiting...");
    setError("");
  };

  const handleOperationChange = (op) => {
    setCurrentOperation(op);
    setResult("Waiting...");
    setError("");
  };

  const handleValueChange = (field, value) => {
    setValues((prev) => ({ ...prev, [field]: value }));
  };

  const loadHistory = async () => {
    setHistoryLoading(true);
    try {
      const data = await getHistory();
      const sorted = [...data].sort((a, b) => (b.id || 0) - (a.id || 0)).slice(0, 5);
      setHistory(sorted);
    } catch (err) {
      console.error(err);
    } finally {
      setHistoryLoading(false);
    }
  };

  const validate = () => {
    if (values.value1 === "") return "Enter First Value";
    if (currentOperation !== "convert" && values.value2 === "") return "Enter Second Value";
    return null;
  };

  const handleCalculate = async () => {
    const validationError = validate();
    if (validationError) {
      setError(validationError);
      return;
    }
    setError("");
    setLoading(true);

    const thisQty = { value: Number(values.value1), unit: values.unit1, measurementType: currentType };
    const thatQty = { value: Number(values.value2), unit: values.unit2, measurementType: currentType };
    const targetQty = { value: 0, unit: values.targetUnit, measurementType: currentType };

    try {
      let response;
      switch (currentOperation) {
        case "compare":
          response = await compareQuantities(thisQty, thatQty);
          setResult(response.result ? "✅ Quantities are Equal" : "❌ Quantities are Not Equal");
          break;
        case "convert":
          response = await convertQuantity(thisQty, targetQty);
          setResult(`${response.result.value} ${response.result.unit}`);
          break;
        case "add":
          response = await addQuantities(thisQty, thatQty);
          setResult(`${response.result.value} ${response.result.unit}`);
          break;
        case "subtract":
          response = await subtractQuantities(thisQty, thatQty, targetQty);
          setResult(`${response.result.value} ${response.result.unit}`);
          break;
        case "divide":
          response = await divideQuantities(thisQty, thatQty);
          setResult(String(response.result));
          break;
        default:
          break;
      }
      loadHistory();
    } catch (err) {
      console.error(err);
      setError(err?.response?.data?.message || "Something went wrong. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container">
      <Navbar />

      <QuantityForm
        currentType={currentType}
        onTypeChange={handleTypeChange}
        currentOperation={currentOperation}
        onOperationChange={handleOperationChange}
        values={values}
        onValueChange={handleValueChange}
        onCalculate={handleCalculate}
        loading={loading}
      />

      {error && <div className="alert alert-error">{error}</div>}

      <section className="result">
        <h2>Result</h2>
        <div id="result">{result}</div>
      </section>

      <section className="history">
        <div className="history-header">
          <h2>Recent Operations</h2>
        </div>
        {historyLoading ? <p>Loading...</p> : <HistoryTable entries={history} />}
      </section>
    </div>
  );
}