package org.design;


import org.junit.Test;

import java.io.*;
import java.util.Scanner;

public class AppTest {

    @Test
    public void testCase1() throws IOException {
        testHelper("test-case1/input.txt", "test-case1/output.txt");
    }

    @Test
    public void testCase2() throws IOException {
        testHelper("test-case2/input.txt", "test-case2/output.txt");
    }

    private void testHelper(String inputFilePath, String expectedOutputFilePath) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(inputFilePath);
        PipedOutputStream outputStream = new PipedOutputStream();
//        System.setOut(new PrintStream(outputStream));
//        Scanner actualOutputScanner = new Scanner(new PipedInputStream(outputStream));
        App.run(inputStream);
        InputStream expectedOutputStream = getClass().getClassLoader().getResourceAsStream(expectedOutputFilePath);
        Scanner expectedOutputScanner = new Scanner(expectedOutputStream);

//        while(actualOutputScanner.hasNext() && expectedOutputScanner.hasNext())  {
//            //do something
//        }
    }
}
