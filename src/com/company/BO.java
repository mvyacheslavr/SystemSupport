package com.company;

public class BO {
    private String name;
    private double coefficient;

    BO(String n, double c){
        name = n;
        coefficient = c;
    }

    public String getName() {
        return name;
    }
    public double getCoefficient() {
        return coefficient;
    }

    @Override
    public String toString() {
        return "BO{" +
                name + " - Весовой коэффициент= "+coefficient+
                '}';
    }
}
