package nl.wisdelft.cf.weblayer;


import com.google.common.collect.*;
import nl.wisdelft.cf.exception.*;
import nl.wisdelft.cf.unit.*;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.entity.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.json.*;
import org.slf4j.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class WebCall {

    protected WebUtil theWebUtil;

    public WebCall(WebUtil aWebUtil)
    {
        theWebUtil = aWebUtil;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UnitControllerImpl.class);


    @Web
    public String get(String url)
    {
        HttpClient httpClient = new DefaultHttpClient();
        String output = "";

        HttpGet get = new HttpGet(url);

        try
        {
            LOGGER.info("HTTP GET @ url - {}",
                        url);

            output = theWebUtil.readResponse(httpClient.execute(get));
            theWebUtil.trapException(output);

        }
        catch (ClientProtocolException e)
        {
            LOGGER.error(e.toString());
        }
        catch (IOException e)
        {
            LOGGER.error(e.toString());
        }
        catch (CrowdFlowerException e)
        {
            LOGGER.error(e.toString());
        }

        return output;
    }

    public String create(
            String url,
            List<NameValuePair> attributes)
    {
        HttpClient httpClient = new DefaultHttpClient();

        String output = "";

        try
        {
            HttpPost post = new HttpPost(url);
            post.setEntity(new UrlEncodedFormEntity(attributes));

            LOGGER.info("HTTP POST @ url - {}",
                        url);

            output = theWebUtil.readResponse(httpClient.execute(post));
            theWebUtil.trapException(output);

        }
        catch (MalformedURLException e)
        {
            LOGGER.error(e.toString());
        }
        catch (IOException e)
        {
            LOGGER.error(e.toString());
        }
        catch (CrowdFlowerException e)
        {
            LOGGER.error(e.toString());
        }

        return output;
    }

    public void update(
            String url,
           Collection<NameValuePair> attributes)
    {
        try
        {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPut put = new HttpPut(url);
            put.setEntity(new UrlEncodedFormEntity(Lists.newArrayList(attributes)));

            LOGGER.info("HTTP PUT @ url - " + url);

            theWebUtil.readResponse(httpClient.execute(put));

        }
        catch (ClientProtocolException e)
        {
            LOGGER.error(e.toString());
        }
        catch (IOException e)
        {
            LOGGER.error(e.toString());
        }

    }

    public void put(String url)
    {
        HttpClient httpClient = new DefaultHttpClient();

        try
        {

            HttpPut put = new HttpPut(url);

            LOGGER.info("HTTP PUT @ url - " + url);

            System.out.println(httpClient.execute(put));

        }
        catch (ClientProtocolException e)
        {
            LOGGER.error(e.toString());
        }
        catch (IOException e)
        {
            LOGGER.error(e.toString());
        }

    }

    public JSONObject getMeta(String url) throws MalformedCrowdURLException
    {
        URL crowdFlower;

        try
        {
            crowdFlower = new URL(url);

            LOGGER.info("Reading url - " + url);

            return new JSONObject(theWebUtil.urlReader(crowdFlower));
        }
        catch (MalformedURLException e1)
        {

            throw new MalformedCrowdURLException(url);
        }
        catch (JSONException e)
        {
            LOGGER.error(e.toString());

        }
        return null;

    }

    public void delete(String url)
    {
        HttpClient httpClient = new DefaultHttpClient();
        try
        {
            String output;
            HttpDelete delete = new HttpDelete(url);

            LOGGER.info("HTTP DELETE @ url - " + url);


            HttpResponse response = httpClient.execute(delete);
            output = theWebUtil.readResponse(response);
            theWebUtil.trapException(output);
        }
        catch (MalformedURLException e)
        {
            LOGGER.error(e.toString());
        }
        catch (IOException e)
        {
            LOGGER.error(e.toString());
        }
        catch (CrowdFlowerException e)
        {
            LOGGER.error(e.toString());
        }
    }
}
