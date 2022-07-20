package vttp.workshop13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


@SpringBootTest
public class FunctionTest {
    private Logger logger = Logger.getLogger(FunctionTest.class.getName());

    private Path workingDir;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void readContact() throws IOException {
        this.workingDir = Path.of("", "/Users/elly/vttp_ssf/workshop13/data");
        Path file = this.workingDir.resolve("9df6eb67");
        String content = Files.readString(file);
        assertThat(content, is(notNullValue()));
    }

    @Test
    public void saveContact() throws IOException{

        MultiValueMap<String, String> payload = new LinkedMultiValueMap<>();       // structure of contact info
        payload.add("name", "jenny");
        payload.add("email", "jenny@hotmail.com");
        payload.add("phone", "90909090");
        
        logger.log(Level.INFO, "" + objectMapper.writeValueAsString(payload));
        
    }
}
