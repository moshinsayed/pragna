package com.pragna.project.model;

import java.util.ArrayList;

import lombok.Data;

@Data
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