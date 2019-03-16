package rc.voxxed.legostore.db;

import rc.voxxed.legostore.model.AvgRatingModel;

import java.util.List;

public interface ReportingRepository {
    List<AvgRatingModel> getAvgRatingReport();
}
