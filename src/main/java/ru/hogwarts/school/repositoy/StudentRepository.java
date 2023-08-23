package ru.hogwarts.school.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int min, int max);

    Student findByNameContainingIgnoreCase(String name);

    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Integer countAll();

    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    Long averageAge();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    Collection<Student> last5Students();

}
