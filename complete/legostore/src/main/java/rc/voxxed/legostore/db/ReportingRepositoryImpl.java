package rc.voxxed.legostore.db;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import rc.voxxed.legostore.model.AvgRatingModel;
import rc.voxxed.legostore.model.LegoSet;

import java.util.Collection;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

@Repository
public class ReportingRepositoryImpl implements ReportingRepository {
    private MongoTemplate mongoTemplate;

    public ReportingRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Collection<AvgRatingModel> getAvgRatingReport() {
        var projection = project()
                .andExpression("$name").as("legoSetName")
                .andExpression("{$avg : '$reviews.rating'}").as("avgRating");
        var aggregation = newAggregation(LegoSet.class, projection);

        var result = mongoTemplate.aggregate(aggregation, "legosets", AvgRatingModel.class);

        return result.getMappedResults();
    }
}
