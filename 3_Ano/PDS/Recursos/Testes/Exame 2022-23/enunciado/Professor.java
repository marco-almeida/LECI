public class Professor {
    private String className;
    private String name;

    public Professor(StudentAdm adm, String name, String className) {
        this.name = name;
        this.className = className;
        // ...
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

}
