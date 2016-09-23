package listners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

/**
 * Created by Al on 23.09.2016.
 */
public class TestListner implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        System.out.println("[METHOD_STARTED] - " + iInvokedMethod.getTestMethod().getMethodName());
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        System.out.println(String.format("[METHOD_FINISHED] - %s >>> %s",
                iInvokedMethod.getTestMethod().getMethodName(), iTestResult.getStatus() == 1 ? "Success" : "Fail"));
    }
}
