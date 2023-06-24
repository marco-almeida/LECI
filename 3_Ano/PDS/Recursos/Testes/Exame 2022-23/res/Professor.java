package pds2023;

public class Professor extends Monitor {
    private String className;
    private String name;

    public Professor(StudentAdm adm, String name, String className) {
        this.name = name;
        this.className = className;
        this.adm = adm;
        this.adm.subscribe(this);
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    @Override
    protected void update(double score) {
        System.out.printf("Professor %s of %s class evaluated student %s (%d) with score: %.1f\n",
                name,
                className,
                this.adm.getStudent().getName(),
                this.adm.getStudent().getId(),
                score);
    }
}
