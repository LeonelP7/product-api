package com.example.demo.catfact;

import com.example.demo.Query;
import com.example.demo.catfact.entity.CatFactEntity;
import com.example.demo.catfact.entity.CatFactRepository;
import com.example.demo.exeptions.ExternalCatFactsDownException;
import com.example.demo.exeptions.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatFactQueryHandler implements Query<Void, CatFactDTO> {

    private final String CAT_FACT_URL = "https://catfact.ninja/fact";

    private final RestTemplate restTemplate;

    private final CatFactRepository catFactRepository;

    public CatFactQueryHandler(RestTemplate restTemplate, CatFactRepository catFactRepository) {
        this.restTemplate = restTemplate;
        this.catFactRepository = catFactRepository;
    }

    @Override
    public ResponseEntity<CatFactDTO> execute(Void input) {
        CatFact catFact = getCatFact();
        CatFactDTO catFactDTO = new CatFactDTO(catFact.getFact());
        return ResponseEntity.ok(catFactDTO);
    }

    private CatFact getCatFact() {
        try {
            CatFact catFact = restTemplate.getForObject(CAT_FACT_URL, CatFact.class);
            //save to database a copy of JSON
            catFactRepository.save(new CatFactEntity(catFact));
            return catFact;
        }catch (Exception exception){
            throw new ExternalCatFactsDownException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    new SimpleResponse("External API is down, not our fault")
            );
        }
    }
}
