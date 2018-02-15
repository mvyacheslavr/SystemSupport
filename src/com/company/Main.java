package com.company;

import java.util.*;

/**
 *
 * */

public class Main {

    static List<G> gList;
    private static List<BO> boList;
    private static List<MatrixForBO> matrixForBOList;
    private static List<MatrixForMechanismOfTournament> matrixForMechanismOfTournamentList;
    private static Map<String,Double> finalColumn;
    private static List<MatrixKMAX> matrixKMAXList;

    public static void main(String[] args) {
        Main main = new Main();

        main.createListsGandBO();
        Main.viewList(gList);//ВЫВОД
        Main.viewList(boList);//ВЫВОД

        main.createArraysBO();
        Main.viewList(matrixForBOList);//ВЫВОД

        main.createMatrixMechanismOfTournament();
        Main.viewList(matrixForMechanismOfTournamentList);//ВЫВОД

        System.out.println("ПОСЛЕ УМНОЖЕНИЯ НА КОЭФФИЦИЕНТ И РАНЖИРОВАНИЯ");//ВЫВОД
        main.makeTMWithWeightCoefficient();
        Main.viewList(matrixForMechanismOfTournamentList);//ВЫВОД

        main.createFinalColumn();
        System.out.println("ФИНАЛЬНЫЙ СТОЛБЕЦ");//ВЫВОД
        System.out.println(MatrixForMechanismOfTournament.printTournamentsSum(finalColumn));//ВЫВОД

        main.createMatrixKMAXList();
        Main.viewList(matrixKMAXList);
    }

    private void createListsGandBO() {
        gList = new ArrayList<>();
        gList.add(new G("iPhone X", new int[]{0, 6, 9, 5, 6, 7, 6}));
        gList.add(new G("Galaxy S8", new int[]{3, 5, 9, 5, 7, 10, 5}));
        gList.add(new G("Xperia XZ1", new int[]{1, 4, 7, 5, 5, 6, 4}));
        gList.add(new G("Mi 6", new int[]{8, 5, 4, 6, 2, 5, 5}));
        gList.add(new G("LG Q6", new int[]{6, 6, 7, 7, 7, 5, 4}));
        gList.add(new G("OnePlus 5", new int[]{7, 4, 8, 7, 4, 5, 7}));
        gList.add(new G("Nokia 3310", new int[]{10, 10, 0, 10, 0, 10, 0}));

        boList = new ArrayList<>();
        boList.add(new BO("Стоимость",0.6));
        boList.add(new BO("Габариты",0.5));
        boList.add(new BO("Камера",0.7));
        boList.add(new BO("Аккумулятор",0.5));
        boList.add(new BO("Влагозащита",0.3));
        boList.add(new BO("Внешний вид",0.4));
        boList.add(new BO("Поддержка компании",0.2));
    }
    private void createArraysBO() {
        matrixForBOList = new ArrayList<>();

        String name;
        for (int i = 0; i < boList.size(); i++) {
            name = boList.get(i).getName();
            matrixForBOList.add(new MatrixForBO(i, name, gList));
        }
    }
    private void createMatrixMechanismOfTournament() {
        matrixForMechanismOfTournamentList = new ArrayList();
        for (int i = 0; i < matrixForBOList.size(); i++) {
            matrixForMechanismOfTournamentList.add(new MatrixForMechanismOfTournament(matrixForBOList.get(i),boList.get(i).getCoefficient()));
        }
    }
    private void makeTMWithWeightCoefficient() {
        for (int i = 0; i < matrixForMechanismOfTournamentList.size(); i++) {
            matrixForMechanismOfTournamentList.get(i).setNewTournamentsSum();
        }
    }
    private void createFinalColumn() {
        finalColumn = new LinkedHashMap<>();
        for (int i = 0; i < boList.size(); i++) {
            String name = gList.get(i).getName();
            finalColumn.put(name,sumForFinalColumn(name));
        }
        finalColumn = MatrixForMechanismOfTournament.sortByValue(finalColumn);
    }
    private double sumForFinalColumn(String name) {
        double sum = 0.0;
            for (MatrixForMechanismOfTournament m:matrixForMechanismOfTournamentList){
                sum+=m.getTournamentValueOnName(name);
            }
        return sum;
    }

    private void createMatrixKMAXList() {
        matrixKMAXList = new ArrayList<>();
        for (int i = 0; i < matrixForBOList.size(); i++) {
            matrixKMAXList.add(new MatrixKMAX(matrixForBOList.get(i)));
        }
    }

    public static void viewList(List list) {
        for (Object s : list)
            System.out.println(s);
        System.out.println();
    }

}
