package nl.wisdelft.cf.weblayer;

import org.json.*;

import java.io.*;
import java.net.*;

public class WebJobsCall extends WebCall {

    public WebJobsCall(final WebUtil aWebUtil)
    {
        super(aWebUtil);
    }

    public JSONArray getJobs(String URL)
    {
        JSONArray json = null;
        URL crowdFlower;

        try
        {
            crowdFlower = new URL(URL);
            URLConnection yc = crowdFlower.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String jsonInput = "";
            String inputLine;
            while ((inputLine = in.readLine()) != null)
            {
                jsonInput = jsonInput + inputLine;
            }
            in.close();

            json = new JSONArray(jsonInput);

        }
        catch (MalformedURLException e1)
        {
            e1.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return json;

    }

}
