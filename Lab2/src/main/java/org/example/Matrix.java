package org.example;

import java.util.Arrays;
import java.util.Random;

public class Matrix {
    protected double[][] values;

    public Matrix(){
        this(0,0);
    }

    public Matrix(int rows, int cols){
        if(rows < 0 || cols < 0){
            throw new Exception("Illegal dimension value");
        }
        values = new double[rows][cols];
    }

    public Matrix(Matrix matrix){
        this(matrix.getRows(),matrix.getCols());
        for(int i = 0; i < matrix.getRows(); i++){
            System.arraycopy(matrix.values[i], 0, this.values[i], 0, getCols());
        }
    }

    public int getRows(){
        return values.length;
    }

    public int getCols(){
        if(getRows() == 0){
            return 0;
        }
        return values[0].length;
    }

    public void fillRandom(int start, int end){
        Random rand = new Random();
        for(int i = 0; i < values.length; i++){
            for(int j = 0; j < values[0].length; j++){
                values[i][j] = rand.nextDouble(start, end);
            }
        }
    }

    public double getValue(int row, int col){
        if(row < 0 || row > getRows()-1 || col < 0 || col > getCols()-1){
            throw new Exception("Element position out of bounds");
        }
        return values[row][col];
    }

    public void setValue(int row, int col, double value){
        if(row < 0 || row >= getRows() || col < 0 || col >= getCols()){
            throw new Exception("Given row or column out of matrix bounds");
        }
        values[row][col] = value;
    }

    public Matrix getRow(int rowPos){
        if(rowPos < 0 || rowPos > getRows()-1){
            throw new Exception("Row position out of bounds");
        }
        Matrix row = new Matrix(1, getCols());
        System.arraycopy(values[rowPos], 0, row.values[0], 0, getCols());
        return row;
    }

    public void setRow(int index, double... row){
        if(index < 0 || index >= values.length){
            throw new Exception("Row index out of bounds");
        }
        if(getCols() != row.length){
            throw new Exception("Given row length does not fit boundaries");
        }
        System.arraycopy(row, 0, values[index], 0, getCols());
    }

    public Matrix getCol(int colPos){
        if(colPos < 0 || colPos > getCols()-1){
            throw new Exception("Column position out of bounds");
        }
        Matrix column = new Matrix(getRows(), 1);
        for (int i = 0; i < getRows(); i++) {
            column.values[i][0] = values[i][colPos];
        }
        return column;
    }

    public void setCol(int index, double... col){
        if(index < 0 || index > getRows()){
            throw new Exception("Column index out of bounds");
        }
        if(col.length != getRows()){
            throw new Exception("Column does not fit boundaries");
        }
        for (int i = 0; i < getRows(); i++) {
            values[i][index] = col[i];
        }
    }

