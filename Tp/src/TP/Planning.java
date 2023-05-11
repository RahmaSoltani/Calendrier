package TP;
import java.time.LocalDate;

import java.util.ArrayList;
public class Planning {
    private LocalDate datedebut;
    private LocalDate datefin;
    
    private int nbtachestermin;
    private int encouragement;
    private int Good;
    private int VeryGood;
    private int Excelent;
    
    public Planning(LocalDate datedebut, LocalDate datefin) throws DateException {
        LocalDate currentDate = LocalDate.now();

        if (datedebut.isBefore(currentDate) || datefin.isBefore(currentDate)) {
            throw new DateException("Provided date is before the current date.");
        }

        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    public LocalDate getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(LocalDate datedebut) {
        this.datedebut = datedebut;
    }

    public LocalDate getDatefin() {
        return datefin;
    }

    public void setDatefin(LocalDate datefin) {
        this.datefin = datefin;
    }

    public boolean isCurrentDayWithin() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.isAfter(datedebut) && currentDate.isBefore(datefin);
    }

    public int getNbtachestermin() {
        return nbtachestermin;
    }

    public void setNbtachestermin(int nbtachestermin) {
        this.nbtachestermin = nbtachestermin;
    }

    public int getEncouragement() {
        return encouragement;
    }

    public void setEncouragement(int encouragement) {
        this.encouragement = encouragement;
    }

    public int getGood() {
        return Good;
    }

    public void setGood(int good) {
        Good = good;
    }

    public int getVeryGood() {
        return VeryGood;
    }

    public void setVeryGood(int veryGood) {
        VeryGood = veryGood;
    }

    public int getExcelent() {
        return Excelent;
    }

    public void setExcelent(int excelent) {
        Excelent = excelent;
    }

    public boolean isCurrentDayWithinPlanning() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.isEqual(datedebut) || (currentDate.isAfter(datedebut) && currentDate.isBefore(datefin));
    }

    public void incrementGood() {
        Good++;
    }
    
    public void incrementVeryGood() {
        VeryGood++;
    }
    
    public void incrementExcelent() {
        Excelent++;
    }
    public void incrementencouragement()
    {
        encouragement++;
    }
    
    public void incrementtachecomplets() {
        nbtachestermin++;
    }
}