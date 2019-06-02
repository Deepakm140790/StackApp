package com.app.stack.businesslogic;

import org.junit.Test;

import static org.junit.Assert.*;

public class ComputerTest {
    private Computer computer;

    public ComputerTest() {
        computer = new Computer(100);
    }

    @Test
    public void isStackFullTest() {
        //Stack is full
        computer.setAddress(100);
        assertTrue(computer.isFull(computer.pointerAddress, computer.capacity));

        //Stack is not full
        computer.setAddress(50);
        assertFalse(computer.isFull(computer.pointerAddress, computer.capacity));
    }

    @Test
    public void isStackEmptyTest() {
        //Stack is empty
        computer.setAddress(0);
        assertTrue(computer.isEmpty(computer.pointerAddress));

        //Stack is not empty
        computer.setAddress(10);
        assertFalse(computer.isEmpty(computer.pointerAddress));
    }

    @Test
    public void popTest() {
        System.out.println("popTest");

        int element = 1490;
        computer.push(element, computer.pointerAddress, computer.capacity, computer.array);
        int actual = computer.pop(computer.pointerAddress, computer.array);
        assertEquals(element, actual);
        //Testing pointer address
        assertEquals(-1, computer.getPointerAddress());

        int element2 = 1590;
        computer.push(element2, computer.pointerAddress, computer.capacity, computer.array);
        int actual2 = computer.pop(computer.pointerAddress, computer.array);
        assertEquals(element2, actual2);
        //Testing pointer address
        assertEquals(-1, computer.getPointerAddress());

        //Testing for Underflow error. Execute pop().
        int actual3 = computer.pop(computer.pointerAddress, computer.array);
        assertEquals(-1, actual3);
    }

    @Test
    public void pushTest() {
        System.out.println("pushTest");
        int actual = computer.push(1490, computer.pointerAddress, computer.capacity, computer.array);
        assertEquals(0, actual);

        int actual2 = computer.push(1590, computer.pointerAddress, computer.capacity, computer.array);
        assertEquals(0, actual2);

        //Testing for Underflow error. So set the pointer to max address and execute push().
        computer.setAddress(100);
        int actual3 = computer.push(22, computer.pointerAddress, computer.capacity, computer.array);
        assertEquals(-1, actual3);
    }

    @Test
    public void multiplyTest() {
        System.out.println("multiplyTest");
        int expected = 8;
        int actual = computer.multiply(2, 4);
        assertEquals(expected, actual);
    }

    @Test
    public void setAddressTest() {
        System.out.println("setAddressTest");
        int expected = 50;
        int value = computer.setAddress(expected);
        //Check the return value
        assertEquals(0, value);
        //Check the pointer location
        assertEquals(expected, computer.getPointerAddress() + 1);

        int expected2 = 150;
        int value2 = computer.setAddress(expected2);
        //Check the return value
        assertEquals(-1, value2);
        //Check the pointer location
        assertEquals(50, computer.getPointerAddress() + 1);
    }
}