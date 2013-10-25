package nl.wisdelft.cf.acceptance.test.local.unit;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.unit.*;
import org.json.*;
import org.junit.*;

import java.io.*;

public class TestUnitUpdate {

    @Ignore
    @org.junit.Test
    public void shouldUpdateUnits() throws IOException, JSONException {
        UnitController myUnitController = CrowdFlowerFactory.getUnitController();

        Unit myUnit = new Unit();

        JSONObject json = new JSONObject();

        try
        {

            json.put("phone",
                     "12321");
            json.put("name",
                     "debarshi");
            json.put("company",
                     "asda");
            json.put("email",
                     "asdasd@asda.com");

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        myUnit.addProperty(UnitAttribute.DATA,
                         json.toString());
        myUnit.addProperty(UnitAttribute.JOB_ID, "279897");
        myUnit.addProperty(UnitAttribute.ID, "337282470" );

        myUnitController.update(myUnit);
    }

}
