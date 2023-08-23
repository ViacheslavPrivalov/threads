package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositoy.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {

        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {

        logger.info("Was invoked method for find faculty");
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {


        logger.info("Was invoked method for edit faculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {

        logger.info("Was invoked method for delete faculty");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAll() {

        logger.info("Was invoked method for find all faculties");
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getByNameOrColor(String name, String color) {

        logger.info("Was invoked method for find faculty by name");
        return facultyRepository.findByNameOrColorContainingIgnoreCase(name, color);
    }

    public Collection<Student> getStudentsFromFaculty(long id) {

        logger.info("Was invoked method for find faculty's students");
        return facultyRepository.findById(id).get().getStudents();
    }

    public String getLongestName() {
        List<Faculty> faculties = facultyRepository.findAll();
//        int maxLength = 0;
//        String longestName = "";
//        for (Faculty faculty : faculties) {
//            if (faculty.getName().length() > maxLength) {
//                maxLength = faculty.getName().length();
//                longestName = faculty.getName();
//            }
//        }
//        return longestName;

        return faculties.stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .get();
    }
}
