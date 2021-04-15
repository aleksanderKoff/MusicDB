package fi.hh.swd20.MusicDB.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.hh.swd20.MusicDB.domain.Playlist;
import fi.hh.swd20.MusicDB.domain.PlaylistRepository;

@Controller
public class PlaylistController {

	@Autowired PlaylistRepository playlistRepository;
	
	@RequestMapping(value = "/playlists", method = RequestMethod.GET)
	private String playLists(Model model) {
		model.addAttribute("playlists", playlistRepository.findAll());
		return "playlists";
	}
	
	@RequestMapping(value = "/addplaylist", method = RequestMethod.GET)
	private String addPlaylist(Model model) {
		model.addAttribute("playlist", new Playlist());
		return "addplaylist";
	}
	
	@RequestMapping(value = "/saveplaylist", method = RequestMethod.POST)
	private String savePlaylist(Playlist playlist) {
		playlistRepository.save(playlist);
		return "redirect:playlists";
	}
}

