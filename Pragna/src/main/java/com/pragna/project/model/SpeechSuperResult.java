package com.pragna.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpeechSuperResult {
	public String tokenId;
    public String dtLastResponse;
    public String recordId;
    public int eof;
    public Params params;
    public Result result;
    public String applicationId;
    public String refText;
  }