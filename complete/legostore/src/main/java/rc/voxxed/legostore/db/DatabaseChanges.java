package rc.voxxed.legostore.db;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import rc.voxxed.legostore.model.LegoSet;

@ChangeLog
public class DatabaseChanges {

    @ChangeSet(order = "001", id = "update price", author = "dan")
    public void setPriceValue(MongoTemplate mongoTemplate) {
        var priceZeroCriteria = new Criteria().orOperator(
                Criteria.where("price").is(0),
                Criteria.where("price").is(null));

        mongoTemplate.updateMulti(new Query(priceZeroCriteria), Update.update("price", 122), LegoSet.class);
        System.out.println("Applied changeset 001");
    }
}
