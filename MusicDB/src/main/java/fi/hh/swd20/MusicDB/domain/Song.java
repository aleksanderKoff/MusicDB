package fi.hh.swd20.MusicDB.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Song {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long songId;
	private String title;
	private String artist;
	private String album;
	private String year;
	private String length;
	
	@ManyToOne
	@JsonIgnoreProperties ("songs")
	@JoinColumn(name = "genreid")
	private Genre genre;
	
	@ManyToMany (cascade = {
				CascadeType.MERGE,
				CascadeType.PERSIST
				})
	@JoinTable(
			name = "Song_Playlist",
			joinColumns =  @JoinColumn(name = "songId") ,
			inverseJoinColumns =  @JoinColumn(name = "playlistId") 
		)
		Set<Playlist> playlists = new HashSet<>();
	
	public Song() {
		super();
	}

	public Song(String title, String artist, String album, String year, String length, Genre genre) {
		super();
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.year = year;
		this.length = length;
		this.genre = genre;
	}

	public Long getSongId() {
		return songId;
	}

	public void setSongId(Long songId) {
		this.songId = songId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Set<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(Set<Playlist> playlists) {
		this.playlists = playlists;
	}

	@Override
	public String toString() {
		return "Song [id=" + songId + ", title=" + title + ", artist=" + artist + ", album=" + album + ", year=" + year
				+ ", length=" + length + "]";
	}

	
}
