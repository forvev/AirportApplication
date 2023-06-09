package com.example.demo.model;

public class Seat {
    public enum SeatClass {
        FIRST(3), ECONOMY_PLUS(2), ECONOMY(1);
        private double priceMultiplier;

        SeatClass(double priceMultiplier) {
            this.priceMultiplier = priceMultiplier;
        }
    }

    private SeatClass seatClass;
    private boolean isEmergencySeat;
    private int column;
    private char row;

    private boolean isReserved;

    public Seat(SeatClass seatClass, boolean isEmergencySeat, int column, char row, boolean isReserved) {
        this.seatClass = seatClass;
        this.isEmergencySeat = isEmergencySeat;
        this.column = column;
        this.row = row;
        this.isReserved = isReserved;

    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatClass=" + seatClass +
                ", isEmergencySeat=" + isEmergencySeat +
                ", column=" + column +
                ", row=" + row +
                ", isReserved=" + isReserved +
                '}';
    }

    public boolean isReserved() {
        return isReserved;
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(SeatClass seatClass) {
        this.seatClass = seatClass;
    }

    public boolean isEmergencySeat() {
        return isEmergencySeat;
    }

    public void setEmergencySeat(boolean emergencySeat) {
        isEmergencySeat = emergencySeat;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
}
