package main.Controller;

import main.module.Business;
import main.module.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BusinessController
{
    @Autowired
    private BusinessRepository businessRepository;
    //Добавить дело
    @PostMapping("/business/")
    public int add(@RequestBody Business business)
    {
        businessRepository.save(business);
        return business.getId();
    }

    //Получить список
    @GetMapping("/businesses/")
    public List<Business> getListBusiness()
    {
        List<Business> businesses = new ArrayList<>();
        for (Business b : businessRepository.findAll()) {
            businesses.add(b);
        }
        return businesses;
    }

    //Получить дело
    @GetMapping("/business/{id}")
    public ResponseEntity<Business> get(@PathVariable("id") Integer id)
    {
        Optional<Business> business = businessRepository.findById(id);
        if (!business.isPresent())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(business.get(), HttpStatus.OK);
    }

    //Обновить дело
    @PutMapping("/business/{id}")
    public ResponseEntity<Business> update(@PathVariable("id") Integer id, @RequestBody Business business)
    {
        Optional<Business> businessOptional = businessRepository.findById(id);
        if (!businessOptional.isPresent())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Business updateBusiness = businessOptional.get();
        updateBusiness.setName(business.getName());
        updateBusiness.setDescription(business.getDescription());
        businessRepository.save(updateBusiness);
        return new ResponseEntity(updateBusiness, HttpStatus.OK);
    }

    //Удаление всего списка
    @DeleteMapping("/businesses/")
    public boolean deleteAll()
    {
        try {
            businessRepository.deleteAll();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //Удаление одного дела
    @DeleteMapping("/business/{id}")
    public boolean deleteBusiness(@PathVariable("id") Integer id)
    {
        try {
            businessRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }


}













