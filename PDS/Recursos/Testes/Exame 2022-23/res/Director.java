package pds2023;

public class Director extends Monitor {
    private Course degreeName;

    public Director(StudentAdm stud, Course degree) throws Exception {
        if (degree != stud.getStudent().getCourse()) {
            throw new Exception("Director's course must be the same as student's course");
        }
        this.degreeName = degree;
        this.adm = stud;
        this.adm.subscribe(this);
    }

    public Course getCourseName() {
        return degreeName;
    }

    @Override
    protected void update(double score) {
        System.out.printf("%s director reports that student %s (%d) has changed the overall grade: %.1f\n",
                degreeName,
                this.adm.getStudent().getName(),
                this.adm.getStudent().getId(),
                score);
    }
}
