//Name: Parampreet Chauhan
//Student Number: 501150706

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks.
 */
public class Library {
    private final ArrayList<AudioContent> audiolibrary;
    private ArrayList<Song> songs;
    private ArrayList<Podcast> podcasts;
    private ArrayList<AudioBook> audiobooks;
    private ArrayList<Playlist> playlists;
    AudioContentStore audioContentStore = new AudioContentStore();

    // Public methods in this class set errorMsg string
    // Error Messages can be retrieved from main in class MyAudioUI by calling
    // getErrorMessage()
    // In assignment 2 we will replace this with Java Exceptions
    String errorMsg = "";

    public String getErrorMessage() {
        return errorMsg;
    }

    // AudioContentStore store = new AudioContentStore();

    public Library() throws IOException {
        songs = new ArrayList<Song>();
        // podcasts = new ArrayList<Podcast>();
        audiobooks = new ArrayList<>();
        playlists = new ArrayList<>();

        audiolibrary = new ArrayList<>();
        // contents.addAll(store.getAudioContents());
    }

    /*
     * Download audio content from the store. Since we have decided (design
     * decision) to keep 3 separate lists in our library
     * to store our songs, podcasts and audiobooks (we could have used one list)
     * then we need to look at the type of
     * audio content to determine which list it belongs to above
     *
     * Make sure you do not add song/podcast/audiobook to a list if it is already
     * there. Hint: use the equals() method
     * If it is already in a list, set the errorMsg string and return false.
     * Otherwise add it to the list and return true
     */
    // public boolean download(AudioContent content) {
    // if (content.getType().equals(Song.TYPENAME)) {
    // if (songs.contains(content)) {
    // errorMsg = "Song already downloaded";
    // return false;
    // }
    // songs.add((Song) content);
    // } else if (content.getType().equals(AudioBook.TYPENAME)) {
    // if (audiobooks.contains(content)) {
    // errorMsg = "AudioBook already downloaded";
    // return false;
    // }
    // audiobooks.add((AudioBook) content);
    // }
    //// if (content.getType().equals(Podcast.TYPENAME)) {
    //// if (podcasts.contains(content)) {
    //// errorMsg = "Podcast already downloaded";
    //// return false;
    //// }
    //// podcasts.add((Podcast) content);
    //// }
    // return true;
    // }

    public void download(int fromIndex, int toIndex) throws IOException {
        for (int i = fromIndex - 1; i < toIndex; i++) {
            AudioContent content = audioContentStore.getAudioContents().get(i);

            if (audiolibrary.contains(content)) {
                System.out.println(content.getType() + " " + content.getTitle() + " already downloaded.");
            } else {
                audiolibrary.add(content);
                System.out.println(content.getType() + " " + content.getTitle() + " Added to Library.");
            }
        }
    }

    // Print Information (printInfo()) about all songs in the array list
    public void listAllSongs() {
        List<AudioContent> audioContentList = audioContentStore.getAudioContents();
        for (int i = 0; i < audioContentList.size() - 3; i++) {
            int index = i + 1;
            System.out.print(index + ". ");

            audioContentList.get(i).printInfo();
            System.out.println();

            if ("SONGS".equalsIgnoreCase(audioContentList.get(i).getType())) {

                System.out.println();
            }

        }
    }

    // Print Information (printInfo()) about all audiobooks in the array list
    public void listAllAudioBooks() {
        List<AudioContent> audioContentList = audioContentStore.getAudioContents();
        for (int i = 9; i < audioContentList.size(); i++) {
            int index = i + 1;
            System.out.print(index + ". ");
            if ("AUDIOBOOK".equalsIgnoreCase(audioContentList.get(i).getType())) {
                audioContentList.get(i).printInfo();
                System.out.println();
            }

        }
    }

    // Print Information (printInfo()) about all podcasts in the array list
    // public void listAllPodcasts() {
    // for (int i = 0; i < podcasts.size(); i++) {
    // int index = i + 1;
    // System.out.print(index + ". ");
    // podcasts.get(i).printInfo();
    // }
    // }

