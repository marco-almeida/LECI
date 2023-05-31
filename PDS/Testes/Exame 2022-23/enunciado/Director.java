public class Director {
    private Course degreeName;

    public Director (StudentAdm stud, Course degree) {
        this.degreeName = degree;
        // ...
    }

    public Course getCourseName() {
        return degreeName;
    }

}
