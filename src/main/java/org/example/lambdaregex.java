package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class lambdaregex {
    public static void main(String[] args) {
        List<String> sentences = Arrays.asList(
                "Hello, world!",
                "This is a sample sentence.",
                "Regex is awesome!"
        );

        // Replacing words matching a pattern using Lambda and Regex
        List<String> replacedSentences = sentences.stream()
                .map(sentence -> sentence.replaceAll("\\b\\w{5}\\b", "*****")) // Replace 5-letter words with *****
                .collect(Collectors.toList());

        System.out.println("Replaced Sentences: " + replacedSentences);
    }

}