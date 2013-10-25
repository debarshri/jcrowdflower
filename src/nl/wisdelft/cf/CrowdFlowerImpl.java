package nl.wisdelft.cf;

import com.google.common.collect.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.job.*;
import nl.wisdelft.cf.unit.*;
import org.json.*;
import org.slf4j.*;

import java.io.*;
import java.util.*;

public class CrowdFlowerImpl implements CrowdFlower {

    /**
     * BuilderJobs fetches the list of Jobs in -Your Jobs-
     */

    private List<JobController> theJobControllerList;
    private static String API_KEY;
    private final static String URL = "https://api.crowdflower.com/v1/";
    private Logger logger = LoggerFactory.getLogger(CrowdFlowerImpl.class);
    private CrowdFlowerFactory theCrowdFlowerFactory = new CrowdFlowerFactory();

    /**
     *
     * When we do not specify any parameter in the constructor,
     * It takes default properties file
     *
     */

    public CrowdFlowerImpl()
    {
        theJobControllerList = Lists.newArrayList();
        Properties myProperties = new Properties();
        try
        {
            myProperties.load(new FileInputStream("default.properties"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        API_KEY = myProperties.getProperty("apiKey");

    }

    /**
     * This constructor consumes a Path to the properties file
     * and picks up corresponding API key
     *
     * @param aPropertiesPath
     */

    public CrowdFlowerImpl(Properties aPropertiesPath)
    {
        theJobControllerList = Lists.newArrayList();
        API_KEY = aPropertiesPath.getProperty("apiKey");
    }

    /**
     *
     * This constructor takes API String and assigns it to the Field,
     * Does not require any path to properties file
     *
     * @param aAPIKey
     */
    public CrowdFlowerImpl(String aAPIKey)
    {
        theJobControllerList = Lists.newArrayList();
        API_KEY = aAPIKey;
    }

    /**
     * This methods returns list of jobs that you have submitted so far. Since
     * it is a list, we can iterate and fetch individual jobs
     */
    @Override
    public List<Job> getJobs()
    {
        try
        {
            JSONObject json;
            List<Job> myJobs = Lists.newArrayList();

            // Augment the URL
            String augUrl = theCrowdFlowerFactory.createWebUtil()
                                                 .urlTransform(URL,"jobs.json?key=" + API_KEY);

            logger.debug("Web call @ URL - " + augUrl);

            // Fetch the array of all the jobs
            JSONArray jsonArray = theCrowdFlowerFactory.createWebJobsCall()
                                                       .getJobs(augUrl);

            logger.info("Creating list of Jobs");


            for (int i = 0; i < jsonArray.length(); i++)
            {
                json = jsonArray.getJSONObject(i);

                myJobs.add(new Job(json));

                logger.info("{} Job added ",i);
            }

            return myJobs;

        }
        catch (JSONException e)
        {
            logger.error(e.toString());
        }
        return null;
    }

    @Override
    public String getApiKey()
    {
        return API_KEY;
    }

    @Override
    public JobController getJobController()
    {

        return new JobControllerImpl(API_KEY);
    }

    @Override
    public UnitController getUnitController()
    {

        return new UnitControllerImpl(API_KEY);
    }

    @Override
    public Account getAccount()
    {

        Account myAccount = new Account(API_KEY,
                                        Maps.<String, String>newHashMap(),
                                        theCrowdFlowerFactory);
        myAccount.refresh();
        return myAccount;
    }
}
