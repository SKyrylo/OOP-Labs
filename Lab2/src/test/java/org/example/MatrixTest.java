package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MatrixTest {

    @Test
    void shouldThrowException() {
        assertThrows(Exception.class, () -> new Matrix(-1, 1));
    }

    @Test
    void setGetValue() {
        Matrix matrix = new Matrix(3, 4);
        matrix.setValue(2, 1, 12);
        assertEquals(12, matrix.getValue(2, 1));
    }

    @Test
    void setValueException() {
        Matrix matrix = new Matrix(3, 4);
        assertThrows(Exception.class, () -> matrix.setValue(8, 7, 7));
    }

    @Test
    void getValueException() {
        Matrix matrix = new Matrix(3, 4);
        assertThrows(Exception.class,() -> matrix.getValue(3, 3));
    }

    @Test
    void setRow() {
        Matrix matrix = new Matrix(3, 4);
        matrix.setRow(2, 1, 1, 1, 1);
        assertEquals(1, matrix.getValue(2, 0));
        assertEquals(1, matrix.getValue(2, 1));
        assertEquals(1, matrix.getValue(2, 2));
        assertEquals(1, matrix.getValue(2, 3));
    }

    @Test
    void setRowException() {
        Matrix matrix = new Matrix(3, 4);
        assertThrows(Exception.class,() -> matrix.setRow(-4, 1, 1, 1, 1));
    }

    @Test
    void setRowException2() {
        Matrix matrix = new Matrix(3, 4);
        assertThrows(Exception.class,() -> matrix.setRow(1, 1, 1, 1, 1, 1));
    }

    @Test
    void setCol() {
        Matrix matrix = new Matrix(3, 4);
        matrix.setCol(3, 1, 1, 1);
        assertEquals(1, matrix.getValue(0, 3));
        assertEquals(1, matrix.getValue(1, 3));
        assertEquals(1, matrix.getValue(2, 3));
    }

    @Test
    void setColException() {
        Matrix matrix = new Matrix(3, 4);
        assertThrows(Exception.class,() -> matrix.setCol(-1, 1, 1, 1, 1, 1));
    }

    @Test
    void setColException2() {
        Matrix matrix = new Matrix(3, 4);
        assertThrows(Exception.class,() -> matrix.setCol(1, 1, 1, 1, 1));
    }

    @Test
    void getRow() {
        Matrix matrix = new Matrix(4, 3);
        matrix.setRow(1, 1, 2, 3);
        Matrix row = matrix.getRow(1);
        assertEquals(1, row.getValue(0, 0));
        assertEquals(2, row.getValue(0, 1));
        assertEquals(3, row.getValue(0, 2));
    }

    @Test
    void getRowException() {
        Matrix matrix = new Matrix(4, 3);
        assertThrows(Exception.class,  () -> matrix.getRow(5));
    }

    @Test
    void getCol() {
        Matrix matrix = new Matrix(4, 3);
        matrix.setCol(2, 1, 2, 3, 4);
        Matrix column = matrix.getCol(2);
        assertEquals(1, column.getValue(0, 0));
        assertEquals(2, column.getValue(1, 0));
        assertEquals(3, column.getValue(2, 0));
        assertEquals(4, column.getValue(3, 0));
    }

    @Test
    void getColException() {
        Matrix matrix = new Matrix(4, 3);
        assertThrows(Exception.class,  () -> matrix.getCol(3));
    }

    @Test
    void copy() {
        Matrix matrix = new Matrix(3, 5);
        matrix.setRow(0, 1, 2, 3, 4, 5);
        matrix.setRow(1, 6, 7, 8, 9, 1);
        matrix.setRow(2, 2, 3, 4, 5, 6);
        Matrix copy = new Matrix(matrix);
        assertEquals(matrix, copy);
    }

    @Test
    void add() {
        Matrix m1 = new Matrix(3, 3);
        m1.setRow(0, 2, 4, 6);
        m1.setRow(1, 1, 5, 9);
        m1.setRow(2, 8, 3, 5);
        Matrix m2 = new Matrix(3, 3);
        m2.setRow(0, 1, 4, -3);
        m2.setRow(1, 1, -4, 3);
        m2.setRow(2, -1, 4, 3);
        Matrix sum = m1.add(m2);
        assertSame(sum, m1);
        Matrix expected = new Matrix(3, 3);
        expected.setRow(0, 3, 8, 3);
        expected.setRow(1, 2, 1, 12);
        expected.setRow(2, 7, 7, 8);
        assertEquals(expected, sum);
    }

    @Test
    void addException() {
        Matrix m1 = new Matrix(3, 3);
        m1.setRow(0, 2, 4, 6);
        m1.setRow(1, 1, 5, 9);
        m1.setRow(2, 8, 3, 5);
        Matrix m2 = new Matrix(2, 3);
        m2.setRow(0, 1, 4, -3);
        m2.setRow(1, 1, -4, 3);
        assertThrows(Exception.class, () -> m1.add(m2));

    }

    @Test
    void multiplyByNumber() {
        Matrix m1 = new Matrix(3, 3);
        m1.setRow(0, 2, 4, 6);
        m1.setRow(1, 1, 5, 9);
        m1.setRow(2, 8, 3, 5);
        Matrix scaled = m1.multiply(2);
        assertSame(scaled, m1);
        Matrix expected = new Matrix(3, 3);
        expected.setRow(0, 4, 8, 12);
        expected.setRow(1, 2, 10, 18);
        expected.setRow(2, 16, 6, 10);
        assertEquals(expected, scaled);
    }

    @Test
    void transpose() {
        Matrix m1 = new Matrix(4, 3);
        m1.setRow(0, 2, 4, 6);
        m1.setRow(1, 1, 5, 9);
        m1.setRow(2, 8, 3, 5);
        m1.setRow(3, 6, 2, 1);
        Matrix transposed = m1.transpose();
        assertSame(transposed, m1);
        Matrix expected = new Matrix(3, 4);
        expected.setRow(0, 2, 1, 8, 6);
        expected.setRow(1, 4, 5, 3, 2);
        expected.setRow(2, 6, 9, 5, 1);
        assertEquals(expected, transposed);
    }

    @Test
    void multiplyMatrices() {
        Matrix m1 = new Matrix(3, 2);
        m1.setRow(0, 1, 1);
        m1.setRow(1, 0, 1);
        m1.setRow(2, 1, 0);
        Matrix m2 = new Matrix(2, 4);
        m2.setRow(0, 0, 0, 1, 1);
        m2.setRow(1, 1, 1, 0, 1);
        Matrix product = m1.multiply(m2);
        assertSame(product, m1);
        Matrix expected = new Matrix(3, 4);
        expected.setRow(0, 1, 1, 1, 2);
        expected.setRow(1, 1, 1, 0, 1);
        expected.setRow(2, 0, 0, 1, 1);
        assertEquals(expected, product);
    }


    @Test
    void multiplyMatricesException() {
        Matrix m1 = new Matrix(3, 2);
        m1.setRow(0, 1, 1);
        m1.setRow(1, 0, 1);
        m1.setRow(2, 1, 0);
        Matrix m2 = new Matrix(3, 3);
        m2.setRow(0, 0, 0, 1);
        m2.setRow(1, 1, 1, 0);
        m2.setRow(2, 0, 1, 0);
        assertThrows(Exception.class, () -> m1.multiply(m2));
    }

    @Test
    void createDiagonal() {
        Matrix diagonal = Matrix.createDiagonal(1, 2, 3, 4);
        Matrix expected = new Matrix(4, 4);
        expected.setValue(0, 0, 1);
        expected.setValue(1, 1, 2);
        expected.setValue(2, 2, 3);
        expected.setValue(3, 3, 4);
        assertEquals(expected, diagonal);
    }

    @Test
    void createUnit() {
        Matrix identity = Matrix.createUnit(4);
        Matrix expected = new Matrix(4, 4);
        expected.setValue(0, 0, 1);
        expected.setValue(1, 1, 1);
        expected.setValue(2, 2, 1);
        expected.setValue(3, 3, 1);
        assertEquals(expected, identity);
    }

    @Test
    void createUnitException() {
        assertThrows(Exception.class,  () -> Matrix.createUnit(-4));
    }

    @Test
    void createRowException() {
        assertThrows(Exception.class,  () -> Matrix.createRow(-1));
    }

    @Test
    void createColException() {
        assertThrows(Exception.class,() -> Matrix.createCol(-1));
    }

    @Test
    void invert() {
        Matrix matrix = new Matrix(3, 3);
        matrix.setRow(0, 1, 0, 1);
        matrix.setRow(1, 0, 1, 1);
        matrix.setRow(2, 1, 1, 0);
        Matrix expected = new Matrix(3, 3);
        expected.setRow(0, 0.5, -0.5, 0.5);
        expected.setRow(1, -0.5, 0.5, 0.5);
        expected.setRow(2, 0.5, 0.5, -0.5);
        assertEquals(expected, matrix.invert());
    }

    @Test
    void invertException() {
        Matrix matrix = new Matrix(4, 3);
        assertThrows(Exception.class,matrix::invert);
    }

    @Test
    void invertException2() {
        Matrix matrix = new Matrix(3, 3);
        matrix.setRow(0, 1, 2, 1);
        matrix.setRow(1, 2, 4, 2);
        matrix.setRow(2, 1, 1, 0);
        assertThrows(Exception.class,matrix::invert);
    }

}
