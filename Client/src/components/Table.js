import React, { useEffect, useState } from "react";
import axios from "axios";
import "../App.css";

const Table = () => {
  const [object, setObject] = useState(null);
  const [loading, setLoading] = useState(true);

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
    return <div>Loading...</div>;
  }

  const renderObjectFields = (obj) => {
    return Object.entries(obj).map(([key, value]) => {
      if (typeof value === "object" && !Array.isArray(value)) {
        return (
          <tr key={key} className="tableHeaders">
            {renderObjectFields(value)}
          </tr>
        );
      } else {
        return <th key={key}>{`${key}`}</th>;
      }
    });
  };

  const renderObjectValue = (obj) => {
    return Object.entries(obj).map(([key, value]) => {
      if (typeof value === "object" && !Array.isArray(value)) {
        return <tr key={key}>{renderObjectValue(value)}</tr>;
      } else {
        return <td key={key}>{`${value}`}</td>;
      }
    });
  };

  return (
    <table className="tableHeaders">
      {renderObjectFields(object)}
      <tbody>{renderObjectValue(object)}</tbody>
    </table>
  );
};

export default Table;
