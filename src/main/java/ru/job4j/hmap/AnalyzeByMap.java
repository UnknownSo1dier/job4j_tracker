package ru.job4j.hmap;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double score = 0D;
        int count = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                score += subject.score();
                count++;
            }
        }
        return score / count;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> averageByPupil = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double score = 0D;
            int count = pupil.subjects().size();
            for (Subject subject : pupil.subjects()) {
                score += subject.score();
            }
            averageByPupil.add(new Label(pupil.name(), score / count));
        }
        return averageByPupil;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        List<Label> averageBySubject = new ArrayList<>();
        Map<String, Integer> temp = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                temp.merge(subject.name(), subject.score(), Integer::sum);
            }
        }
        for (Map.Entry<String, Integer> entry : temp.entrySet()) {
            averageBySubject.add(new Label(entry.getKey(),
                    entry.getValue() / pupils.size()));
        }
        return averageBySubject;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> averageByPupil = new ArrayList<>();
        for (Pupil pupil : pupils) {
            int score = 0;
            for (Subject subject : pupil.subjects()) {
                score += subject.score();
            }
            averageByPupil.add(new Label(pupil.name(), score));
        }
        Collections.sort(averageByPupil);
        return averageByPupil.get(pupils.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        List<Label> averageBySubject = new ArrayList<>();
        Map<String, Integer> temp = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                temp.merge(subject.name(), subject.score(), Integer::sum);
            }
        }
        for (Map.Entry<String, Integer> entry : temp.entrySet()) {
            averageBySubject.add(new Label(entry.getKey(), entry.getValue()));
        }
        Collections.sort(averageBySubject);
        return averageBySubject.get(pupils.size() - 1);
    }
}