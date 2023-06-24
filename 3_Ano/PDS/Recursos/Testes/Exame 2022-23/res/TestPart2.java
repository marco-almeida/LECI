package pds2023;

import java.util.Iterator;

public class TestPart2 {
    public static void main(String[] args) {
        GradingSystem gs = new GradingSystem();

        StudentAdm sAdm1 = gs.addStudent(new Student("John", 98765, Course.LEI));
        StudentAdm sAdm2 = gs.addStudent(new Student("George", 65432, Course.LECI));
        StudentAdm sAdm3 = gs.addStudent(new Student("Mary", 102345, Course.LEI));
        StudentAdm sAdm4 = gs.addStudent(new Student("Peter", 101023, Course.LEEC));

        gs.addDirector(Course.LEI);
        gs.addDirector(Course.LECI);
        gs.addDirector(Course.LEEC);

        gs.addProfessor(new Professor(sAdm1, "Mary", "Programming"));
        gs.addProfessor(new Professor(sAdm1, "Peter", "BD"));
        gs.addProfessor(new Professor(sAdm1, "Jose", "PDS")); 
        gs.addProfessor(new Professor(sAdm2, "Ana", "Project"));
        gs.addProfessor(new Professor(sAdm3, "Mario", "POO"));
        gs.addProfessor(new Professor(sAdm4, "Xavier", "Digital Eletronics"));
        gs.addProfessor(new Professor(sAdm4, "Paula", "Mathematics"));

        sAdm1.addScore("Programming", 17.0);
        sAdm1.addScore("BD",  16.0);
        sAdm1.addScore("PDS", 15.0);
 
        sAdm2.addScore("Project", 15.0);
        sAdm3.addScore("POO",  16.0);
        sAdm4.addScore("Digital Eletronics", 12.0);
        sAdm4.addScore("Mathematics", 14.0);

        // your code to list the students of LEI using the Iterator goes here... 
        Iterator<StudentAdm> leiStudents = gs.iterator(Course.LEI);

        while (leiStudents.hasNext()) {
            System.out.println(leiStudents.next().getStudent());
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
Professor Jose of PDS class evaluated student John (98765) with score: 15.0
LECI director reports that student George (65432) has changed the overall grade: 15.0
Professor Ana of Project class evaluated student George (65432) with score: 15.0
LEI director reports that student Mary (102345) has changed the overall grade: 16.0
Professor Mario of POO class evaluated student Mary (102345) with score: 16.0
LEEC director reports that student Peter (101023) has changed the overall grade: 12.0
Professor Xavier of Digital Eletronics class evaluated student Peter (101023) with score: 12.0
LEEC director reports that student Peter (101023) has changed the overall grade: 13.0
Professor Xavier of Digital Eletronics class evaluated student Peter (101023) with score: 12.0
Professor Paula of Mathematics class evaluated student Peter (101023) with score: 14.0

Students of LEI course:
John (98765)
Mary (102345)
* The end...
*/
