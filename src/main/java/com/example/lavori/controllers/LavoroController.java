package com.example.lavori.controllers;

import com.example.lavori.models.Lavoro;
import com.example.lavori.services.Impl.LavoroServiceImpl;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/lavoro")
@RestController
public class LavoroController {

    private final LavoroServiceImpl lavoroServiceImpl;
    @Autowired
    public LavoroController(LavoroServiceImpl lavoroServiceImpl){this.lavoroServiceImpl = lavoroServiceImpl;}

    @PostMapping
    public void addLavoro(@RequestBody Lavoro lavoro){lavoroServiceImpl.addLavoro(lavoro);}

    @GetMapping
    public List<Lavoro> getAllLavori(){return lavoroServiceImpl.getAllLavori();}

    @GetMapping(path = "/singleLavoro/{id}")
    public Lavoro getLavoroById(@PathVariable("id") Long id){
        return lavoroServiceImpl.getLavoroById(id)
                .orElse(null);
    }
    @PutMapping(path = "/{id}")
    public void updateLavoro(@PathVariable("id") Long id,@RequestBody Lavoro lavoro){
        lavoroServiceImpl.updateLavoro(id, lavoro);
    }

    @GetMapping(path = "/{name}")
    public List<Lavoro> findByUserName(@PathVariable("name") String name){
        return lavoroServiceImpl.findByUserName(name);
    }

    @DeleteMapping(path = "{id}")
    public void deleteLavoro(@PathVariable("id") Long id){
        lavoroServiceImpl.deleteLavoro(id);
    }
}
