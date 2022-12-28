package br.com.dbccompany.runners;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("br.com.dbccompany.tests")
public class TodosTestsRunner {
}
