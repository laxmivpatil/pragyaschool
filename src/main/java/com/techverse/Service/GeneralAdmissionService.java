package com.techverse.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techverse.Model.GeneralAdmission;
import com.techverse.Repository.GeneralAdmissionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GeneralAdmissionService {

    @Autowired
    private GeneralAdmissionRepository generalAdmissionRepository;

    public List<GeneralAdmission> getAllAdmissions() {
        return generalAdmissionRepository.findAll();
    }

    public Optional<GeneralAdmission> getAdmissionById(Long id) {
        return generalAdmissionRepository.findById(id);
    }

    public GeneralAdmission createAdmission(GeneralAdmission admission) {
        return generalAdmissionRepository.save(admission);
    }
}
