package nl.wisdelft.cf;

import com.google.common.collect.*;
import nl.wisdelft.cf.job.*;
import nl.wisdelft.cf.unit.*;
import nl.wisdelft.cf.weblayer.*;
import org.apache.http.*;
import org.json.*;

import java.io.*;
import java.util.*;

public class CrowdFlowerFactory {

    private static CrowdFlower theCrowdFlower = new CrowdFlowerImpl();

    public CrowdFlowerFactory()
    {
        //ignore
    }

    public static void setProperties(String aPath)
    {
        Properties myProperties = new Properties();
        try
        {
            myProperties.load(new FileInputStream(aPath));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        theCrowdFlower = new CrowdFlowerImpl(myProperties.getProperty("apiKey"));
    }

    public static UnitController getUnitController()
    {
        return theCrowdFlower.getUnitController();
    }

    public static JobController getJobController()
    {
        return theCrowdFlower.getJobController();
    }

    public WebCall createWebCall()
    {
        return new WebCall(createWebUtil());
    }

    public WebUtil createWebUtil()
    {
        return new WebUtil();
    }

    public List<NameValuePair> createAttributes()
    {
        return Lists.newArrayList();
    }

    public JSONObject createValues()
    {
        return new JSONObject();
    }

    public WebJobCall createWebJobCall()
    {
        return new WebJobCall(createWebUtil());
    }

    public WebJobsCall createWebJobsCall()
    {
        return new WebJobsCall(createWebUtil());
    }
}
