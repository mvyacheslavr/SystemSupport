package com.company;

public class G {
    private String name;
    private int Criterion[] = new int[CONST.CRITERIONS];

    G(String n, int[] k) {
        name = n;
        try {
            for (int i = 0; i < Criterion.length; i++) {
                Criterion[i]=k[i];
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("!!!ОШИБКА ВВОДА в " + name + e);
        }
    }

    public String getName() {
        return name;
    }
    public int[] getCriterion() {
        return Criterion;
    }
    public int getCriterionN(int i) {
        return Criterion[i];
    }

    @Override
    public String toString() {
        return "G{" +
                name + " ["+ MatrixForBO.printArray(Criterion)+
                "] }";
    }
}
