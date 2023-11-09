package com.example.Lavori.controllers;

import com.example.Lavori.models.Lavoro;
import com.example.Lavori.services.LavoroServiceImpl;
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
}
