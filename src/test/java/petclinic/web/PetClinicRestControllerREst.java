package petclinic.web;

import com.example.PetClinicApplication.model.Owner;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PetClinicRestControllerREst {

    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();

    }

    @Test
    public void testDeleteOwner() {
        restTemplate.delete("http://localhost:8080/rest/owner/1");
        try {
            restTemplate.getForEntity("http://localhost:8080/rest/owner/1", Owner.class);
        } catch (HttpClientErrorException exception) {
        MatcherAssert.assertThat(exception.getStatusCode().value(),Matchers.equalTo(404));

        }
    }

    @Test
    public void testUpdateOwner() {
        Owner owner = restTemplate.getForObject("http://localhost:8080/rest/owner/3", Owner.class);
        MatcherAssert.assertThat(owner.getFirstname(), Matchers.equalTo("Salim"));

        owner.setFirstname("deneme yanilma");
        restTemplate.put("http://localhost:8080/rest/owner/3", owner);
        owner = restTemplate.getForObject("http://localhost:8080/rest/owner/3", Owner.class);
        MatcherAssert.assertThat(owner.getFirstname(), Matchers.equalTo("Salim"));

    }

    @Test
    public void testCreateOwner() {
        Owner owner = new Owner();
        owner.setFirstname("furkan");
        owner.setLastname("aydogan");

        URI location = restTemplate.postForLocation("http://localhost:8080/rest/owner", owner);
        Owner owner_2 = restTemplate.getForObject(location, Owner.class);

        MatcherAssert.assertThat(owner.getFirstname(), Matchers.equalTo(owner_2.getFirstname()));
        MatcherAssert.assertThat(owner.getLastname(), Matchers.equalTo(owner_2.getLastname()));

    }


    @Test
    public void testSetOwnersById() {

        ResponseEntity<Owner> response = restTemplate.getForEntity("http://localhost:8080/rest/owner/1", Owner.class);
        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
        MatcherAssert.assertThat(response.getBody().getFirstname(), Matchers.equalTo("Kenan"));
    }


    @Test
    public void testOwnersByLastname() {
        ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8080/rest/owner?ln=Sevindik",
                List.class);
        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
        List<Map<String, String>> body = response.getBody();
        List<String> firstname = body.stream().map(e -> e.get("firstname")).collect(Collectors.toList());
        MatcherAssert.assertThat(firstname, Matchers.containsInAnyOrder("Kenan", "Hümeyra", "Salim"));


    }

    @Test
    public void testGetOwners() {
        ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8080/rest/owners", List.class);
        List<Map<String, String>> body = response.getBody();
        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
        List<String> firstname = body.stream().map(e -> e.get("firstname")).collect(Collectors.toList());
        MatcherAssert.assertThat(firstname, Matchers.containsInAnyOrder("Kenan", "Hümeyra", "Salim", "yunus"));


    }

}
