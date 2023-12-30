package org.example;

public final class ImmutableMatrix extends Matrix {

    public ImmutableMatrix() {
        super();
    }

    public ImmutableMatrix(int rows, int cols) {
        super(rows, cols);
    }


    public ImmutableMatrix(Matrix matrix) {
        super(matrix);
    }

    @Override
    public void setValue(int row, int col, double value) {
        throw new Exception("Illegal action for immutable matrix");
    }

    @Override
    public void setRow(int index, double... row) {
        throw new Exception("Illegal action for immutable matrix");
    }

    @Override
    public void setCol(int index, double... column) {
        throw new Exception("Illegal action for immutable matrix");
    }

    @Override
    public Matrix add(Matrix matrix) {
        return new Matrix(this).add(matrix);
    }

    @Override
    public Matrix multiply(double number) {
        return new Matrix(this).multiply(number);
    }

    @Override
    public Matrix multiply(Matrix matrix) {
        return new Matrix(this).multiply(matrix);
    }

    @Override
    public Matrix transpose() {
        return new Matrix(this).transpose();
    }

    @Override
    public Matrix invert() {
        return new Matrix(this).invert();
    }

}
