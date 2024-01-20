package org.example.service;

import jakarta.persistence.EntityManager;
import org.apache.catalina.LifecycleState;
import org.example.dto.MobileCreateDTO;
import org.example.dto.MobileDTO;
import org.example.dto.MobileUpdateDTO;
import org.example.exception.ResourceNotFoundException;
import org.example.mapper.MobileMapper;
import org.example.mapper.ReferenceMapper;
import org.example.model.Mobile;
import org.example.repository.MobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MobileService {
    @Autowired
    private MobileRepository mobileRepository;

    @Autowired
    private MobileMapper mobileMapper;

    public List<MobileDTO> getAllMobiles() {
        var mobiles = mobileRepository.findAll();
        return mobiles.stream()
                .map(mobileMapper::map)
                .toList();
    }

    public MobileDTO findById(Long id) {
        var mobile = mobileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mobile with id " + id + " not found"));
        return mobileMapper.map(mobile);
    }

    public MobileDTO createNewMobile(MobileCreateDTO mobileCreateDTO) {
        var mobile = mobileMapper.map(mobileCreateDTO);
        mobileRepository.save(mobile);
        return mobileMapper.map(mobile);
    }

    public MobileDTO updateMobile(MobileUpdateDTO mobileUpdateDTO, Long id) {
        var mobile = mobileMapper.map(findById(id));
        mobileMapper.update(mobileUpdateDTO, mobile);
        mobile.setId(id);
        mobileRepository.save(mobile);
        return mobileMapper.map(mobile);
    }

    public void deleteById(Long id) {
        mobileRepository.deleteById(id);
    }
}
