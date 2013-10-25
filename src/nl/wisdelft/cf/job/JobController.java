package nl.wisdelft.cf.job;

import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.exception.*;
import nl.wisdelft.cf.order.*;

import org.json.*;

import java.util.*;

public interface JobController {

    public Job create(Job aJob);

    /**
     * [ web call ] Uploads data from the specified path with the specified
     * contentType For the given job id
     *
     * @param absolutePath
     * @param contentType
     * @throws NullAPIKeyException
     * @Web
     */
    public void upload(Job aJob,
                       String absolutePath,
                       String contentType)
            throws NullAPIKeyException;


    /**
     * [ web call ] Same as upload but with parameters
     * Possible parameters force
     *
     * @Web
     */
    public void upload(String aJobId,
                       String absolutePath,
                       String contentType,
                       Map<String, String> param);

    /**
     * [ Non-web call ] Sets an API key
     *
     * @param apiKey
     */
    public void setApiKey(String apiKey);

    /**
     * [ web call ] Fetches a list of units that belong to the job
     *
     * @return
     */
    public List<Unit> getJobUnits(String aJobId);

    /**
     * [ web call ] Get all the attributes for that given jobId
     *
     * @return
     * @throws NullAPIKeyException
     * @Web
     */

    public Job getJob(String aJobId) throws NullAPIKeyException;

    /**
     * Updates the Job with the properties set in the new Job Object
     * @throws NullAPIKeyException
     * @Web [ web call ] HTTP PUT operation on the job by id
     */

    public void update(Job aJob) throws NullAPIKeyException;

    /**
     * [ web call ] Uploads data from the specified rss feed in the Job specified
     *
     * @param url
     * @Web
     */

    public void upload(Job aJob, String url);

    /**
     * [ non-web call ] Obtains the uploading status of the units for the given jobId
     *
     * @return
     */

    public JSONObject getUnitsStatus(String aJobId);

    /**
     * @return TODO
     * @Web [ web call ] Creates an new order for the job
     */

    public OrderController order(String aJobId);

    /**
     * [ web call ] Pauses the job
     */

    public void pause(String aJobId);

    /**
     * @Web [ web call ] Resumes a paused job
     */

    public void resume(String aJobId);

    /**
     * @Web [ web call ]Cancels an on-going job
     */

    public void cancel(String aJobId);

    /**
     * @return
     * @Web [ web call ] Returns the status of the job
     */

    public String status(String aJobId);

    /**
     * [ web call ]  Deletes the JobController by id in crowdflower. This makes a http delete
     * call.
     *
     * @Web
     */

    public void delete(String aJobId);

    /**
     * [ web call ] Fetches all the channels both enabled and available
     *
     * @return
     * @Web
     */
    public Channel getChannels(String aJobId);

    /**
     * [ web call ] Sets a channel
     *
     * @param channels
     * @Web
     */
    public void setChannels(String aJobId, List<String> channels);

    // TODO
    // public void copy(String all_unit,String gold);

    // TODO
    public List<Judgment> getJudgments(String aJobId);

    /**
     * [ web call ] Splits the with and on parameters specified.
     *
     * @param aJobId
     * @param on
     * @param with
     */
    public void bulkSplit(
            String aJobId,
            String on,
            String with);

    /**
     * [ web call ] to fetch judgment by id
     *
     * @param aJobId
     * @param aJudgmentId
     * @return
     */
    public Judgment getJudgment(String aJobId, String aJudgmentId);

    /**
     * [ non web call ] Gets an instance of UnitController
     *
     * @return
     */
    public Unit getUnit(String aJobId, String aUnitId) throws JSONException;


    public void addRemoveGold(String aJobId, Map<String, String> param);

    public JSONObject legend(String aJobId);

    public void upload(
            Job aJob,
            String absolutePath,
            String contentType,
            boolean async) throws NullAPIKeyException;


    /**
     * [ non web call ] Sets pay for assignment
     *
     * @param pay
     */

    public void setPayPerAssignment(Job aJob, String pay);

}
