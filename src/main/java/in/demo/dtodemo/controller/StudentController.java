package in.demo.dtodemo.controller;

import in.demo.dtodemo.dto.CreateStudentRequestDto;
import in.demo.dtodemo.dto.CreateStudentResponseDto;
import in.demo.dtodemo.entity.Student;
import in.demo.dtodemo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //create {POST}
    @PostMapping
    public ResponseEntity<CreateStudentResponseDto> createStudent(
            @Valid @RequestBody CreateStudentRequestDto studentReqDto){


        CreateStudentResponseDto createdStudent= studentService.createStudent(studentReqDto);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);

    }

    //read {GET}
    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDto> getStudent(@PathVariable Long id){
        CreateStudentResponseDto studentRespDto=studentService.getStudent(id);

        return ResponseEntity
                .ok(studentRespDto);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudent(){
        List<Student> studentlist=studentService.getAllStudent();

        return ResponseEntity
                .ok(studentlist);
    }

    //update {PUT}
    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestParam Long id,@RequestBody Student studentReq){
        Student studentResp=studentService.updateStudent(id,studentReq);
        return ResponseEntity
                .ok(studentResp);
    }

    //Hard delete {DELETE}
    @DeleteMapping
    public ResponseEntity<Boolean> deleteStudent(@RequestParam Long id){
        studentService.deleteStudent(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT).build();
    }

    //soft delete
    @PatchMapping("/soft-delete")
    public ResponseEntity<String> softDelete(@RequestParam Long id){
        studentService.deleteStudentSoftly(id);
        return ResponseEntity
                .ok("Record Deleted..");

    }

}
