package com.techverse.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.techverse.Model.GeneralAdmission;

@Repository
public interface GeneralAdmissionRepository extends JpaRepository<GeneralAdmission, Long> {
}

