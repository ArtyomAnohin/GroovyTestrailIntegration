package com.gurock.testrail

import groovy.json.JsonSlurper
import org.apache.log4j.Logger
import org.json.simple.JSONObject
import org.testng.Assert
import org.testng.annotations.AfterClass
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeClass
import org.testng.annotations.BeforeTest

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

class TestRail {
    static APIClient client
    static Integer runId
    static def caseIds
    static def assignedToId
    static Logger logger
    static CustomAppender appender

    static {
        def json = new File(getClass().getResource('/TestRailConfig.json').toURI())
        def testrailConfig = new JsonSlurper().parse(json)
        client = new APIClient(testrailConfig.url)
        client.setUser(testrailConfig.username)
        client.setPassword(testrailConfig.password)
        caseIds = testrailConfig.testcaseIds
        assignedToId = testrailConfig.assignedToId
    }

    @BeforeClass
    void setupRun() {
        createTestRun()
    }

    @BeforeTest
    void startLog() {
        logger = Logger.getLogger("new logger")

        appender = new CustomAppender()
        logger.addAppender(appender)

    }

    @AfterTest
    void endLog() {
        logger.removeAllAppenders()
    }

    @AfterClass
    void closeRun() {
        closeTestRun()
    }

    /**
     * projectId - The ID of the project the test run should be added to
     * @return runId - The ID of the test run
     */
    def createTestRun() {

        def projectId = 1
        Map data = new HashMap();
        data.put("name", "AutoRun " + new Date().format("MM/dd/yyyy - HH:mm:ss", TimeZone.getTimeZone('UTC')))
        data.put("description", "This ia autotest run.")
        data.put("assignedto_id", assignedToId)
        data.put("include_all", false)
        data.put("case_ids", caseIds)
        JSONObject response = (JSONObject) client.sendPost("/add_run/$projectId", data)
        runId = (Integer) response.get("id")
    }

    /**
     * runId - The ID of the test run
     */
    def closeTestRun() {
        client.sendPost("/close_run/$runId", "")
    }


}

@Retention(RetentionPolicy.RUNTIME)
@interface TestCaseId {
    int id()
}