    // Print the name of all playlists in the playlists array list
    // First print the index number as in listAllSongs() above
    public void listAllPlaylists() {
        for (int i = 0; i < playlists.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + playlists.get(i).getTitle());
        }
    }

    // Print the name of all artists.
    // Go through the songs array list and add to an arraylist only if it is
    // not already there. Once the artist arraylist is complete, print the artists
    // names
    public void listAllArtists() {
        ArrayList<String> artists = new ArrayList<String>();

        for (Song song : songs) {
            if (!artists.contains(song.getArtist()))
                artists.add(song.getArtist());
        }
        for (int i = 0; i < artists.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + artists.get(i));
        }
    }

    // Delete a song from the library (i.e. the songs list) -
    // also go through all playlists and remove it from any playlist as well
    public boolean deleteSong(int index) {
        if (index < 1 || index > songs.size()) {
            errorMsg = "Song Not Found";
            return false;
        }
        Song song = songs.remove(index - 1);

        // Search all playlists
        for (int i = 0; i < playlists.size(); i++) {
            Playlist pl = playlists.get(i);
            if (pl.getContent().contains(song))
                pl.getContent().remove(song);
        }
        return true;
    }

    // Sort songs in library by year
    public void sortSongsByYear() {
        Collections.sort(songs, new SongYearComparator());
    }

    private class SongYearComparator implements Comparator<Song> {
        public int compare(Song otherA, Song otherB) {
            if (otherA.getYear() > otherB.getYear())
                return 1;
            if (otherA.getYear() < otherB.getYear())
                return -1;
            return 0;
        }
    }

    // Sort songs by length
    public void sortSongsByLength() {
        Collections.sort(songs, new SongLengthComparator());
    }

    private class SongLengthComparator implements Comparator<Song> {
        public int compare(Song otherA, Song otherB) {
            if (otherA.getLength() > otherB.getLength())
                return 1;
            if (otherA.getLength() < otherB.getLength())
                return -1;
            return 0;
        }
    }

    // Sort songs by title (Comparable)
    public void sortSongsByName() {
        Collections.sort(songs);
    }

    /*
     * Play Content
     */

    // Play song from songs list
    public void playSong(int index) {
        if (index < 0 || index >= audiolibrary.size()) {
            throw new AudioContentNotFoundException("Song not found in library.");
        }
        AudioContent content = audiolibrary.get(index);
        if (content instanceof Song) {
            Song song = (Song) content;
            // play song
        } else {
            throw new InvalidAudioContentTypeException("Invalid audio content type.");
        }
    }

    // Play podcast from list
    // public boolean playPodcast(int index, int season, int episode) {
    // if (index < 1 || index > podcasts.size()) {
    // errorMsg = "Podcast Not Found";
    // return false;
    // }
    // Podcast podcast = podcasts.get(index - 1);
    // if (season < 1 || season > podcast.getSeasons().size()) {
    // errorMsg = "Season Not Found";
    // return false;
    // }
    //
    // if (index < 1 || index > podcast.getSeasons().get(season -
    // 1).episodeTitles.size()) {
    // errorMsg = "Episode Not Found";
    // return false;
    // }
    // podcast.setSeason(season - 1);
    // podcast.setEpisode(episode - 1);
    // podcast.play();
    // return true;
    // }

    // public boolean printPodcastEpisodes(int index, int season) {
    // if (index < 1 || index > podcasts.size()) {
    // errorMsg = "Podcast Not Found";
    // return false;
    // }
    // Podcast podcast = podcasts.get(index - 1);
    // podcast.printSeasonEpisodes(season);
    // return true;
    // }

    // Play audio book from list
    public boolean playAudioBook(int index, int chapter) {
        if (index < 1 || index > audiobooks.size()) {
            errorMsg = "AudioBook Not Found";
            return false;
        }
        AudioBook book = audiobooks.get(index - 1);
        if (chapter < 1 || chapter > book.getNumberOfChapters()) {
            errorMsg = "AudioBook Chapter Not Found";
            return false;
        }
        book.selectChapter(chapter);
        book.play();
        return true;
    }

    public boolean printAudioBookTOC(int index) {
        if (index < 1 || index > audiobooks.size()) {
            errorMsg = "AudioBook Not Found";
            return false;
        }
        AudioBook book = audiobooks.get(index - 1);
        book.printTOC();
        return true;
    }

    /*
     * Playlist Related Methods
     */

    // Make a new playlist
    // Make sure a playlist with the same title doesn't already exist
    public boolean makePlaylist(String title) {
        Playlist pl = new Playlist(title);
        if (playlists.contains(pl)) {
            errorMsg = "Playlist " + title + " Already Exists";
            return false;
        }
        playlists.add(pl);
        return true;
    }

    // Print list of content (songs, audiobooks etc) in playlist named title from
    // list of playlists
    public boolean printPlaylist(String title) {
        int index = playlists.indexOf(new Playlist(title));

        if (index == -1) {
            errorMsg = "Playlist Not Found";
            return false;
        }
        playlists.get(index).printContents();
        return true;
    }

    // Play all content in a playlist
    public boolean playPlaylist(String playlistTitle) {
        int index = playlists.indexOf(new Playlist(playlistTitle));

        if (index == -1) {
            errorMsg = "Playlist Not Found";
            return false;
        }
        playlists.get(index).playAll();
        return true;
    }

    // Play a specific song/audiobook in a playlist
    public boolean playPlaylist(String playlistTitle, int index) {
        int plIndex = playlists.indexOf(new Playlist(playlistTitle));

        if (plIndex == -1) {
            errorMsg = "Playlist Not Found";
            return false;
        }
        Playlist pl = playlists.get(plIndex);

        if (index < 1 || index > pl.getContent().size()) {
            errorMsg = "Invalid Playlist AudioContent #";
            return false;
        }
        System.out.println(pl.getTitle());

        // Play chapter 1 if this is an audio book. Could also set it to play all
        if (pl.getContent(index).getType().equals(AudioBook.TYPENAME)) {
            AudioBook book = (AudioBook) pl.getContent(index);
            book.selectChapter(1);
        }

        pl.play(index);
        return true;
    }

    // Add a song/audiobook/podcast from library to a playlist
    public boolean addContentToPlaylist(String type, int index, String playlistTitle) {
        AudioContent ac = null;

        if (type.equalsIgnoreCase("SONG")) {
            if (index < 1 || index > songs.size()) {
                errorMsg = "Song Not Found";
                return false;
            }
            ac = songs.get(index - 1);
        } else if (type.equalsIgnoreCase("AUDIOBOOK")) {
            if (index < 1 || index > audiobooks.size()) {
                errorMsg = "AudioBook Not Found";
                return false;
            }
            ac = audiobooks.get(index - 1);
        }
        // else if (type.equalsIgnoreCase("PODCAST")) {
        // if (index < 1 || index > podcasts.size()) {
        // errorMsg = "Podcast Not Found";
        // return false;
        // }
        // ac = podcasts.get(index - 1);
        // }

        return true;
    }

    // Delete a song/audiobook/podcast from a playlist
    // Make sure the index is valid
    public boolean delContentFromPlaylist(int index, String playlistTitle) {
        int plIndex = playlists.indexOf(new Playlist(playlistTitle));

        if (plIndex == -1) {
            errorMsg = "Playlist Not Found";
            return false;
        }
        Playlist pl = playlists.get(plIndex);

        // Delete Content
        if (!pl.contains(index)) {
            errorMsg = "Content Not In Playlist";
            return false;
        }
        pl.deleteContent(index);
        return true;
    }
}

class AudioContentNotFoundException extends RuntimeException {
    public AudioContentNotFoundException(String s) {
    }
}

class InvalidAudioContentTypeException extends RuntimeException {
    public InvalidAudioContentTypeException(String s) {
    }
}
