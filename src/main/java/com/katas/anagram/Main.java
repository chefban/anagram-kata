package com.katas.anagram;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> wordList = readWordList("src/main/resources/words.txt");
        List<String> anagrams = generateTwoWordAnagrams("documenting", wordList);
        System.out.println(wordList.size());

        for (String anagram : anagrams) {
            System.out.println("Anagram= "+anagram);
        }

        System.out.println("Anagram numbers= "+anagrams.size());
    }

    private static List<String> readWordList(String filePath) {
        List<String> wordList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                for(String word : Arrays.stream(line.trim().split(" ")).toList()){
                    wordList.add(word);
                }
            }
            Iterator<String> wordItarator =  wordList.iterator();
            while (wordItarator.hasNext()){
                String element = wordItarator.next();
                if ("".equals(element)) {
                    wordItarator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }

    private static List<String> generateTwoWordAnagrams(String input, List<String> wordList) {
        List<String> anagrams = new ArrayList<>();
        char[] inputChars = input.toCharArray();
        Arrays.sort(inputChars);

        for (String word : wordList) {
            char[] wordChars = word.toCharArray();
            Arrays.sort(wordChars);
            int k = 0;
            boolean isAnagram = true;
            while(k < wordChars.length){
                if(!isPresent(wordChars[k], inputChars))
                    isAnagram = false;
                k++;
            }
            if(isAnagram)
                anagrams.add(word);
        }
        return anagrams;
    }

    private static boolean isPresent(char caractere, char[] tab) {
        for (char c : tab) {
            if (c == caractere) {
                return true;
            }
        }
        return false;
    }

}