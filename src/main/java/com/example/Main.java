package com.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@SpringBootApplication
public class Main {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Main.class, args);
	}
	
	@RequestMapping("/system-ekspercki")
	String systemyEksperckie() {
		
		return "system-ekspercki";
	}
	
	@RequestMapping(value="/system-ekspercki-rezultat")
	String systemyEksperckieRezultat(
			Model model,
			@RequestParam("staz") int staz,
			@RequestParam("wiek") int wiek,
			@RequestParam("dochod") int dochod,
			@RequestParam("wyksztalcenie") boolean czyWyzszeWyksztalcenie,
			@RequestParam("majatek") boolean czyMajatekPowyzej100Tys,
			@RequestParam("posiadaneKredyty") int posiadaneKredyty,
			@RequestParam("sumaRat") int sumaRat) {
		int wynik = 0;
		
		System.out.println(staz);
		System.out.println(wiek);
		System.out.println(dochod);
		System.out.println(czyWyzszeWyksztalcenie);
		System.out.println(czyMajatekPowyzej100Tys);
		System.out.println(posiadaneKredyty);
		System.out.println(sumaRat);
		System.out.println((double)(0.33 * dochod));
		
		//sprawdzenie stazu pracy
		if (staz > 0 && staz <= 3) wynik += 4;
		else if(staz > 3) wynik += 12;
		
		//sprawdzenie wieku
		if (wiek > 18 && wiek < 25) wynik += 2;
		else if (wiek >= 25 && wiek < 34) wynik += 6;
		else if (wiek >= 35 && wiek < 49) wynik += 8;
		
		//sprawdzenie dochodu
		if (dochod < 3000) wynik += 5;
		else if (dochod >= 3000 && dochod < 6000) wynik += 20;
		else wynik += 34;
		
		//sprawdzenie wyksztalcenia
		if (czyWyzszeWyksztalcenie) wynik += 6;
		
		//sprawdzenie wielkosci majątku
		if (czyMajatekPowyzej100Tys) wynik += 15;
		else wynik += 5;
		
		//sprawdzenie ilosci posiadanych kredytów
		if (posiadaneKredyty < 2) wynik += 10;
		
		//sprawdzenie sumy rat
		if ((double)sumaRat < (double)(0.33 * dochod)) {
			wynik += 15;
		}
		
		if(wynik > 67) model.addAttribute("message", "Otrzymasz kredyt z dużą szansą");
		else if(wynik < 67 && wynik > 33) model.addAttribute("message", "Otrzymasz kredyt ze średnią szansą");
		else model.addAttribute("message", "Otrzymasz kredyt z małą szansą");
		model.addAttribute("wynik", wynik);
		
		return "system-ekspercki-rezultat";
	}
}
