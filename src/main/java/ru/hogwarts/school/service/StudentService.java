package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositoy.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class StudentService {
    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {

        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {

        logger.info("Was invoked method for find student");
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {

        logger.info("Was invoked method for edit student");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {

        logger.info("Was invoked method for delete student");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAll() {

        logger.info("Was invoked method for find all students");
        return studentRepository.findAll();
    }

    public Collection<Student> getByAge(int min, int max) {

        logger.info("Was invoked method for get student by age");
        return studentRepository.findByAgeBetween(min, max);
    }

    public String getStudentsFaculty(String name) {

        logger.info("Was invoked method for find student's faculty");
        return studentRepository.findByNameContainingIgnoreCase(name).getFaculty().getName();
    }

    public Integer countAll() {

        logger.info("Was invoked method for count students");
        return studentRepository.countAll();
    }

    public Long averageAge() {

        logger.info("Was invoked method for find average age of students");
        return studentRepository.averageAge();
    }

    public Collection<Student> last5Students() {

        logger.info("Was invoked method for find last 5 students");
        return studentRepository.last5Students();
    }

    public List<String> getStudentsNamesByLetter(String letter) {
        List<Student> students = studentRepository.findAll();
//        List<String> names = new ArrayList<>();
//        for (Student student : students) {
//            if (student.getName().startsWith(letter)) {
//                names.add(student.getName().toUpperCase());
//            }
//        }
//        return names;
        List<String> names = students.stream()
                .map(Student::getName)
                .filter(name -> name.startsWith(letter))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
        return names;
    }

    public Long getAverageAgeByStream() {
        List<Student> students = studentRepository.findAll();
//        int sum = 0;
//        for (Student student : students) {
//            sum += student.getAge();
//        }
//
//        return sum / students.size();

        return (long) students.stream()
                .mapToLong(Student::getAge)
                .average().getAsDouble();

    }

    public long getSomeNumber() {
        long time = System.currentTimeMillis();

        int sum = Stream
                .iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);


        time = System.currentTimeMillis() - time;
        return time;
    }

    public void printStudentsInThreads() {
        List<Student> students = studentRepository.findAll();

        System.out.println(students.get(0));
        System.out.println(students.get(1));
        System.out.println("Основной поток закончился");

        new Thread(() -> {
            System.out.println("Параллельный поток 1 включился");
            System.out.println(students.get(2));
            System.out.println(students.get(3));
        }).start();

        new Thread(() -> {
            System.out.println("Параллельный поток 2 включился");
            System.out.println(students.get(4));
            System.out.println(students.get(5));
        }).start();
    }

    public void printStudentsInSyncronizedThreads() {
        List<Student> students = studentRepository.findAll();

        printStudent(students.get(0));
        printStudent(students.get(1));

        new Thread(() -> {
            printStudent(students.get(2));
            printStudent(students.get(3));
        }).start();

        new Thread(() -> {
            printStudent(students.get(4));
            printStudent(students.get(5));
        }).start();
    }

    public void printStudent(Student student) {
        synchronized (StudentService.class) {
            System.out.println(student);
        }
    }
}
