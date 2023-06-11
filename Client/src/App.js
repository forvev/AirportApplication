import { Route, Routes, Navigate } from "react-router-dom";
import ReactDOM from "react-dom";
import "./App.css";
import Table from "./components/Table/Table";
import Seats from "./components/Seats/Seats";

function App() {
  return (
    <Routes>
      <Route path="/" exact element={<Table />} />
      <Route path="/seats" exact element={<Seats />} />
    </Routes>
  );
}

export default App;
