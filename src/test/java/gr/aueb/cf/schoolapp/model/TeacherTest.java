package gr.aueb.cf.schoolapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    @Test
    void gettersSetters() {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        assertEquals(teacher.getId(),1);

        teacher.setFirstname("Petros");
        assertEquals(teacher.getFirstname(),"Petros");

        teacher.setLastname("Moustakis");
        assertEquals(teacher.getLastname(),"Moustakis");
    }

    @Test
    void overloadedConstructorAndToString() {
        Teacher teacher = new Teacher(2, "Anna", "Giannoutsou");
        assertEquals(teacher.getId(),2);
        assertEquals(teacher.getFirstname(),"Anna");
        assertEquals(teacher.getLastname(),"Giannoutsou");

        String expected = "2, Anna, Giannoutsou";
        assertEquals(expected, teacher.toString());
    }
}