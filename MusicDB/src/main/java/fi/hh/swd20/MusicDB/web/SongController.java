package fi.hh.swd20.MusicDB.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.hh.swd20.MusicDB.domain.Song;
import fi.hh.swd20.MusicDB.domain.SongRepository;
import fi.hh.swd20.MusicDB.domain.Genre;
import fi.hh.swd20.MusicDB.domain.GenreRepository;
import fi.hh.swd20.MusicDB.domain.Playlist;
import fi.hh.swd20.MusicDB.domain.PlaylistRepository;

@Controller
public class SongController {
	
	@Autowired
	private SongRepository songRepository;
	@Autowired
	private GenreRepository genreRepository;
	@Autowired
	private PlaylistRepository playlistRepository;

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/songlist", method = RequestMethod.GET)
	public String songList(Model model) {
		model.addAttribute("songs", songRepository.findAll());
		return "songlist";
	}
	
	@RequestMapping(value = "/addsong", method = RequestMethod.GET)
	public String addSong(Model model) {
		model.addAttribute("song", new Song());
		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("playlists", playlistRepository.findAll());
		return "addsong";
	}
	
	@RequestMapping(value = "/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editSong(@PathVariable(value = "id") Long songId, Model model) {
		model.addAttribute("song", songRepository.findById(songId));
		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("playlists", playlistRepository.findAll());
		return "editsong";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteSong(@PathVariable(value = "id") Long songId) {
		songRepository.deleteById(songId);
		return "redirect:../songlist";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid Song song, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			// model.addAttribute("song", new Song());
			model.addAttribute("genres", genreRepository.findAll());
			model.addAttribute("playlists", playlistRepository.findAll());
			return "addsong";
		} else {
		model.addAttribute("song", song);
		songRepository.save(song);
		return "redirect:songlist";
		}
	}
	
	@RequestMapping(value = "/songs", method = RequestMethod.GET)
	public @ResponseBody List<Song> songListRest() {
		return (List<Song>) songRepository.findAll();
	}
	
	@RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Song> findSongRest(@PathVariable("id") Long songId) {
		return songRepository.findById(songId);
	}
	
	@RequestMapping(value = "/books", method = RequestMethod.POST)
	public @ResponseBody Song saveSongRest(@RequestBody Song song) {
		return songRepository.save(song);
	}
}
