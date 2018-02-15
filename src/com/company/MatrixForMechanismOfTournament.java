package com.company;

import java.util.*;
import java.util.stream.Stream;

public class MatrixForMechanismOfTournament {
    private String name;
    private Map<String, Double> tournamentsSum;
    private double coefficient;

    MatrixForMechanismOfTournament(MatrixForBO matrixForBO, double c) {
        name = matrixForBO.getName();

        tournamentsSum = sortByValue(matrixForBO.getTournamentSums());
//        tournamentsSum = matrixForBO.getTournamentSums();
        coefficient = c;
    }

    @Override
    public String toString() {
        return
                "ТМ " + name + '\n' + printTournamentsSum(tournamentsSum);
    }

    public static String printTournamentsSum(Map<String,Double> map) {
        String string = "";

        for (String s : map.keySet()) {
            string += s + "=";
            string += String.format("%.3f",map.get(s)) + "\n";
        }
        return string;
    }

    public double getTournamentValueOnName(String name) {
        return tournamentsSum.get(name);
    }

    final public void setNewTournamentsSum() {
        for (Map.Entry<String, Double> entry : tournamentsSum.entrySet()) {
                entry.setValue(entry.getValue()*coefficient);
        }
    }

    public static  <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map ) {
        Map<K,V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K,V>> st = map.entrySet().stream();
        st.sorted(Comparator.comparing(e -> e.getValue())).forEach(e ->result.put(e.getKey(),e.getValue()));
        return result;
    }
}
