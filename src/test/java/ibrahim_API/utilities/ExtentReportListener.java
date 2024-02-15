package ibrahim_API.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

public class ExtentReportListener implements ITestListener, IAnnotationTransformer {

    private static ExtentReports extent = new ExtentReports();
    private static ExtentTest test;
    private SoftAssert softAssert = new SoftAssert();


    public void onTestStart(ITestResult result) {

        String testName = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName();

        if (testName.equals("")) {
            test = extent.createTest("Method name: " + result.getName());
        } else {
            test = extent.createTest(testName);
        }
    }

    public void onTestSuccess(ITestResult tr) {

        test.log(Status.PASS, "Test passed successfully");


    }

    public void onTestFailure(ITestResult tr) {
        Throwable throwable = tr.getThrowable();
        String errorMessage = throwable.getMessage();
        List<String> failedAssertions = Arrays.asList(errorMessage.split(","));
        System.out.println("Failed assertions: " + failedAssertions);

        for (int i = 0; i < failedAssertions.size(); i++) {
            if (failedAssertions.get(i).contains("expected")) {
                test.log(Status.FAIL, failedAssertions.get(i));
            } else {
                test.log(Status.FAIL, "Test failed");
                return;
            }
        }


    }


    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test skipped");
    }

    public void onFinish(ITestContext context) {
        softAssert.assertAll();
        ExtentSparkReporter spark = new ExtentSparkReporter("user/build/name/test.xml");
        extent.attachReporter(spark);
        extent.flush();
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        IAnnotationTransformer.super.transform(annotation, testClass, testConstructor, testMethod);
    }

    @Override
    public void transform(IConfigurationAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        IAnnotationTransformer.super.transform(annotation, testClass, testConstructor, testMethod);
    }

    @Override
    public void transform(IDataProviderAnnotation annotation, Method method) {
        IAnnotationTransformer.super.transform(annotation, method);
    }

    @Override
    public void transform(IFactoryAnnotation annotation, Method method) {
        IAnnotationTransformer.super.transform(annotation, method);
        Parameter[] parameters = method.getParameters();
        System.out.println("aaaaaaaaa");
        System.out.println(parameters[0].getName());
    }

    @Override
    public void transform(IListenersAnnotation annotation, Class<?> testClass) {
        IAnnotationTransformer.super.transform(annotation, testClass);
    }
}
