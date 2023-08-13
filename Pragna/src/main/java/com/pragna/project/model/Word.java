package com.pragna.project.model; 
import java.util.ArrayList;

import lombok.Data; 

@Data
public class Word{
    public String word;
    public ArrayList<Phoneme> phonemes;
    public Span span;
    public ArrayList<WordPart> word_parts;
    public Pause pause;
    public ArrayList<Phonic> phonics;
    public Scores scores;
    public int charType;
}