    public int[] getDimension(){
        int[] dim = new int[2];
        if(values.length == 0){
            return dim;
        }
        dim[0] = getRows();
        dim[1] = getCols();
        return dim;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Matrix matrix)){
            return false;
        }
        return Arrays.deepEquals(values, matrix.values);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(values);
    }

    public Matrix add(Matrix matrix) {
        if (matrix.getRows() != getRows() || matrix.getCols() != getCols()) {
            throw new Exception("Can't add different dimension matrices");
        }
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                values[i][j] += matrix.values[i][j];
            }
        }
        return this;
    }

    public Matrix multiply(double number) {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                values[i][j] *= number;
            }
        }
        return this;
    }

    public Matrix multiply(Matrix matrix) {
        if (getCols() != matrix.getRows()) {
            throw new Exception("Illegal matrix dimension passed");
        }
        double[][] result = new double[getRows()][matrix.getCols()];
        int elementsN = getCols();
        for (int rowI = 0; rowI < result.length; rowI++) {
            for (int columnI = 0; columnI < result[0].length; columnI++) {
                for (int i = 0; i < elementsN; i++) {
                    result[rowI][columnI] += values[rowI][i] * matrix.values[i][columnI];
                }
            }
        }
        this.values = result;
        return this;
    }

    public Matrix transpose() {
        double[][] temp = new double[getCols()][getRows()];
        for (int i = 0; i < getCols(); i++) {
            for (int j = 0; j < getRows(); j++) {
                temp[i][j] = values[j][i];
            }
        }
        values = temp;
        return this;
    }

    public static Matrix createDiagonal(double... vector) {
        Matrix temp = new Matrix(vector.length, vector.length);
        for (int i = 0; i < vector.length; i++) {
            temp.values[i][i] = vector[i];
        }
        return temp;
    }

    public static Matrix createUnit(int squareRows) {
        if (squareRows < 0) {
            throw new Exception("Illegal matrix dimension passed");
        }
        Matrix temp = new Matrix(squareRows, squareRows);
        for (int i = 0; i < squareRows; i++) {
            temp.values[i][i] = 1;
        }
        return temp;
    }

    public static Matrix createRow(int cols) {
        if (cols < 0) {
            throw new Exception("Illegal matrix dimension passed");
        }
        Matrix temp = new Matrix(1, cols);
        Random rand = new Random();
        for (int i = 0; i < cols; i++) {
            temp.values[0][i] = rand.nextDouble();
        }
        return temp;
    }

    public static Matrix createCol(int rows) {
        if (rows < 0) {
            throw new Exception("Illegal matrix dimension passed");
        }
        Matrix result = new Matrix(rows, 1);
        for (int rowI = 0; rowI < rows; rowI++) {
            result.values[rowI][0] = Math.random();
        }
        return result;
    }

    public Matrix invert() {
        if (getRows() != getCols()) {
            throw new Exception("Impossible to invert a non-square matrix");
        }
        int dimension = getRows();
        double[][] temp = new double[dimension][dimension];
        double[][] temp2 = new double[dimension][dimension];

        for (int i = 0; i < temp2.length; i++) {
            temp[i][i] = 1;
            System.arraycopy(values[i], 0, temp2[i], 0, temp2[0].length);
        }
        for (int j = 0; j < dimension; j++) {
            int nonZeroI = j;
            while (nonZeroI < dimension && temp2[nonZeroI][j] == 0) {
                nonZeroI++;
            }
            if (nonZeroI == dimension) {
                throw new Exception("Inverted matrix does not exist");
            }
            swapRows(temp2, nonZeroI, j);
            swapRows(temp, nonZeroI, j);

            double number = temp2[j][j];
            for (int k = 0; k < dimension; k++) {
                temp[j][k] /= number;
                temp2[j][k] /= number;
            }

            for (int i = j+1; i < dimension; i++) {
                number = temp2[i][j];
                for (int k = 0; k < dimension; k++) {
                    temp[i][k] -= temp[j][k] * number;
                    temp2[i][k] -= temp2[j][k] * number;
                }
            }
        }
        for (int i = dimension-1; i > 0; i--) {
            for (int j = i-1; j >= 0; j--) {
                double number = temp2[j][i];
                for (int k = 0; k < dimension; k++) {
                    temp[j][k] -= temp[i][k] * number;
                }
            }
        }
        values = temp;
        return this;
    }

    private void swapRows(double[][] matrix, int row1, int row2) {
        double[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
    }

    public void print(){
        System.out.println();
        System.out.print("[");
        for (int i = 0; i < values.length; i++) {
            if(i >= 1){
                System.out.print(" ");
            }
            System.out.print("[");
            for (int j = 0; j < values[0].length; j++) {
                if(j < values[0].length-1){
                    System.out.printf("%.2f\t", values[i][j]);
                }
                else{
                    System.out.printf("%.2f",values[i][j]);
                }
            }
            System.out.print("]");
            if(i < values.length-1){
                System.out.println();
            }
        }
        System.out.print("]");
    }
}
