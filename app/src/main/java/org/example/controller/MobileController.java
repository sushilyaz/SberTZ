package org.example.controller;

import org.example.dto.MobileCreateDTO;
import org.example.dto.MobileDTO;
import org.example.dto.MobileUpdateDTO;
import org.example.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mobiles")
public class MobileController {
    @Autowired
    private MobileService mobileService;
    @GetMapping("")
    List<MobileDTO> index() {
        return mobileService.getAllMobiles();
    }

    @GetMapping("/{id}")
    MobileDTO show(@PathVariable Long id) {
        return mobileService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    MobileDTO create (@RequestBody MobileCreateDTO mobileCreateDTO) {
        return mobileService.createNewMobile(mobileCreateDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    MobileDTO update (@RequestBody MobileUpdateDTO mobileUpdateDTO, @PathVariable Long id) {
        return mobileService.updateMobile(mobileUpdateDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void destroy(@PathVariable Long id) {
        mobileService.deleteById(id);
    }
}
