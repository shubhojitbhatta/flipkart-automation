package net.com.testngrunner;

import net.com.datadriven.ExcelUtil;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestNGRunner {

    public static void generateXmlAndExecute() {

        List<XmlSuite> xmlSuites = new ArrayList<XmlSuite>();
        List<XmlTest> xmlTests = new ArrayList<XmlTest>();
        List<XmlClass> xmlClasses = new ArrayList<XmlClass>();
        List<XmlInclude> testMethods = new ArrayList<XmlInclude>();

        XmlSuite xmlSuite = new XmlSuite();
        xmlSuite.setName("Flipkart Suite");
        xmlSuites.add(xmlSuite);

        XmlTest xmlTest = new XmlTest(xmlSuite);
        xmlTest.setName("Flipkart Test");

        Map<String, String> browserParameter = new HashMap<String, String>();
        browserParameter.put("browser", "FF");
        xmlTest.setParameters(browserParameter);
        xmlTests.add(xmlTest);

        xmlSuite.setTests(xmlTests);

        XmlClass xmlClass = new XmlClass("net.com.test.TestFlipkart");
        xmlClasses.add(xmlClass);
        xmlTest.setClasses(xmlClasses);


        ExcelUtil excelUtil = new ExcelUtil(System.getProperty("user.dir") + "\\resources\\testcases.xlsx");

        for (int rowNumber = 0; rowNumber < excelUtil.getRowCount(); rowNumber++) {
            if (excelUtil.readRowData(rowNumber).get(1).equals("Y")) {
                XmlInclude testMethod = new XmlInclude(excelUtil.getCellData(rowNumber, 0));
                testMethods.add(testMethod);
            }
        }

        xmlClass.setIncludedMethods(testMethods);

        xmlSuite.addListener("net.com.testngrunner.Listeners");

        createXmlFile(xmlSuite);

        TestNG testRun = new TestNG();
        testRun.setXmlSuites(xmlSuites);
        testRun.run();
    }

    public static void createXmlFile(XmlSuite mSuite) {
        FileWriter writer;
        try {
            writer = new FileWriter(new File(System.getProperty("user.dir") + "\\resources\\customTestNG.xml"));
            writer.write(mSuite.toXml());
            writer.flush();
            writer.close();
            System.out.println(new File("customTestNG.xml").getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        TestNGRunner testNGRunner = new TestNGRunner();
        testNGRunner.generateXmlAndExecute();
    }
}
