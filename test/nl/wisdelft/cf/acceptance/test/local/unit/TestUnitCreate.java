package nl.wisdelft.cf.acceptance.test.local.unit;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.unit.*;
import org.json.*;
import org.junit.*;

import java.io.*;

public class TestUnitCreate {

    /**
     * This example create a UnitController in given JobController
     *
     */

    @Ignore
    @org.junit.Test
    public void shouldCreateUnits() throws IOException, JSONException
    {
        UnitController myUnitController = CrowdFlowerFactory.getUnitController();

        JSONObject json = new JSONObject();

        try
        {

            json.put("phone",
                     "911");
            json.put("name",
                     "debarshi-basak");
            json.put("company",
                     "tudelft");
            json.put("email",
                     "asdasd@asda.com123");

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        Unit myUnit = new Unit();
        myUnit.addProperty(UnitAttribute.JOB_ID, "206783");
        myUnit.addProperty("data",
                         json.toString());
        Unit myUnitAfterCreation = myUnitController.create(myUnit);
        System.out.println(myUnitAfterCreation);

    }
}
