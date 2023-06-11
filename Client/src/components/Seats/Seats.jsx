import React, { useEffect, useState } from "react";
import axios from "axios";
import "./styles.Seats.css";
import { Link } from "react-router-dom"

const Table = () => {
    const [object, setObject] = useState(null);
    const [loading, setLoading] = useState(true);
    const [checkedStatus, setCheckedStatus] = useState({});
    const nameOfRow = ["A", "B", "C", "D", "E", "F", "G", "H"];
    const idOfFlight = localStorage.getItem("id");

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get(
                    "http://localhost:8080/api/flights/" + idOfFlight
                );
                setObject(response.data);
                setLoading(false);
            } catch (error) {
                console.error(error);
            }
        };

        fetchData();
    }, [idOfFlight]);
    useEffect(() => {
        if (object && object.flight_seats) {
            const newCheckedStatus = {};
            for (const seat of object.flight_seats) {
                const idCheck = `${seat.row}-${seat.column}`;
                newCheckedStatus[idCheck] = seat.reserved;
            }
            setCheckedStatus(newCheckedStatus);
        }
    }, [object]);
    if (loading) {
        return <h1>Loading data...</h1>;
    }

    const renderObjectFields = () => {
        const headers = [];
        headers.push(<th key={0} className="seatsTh"></th>);
        for (let i = 1; i <= 53; i++) {
            headers.push(<th key={i} className="seatsTh">{i}</th>);
        }
        return headers;
    };

    const renderObjectValue = () => {
        const handleCheckboxChange = (idCheck) => {
            setCheckedStatus((prevStatus) => ({//change this to request to database
                ...prevStatus,
                [idCheck]: !prevStatus[idCheck],
            }));
        };
        const tableRows = [];
        for (let i = 0; i < 8; i++) {
            const tableColumns = [];
            tableColumns.push(<td key={0} className="seatsTd">{nameOfRow[i]}</td>);
            for (let j = 1; j < 54; j++) {
                const idCheck = nameOfRow[i] + "-" + j;
                tableColumns.push(
                    <td key={j + 1} className="seatsTd">
                        <input
                            type="checkbox"
                            id={idCheck}
                            checked={checkedStatus[idCheck] || false}
                            onChange={() => handleCheckboxChange(idCheck)}
                        />
                    </td>
                );
            }
            tableRows.push(<tr key={i} className="seatsTr">{tableColumns}</tr>);
        }
        return tableRows;
    };

    return (
        <div className="plane">
            <table className="seatsTableHeaders">
                <thead>
                    <tr className="seatsTr">{renderObjectFields()}</tr>
                </thead>
                <tbody>{renderObjectValue()}</tbody>
            </table>
            <Link to="/" className="backLink">
                <button type="button"
                    className="backButton"
                    onClick={() => localStorage.clear()}>
                    Back to flight table
                </button>
            </Link>
        </div>
    );
};

export default Table;