package br.udesc.ceavi.eso.dsw.primeiroprojeto;

import org.springframework.web.bind.annotation.*;

@RestController
public class InicioController {


    @GetMapping("/inicio")
    public String inicio(@RequestParam(value = "nome" , defaultValue = "World") String name){
        return String.format("Olá %s!", name);
    }

    @GetMapping(path ="/bean")
    public PrimeiroBean bean(){
        return  new PrimeiroBean("Olá Mundo!");
    }



}
