package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    @Test
    public void testShow() throws Exception {
        var task = getNewTaskObject();
        taskRepository.save(task);

        mockMvc.perform(get("/tasks/" + task.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreate() throws Exception {
        var task = getNewTaskObject();

        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(task));

        var result = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();

        var createdTask = taskRepository.findByTitle(task.getTitle()).get();
        assertThatJson(result.getResponse().getContentAsString()).and(
                a -> a.node("id").isEqualTo(createdTask.getId()),
                a -> a.node("title").isEqualTo(createdTask.getTitle()),
                a -> a.node("description").isEqualTo(createdTask.getDescription())
        );
    }

    @Test
    public void testUpdate() throws Exception {
        var task = getNewTaskObject();
        taskRepository.save(task);

        var data = new HashMap<>();
        data.put("title", task.getTitle());
        data.put("description", faker.lorem().sentence(10));

        var request = put("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        var updatedTask = taskRepository.findById(task.getId()).get();
        assertThat(task.getTitle()).isEqualTo(updatedTask.getTitle());
        assertThat(task.getDescription()).isNotEqualTo(updatedTask.getDescription());
    }

    @Test
    public void testDelete() throws Exception {
        var task = getNewTaskObject();
        taskRepository.save(task);

        assertThat(taskRepository.existsById(task.getId())).isTrue();

        mockMvc.perform(delete("/tasks/" + task.getId()))
                .andExpect(status().isOk());

        assertThat(taskRepository.existsById(task.getId())).isFalse();
    }

    public Task getNewTaskObject() {
        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().sentence(10))
                .ignore(Select.field(Task::getCreatedAt))
                .ignore(Select.field(Task::getUpdatedAt))
                .create();
        return task;
    }
    // END
}
