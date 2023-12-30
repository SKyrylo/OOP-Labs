package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ImmutableMatrixTest {

    @Test
    void setValueException() {
        Matrix matrix = new ImmutableMatrix(3, 4);
        assertThrows(Exception.class, () -> matrix.setValue(0, 0, 1));
    }

    @Test
    void setRowException() {
        Matrix matrix = new ImmutableMatrix(3, 4);
        assertThrows(Exception.class, () -> matrix.setRow(0, 1, 2, 3, 4));
    }

    @Test
    void setColException() {
        Matrix matrix = new ImmutableMatrix(3, 4);
        assertThrows(Exception.class, () -> matrix.setCol(0, 1, 2, 3));
    }

    @Test
    void add() {
        Matrix matrix = new Matrix(3, 3);
        matrix.setRow(0, 0, 1, 0);
        matrix.setRow(1, 1, 0, 1);
        matrix.setRow(2, 0, 1, 0);
        Matrix immutable = new ImmutableMatrix(Matrix.createUnit(3));
        Matrix result = immutable.add(matrix);
        assertNotSame(result, immutable);
        assertEquals(Matrix.createUnit(3), immutable);

        Matrix expectedResult = new Matrix(3, 3);
        expectedResult.setRow(0, 1, 1, 0);
        expectedResult.setRow(1, 1, 1, 1);
        expectedResult.setRow(2, 0, 1, 1);
        assertEquals(expectedResult, result);
    }

    @Test
    void multiplyByNumber() {
        Matrix immutable = new ImmutableMatrix(Matrix.createUnit(3));
        Matrix result = immutable.multiply(2.3);
        assertNotSame(result, immutable);
        assertEquals(Matrix.createUnit(3), immutable);

        Matrix expectedResult = new Matrix(3, 3);
        expectedResult.setValue(0, 0, 2.3);
        expectedResult.setValue(1, 1, 2.3);
        expectedResult.setValue(2, 2, 2.3);
        assertEquals(expectedResult, result);
    }

    @Test
    void multiplyByMatrix() {
        Matrix m1 = new Matrix(3, 2);
        m1.setRow(0, 1, 1);
        m1.setRow(1, 2, 0);
        m1.setRow(2, 0, 1);
        Matrix m2 = new Matrix(2, 4);
        m2.setRow(0, 1, 0, 1, 1);
        m2.setRow(1, 2, 0, 0, 1);

        Matrix immutable = new ImmutableMatrix(m1);
        Matrix result = immutable.multiply(m2);

        assertNotSame(result, immutable);
        assertEquals(m1, immutable);

        Matrix expectedResult = new Matrix(3, 4);
        expectedResult.setRow(0, 3, 0, 1, 2);
        expectedResult.setRow(1, 2, 0, 2, 2);
        expectedResult.setRow(2, 2, 0, 0, 1);
        assertEquals(expectedResult, result);
    }

    @Test
    void transpose() {
        Matrix m1 = new Matrix(3, 2);
        m1.setRow(0, 1, 1);
        m1.setRow(1, 2, 0);
        m1.setRow(2, 0, 1);

        Matrix immutable = new ImmutableMatrix(m1);
        Matrix result = immutable.transpose();

        assertNotSame(result, immutable);
        assertEquals(m1, immutable);

        Matrix expectedResult = new Matrix(2, 3);
        expectedResult.setRow(0, 1, 2, 0);
        expectedResult.setRow(1, 1, 0, 1);
        assertEquals(expectedResult, result);
    }

    @Test
    void invert() {
        Matrix m1 = new Matrix(3, 3);
        m1.setRow(0, 1, 1, 1);
        m1.setRow(1, 2, 0, 1);
        m1.setRow(2, 0, 1, 0);

        Matrix immutable = new ImmutableMatrix(m1);
        Matrix result = immutable.invert();

        assertNotSame(result, immutable);
        assertEquals(m1, immutable);

        Matrix expectedResult = new Matrix(3, 3);
        expectedResult.setRow(0, -1, 1, 1);
        expectedResult.setRow(1, 0, 0, 1);
        expectedResult.setRow(2, 2, -1, -2);
        assertEquals(expectedResult, result);
    }

}
