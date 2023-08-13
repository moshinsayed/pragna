package com.pragna.project.model;

import lombok.Data;

@Data
public class WordPart{
    public int endIndex;
    public int beginIndex;
    public String part;
    public int charType;
}
