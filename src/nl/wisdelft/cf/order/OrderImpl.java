package nl.wisdelft.cf.order;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.weblayer.*;
import org.apache.http.*;
import org.apache.http.message.*;
import org.fest.util.*;

import java.net.*;
import java.util.*;

public class OrderImpl implements Order {

    private String apiKey;
    private String url = "https://api.crowdflower.com/v1/jobs/";
    private String jobId;
    private List<NameValuePair> attributes;
    private WebUtil theWebUtil;
    private WebCall theWebCall;

    public OrderImpl(String jobId)
    {
       this(jobId, new CrowdFlowerFactory());
    }

    @VisibleForTesting
    OrderImpl(
            String aJobId,
            CrowdFlowerFactory aCrowdFlowerFactory)
    {
        jobId = aJobId;
        attributes = aCrowdFlowerFactory.createAttributes();
    }

    public OrderImpl(
            String jobId,
            String apiKey)
    {
        this(jobId,apiKey, new CrowdFlowerFactory());
    }

    @VisibleForTesting
    OrderImpl(
            String aJobId,
            String aApiKey,
            CrowdFlowerFactory aCrowdFlowerFactory)
    {
        jobId = aJobId;
        apiKey = aApiKey;
        attributes = aCrowdFlowerFactory.createAttributes();
        theWebUtil = aCrowdFlowerFactory.createWebUtil();
        theWebCall = aCrowdFlowerFactory.createWebCall();
        attributes.add(new BasicNameValuePair("key",
                                              aApiKey));
    }

    @Override
    public void create()
    {
        url = url + jobId + "/orders.json";
        theWebCall.create(url,
                       attributes);
    }

    @Override
    public String retrieve(String id)
    {
        try
        {
            url = url + jobId + "/" + "/orders/" + id + ".json?key=" + apiKey;
            return theWebUtil.urlReader(new URL(url));
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public void addChannel(String channel)
    {
        attributes.add(new BasicNameValuePair("channels[0]",
                                              channel));
    }

    @Override
    public void setDebitUnitCount(String count)
    {
        attributes.add(new BasicNameValuePair("debit[units_count]",
                                              count));
    }
}
