package in.demo.dtodemo.controller;

import in.demo.dtodemo.entity.Student;
import in.demo.dtodemo.service.StudentService;
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
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){

        Student createdStudent= studentService.createStudent(student);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);

    }

    //read {GET}
    @GetMapping("/get")
    public ResponseEntity<Student> getStudent(@RequestParam Long id){
        Student studentResp=studentService.getStudent(id);

        if(studentResp==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity
                .ok(studentResp);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getStudent(){
        List<Student> studentlist=studentService.getAllStudent();

        if(studentlist.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity
                .ok(studentlist);
    }

    //update {PUT}
    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestParam Long id,@RequestBody Student studentReq){
        Student studentResp=studentService.updateStudent(id,studentReq);

        if(studentResp==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity
                .ok(studentResp);
    }

    //Hard delete {DELETE}
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteStudent(@RequestParam Long id){
        Boolean  isDeleted=studentService.deleteStudent(id);

        if(!isDeleted) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity
                .ok(true);
    }

    //soft delete
    @PatchMapping("/soft-delete")
    public ResponseEntity<String> softDelete(@RequestParam Long id){
        Boolean isDeleted = studentService.deleteStudentSoftly(id);

        if(!isDeleted) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity
                .ok("Record Deleted..");

    }

}
