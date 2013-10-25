package nl.wisdelft.cf.acceptance.test.local.unit;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.job.*;
import nl.wisdelft.cf.unit.*;
import org.json.*;
import org.junit.*;
import org.junit.Test;

import java.io.*;

public class TestGold {

    @Ignore
    @Test
    public void shouldAddGold() throws IOException, JSONException
    {
        JobController myJobController = CrowdFlowerFactory.getJobController();
        UnitController myUnitController = CrowdFlowerFactory.getUnitController();

        Unit myUnit = myJobController.getUnit("214524", "307457390");

        myUnitController.addGold(myUnit, "contains",
                                 "true",
                                 "asdsafafas");


    }
}
