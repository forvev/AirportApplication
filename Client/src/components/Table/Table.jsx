import React, { useEffect, useState } from "react";
import axios from "axios";

import { Link } from "react-router-dom"
import "./styles.Table.css";

const Table = () => {
  const [object, setObject] = useState(null);
  const [loading, setLoading] = useState(true);
  const headers = [
    "Operating airline",
    "Iata Code",
    "Aircraft model",
    "Flight number",
    "Departure airport",
    "Arrival airport",
    "Departure terminal",
  ];
  const headersCompare = [
    "operatingAirline",
    "iataCode",
    "aircraftModelNameComboBox",
    "flightNumber",
    "departureAirport",
    "arrivalAirport",
    "departureTerminal",
  ];

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/flights");
        setObject(response.data);
        setLoading(false);
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
  }, []);

  if (loading) {
    return <h1>Loading data...</h1>;
  }

  const renderObjectFields = () => {
    let hasNestedObject = false;
    const rows = [];
    let done = true;
    const renderFields = () => {
      for (const key of headers) {
        rows.push(<th key={key}>{key}</th>);
        hasNestedObject = true;
        done = false;
      }
    };

    renderFields();
    if (hasNestedObject) {
      rows.push(
        <React.Fragment key="buttons">
          <th>Reserve</th>
        </React.Fragment>
      );
      hasNestedObject = false;
    }

    return rows;
  };
  const reservation = (obj) => {
    const fetchData = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/flights/" + obj);
        console.log(response.data.flight_seats[0]);
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();

  }


  const renderObjectValue = (obj) => {
    let hasNestedObject = false;
    const rows = [];

    const renderFields = (obj) => {
      const fields = []; // Separate array for fields within a single object

      for (const [key, value] of Object.entries(obj)) {
        if (typeof value === "object" && !Array.isArray(value)) {
          renderFields(value); // Directly call renderFields for nested objects
        } else if (headersCompare.includes(key)) {
          fields.push(<td key={key}>{value}</td>);
          hasNestedObject = true;
        }
      }

      if (fields.length > 0) {
        rows.push(
          <tr key={Math.random()}>
            {fields}
            <td className="noPadding">
              <Link to="/seats">
                <button type="button"
                  className="noPadding editButton" onClick={() => localStorage.setItem("id", obj.id)}>
                  Reserve
                </button>
              </Link>
            </td>
          </tr>
        );
      }
    };

    renderFields(obj);

    return rows;
  };

  return (
    <table className="tableHeaders">
      <thead>
        <tr>{renderObjectFields()}</tr>
      </thead>
      <tbody>{renderObjectValue(object)}</tbody>
    </table>
  );
};

export default Table;
