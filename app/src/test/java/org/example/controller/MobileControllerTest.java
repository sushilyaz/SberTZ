package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.mapper.MobileMapper;
import org.example.model.Mobile;
import org.example.repository.MobileRepository;
import org.example.util.ModelGenerator;
import org.instancio.Instancio;
import org.instancio.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MobileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MobileRepository mobileRepository;

    @Autowired
    private MobileMapper mobileMapper;

    @Autowired
    private ModelGenerator modelGenerator;

    private Mobile testMobile;

    @BeforeEach
    public void setUp() {
        testMobile = Instancio.of(modelGenerator.getMobileModel()).create();
    }

    @Test
    public void testIndex() throws Exception {
        mobileRepository.save(testMobile);
        var result = mockMvc.perform(get("/mobiles"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }

    @Test
    public void testShow() throws Exception {

        mobileRepository.save(testMobile);

        var request = get("/mobiles/{id}", testMobile.getId());
        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        var body = result.getResponse().getContentAsString();

        assertThatJson(body).and(
                v -> v.node("model").isEqualTo(testMobile.getModel()),
                v -> v.node("version").isEqualTo(testMobile.getVersion())
        );
    }

    @Test
    public void testCreate() throws Exception {
        var dto = mobileMapper.map(testMobile);

        var request = post("/mobiles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(dto));

        mockMvc.perform(request)
                .andExpect(status().isCreated());

        var mobile = mobileRepository.findByModelAndVersion(
                testMobile.getModel(), testMobile.getVersion()).get();

        assertThat(mobile).isNotNull();
        assertThat(mobile.getModel()).isEqualTo(testMobile.getModel());
        assertThat(mobile.getVersion()).isEqualTo(testMobile.getVersion());
    }

    @Test
    public void testUpdate() throws Exception {
        mobileRepository.save(testMobile);

        var dto = mobileMapper.map(testMobile);

        dto.setModel("xiaomi");
        dto.setVersion("Redmi note 10");

        var request = put("/mobiles/{id}", testMobile.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(dto));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        var mobile = mobileRepository.findById(testMobile.getId()).get();

        assertThat(mobile.getModel()).isEqualTo(dto.getModel());
        assertThat(mobile.getVersion()).isEqualTo(dto.getVersion());
    }

    @Test
    public void testPartialUpdate() throws Exception {
        mobileRepository.save(testMobile);

        var dto = new HashMap<String, String>();
        dto.put("version", "POCO bombo");

        var request = put("/mobiles/{id}", testMobile.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(dto));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        var mobile = mobileRepository.findById(testMobile.getId()).get();

        assertThat(mobile.getModel()).isEqualTo(testMobile.getModel());
        assertThat(mobile.getVersion()).isEqualTo(dto.get("version"));
    }

    @Test
    public void testDestroy() throws Exception {
        mobileRepository.save(testMobile);
        var request = delete("/mobiles/{id}", testMobile.getId());
        mockMvc.perform(request)
                .andExpect(status().isNoContent());

        assertThat(mobileRepository.existsById(testMobile.getId())).isEqualTo(false);
    }
}
