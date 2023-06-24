package pds2023;

import java.util.LinkedHashMap;
import java.util.Map;

public class StudentAdm {
    private Student student;
    private Map<Monitor, Boolean> knownMonitors;

    public StudentAdm(Student s) {
        this.student = s;
        knownMonitors = new LinkedHashMap<>();
    }

    public void addScore(String className, double score) {
        student.addScore(className, (Double) score);
        awakeTeachers(className);
        notifyMonitors(score);
    }

    private void awakeTeachers(String className) {
        for (Monitor m : knownMonitors.keySet()) {
            if (m instanceof Professor) {
                if (((Professor) m).getClassName().equals(className)) {
                    knownMonitors.put(m, true);
                }
            }
        }
    }

    private void notifyMonitors(double score) {
        for (Monitor m : knownMonitors.keySet()) {
            if (m instanceof Director) {
                m.update(this.getStudent().getOverallGrade());
            } else {
                if (Boolean.TRUE.equals(knownMonitors.get(m))) {
                    m.update(this.getStudent().getScore(((Professor) m).getClassName()));
                }
            }
        }
    }

    public void subscribe(Monitor m) {
        boolean b = m instanceof Director;
        knownMonitors.put(m, b);
    }

    public Student getStudent() {
        return student;
    }

}








