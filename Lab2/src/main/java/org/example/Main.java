package org.example;

public class Main {
    public static void main(String[] args){
        Matrix m = new Matrix(4,4);
        m.fillRandom(1,5);
        m.print();
        System.out.println();
        ImmutableMatrix m1 = new ImmutableMatrix(m);
        m1.print();
        System.out.println();
        m.setValue(1,1,-100);
        System.out.println();
        m.print();
        System.out.println();
        m1.print();
        System.out.println();

        ImmutableMatrix mt = new ImmutableMatrix(m1);
        mt.transpose();
        System.out.println("\n\n\nmt matrix");
        mt.print();
        mt.multiply(m1);
        System.out.println();
        mt.print();



    }
}
