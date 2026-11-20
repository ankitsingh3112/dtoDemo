package in.demo.dtodemo.repository;

import in.demo.dtodemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository  extends JpaRepository<Student,Long> {


    List<Student> findByDeletedIsFalse();

    Optional<Student> findByIdAndDeletedIsFalse(Long id);

    Boolean existsByEmail(String email);
}
