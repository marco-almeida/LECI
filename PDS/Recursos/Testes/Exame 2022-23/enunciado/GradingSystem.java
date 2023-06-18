import java.util.ArrayList;
import java.util.List;

// GradingSystem class
public class GradingSystem {
    List<StudentAdm> studentsAdm = new ArrayList<>();
    List<Professor> profes = new ArrayList<>();
    List<Director> directs = new ArrayList<>();


    // Add a student to the system
    // The director of student's course is added as Monitor
    public StudentAdm addStudent(Student student) {
        StudentAdm studentAdm = new StudentAdm(student);
        //... 
        return studentAdm;
    }   

    // Add a director to the system
    // The director is added as monitor of the students in the same course
    public void addDirector(Course course) {

        //...
    } 

    public void addProfessor(Professor professor) {
        profes.add(professor);
    }


    public List<Director> getDirectors() {
        return directs;
    }

    public List<Professor> getProfessors() {
        return profes;
    }

}