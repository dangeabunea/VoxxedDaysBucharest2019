package rc.voxxed.legostore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import rc.voxxed.legostore.db.LegoSetRepository;
import rc.voxxed.legostore.db.ReportingRepository;
import rc.voxxed.legostore.db.ReportingRepositoryImpl;
import rc.voxxed.legostore.model.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class LegoStoreRepositoryTests {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private LegoSetRepository legoSetRepository;

    @Before
    public void contextLoads() {
        this.legoSetRepository.deleteAll();
    }

    @Test
    public void should_insert_document_in_db(){
        // arrange
        var milleniumFalcon = new LegoSet(
                "Millennium Falcon",
                "Star Wars",
                LegoSetDifficulty.HARD,
                new DeliveryInfo(LocalDate.now().plusDays(3), 50, true),
                List.of(
                        new ProductReview("Dan", 7),
                        new ProductReview("Anna", 10),
                        new ProductReview("John", 8)
                )
        );

        // act
        this.legoSetRepository.insert(milleniumFalcon);

        // assert
        var result = this.legoSetRepository.findById(milleniumFalcon.getId());
        assertNotNull(result);
    }

    @Test
    public void should_compute_avg_ratings_report(){
        // arrange
        var milleniumFalcon = new LegoSet(
                "Millennium Falcon",
                "Star Wars",
                LegoSetDifficulty.HARD,
                new DeliveryInfo(LocalDate.now().plusDays(3), 50, true),
                List.of(
                        new ProductReview("Dan", 7),
                        new ProductReview("Anna", 10),
                        new ProductReview("John", 4)
                )
        );
        this.legoSetRepository.insert(milleniumFalcon);

        // act
        var reportingRepository = new ReportingRepositoryImpl(this.mongoTemplate);
        List<AvgRatingModel> result = reportingRepository.getAvgRatingReport();

        // assert
        assertEquals(1, result.size());
        assertEquals(7.0, result.get(0).getAvgRating(), 0.01);
    }

}
