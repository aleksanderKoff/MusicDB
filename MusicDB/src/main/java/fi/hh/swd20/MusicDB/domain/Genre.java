package fi.hh.swd20.MusicDB.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Genre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long genreId;
	@Size(min=2, max=20)
	private String name;
	
	@JsonIgnoreProperties ("genre")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
	private List<Song> songs;
	
	public Genre() {
		super();
	}

	public Genre(String name) {
		super();
		this.name = name;
	}

	public Long getGenreId() {
		return genreId;
	}

	public void setGenreId(Long genreId) {
		this.genreId = genreId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		return "Genre [id=" + genreId + ", name=" + name + "]";
	}
	
	
	
	
}
