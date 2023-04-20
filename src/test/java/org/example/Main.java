package org.example;

import org.testng.TestNG;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //   Create object of TestNG Class
        TestNG runner = new TestNG();
        // Create a list of String
        List<String> xmlfile = new ArrayList<String>();

        // Add xml file which you have to execute
        xmlfile.add("C:\\Users\\Fi-User\\IdeaProjects\\Sathish\\testng.xml");

// now set xml file for execution
        runner.setTestSuites(xmlfile);

// finally execute the runner using run method
        runner.run();

    }
}