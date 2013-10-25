package nl.wisdelft.cf;

import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.job.*;
import nl.wisdelft.cf.judgment.*;
import org.json.*;

public class CrowdFlowerTestDataFactory {

    private CrowdFlowerTestDataFactory()
    {
      //ignore
    }

    public static String createUnitData()
    {
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

        return json.toString();
    }

    public static String createCML()
    {
      return "<p>Company{{company}}</p>" +
             "{{company}}" +
             "<cml:text validates='required' label='Text' class='' />";
    }

    public static String createInstruction()
    {
        return "nl.wisdelft.cf.acceptance.test-instruction";
    }

    public static String createPathToData()
    {
        return "log/crowd.csv";
    }

    public static String createApplicationType()
    {
        return "text/csv";
    }

    public static Job createSampleJob()
    {
        Job myJob = new Job();

        myJob.addProperty(
                JobAttribute.TITLE,
                "Full Scale Web Service Testing "
                + System.currentTimeMillis());
        myJob.addProperty("instructions", createInstruction());
        myJob.addProperty("cml", createCML());
        return myJob;
    }

    public static Judgment createJudgment()
    {
        Judgment myJudgment = new Judgment();
        myJudgment.addProperty(JudgAttribute.data,"nl.wisdelft.cf.acceptance.test-data");
        myJudgment.addProperty("id","1");
        return myJudgment;
    }
}
