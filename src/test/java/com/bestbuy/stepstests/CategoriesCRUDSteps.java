package com.bestbuy.stepstests;


import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class CategoriesCRUDSteps extends TestBase {

    static String name = "TestCategory" + TestUtils.getRandomValue();
    static String category = "10" + TestUtils.getRandomValue();
    static Object categoryId;

    @Steps
    C


}
