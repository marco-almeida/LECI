package pds2023;

import java.util.Map;
import java.util.HashMap;

public class Student {
    private String name;
    private int id;
    private Course course;
    private Map<String, Double>  score = new HashMap<>();

    public Student(String name, int id, Course course) {
        this.name = name;
        this.id = id;
        this.course = course;
    }

    public double getOverallGrade() {
        double total = 0.0;
        for (Double v : score.values()) {
            total += v;
        }
        return total / score.size();
    }

    public void addScore(String className, Double score) {
        this.score.put(className, score);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }
    
    public Double getScore(String className) {
        return score.get(className);
    }

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }

}
