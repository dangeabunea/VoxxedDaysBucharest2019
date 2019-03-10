package rc.voxxed.legostore.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DeliveryInfo {
    private LocalDate deliveryDate;
    private BigDecimal deliveryFee;
    private boolean inStock;

    public DeliveryInfo(LocalDate deliveryDate,
                        BigDecimal deliveryFee,
                        boolean inStock) {
        this.deliveryDate = deliveryDate;
        this.deliveryFee = deliveryFee;
        this.inStock = inStock;
    }

    public BigDecimal getDeliveryFee() {
        return this.deliveryFee;
    }

    public LocalDate getDeliveryDate() {
        return this.deliveryDate;
    }

    public boolean isInStock() {
        return this.inStock;
    }
}
