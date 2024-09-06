package com.techverse.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.techverse.Model.GeneralAdmission;

 
public interface GeneralAdmissionRepository extends JpaRepository<GeneralAdmission, Long> {
}

