package nl.wisdelft.cf.acceptance.test.local.unit;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.unit.*;
import org.json.*;
import org.junit.*;

import java.io.*;

public class TestUnitDelete {

    @Ignore
    @org.junit.Test
    public void shouldDeleteUnits() throws IOException, JSONException
    {
        UnitController myUnitController = CrowdFlowerFactory.getUnitController();
        myUnitController.delete("207545","300144656" );
    }

}
