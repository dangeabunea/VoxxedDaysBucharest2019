package rc.voxxed.legostore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Document(collection = "legosets")
public class LegoSet {
    @Id
    private String id;
    @TextIndexed
    @Indexed(direction = IndexDirection.ASCENDING)
    private String name;
    private LegoSetDifficulty difficulty;
    @TextIndexed
    private String theme;
    private Collection<ProductReview> reviews = new ArrayList<>();
    @Field("delivery")
    private DeliveryInfo deliveryInfo;
    private int price;

    public LegoSet(String name,
                   String theme,
                   LegoSetDifficulty difficulty,
                   DeliveryInfo deliveryInfo,
                   Collection<ProductReview> reviews){
        this.name = name;
        this.theme = theme;
        this.difficulty = difficulty;
        this.deliveryInfo = deliveryInfo;
        if(reviews != null){
            this.reviews = reviews;
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public LegoSetDifficulty getDifficulty() {
        return difficulty;
    }

    public String getTheme() {
        return theme;
    }

    public Collection<ProductReview> getReviews() {
        return Collections.unmodifiableCollection(this.reviews);
    }

    public int getPrice() {
        return price;
    }
}
