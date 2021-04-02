package fi.hh.swd20.MusicDB.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.hh.swd20.MusicDB.domain.Genre;
import fi.hh.swd20.MusicDB.domain.GenreRepository;

@Controller
public class GenreController {
	
	@Autowired GenreRepository genreRepository;
	
	@RequestMapping(value = "/genrelist", method = RequestMethod.GET)
	public String genreList(Model model) {
		model.addAttribute("genres", genreRepository.findAll());
		return "genrelist";
	}
	
	@RequestMapping(value = "/addgenre", method = RequestMethod.GET)
	public String addGenre(Model model) {
		model.addAttribute("genre", new Genre());
		return "addgenre";
	}
	
	@RequestMapping(value = "/savegenre", method = RequestMethod.POST)
	public String saveGenre(Genre genre) {
		genreRepository.save(genre);
		return "redirect:genrelist";
	}
}
