package Failedtestcases;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int maxtry = 1;

    int count =0;
    @Override
    public boolean retry(ITestResult result)
    {
        if(!result.isSuccess())
        {
            if(count<maxtry)
            {
                count ++;
                return true;
            }
        }
        return false;
    }
}
