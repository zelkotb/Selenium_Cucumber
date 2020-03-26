$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("FillStudentForm.feature");
formatter.feature({
  "line": 1,
  "name": "fullfill student form if the student is authorized",
  "description": "",
  "id": "fullfill-student-form-if-the-student-is-authorized",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 4,
  "name": "fill the student form and submit",
  "description": "",
  "id": "fullfill-student-form-if-the-student-is-authorized;fill-the-student-form-and-submit",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 3,
      "name": "@important"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "navitate to login \"\u003curl\u003e\"",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "submit \"\u003cusername\u003e\" and \"\u003cpassword\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "user is logged in",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "user fill the form",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "user submit",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "pop up appear with successfull",
  "keyword": "Then "
});
formatter.examples({
  "line": 12,
  "name": "",
  "description": "",
  "id": "fullfill-student-form-if-the-student-is-authorized;fill-the-student-form-and-submit;",
  "rows": [
    {
      "cells": [
        "url",
        "username",
        "password"
      ],
      "line": 13,
      "id": "fullfill-student-form-if-the-student-is-authorized;fill-the-student-form-and-submit;;1"
    },
    {
      "cells": [
        "http://localhost:4200/login",
        "zelkotb",
        "mamamangepapa"
      ],
      "line": 14,
      "id": "fullfill-student-form-if-the-student-is-authorized;fill-the-student-form-and-submit;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 50207238600,
  "status": "passed"
});
formatter.scenario({
  "line": 14,
  "name": "fill the student form and submit",
  "description": "",
  "id": "fullfill-student-form-if-the-student-is-authorized;fill-the-student-form-and-submit;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 3,
      "name": "@important"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "navitate to login \"http://localhost:4200/login\"",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "submit \"zelkotb\" and \"mamamangepapa\"",
  "matchedColumns": [
    1,
    2
  ],
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "user is logged in",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "user fill the form",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "user submit",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "pop up appear with successfull",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "http://localhost:4200/login",
      "offset": 19
    }
  ],
  "location": "FillStudentForm.navitate_to_login(String)"
});
formatter.result({
  "duration": 11501462500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "zelkotb",
      "offset": 8
    },
    {
      "val": "mamamangepapa",
      "offset": 22
    }
  ],
  "location": "FillStudentForm.submit_and(String,String)"
});
formatter.result({
  "duration": 817372100,
  "status": "passed"
});
formatter.match({
  "location": "FillStudentForm.user_is_logged_in()"
});
formatter.result({
  "duration": 841858900,
  "error_message": "java.lang.AssertionError: expected [Student informations s] but found [Student informations]\r\n\tat org.testng.Assert.fail(Assert.java:94)\r\n\tat org.testng.Assert.failNotEquals(Assert.java:513)\r\n\tat org.testng.Assert.assertEqualsImpl(Assert.java:135)\r\n\tat org.testng.Assert.assertEquals(Assert.java:116)\r\n\tat org.testng.Assert.assertEquals(Assert.java:190)\r\n\tat org.testng.Assert.assertEquals(Assert.java:200)\r\n\tat steps.FillStudentForm.user_is_logged_in(FillStudentForm.java:31)\r\n\tat ✽.Then user is logged in(FillStudentForm.feature:7)\r\n",
  "status": "failed"
});
formatter.match({
  "location": "FillStudentForm.user_fill_the_form()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "FillStudentForm.user_submit()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "FillStudentForm.pop_up_appear_with_successfull()"
});
formatter.result({
  "status": "skipped"
});
formatter.embedding("image/png", "embedded0.png");
formatter.after({
  "duration": 1016714500,
  "status": "passed"
});
});