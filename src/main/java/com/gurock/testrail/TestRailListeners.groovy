package com.gurock.testrail

import org.json.simple.JSONObject
import org.testng.ITestContext
import org.testng.ITestListener
import org.testng.ITestResult

/**
 * Created by artyom on 16.10.17.
 */

class TestRailListeners extends TestRail implements ITestListener {

    @Override
    void onTestStart(ITestResult result) {
    }

    @Override
    void onTestSuccess(ITestResult result) {
        def caseId = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestCaseId.class).id()
        Map data = new HashMap()
        data.put("status_id", 1)
        data.put("comment", appender.getLogAsString())
        client.sendPost("add_result_for_case/$runId/$caseId", data)
    }

    @Override
    void onTestFailure(ITestResult result) {
        logger.debug(result.getThrowable().getMessage())
        //logger.debug("StackTrace " + result.getThrowable().getStackTrace().toString().replace(",", "\n"))

        def caseId = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestCaseId.class).id()
        Map data = new HashMap()
        data.put("status_id", 5)
        data.put("comment", appender.getLogAsString())
        client.sendPost("add_result_for_case/$runId/$caseId", data)
    }

    @Override
    void onTestSkipped(ITestResult result) {
        def caseId = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestCaseId.class).id()
        Map data = new HashMap()
        data.put("status_id", 2)
        data.put("comment", "This test worked fine!")
        client.sendPost("add_result_for_case/$runId/$caseId", data)
    }

    @Override
    void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    void onStart(ITestContext context) {

    }

    @Override
    void onFinish(ITestContext context) {

    }
}
