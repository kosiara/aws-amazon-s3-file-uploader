package com.bizbeam.s3.uploader.jcommander;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class JCommanderParams {

    @Parameter
    private List<String> parameters = new ArrayList<>();

    @Parameter(names = { "-int", "-interval" }, description = "Interval between calls in seconds")
    private Integer intervalSeconds = -1;


    public List<String> getParameters() {
        return parameters;
    }

    public Integer getIntervalSeconds() {
        return intervalSeconds;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public void setIntervalSeconds(Integer intervalSeconds) {
        this.intervalSeconds = intervalSeconds;
    }
}
