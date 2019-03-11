package rc.voxxed.legostore.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import rc.voxxed.legostore.model.DeliveryInfo;
import rc.voxxed.legostore.model.LegoSet;
import rc.voxxed.legostore.model.LegoSetDifficulty;
import rc.voxxed.legostore.model.ProductReview;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@ConditionalOnProperty(name = "legostore.initdb", havingValue = "true")
public class DbSeeder implements CommandLineRunner {
    private LegoSetRepository legoSetRepository;

    public DbSeeder(LegoSetRepository legoSetRepository) {
        this.legoSetRepository = legoSetRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        // Initial delete => start clean
        this.legoSetRepository.deleteAll();

        /*
        Lego Sets
         */

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

        var skyPolice = new LegoSet(
                "Sky Police Air Base",
                "City",
                LegoSetDifficulty.MEDIUM,
                new DeliveryInfo(LocalDate.now().plusDays(8), 40, true),
                List.of(
                        new ProductReview("Dan", 5),
                        new ProductReview("Andrew", 8)
                )
        );

        var mcLarenSenna = new LegoSet(
                "McLaren Senna",
                "Speed Champions",
                LegoSetDifficulty.EASY,
                new DeliveryInfo(LocalDate.now().plusDays(3), 60, false),
                List.of(
                        new ProductReview("Bogdan", 9),
                        new ProductReview("Christa", 9)
                )
        );

        var mindstormsEve = new LegoSet(
                "MINDSTORMS EV3",
                "Mindstorms",
                LegoSetDifficulty.HARD,
                new DeliveryInfo(LocalDate.now().plusDays(21), 100, true),
                List.of(
                        new ProductReview("Cosmin", 10),
                        new ProductReview("Jane", 9),
                        new ProductReview("James", 10)
                )
        );

        // Populate collections
        var legosets = List.of(milleniumFalcon, skyPolice, mindstormsEve, mcLarenSenna);
        this.legoSetRepository.saveAll(legosets);

        System.out.printf("Lego Store DataBase Initialized");
    }
}
