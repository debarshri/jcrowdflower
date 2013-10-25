package nl.wisdelft.cf.weblayer;

import nl.wisdelft.cf.exception.*;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.entity.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;
import org.json.*;
import org.slf4j.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class WebJobCall extends WebCall {

    /**
     * This layer translates the cURL call of CrowdFlower to HttpClient 4.x
     * calls, And provides Object Relationship Mapping between JSON object and
     * JCrowdFlower's Java object
     *
     * @author debarshi
     */

    private static Logger logger = LoggerFactory.getLogger(WebJobCall.class);

    public WebJobCall(final WebUtil aWebUtil)
    {
        super(aWebUtil);
    }


    /**
     * Opens an inputstream and reads the url GET
     *
     * @param URL
     * @return
     * @throws MalformedCrowdURLException
     */

    public  JSONObject getJob(String URL) throws MalformedCrowdURLException
    {
        JSONObject json = null;
        URL crowdFlower;


        try
        {
            crowdFlower = new URL(URL);

            json = new JSONObject(theWebUtil.urlReader(crowdFlower));

            return json;

        }
        catch (MalformedURLException e1)
        {

            throw new MalformedCrowdURLException(URL);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return json;

    }

    /**
     * POST operation Creates the job
     *
     * @param URL
     * @param attributes
     * @return
     */

    public  String createJob(
            String URL,
            List<NameValuePair> attributes)
    {

        return create(URL,
                            attributes);
    }

    /**
     * Uploads data
     *
     * @param absolutePath
     * @param URL
     * @param type
     */
    @SuppressWarnings("deprecation")
    public  void upload(
            String absolutePath,
            String URL,
            String type)
    {
        HttpClient httpClient = new DefaultHttpClient();

        try
        {
           HttpPost post = new HttpPost(URL);

            post.setEntity(new FileEntity(new File(absolutePath),
                                          type));

            logger.info("HTTP POST @ url - " + URL);

            HttpResponse response = httpClient.execute(post);

			/*
			 * There are two ways to do it
			 * Asynchronously and synchronously
			 * 
			 * This implementation is synchronous as
			 * it waits for the response for the outcome 
			 * of upload from the server
			 * 
			 */
            System.out.println(response.getEntity());

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Upload the uri specified in job[uri] property
     *
     * @param URL
     * @param attributes
     */
    public  void uploadURI(
            String URL,
            List<NameValuePair> attributes)
    {
        HttpClient httpClient = new DefaultHttpClient();

        try
        {
            String output = "";

            HttpPost post = new HttpPost(URL);
            post.setEntity(new UrlEncodedFormEntity(attributes));

            logger.info("HTTP POST @ url - " + URL);

            output = theWebUtil.readResponse(httpClient.execute(post));

            theWebUtil.trapException(output);

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (CrowdFlowerException e)
        {
            e.printStackTrace();
        }

    }


    // TODO Read the HttpResponse and throw appropriate exception

    public  JSONObject getUnits(String url)
    {
        JSONObject json = null;
        URL crowdFlower;
        try
        {
            crowdFlower = new URL(url);

            json = new JSONObject(theWebUtil.urlReader(crowdFlower));

            return json;

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        catch (MalformedURLException e1)
        {
            e1.printStackTrace();
        }
        return json;
    }
}
