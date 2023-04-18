package tests.base;

import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

@Log4j2

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.printf("======================================== STARTING TEST %s ========================================%n", iTestResult.getName());
        log.info(format("========================= STARTING TEST %s =========================", iTestResult.getName()));
        // TODO start Test Run via API
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.printf("======================================== FINISHED TEST %s Duration: %ss ========================================%n", iTestResult.getName(),
                getExecutionTime(iTestResult));
        log.info(format("========================= FINISHED TEST %s Duration: %ss =========================", iTestResult.getName(), getExecutionTime(iTestResult)));
        // TODO note test case as automated in JIRA
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.printf("======================================== FAILED TEST %s Duration: %ss ========================================%n", iTestResult.getName(),
                getExecutionTime(iTestResult));
        log.info(format("========================= FAILED TEST %s Duration: %ss =========================", iTestResult.getName(), getExecutionTime(iTestResult)));
        // TODO make bug auto reporting
        log.error(iTestResult.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.printf("======================================== SKIPPING TEST %s ========================================%n", iTestResult.getName());
        log.info(format("========================= SKIPPING TEST %s =========================", iTestResult.getName()));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }

//    private byte[] takeScreenshot(ITestResult iTestResult) {
//        ITestContext context = iTestResult.getTestContext();
//        try {
//            WebDriver driver = (WebDriver) context.getAttribute("browser");
//            if (driver != null) {
//                return SFAPIUtils.takeScreenshot(driver);
//            } else {
//                return new byte[]{};
//            }
//        } catch (NoSuchSessionException | IllegalStateException ex) {
//            return new byte[]{};
//        }
//    }
}
