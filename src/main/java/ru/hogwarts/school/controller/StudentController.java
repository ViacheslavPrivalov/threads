package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> findStudents() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/age")
    public ResponseEntity<Collection<Student>> findStudentsByAge(@RequestParam int min,
                                                                 @RequestParam int max) {
        return ResponseEntity.ok(studentService.getByAge(min, max));
    }

    @GetMapping("/faculty")
    public ResponseEntity<String> findStudentsFaculty(@RequestParam String name) {
        return ResponseEntity.ok(studentService.getStudentsFaculty(name));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countAll() {
        return ResponseEntity.ok(studentService.countAll());
    }

    @GetMapping("/average_age")
    public ResponseEntity<Long> averageAge() {
        return ResponseEntity.ok(studentService.averageAge());
    }

    @GetMapping("/last5")
    public ResponseEntity<Collection<Student>> last5Students() {
        return ResponseEntity.ok(studentService.last5Students());
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getStudentsNamesByLetter(@RequestParam String letter) {
        return ResponseEntity.ok(studentService.getStudentsNamesByLetter(letter));
    }

    @GetMapping("/average_age_stream")
    public ResponseEntity<Long> getAverageAgeByStream() {
        return ResponseEntity.ok(studentService.getAverageAgeByStream());
    }

    @GetMapping("/number")
    public ResponseEntity<Long> getSomeNumber() {
        return ResponseEntity.ok(studentService.getSomeNumber());
    }

    @GetMapping("/print_students_threads")
    public ResponseEntity<Void> printStudentsInThreads() {
        studentService.printStudentsInThreads();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/print_students_syncro_threads")
    public ResponseEntity<Void> printStudentsInSyncronizedThreads() {
        studentService.printStudentsInSyncronizedThreads();
        return ResponseEntity.ok().build();
    }

}
