/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flexisaf.ses.tests;

import java.io.Serializable;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *
 * @author ushie
 */
public class TestRunner implements Serializable {

    public TestRunner() {

    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(UserAccountCreateTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println("++++++++++++++++++++++++++ [UserAccountCreateTest] Successful: " + result.wasSuccessful());
        
//        result = JUnitCore.runClasses(StudentCreateTest.class);
//
//        for (Failure failure : result.getFailures()) {
//            System.out.println(failure.toString());
//        }
//
//        System.out.println("++++++++++++++++++++++++++ [StudentCreateTest] Successful: " + result.wasSuccessful());
//        
//        result = JUnitCore.runClasses(StudentFindByFirstNameTest.class);
//
//        for (Failure failure : result.getFailures()) {
//            System.out.println(failure.toString());
//        }
//
//        System.out.println("++++++++++++++++++++++++++ [StudentFindByFirstNameTest] Successful: " + result.wasSuccessful());

        
        
//        TestSuite suite = new TestSuite(StudentCreateTest.class, StudentFindByFirstNameTest.class);
//        TestResult result = new TestResult();
//        suite.run(result);
//        System.out.println("Number of test cases = " + result.runCount());
    }

}
