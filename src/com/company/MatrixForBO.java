package com.company;

import java.util.*;

public class MatrixForBO {
    private String name;
    private int[][] arr = new int[CONST.VARIANTS][CONST.VARIANTS];
    private int numberCriterion;
    private List<G> list;
    private List<String> namesMechanismOfDominance;
    private List<String> namesMechanismOfBlocking;
    private Map<String, Double> tournamentSums;

    MatrixForBO(int numberCriterion, String name, List<G> gList) {
        this.name = name;
        this.numberCriterion = numberCriterion;
        list = gList;

        createBoMatrix();
        executeMechanismOfDominance();
        executeMechanismOfBlocking();
        executeSumForTournamentMechanism();
    }

    private void createBoMatrix() {
        createEmatrix();
        makeBOmatrix();
    }
    private void createEmatrix() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i == j) {
                    arr[i][j] = 1;
                } else {
                    arr[i][j] = 0;
                }
            }
        }
    }
    private void makeBOmatrix() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (list.get(i).getCriterionN(numberCriterion) >= list.get(j).getCriterionN(numberCriterion)) {
                    arr[i][j] = 1;
                } else {
                    arr[i][j] = 0;
                }
            }
        }
    }
    private void executeMechanismOfDominance() {
        namesMechanismOfDominance = new ArrayList<>();
        String name;
        int numberString = 0;
        for (int[] i : arr) {
            int sum = Arrays.stream(i).sum();
            if (sum == CONST.VARIANTS) {
                name = list.get(numberString).getName();
                namesMechanismOfDominance.add(name);
            }
            numberString++;
        }
    }
    private void executeMechanismOfBlocking() {
        namesMechanismOfBlocking = new ArrayList<>();
        String name;
        int numberString = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = 0; j < arr.length; j++) {
                sum += arr[j][i];
            }
            if (sum == 1) {
                name = list.get(numberString).getName();
                namesMechanismOfBlocking.add(name);
            }
            numberString++;
        }
    }
    private void executeSumForTournamentMechanism() {
        tournamentSums = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i++) {
            tournamentSums.put(list.get(i).getName(), sumOfVariant(i));
        }
    }
    private double sumOfVariant(int numberVariant) {
        int[] line = new int[CONST.VARIANTS];
        int[] column = new int[CONST.VARIANTS];
        double sum = 0.0;

        for (int i = 0; i < line.length; i++) {
            line[i] = arr[numberVariant][i];
            column[i] = arr[i][numberVariant];
        }

        for (int i = 0; i < line.length; i++) {
            if (line[i] == 1 & column[i] == 0)
                sum += 1;
            else if (line[i] == 1 & column[i] == 1)
                sum += 0.5;
        }
        return sum;
    }

    @Override
    public String toString() {
        return "БО  "+name +
                "\n" + printArray(arr) +
                "Механизм доминирования " + namesMechanismOfDominance + "\n" +
                "Механизм блокировки" + namesMechanismOfBlocking + "\n" +
                "Турнирная суммы" + tournamentSums +
                "\n";
    }
    public static String printArray(int a[][]) {
        String s = "";
        for (int[] i : a) {
            for (int j : i) {
                s += j + " ";
            }
            s += "\n";
        }
        return s;
    }
    public static String printArray(int a[]) {
        String string = "";
        for (int s : a)
            string += s + " ";
        return string;
    }

    public int[][] getArr() {
        return arr;
    }

    public String getName() {
        return name;
    }
    public Map<String, Double> getTournamentSums() {
        return tournamentSums;
    }
}