package nl.wisdelft.cf.unit;

public enum UnitAttribute {

    ID("id"), RESULTS("results"), CREATED_AT("created_at"), STATE("state"), DIFFICULTY(
            "difficulty"), JOB_ID("job_id"), AGREEMENT("agreement"), JUDGMENTS_COUNT(
            "judgments_count"), DATA("data"), UPDATED_AT("updated_at"), MISSED_COUNT(
            "missed_count");

    private String code;

    private UnitAttribute(String property)
    {
        code = property;
    }

    /* Smart way to return property names */
    public String toString()
    {
        return code;
    }

}
