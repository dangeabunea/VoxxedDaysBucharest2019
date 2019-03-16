package rc.voxxed.legostore.db;

import org.springframework.stereotype.Repository;
import rc.voxxed.legostore.model.AvgRatingModel;

import java.util.Collection;

public interface ReportingRepository {
    Collection<AvgRatingModel> getAvgRatingReport();
}
