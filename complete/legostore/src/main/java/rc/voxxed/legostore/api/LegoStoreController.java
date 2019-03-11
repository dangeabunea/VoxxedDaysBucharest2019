package rc.voxxed.legostore.api;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import rc.voxxed.legostore.db.LegoSetRepository;
import rc.voxxed.legostore.model.LegoSet;

import java.util.Collection;

@RestController
@RequestMapping("legostore/api")
public class LegoStoreController {
    private LegoSetRepository legoSetRepository;

    public LegoStoreController(LegoSetRepository legoSetRepository) {
        this.legoSetRepository = legoSetRepository;
    }

    @PostMapping
    public void insert(@RequestBody LegoSet legoSet) {
        // validations omitted
        this.legoSetRepository.insert(legoSet);
    }

    @PutMapping
    public void update(@RequestBody LegoSet legoSet) {
        // validations omitted
        this.legoSetRepository.save(legoSet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        this.legoSetRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public LegoSet byId(@PathVariable String id){
        var legoSet = this.legoSetRepository.findById(id).orElse(null);
        return legoSet;
    }

    @GetMapping("/byTheme/{theme}")
    public Collection<LegoSet> byTheme(@PathVariable String theme){
        var sort = Sort.by("theme").ascending();
        var legoSets = this.legoSetRepository.findAllByThemeContains(theme, sort);
        return  legoSets;
    }

    @GetMapping("/byDeliveryFeeLessThan/{deliveryFee}")
    public Collection<LegoSet> byDeliveryFeeLessThan(@PathVariable int deliveryFee){
        var cheapLegoSets = this.legoSetRepository.findByDeliveryPriceLessThan(deliveryFee);
        return cheapLegoSets;
    }

    @GetMapping("/ByGreatReviews")
    public Collection<LegoSet> byGreatReviews(){
        var cheapLegoSets = this.legoSetRepository.findByGoodReviews();
        return cheapLegoSets;
    }
}
