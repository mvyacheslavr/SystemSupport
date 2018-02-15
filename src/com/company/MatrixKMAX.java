package com.company;

import java.util.ArrayList;
import java.util.List;

public class MatrixKMAX {
    private String name;
    private int[][] arr = new int[CONST.VARIANTS][CONST.VARIANTS];
    private int[] H;
    private int[] E;
    private int[] N;
    private int[] K1 = new int[CONST.VARIANTS];
    private int[] K2 = new int[CONST.VARIANTS];
    private int[] K3 = new int[CONST.VARIANTS];
    private int[] K4 = new int[CONST.VARIANTS];
    private int count = CONST.VARIANTS;
    StringBuilder stringBuilder = new StringBuilder();

    MatrixKMAX(MatrixForBO matrixForBO) {
        name = matrixForBO.getName();
        arr = matrixForBO.getArr();

        createH_E_N_and_K1_K2_K3_K4_andGlobalConclusion();
    }

    private void createH_E_N_and_K1_K2_K3_K4_andGlobalConclusion() {
        H = new int[arr.length];
        E = new int[arr.length];
        N = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            H[i] = sumForH(i);
            E[i] = sumForE(i);
            N[i] = sumForN(i);
            K1[i] = H[i] + E[i] + N[i];
            K2[i] = H[i] + N[i];
            K3[i] = H[i] + E[i];
            K4[i] = H[i];
        }
    }

    private void createConclusion() {
        createConclusionForArray(K1, "K1", false);
        createConclusionForArray(K2, "K2", true);
        createConclusionForArray(K3, "K3", false);
        createConclusionForArray(K4, "K4", true);
    }
    private void createConclusionForArray(int[] k_n, String name, boolean even) {
        int indexOfMax = 0;
        for (int i = 0; i < arr.length; i++) {
            if (k_n[i] > k_n[indexOfMax]) {
                indexOfMax = i;
            }
        }

        if (even == false) {
            if (k_n[indexOfMax] == count) {
                stringBuilder.append(Main.gList.get(indexOfMax).getName() + " по " + characteristicArray(name) + " (глобальный максимум)\n");
            } else {
                for (int i = 0; i < arr.length; i++) {
                    if (k_n[indexOfMax] == k_n[i]) {
                        stringBuilder.append(Main.gList.get(i).getName() + " по " + characteristicArray(name) + " равен " + k_n[i] + "\n");
                    }
                }
            }
        }
        if (even == true) {
            if (k_n[indexOfMax] == count - 1) {
                stringBuilder.append(Main.gList.get(indexOfMax).getName() + " по " + characteristicArray(name) + " (глобальный максимум)\n");
            } else {
                for (int i = 0; i < arr.length; i++) {
                    if (k_n[indexOfMax] == k_n[i]) {
                        stringBuilder.append(Main.gList.get(i).getName() + " по " + characteristicArray(name) + " равен " + k_n[i] + "\n");
                    }
                }
            }
        }
    }

    private String characteristicArray(String name) {
        String str = "";
        if (name.equals("K1") || name.equals("K2"))
            str = "максимальный";
        if (name.equals("K3") || name.equals("K4"))
            str = "наибольший";
        return name +" "+ str;
    }

    private int sumForH(int numberVariant) {
        int[] line = new int[arr.length];
        int[] column = new int[arr.length];
        int sum = 0;

        for (int i = 0; i < line.length; i++) {
            line[i] = arr[numberVariant][i];
            column[i] = arr[i][numberVariant];
        }

        for (int i = 0; i < line.length; i++) {
            if (line[i] == 1 & column[i] == 0)
                sum += 1;
        }
        return sum;
    }
    private int sumForE(int numberVariant) {
        int[] line = new int[arr.length];
        int[] column = new int[arr.length];
        int sum = 0;

        for (int i = 0; i < line.length; i++) {
            line[i] = arr[numberVariant][i];
            column[i] = arr[i][numberVariant];
        }

        for (int i = 0; i < line.length; i++) {
            if (line[i] == 1 & column[i] == 1)
                sum += 1;
        }
        return sum;
    }
    private int sumForN(int numberVariant) {
        int[] line = new int[arr.length];
        int[] column = new int[arr.length];
        int sum = 0;

        for (int i = 0; i < line.length; i++) {
            line[i] = arr[numberVariant][i];
            column[i] = arr[i][numberVariant];
        }

        for (int i = 0; i < line.length; i++) {
            if (line[i] == 0 & column[i] == 0)
                sum += 1;
        }
        return sum;
    }

    @Override
    public String toString() {
        return "MatrixKMAX  " +
                "name=" + name + '\n' +
//                MatrixForBO.printArray(arr) +
                printList(Main.gList) + "\n" +
                "H = " + printArray(H) + "\n" +
                "E = " + printArray(E) + "\n" +
                "N = " + printArray(N) + "\n" +
                "K1 = " + printArray(K1) + "\n" +
                "K2 = " + printArray(K2) + "\n" +
                "K3 = " + printArray(K3) + "\n" +
                "K4 = " + printArray(K4) + "\n" +
                "\n" + "ВЫВОДЫ:\n" +
                printConclusion() +
                '\n';
    }

    private StringBuilder printConclusion() {
        createConclusion();
        return stringBuilder;
    }

    public static String printArray(int a[]) {
        String string = "";
        for (int s : a)
            string += String.format("%14s", s);
        return string;
    }

    public static String printList(List<G> list) {
        String string = "      ";
        for (G s : list)
            string += String.format("%14s", s.getName());
        return string;
    }
}
