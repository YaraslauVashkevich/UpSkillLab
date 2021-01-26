package com.epam.upskill;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class JunitTest {
    private ArrayList<Double> expected;
    private ArrayList<Double> actual;

    @Before
    public void createArrayListExpectedAndActual() {
        expected = new ArrayList();
        actual = new ArrayList();
    }

    @Test
    public void equationsShouldHaveTwoRoots() {
        expected.add(-5.0); // X1
        expected.add(3.0); // X2
        actual = Junit.equation(-1, -2, 15); // (a, b, c)
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void equationsShouldHaveOneRoots() {
        expected.add(-6.0); // X
        actual = Junit.equation(1, 12, 36); // (a, b, c)
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void equationsShouldNotHaveRoots() {
        expected.isEmpty(); // X
        actual = Junit.equation(5, 3, 7); // (a, b, c)
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void equationsShouldHaveTwoZerroInCoefficientBAndC() {
        expected.add(0.0); // X
        actual = Junit.equation(1, 0, 0); // (a, b, c)
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void equationsNotShouldHaveZerroInCoefficientA() {
        expected.isEmpty(); // X
        actual = Junit.equation(0, -2, 1); // (a, b, c)
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void equationsShouldHaveOneZerroInCoefficientC() {
        expected.add(0.0); // X1
        expected.add(7.0); // X2
        actual = Junit.equation(1, -7, 0); // (a, b, c)
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void equationsShouldHaveOneZerroInCoefficientBSquareXNegative() {
        expected.isEmpty(); // X
        actual = Junit.equation(5, 0, 30); // (a, b, c)
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void equationsShouldHaveOneZerroInCoefficientBSquareXPositive() {
        expected.add(1.5); // X1
        expected.add(-1.5); // X2
        actual = Junit.equation(4, 0, -9); // (a, b, c)
        Assert.assertEquals(expected, actual);
    }
}

