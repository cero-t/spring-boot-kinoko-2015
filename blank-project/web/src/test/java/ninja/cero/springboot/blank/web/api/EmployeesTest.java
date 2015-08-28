package ninja.cero.springboot.blank.web.api;

import ninja.cero.springboot.blank.test.TestContextConfig;
import ninja.cero.springboot.blank.web.BlankWeb;
import ninja.cero.springboot.blank.web.controller.io.EmployeesIn;
import ninja.cero.springboot.blank.web.controller.io.EmployeesOut;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {BlankWeb.class, TestContextConfig.class})
@WebAppConfiguration
@IntegrationTest("server.port=0")
@Transactional
public class EmployeesTest {
    @Value("http://localhost:${local.server.port}/employees")
    String baseUrl;

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void testGetList() {
        ResponseEntity<List> out = restTemplate.getForEntity(baseUrl, List.class);
        System.out.println(out.getBody());
    }

    @Test
    public void testGet() {
        ResponseEntity<EmployeesOut> out = restTemplate.getForEntity(baseUrl + "/" + 7369, EmployeesOut.class);
        System.out.println(out.getBody());
    }

    @Test
    public void testPost() {
        EmployeesIn in = new EmployeesIn();
        in.ename = "TEST";
        in.job = "SALESMAN";
        in.mgr = 7362;
        in.hiredate = LocalDate.of(2015, 8, 26);
        in.sal = BigDecimal.valueOf(700);
        in.comm = null;
        in.deptno = 20;

        ResponseEntity<Integer> out = restTemplate.postForEntity(baseUrl, in, Integer.class);
        System.out.println(out.getBody());
    }

    @Test
    public void testSearch() {
        ResponseEntity<List> out = restTemplate.getForEntity(baseUrl + "/search?hiredateFrom=1981-12-01&deptno=20", List.class);
        System.out.println(out.getBody());
    }

    @Test
    public void testEx() {
        try {
            restTemplate.getForEntity(baseUrl + "/ex", String.class);
            fail();
        } catch (HttpClientErrorException ex) {
            System.out.println(ex.getResponseBodyAsString());
        }
    }
}
