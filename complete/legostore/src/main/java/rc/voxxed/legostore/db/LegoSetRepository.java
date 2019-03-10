package rc.voxxed.legostore.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import rc.voxxed.legostore.model.LegoSet;

@Repository
public interface LegoSetRepository extends MongoRepository<LegoSet, String> {
}
