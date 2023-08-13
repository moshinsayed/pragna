package com.pragna.project.model;

import lombok.Data;

@Data
public class Audio{
    public int channel;
    public int sampleBytes;
    public int sampleRate;
    public String audioType;
}
