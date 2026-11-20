package in.demo.dtodemo.dto;

import jakarta.validation.constraints.*;

public class CreateStudentRequestDto {

    @NotBlank(message = "Name cannot be Blank/null/empty")
    @Size(min =2,max = 25,message = "Student name must be within 2 char to 25 char")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value=18, message = "Student must be 18 above")
    private Integer age;

    @Email(message = "email must be valid")
    @NotNull(message = "email value cannot be blank")
    private String email;

    @NotNull(message = "RollNo is required")
    private Integer rollNo;

    @NotBlank(message = "Subject cannot be Blank")
    private String subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
