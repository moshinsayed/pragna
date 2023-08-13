package com.pragna.project.model;

import lombok.Data;

@Data
public class Phoneme{
    public int pronunciation;
    public Span span;
    public String phoneme;
}
