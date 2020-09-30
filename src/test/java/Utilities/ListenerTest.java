package Utilities;

import Commons.BaseClass;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class ListenerTest extends BaseClass implements ITestListener
{
    @Override
    public void onFinish(ITestContext Result)
    {

    }

    @Override
    public void onStart(ITestContext Result)
    {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult Result)
    {

    }

    // When Test case gets failed, this method is called.
    @Override
    public void onTestFailure(ITestResult Result)
    {
        try
        {
            String screens = BaseClass.takeScreenshot(driver, Result.getName());
            test.fail("Failed Test case ScreenShot", MediaEntityBuilder.createScreenCaptureFromPath(screens).build());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // When Test case gets Skipped, this method is called.
    @Override
    public void onTestSkipped(ITestResult Result)
    {
        //test.log(Status.INFO, "Test case was skipped");
    }

    // When Test case gets Started, this method is called.
    @Override
    public void onTestStart(ITestResult Result)
    {
        //test.log(Status.INFO, "Test case has commenced execution");
    }

    // When Test case gets passed, this method is called.
    @Override
    public void onTestSuccess(ITestResult Result)
    {
            try
            {
                String screens = BaseClass.takeScreenshot(driver, Result.getName());
                test.pass("Successful Test case ScreenShot", MediaEntityBuilder.createScreenCaptureFromPath(screens).build());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

    }
}
