public class StudentAdm {
    private Student student;

    public StudentAdm(Student s) {
        this.student = s;
    }

    public void addScore(String className, double score) {
        student.addScore(className, (Double) score);
        // ...
    }
    public Student getStudent() {
        return student;
    }
    
}








