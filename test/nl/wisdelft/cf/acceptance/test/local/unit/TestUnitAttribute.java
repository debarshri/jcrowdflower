package nl.wisdelft.cf.acceptance.test.local.unit;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.unit.*;
import org.json.*;
import org.junit.*;

import java.io.*;

public class TestUnitAttribute {

    @Ignore
    @org.junit.Test
    public void shouldAddAttributesToUnits() throws IOException, JSONException
    {
        UnitController myUnitController = CrowdFlowerFactory.getUnitController();
        Unit myUnit = myUnitController.getUnit("206783", "299087977");
        System.out.println(myUnit.getAttribute(UnitAttribute.JUDGMENTS_COUNT));
    }
}
