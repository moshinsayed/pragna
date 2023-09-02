package com.pragna.project.model; 
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result{
    public int pronunciation;
    public ArrayList<Word> words;
    public int fluency;
    public int rhythm;
    public String rear_tone;
    public int overall;
    public String resource_version;
    public String oov;
    public int speed;
    public String duration;
    public int integrity;
    public int pause_count;
    public String kernel_version;
}
