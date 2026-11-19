package in.demo.dtodemo.service;

import in.demo.dtodemo.entity.Student;
import in.demo.dtodemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq) {
        // business logic
        // add to db

        studentReq.setDeleted(false);
        Student studentResp=studentRepository.save(studentReq);

        return studentResp;
    }

    public Student getStudent(Long id) {
        Optional<Student> studentResp=studentRepository.findById(id);

        return studentResp.orElse(null);

    }

    public List<Student> getAllStudent() {
        List<Student> studentList=studentRepository.findByDeletedIsFalse();

        return  studentList;
    }

    public Student updateStudent(Long id, Student studentReq) {
        Optional<Student> existingStudent= studentRepository.findByIdAndDeletedIsFalse(id);

        if(existingStudent.isEmpty()) return null;

        Student studentToSave=existingStudent.get();

        studentToSave.setAge(studentReq.getAge());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setName(studentReq.getName());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setDeleted(false);

        return studentRepository.save(studentToSave);


    }

    public Boolean deleteStudent(Long id) {
        Boolean isExist=studentRepository.existsById(id);

        if(!isExist) {
            System.out.println("Wrong Student id");
            return false;
        }


        System.out.println("Student Data Successfully deleted...");
        studentRepository.deleteById(id);

        return true;
    }

    public Boolean deleteStudentSoftly(Long id) {
        Optional<Student> existingStudent=studentRepository.findByIdAndDeletedIsFalse(id);

        if (existingStudent.isEmpty()) return false;

        Student studentToSave=existingStudent.get();
        studentToSave.setDeleted(true);
        studentRepository.save(studentToSave);

        return true;
    }
}
