package fi.hh.swd20.MusicDB.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.hh.swd20.MusicDB.domain.Song;
import fi.hh.swd20.MusicDB.domain.SongRepository;

@Controller
public class SongController {
	
	@Autowired
	private SongRepository songRepository;

	@RequestMapping(value = "/songlist", method = RequestMethod.GET)
	public String songList(Model model) {
		model.addAttribute("songs", songRepository.findAll());
		return "songlist";
	}
	
}
