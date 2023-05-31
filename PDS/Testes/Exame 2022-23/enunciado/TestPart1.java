public class TestPart1 {
    
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        try {
            Student st1 = new Student("John", 98765, Course.LEI); 
            StudentAdm sAdm1 = new StudentAdm(st1);

            Director dir = new Director(sAdm1, Course.LEI);
            Professor prof1 = new Professor(sAdm1, "Mary", "Programming");
            Professor prof2 = new Professor(sAdm1, "Peter", "BD");
            Professor prof3 = new Professor(sAdm1, "Rute", "PDS");

            sAdm1.addScore("Programming", 17.0);
            sAdm1.addScore("BD",  16.0);
            sAdm1.addScore("PDS", 15.0);
 
            Student st2 = new Student("Silvester", 89855, Course.LEEC);
            StudentAdm sAdm2 = new StudentAdm(st2); 
            Director dir2 = new Director(sAdm2, Course.LECI);

                    
        } catch (Exception e) {
            System.out.println("Error: " +  e.getMessage());
        }

        System.out.println("* The end...");

    }
    
}

/*
 *  Expected Output:
LEI director reports that student John (98765) has changed the overall grade: 17.0
Professor Mary of Programming class evaluated student John (98765) with score: 17.0
LEI director reports that student John (98765) has changed the overall grade: 16.5
Professor Mary of Programming class evaluated student John (98765) with score: 17.0
Professor Peter of BD class evaluated student John (98765) with score: 16.0
LEI director reports that student John (98765) has changed the overall grade: 16.0
Professor Mary of Programming class evaluated student John (98765) with score: 17.0
Professor Peter of BD class evaluated student John (98765) with score: 16.0
Professor Rute of PDS class evaluated student John (98765) with score: 15.0
Error: Director's course must be the same as student's course
* The end...
*/