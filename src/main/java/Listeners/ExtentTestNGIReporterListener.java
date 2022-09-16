/**
 * @Author Gladson Antony
 * @Date 10-Sep-2017
 * @Time 10:30:43 AM
 */
package Listeners;

import Utils.DriverUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExtentTestNGIReporterListener  implements ITestNGListener{
    public static final String OUTPUT_FOLDER = "./src/test/resources/Reports/";
    public static final String FILE_NAME = "Extent Report.html";
    public ExtentReports extent;
    public ExtentTest test;

    @SuppressWarnings("rawtypes")
    public void generateReport(List xmlSuites, List suites, String outputDirectory) {
        init();
        for (Object suite : suites) {
            Map result = ((ISuite) suite).getResults();

            for (Object res : result.values()) {
                ITestContext context = ((ISuiteResult) res).getTestContext();

                try {
                    buildTestNodes(context.getFailedTests(), Status.FAIL);
                    buildTestNodes(context.getSkippedTests(), Status.SKIP);
                    buildTestNodes(context.getPassedTests(), Status.PASS);
                } catch (Exception e) {
                }
            }
        }

        for (String s : Reporter.getOutput()){
            extent.addTestRunnerOutput(s);
        }


        extent.setSystemInfo("Author", "Abu Bakkar Shohag");
        extent.setSystemInfo("Browser", DriverUtils.initializeProperties().getProperty("browser"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("OS Version", System.getProperty("os.version"));
        extent.setSystemInfo("OS Architecture", System.getProperty("os.arch"));
        extent.setSystemInfo("OS Bit", System.getProperty("sun.arch.data.model"));
        extent.setSystemInfo("JAVA Version", System.getProperty("java.version"));
        try {
            extent.setSystemInfo("Host Name", InetAddress.getLocalHost().getHostName());
            extent.setSystemInfo("Host IP Address", InetAddress.getLocalHost().getHostAddress());
        } catch (Exception e) {
        }
        extent.flush();
    }

    private void init() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
        sparkReporter.config().setDocumentTitle("Extent Report_Abu_Bakkar_Shohag");
        sparkReporter.config().setReportName("Extent Report_Abu_Bakkar_Shohag");
        sparkReporter.config().setTimeStampFormat("HH:mm:ss");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setProtocol(Protocol.HTTPS);
        sparkReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setReportUsesManualConfiguration(true);
    }

    private void buildTestNodes(IResultMap tests, Status status) throws Exception {
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.createTest(result.getMethod().getMethodName());
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);
                if (result.getThrowable() != null) {
                    test.log(status, result.getThrowable().getMessage());
                } else {
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                }
                System.out.println("After Method Ran: " + result.getStatus());

                if (result.getStatus() == ITestResult.FAILURE) {
                    test.fail(result.getTestClass().getName() + "." + result.getMethod().getMethodName(),
                            MediaEntityBuilder.createScreenCaptureFromPath("Images/" + result.getTestClass().getName()
                                    + "." + result.getMethod().getMethodName() + ".png").build());

                    test.addScreenCaptureFromPath("Images/"
                            + result.getTestClass().getName()
                            + "." + result.getMethod().getMethodName() + ".png");
                    System.out.println("Creating screenshot: Images/" + result.getTestClass().getName()
                                    + "." + result.getMethod().getMethodName() + ".png");

                }
                test.getModel().setStartTime(getTime(result.getStartMillis()));
                test.getModel().setEndTime(getTime(result.getEndMillis()));
            }
        }
    }

//    @AfterMethod(alwaysRun = true)
//    void afterMethod(ITestResult result) throws Exception {
//        System.out.println("After Method Ran: " + result.getStatus());
//        Thread.sleep(2000);
//        if (result.getStatus() == ITestResult.FAILURE) {
//            saveFullPageScreenshot("./src/test/resources/Reports/Images/" + result.getTestClass().getName() + "."
//                    + result.getMethod().getMethodName() + ".png");
//        }
//    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 17);
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}