package com.blitech.app4docker.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.blitech.app4docker.controller.DepartmentController;
import com.blitech.app4docker.controller.impl.CustomUtils;
import com.blitech.app4docker.dto.DepartmentDto;
import com.blitech.app4docker.mapper.DepartmentMapper;
import com.blitech.app4docker.mapper.EntityMapper;
import com.blitech.app4docker.model.Department;
import com.blitech.app4docker.service.DepartmentService;
import com.blitech.app4docker.utils.EmployeeBuilder;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class DepartmentControllerTest {
    private static final String ENDPOINT_URL = "/api/department";
    @InjectMocks
    private DepartmentController departmentController;
    @Mock
    private DepartmentService departmentService;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.departmentController).build();
    }

   /* @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(departmentController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }*/

    @Test
    public void findAllByPage() throws Exception {
        Page<DepartmentDto> page = new PageImpl<>(Collections.singletonList(DepartmentBuilder.getDto()));

        Mockito.when(departmentService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));

        Mockito.verify(departmentService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(departmentService);

       // Mockito.when(employeeMapper.asDTOList(ArgumentMatchers.any())).thenReturn(EmployeeBuilder.getListDTO());

      /*  Mockito.when(employeeService.findAll()).thenReturn(EmployeeBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));

*/
    }

    @Test
    public void getById() throws Exception {
        Mockito.when(departmentService.findById(ArgumentMatchers.anyLong())).thenReturn(DepartmentBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(departmentService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(departmentService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(departmentService.save(ArgumentMatchers.any(DepartmentDto.class))).thenReturn(DepartmentBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(DepartmentBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(departmentService, Mockito.times(1)).save(ArgumentMatchers.any(DepartmentDto.class));
        Mockito.verifyNoMoreInteractions(departmentService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(departmentService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(DepartmentBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(DepartmentBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(departmentService, Mockito.times(1)).update(ArgumentMatchers.any(DepartmentDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(departmentService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(departmentService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CustomUtils.asJsonString(DepartmentBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(departmentService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(departmentService);
    }
}