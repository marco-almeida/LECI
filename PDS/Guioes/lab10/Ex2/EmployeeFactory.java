package lab10.Ex2;

class EmployeeFactory {
    public static final String[] names = {"Mac", "Linux", "Win"};

    public static Employee getEmployee(String name) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(name)) {
                return new Programmer(name);
            }
        }
        return new NullProgrammer();
    }
}
