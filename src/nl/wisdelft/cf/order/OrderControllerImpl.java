package nl.wisdelft.cf.order;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.job.*;
import nl.wisdelft.cf.weblayer.*;
import org.apache.http.*;
import org.apache.http.message.*;
import org.fest.util.*;

import java.net.*;
import java.util.*;

import static nl.wisdelft.cf.weblayer.WebUtil.convertAttributesToNameValuePair;

public class OrderControllerImpl implements OrderController {

    private String apiKey;
    private String url = "https://api.crowdflower.com/v1/jobs/";
    private String jobId;
    private WebUtil theWebUtil;
    private WebCall theWebCall;

    public OrderControllerImpl(String aApiKey)
    {
       this(aApiKey, new CrowdFlowerFactory());
    }

    @VisibleForTesting OrderControllerImpl(
            String aApiKey,
            CrowdFlowerFactory aCrowdFlowerFactory)
    {
        apiKey = aApiKey;
        theWebCall = aCrowdFlowerFactory.createWebCall();
        theWebUtil = aCrowdFlowerFactory.createWebUtil();
    }

    @Override public void create(final Order aOrder)
    {
        url = url + aOrder.getJobId() + "/orders.json?key="+apiKey;
        String myOrder = theWebCall.create(url,
                                     convertAttributesToNameValuePair(aOrder.getAttributes()));

        System.out.println(myOrder);
    }

    @Override
    public String retrieve(String aJobId)
    {
        try
        {
            url = url + jobId + "/" + "/orders/" + aJobId + ".json?key=" + apiKey;
            return theWebUtil.urlReader(new URL(url));
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

        return null;

    }

    @Override public void pause(final String aJobId)
    {
        JobController myJobController = CrowdFlowerFactory.getJobController();
        myJobController.pause(aJobId);
    }

    @Override public void resume(final String aJobId)
    {
        JobController myJobController = CrowdFlowerFactory.getJobController();
        myJobController.resume(aJobId);
    }

    @Override
    public void cancel(final String aJobId)
    {
        JobController myJobController = CrowdFlowerFactory.getJobController();
        myJobController.cancel(aJobId);
    }
}
