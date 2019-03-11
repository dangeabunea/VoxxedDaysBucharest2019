package rc.voxxed.legostore.db;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import rc.voxxed.legostore.model.LegoSet;

import java.util.Collection;

@Repository
public interface LegoSetRepository extends MongoRepository<LegoSet, String> {
    Collection<LegoSet> findAllByThemeContains(String theme, Sort sort);

    @Query("{'delivery.deliveryFee' : {$lt: ?0}}")
    Collection<LegoSet> findByDeliveryPriceLessThan(int deliveryPriceLimit);

    @Query("{'reviews.rating' : {$gt: 9}}")
    Collection<LegoSet> findByGoodReviews();
}
