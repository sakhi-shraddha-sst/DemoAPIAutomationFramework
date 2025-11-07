package com.api.tests;

import java.util.HashMap;
import java.util.Map;

public class Test {

//    Given Nested Map as Map<String, Map<String, String>> consider <student name, <subject, marks>>
//     1. Calculate the average marks for the class for each subject
//2. Identify students with two or more 'N/A' scores


    public static void main(String[] args){


        Map<String, Map<String, String>> outerMap = new HashMap<>();

        Map<String, String> innerMap1 = new HashMap<>();
        innerMap1.put("Math","75");
        innerMap1.put("Science","85.15");
        innerMap1.put("English","62");
        outerMap.put("Rob",innerMap1);

        Map<String, String> innerMap2 = new HashMap<>();
        innerMap2.put("Math","80.55");
        innerMap2.put("Science","70");
        innerMap2.put("English","60");
        outerMap.put("Sam",innerMap2);

        Map<String, String> innerMap3 = new HashMap<>();
        innerMap3.put("Math","70");
        innerMap3.put("Science","N/A");
        innerMap3.put("English","N/A");
        outerMap.put("Tim",innerMap3);


        Map<String , Double> subjectTotal = new HashMap<>();
        Map<String , Integer> subjectCount = new HashMap<>();

        for (Map.Entry<String, Map<String, String>> entry : outerMap.entrySet()){
            Map<String, String> subjects = entry.getValue();
            for(Map.Entry<String,String> subjectEntry : subjects.entrySet()){
                String subject = subjectEntry.getKey();
                String score = subjectEntry.getValue();

                if(score.equals("N/A")){
                    double mark = Double.parseDouble(score);
                    subjectTotal.put(subject, subjectTotal.getOrDefault(subject, 0.0) + mark);
                    subjectCount.put(subject, subjectCount.getOrDefault(subject, 0) + 1);
                }
            }
        }

System.out.println("Average Marks Per Subject : ");
        for (String subject : subjectTotal.keySet()){
            double avg = subjectTotal.get(subject) / subjectCount.get(subject);
            System.out.println(subject + ": " + avg);
        }


        System.out.println("\nStudents with 2 or more N/A");
        for (Map.Entry<String, Map<String, String>> entry : outerMap.entrySet()){
            int naCount = 0;
            for(String score : entry.getValue().values()){
                if(score.equals("N/A")){
                    naCount++;
                }
            }
            if(naCount>=2){
                System.out.println(entry.getKey());
            }
        }
    }

}
