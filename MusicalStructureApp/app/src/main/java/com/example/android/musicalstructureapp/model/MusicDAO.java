package com.example.android.musicalstructureapp.model;

import java.util.ArrayList;
import java.util.Arrays;

public class MusicDAO {

    private ArrayList<Music> musics = new ArrayList();

    private Artist artistA = new Artist(1, "artist A", "Lorem ipsum dolor sit amet," +
            " consectetur adipiscing elit. Mauris id suscipit neque. Integer venenatis sem magna");
    private Artist artistB = new Artist(2, "artist B", "Lorem ipsum dolor sit amet," +
            " consectetur adipiscing elit. Mauris id suscipit neque. Integer venenatis sem magna");
    private Artist artistC = new Artist(3, "artist C", "Lorem ipsum dolor sit amet," +
            " consectetur adipiscing elit.");

    public MusicDAO() {
        musics.addAll(Arrays.asList(
                new Music("musics 01", artistA),
                new Music("musics 02", artistA),
                new Music("musics 03", artistA),
                new Music("musics 04", artistA),
                new Music("musics 05", artistB),
                new Music("musics 06", artistB),
                new Music("musics 07", artistB),
                new Music("musics 08", artistA),
                new Music("musics 09", artistC),
                new Music("musics 10", artistC),
                new Music("musics 11", artistC),
                new Music("musics 12", artistC)
        ));
    }

    public ArrayList<Music> getMusics() {
        return musics;
    }

    public ArrayList<Artist> getArtists() {
        return new ArrayList<>(Arrays.asList(artistA, artistB, artistC));
    }

    public Artist getArtist(int id) {

        for (Artist artist : getArtists()) {
            if (artist.getId() == id) {
                return artist;
            }
        }
        return null;
    }
}
