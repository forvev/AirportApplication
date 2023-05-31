import React, { useEffect, useState } from "react";
import axios from "axios";
import "./App.css";

const App = () => {
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

  const renderObjectValue = (obj) => {
    return Object.entries(obj).map(([key, value]) => {
      if (typeof value === "object" && !Array.isArray(value)) {
        return (
          <div key={key}>
            <div style={{ marginLeft: "1rem" }}>{renderObjectValue(value)}</div>
          </div>
        );
      } else {
        return <div className="object" key={key}>{`${value}`}</div>;
      }
    });
  };
  // console.log(object);
  return <div className="object">{renderObjectValue(object)}</div>;
};

export default App;
