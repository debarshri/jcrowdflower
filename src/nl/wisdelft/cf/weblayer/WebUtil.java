package nl.wisdelft.cf.weblayer;

import com.google.common.collect.*;
import nl.wisdelft.cf.exception.*;
import org.apache.http.*;
import org.apache.http.message.*;
import org.json.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class WebUtil {

    public String urlReader(URL crowdFlower)
    {
        String jsonInput = "";

        try
        {
            URLConnection yc = crowdFlower.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null)
            {
                jsonInput = jsonInput + inputLine;
            }

            in.close();

            trapException(jsonInput);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (CrowdFlowerException e)
        {
            e.printStackTrace();
        }

        return jsonInput;
    }

    public void trapException(String output) throws CrowdFlowerException
    {

        try
        {
            JSONObject error = new JSONObject(output);

            if (error.has("error"))
            {
                throw new CrowdFlowerException(error.get("error").toString());
            }

        }
        catch (JSONException e)
        {
            //ignore
        }
    }

    public void trapException(int output) throws CrowdFlowerException
    {
        if ((output != 200) && (output != 202))
        {
            throw new CrowdFlowerException(String.format("Did not receive correct response code. Received %s", output));
        }
    }

    public String urlTransform(
            String baseurl,
            Map<String, String> param)
    {
        String url = baseurl;

        for (String key : param.keySet())
        {
            url = url + "&" + key + "=" + param.get(key);
        }

        System.out.println(url);
        return url;
    }

    public String urlTransform(
            String baseurl,
            String morph)
    {
        return String.format("%s%s", baseurl, morph);
    }

    public String readResponse(HttpResponse response)
    {
        String output = "";

        HttpEntity entity = response.getEntity();

        try
        {
            trapException(response.getStatusLine().getStatusCode());
        }
        catch (CrowdFlowerException e1)
        {
            e1.printStackTrace();
        }

        InputStream instream;
        try
        {
            instream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    instream));

            // do something useful with the response
            output = output + reader.readLine();
            instream.close();
        }
        catch (IllegalStateException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return output;
    }


    public static List<NameValuePair> convertAttributesToNameValuePair(Map<String, String> aAttributes)
    {
        List<NameValuePair> myNameValuePairs = Lists.newArrayList();

        for (Map.Entry<String, String> myEntry : aAttributes.entrySet())
        {
            myNameValuePairs.add(new BasicNameValuePair(myEntry.getKey(), myEntry.getValue()));
        }
        return myNameValuePairs;
    }


}
