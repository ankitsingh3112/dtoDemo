package in.demo.dtodemo.service;

import in.demo.dtodemo.dto.CreateStudentRequestDto;
import in.demo.dtodemo.dto.CreateStudentResponseDto;
import in.demo.dtodemo.entity.Student;
import in.demo.dtodemo.exception.DuplicateResourceException;
import in.demo.dtodemo.exception.ResourceNotFoundException;
import in.demo.dtodemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDto createStudent(CreateStudentRequestDto studentReqDto) {
        // business logic
        // add to db
        Student student=mapToEntity(studentReqDto);
        if(emailExists(student)){
            throw new DuplicateResourceException("Email "+student.getEmail()+" already exists.");
        }


        Student studentResp=studentRepository.save(student);

        return mapToDto(studentResp);
    }




    public CreateStudentResponseDto getStudent(Long id) {
        Student studentResp=studentRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student with id "+ id +" not found"));

        return mapToDto(studentResp);

    }

    public List<Student> getAllStudent() {
        List<Student> studentList=studentRepository.findByDeletedIsFalse();

        return  studentList;
    }

    public Student updateStudent(Long id, Student studentReq) {
        Student existingStudent= studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student with id "+ id +" not found"));

        Student studentToSave=existingStudent;

        studentToSave.setAge(studentReq.getAge());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setName(studentReq.getName());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setDeleted(false);

        return studentRepository.save(studentToSave);


    }

    public void deleteStudent(Long id) {
        Student studentToBeDeleted=studentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student with id "+ id +" not found"));

        System.out.println("Student Data Successfully deleted...");
        studentRepository.delete(studentToBeDeleted);

    }

    public void deleteStudentSoftly(Long id) {
        Student studentToBeDeleted=studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student with id "+ id +" not found"));

        studentToBeDeleted.setDeleted(true);
        studentRepository.save(studentToBeDeleted);
    }

    private Student mapToEntity(CreateStudentRequestDto studentReqDto) {
        Student student=new Student();

        student.setName(studentReqDto.getName());
        student.setAge(studentReqDto.getAge());
        student.setEmail(studentReqDto.getEmail());
        student.setRollNo(studentReqDto.getRollNo());
        student.setSubject(studentReqDto.getSubject());

        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        student.setDeleted(false);

        return student;

    }

    private CreateStudentResponseDto mapToDto(Student student){
        CreateStudentResponseDto responseDto=new CreateStudentResponseDto();

        responseDto.setId(student.getId());
        responseDto.setName(student.getName());
        responseDto.setAge(student.getAge());
        responseDto.setEmail(student.getEmail());
        responseDto.setRollNo(student.getRollNo());
        responseDto.setSubject(student.getSubject());
        responseDto.setMessage("Student Saved Succesfully");
        responseDto.setCreatedAt(student.getCreatedAt());
        responseDto.setUpdatedAt(student.getUpdatedAt());

        return responseDto;
    }

    private boolean emailExists(Student student) {
       return studentRepository.existsByEmail(student.getEmail());
    }
}
