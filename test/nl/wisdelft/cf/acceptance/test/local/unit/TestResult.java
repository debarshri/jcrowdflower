package nl.wisdelft.cf.acceptance.test.local.unit;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.unit.*;
import org.json.*;
import org.junit.*;

import java.io.*;

public class TestResult {

    @Ignore
    @org.junit.Test
    public void shouldAddResult() throws IOException, JSONException
    {
        CrowdFlower myCrowdFlower = new CrowdFlowerImpl();
        UnitController myUnitController = myCrowdFlower.getUnitController();

        Unit myUnit = myUnitController.getUnit("206783",
                                               "299087977");
        Result result = new Result(myUnit.getAttribute(UnitAttribute.RESULTS));
        System.out.println(result.getJudgments());
    }

}
