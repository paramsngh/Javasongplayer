//Name: Parampreet Chauhan
//Student Number: 501150706

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore {
    private final List<AudioContent> contents;
    private final Map<String, Integer> titleMap;
    private final Map<String, ArrayList<Integer>> genreMap;
    private final Map<String, ArrayList<Integer>> authorMap;

    public AudioContentStore() throws IOException {
        contents = readContentsFromFile();
        titleMap = new HashMap<>();
        authorMap = new HashMap<>();
        genreMap = new HashMap<>();
    }

    public AudioContent getContent(int index) {
        if (index < 1 || index > contents.size()) {
            throw new AudioContentNotFoundException("Audiocontent not found in library.");
        }
        return contents.get(index - 1);
    }

    public List<AudioContent> getAudioContents() {
        return contents;
    }

    public void listAll() {
        for (int i = 0; i < contents.size(); i++) {
            int index = i + 1;
            System.out.print(index + ". ");
            contents.get(i).printInfo();
            System.out.println();
        }
    }

    public int addContent(AudioContent content) {
        int index = contents.size();
        contents.add(content);
        if (content instanceof Song) {
            Song song = (Song) content;
            if (!genreMap.containsKey(song.getGenre().name())) {
                genreMap.put(song.getGenre().name(), new ArrayList<Integer>());
            }
            genreMap.get(song.getGenre().name()).add(index);
        } else if (content instanceof AudioBook) {
            AudioBook audioBook = (AudioBook) content;
            if (!authorMap.containsKey(audioBook.getAuthor())) {
                authorMap.put(audioBook.getAuthor(), new ArrayList<Integer>());
            }
            authorMap.get(audioBook.getAuthor()).add(index);
        }
        titleMap.put(content.getTitle(), index);
        return index;
    }

    public int searchByTitle(String title) {
        if (titleMap.containsKey(title)) {
            return titleMap.get(title);
        } else {
            for (int i = 0; i < contents.size(); i++) {
                AudioContent content = contents.get(i);
                if (content.getTitle().equals(title)) {
                    titleMap.put(title, i);
                    return i;
                }
            }
            return -1;
        }
    }

    public ArrayList<Integer> searchByArtist(String name) {
        ArrayList<Integer> indices = new ArrayList<>();
        if (authorMap.containsKey(name)) {
            indices.addAll(authorMap.get(name));
        } else {
            for (int i = 0; i < contents.size(); i++) {
                AudioContent content = contents.get(i);
                if (content instanceof Song) {
                    String artist = ((Song) content).getArtist();
                    if (!authorMap.containsKey(artist)) {
                        authorMap.put(artist, new ArrayList<>());
                    }
                    if (name.equalsIgnoreCase(artist)) {
                        indices.add(i + 1);
                    }
                    authorMap.get(artist).add(i);
                } else if (content instanceof AudioBook) {
                    String author = ((AudioBook) content).getAuthor();
                    if (!authorMap.containsKey(author)) {
                        authorMap.put(author, new ArrayList<>());
                    }
                    if (name.equalsIgnoreCase(author)) {
                        indices.add(i + 1);
                    }
                    authorMap.get(author).add(i);
                }
            }
        }
        return indices;
    }

    public void searchByGenre(String genre) {
        Map<String, ArrayList<Integer>> genreMap = new HashMap<>();
        for (int i = 0; i < contents.size(); i++) {
            AudioContent content = contents.get(i);
            if (content instanceof Song) {
                Song song = (Song) content;
                if (song.getGenre().name().equals(genre)) {
                    ArrayList<Integer> indices = genreMap.getOrDefault(genre, new ArrayList<Integer>());
                    indices.add(i);
                    genreMap.put(genre, indices);
                }
            }
        }
        ArrayList<Integer> indices = genreMap.get(genre);
        if (indices != null) {
            for (int index : indices) {
                AudioContent content = contents.get(index);
                System.out.println(index + 1 + ": ");
                content.printInfo();
            }
        } else {
            System.out.println("No audio content found with genre " + genre);
        }
    }

    public void downloadByArtist(String artist) {
        List<Integer> indices = searchByArtist(artist);
        for (Integer index : indices) {
            AudioContent content = contents.get(index);
            if (contents.contains(content)) {
                System.out.println(content.getType() + " " + content.getTitle() + " is already in the library.");
            } else {
                contents.add(content);
                System.out.println(content.getType() + " " + content.getTitle() + " downloaded successfully.");
            }
        }
    }

    // public void downloadByGenre(String genre) {
    // searchByGenre(genre);
    // ArrayList<Integer> indices = genreMap.get(genre.toUpperCase());
    // if (indices == null || indices.isEmpty()) {
    // System.out.println("No audio content found in the store for genre " + genre);
    // return;
    // }
    // for (Integer index : indices) {
    // AudioContent content = contents.get(index);
    // if (content instanceof Song) {
    // if (contents.contains(content)) {
    // System.out.println(content.getType()+" " + content.getTitle() + " is already
    // in the library.");
    // } else {
    // contents.add(content);
    // System.out.println(content.getType()+" " + content.getTitle() + " downloaded
    // successfully.");
    // }
    // }
    // }
    // }

    public ArrayList<Song> getSongsByGenre(String genre) {
        ArrayList<Song> songsByGenre = new ArrayList<>();

        for (AudioContent content : contents) {
            if (content instanceof Song) {
                Song song = (Song) content;
                if (song.getGenre().name().equalsIgnoreCase(genre)) {
                    songsByGenre.add(song);
                }
            }
        }

        return songsByGenre;
    }

    public void downloadByGenre(String genre) {
        ArrayList<Song> songsByGenre = getSongsByGenre(genre);

        for (Song song : songsByGenre) {
            if (contents.contains(song)) {
                System.out.println("SONG " + song.getTitle() + " is already in the library.");
            } else {
                contents.add(song);
                System.out.println("SONG " + song.getTitle() + " downloaded successfully.");
            }
        }
    }

    public void searchPartial(String target) {
        for (int i = 0; i < contents.size(); i++) {
            AudioContent ac = contents.get(i);
            if (ac instanceof Song) {
                Song song = (Song) contents.get(i);
                if (song.getTitle().contains(target) || song.getComposer().contains(target)
                        || song.getGenre().name().contains(target) || song.getLyrics().contains(target)) {
                    System.out.println("Index: " + i + ", Info: " + song.getTitle());
                }
            }
            if (ac instanceof AudioBook) {
                AudioBook audioBook = (AudioBook) contents.get(i);
                if (audioBook.getTitle().contains(target) || audioBook.getAuthor().contains(target)
                        || audioBook.getNarrator().contains(target) || audioBook.getChapters().contains(target)
                        || audioBook.getChapterTitles().contains(target)) {
                    System.out.println("Index: " + i + ", Info: " + audioBook.getTitle());
                }
            }

        }
    }

    private ArrayList<AudioContent> readContentsFromFile() throws IOException {
        ArrayList<AudioContent> contents = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("store.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("SONG")) {
                    System.out.println("LOADING SONG");
                    String id = reader.readLine();
                    String title = reader.readLine();
                    int year = Integer.parseInt(reader.readLine());
                    int length = Integer.parseInt(reader.readLine());
                    String artist = reader.readLine();
                    String composer = reader.readLine();
                    String genre = reader.readLine();
                    int lyricsLines = Integer.parseInt(reader.readLine());
                    ArrayList<String> lyrics = new ArrayList<>();
                    for (int i = 0; i < lyricsLines; i++) {
                        lyrics.add(reader.readLine());
                    }
                    contents.add(

                            new Song(id, title, year, length, Song.TYPENAME, artist, composer, genre, lyrics));
                } else if (line.equals("AUDIOBOOK")) {
                    System.out.println("LOADING AUDIOBOOK");
                    String id = reader.readLine();
                    String title = reader.readLine();
                    int year = Integer.parseInt(reader.readLine());
                    int length = Integer.parseInt(reader.readLine());
                    String author = reader.readLine();
                    String narrator = reader.readLine();
                    int chapters = Integer.parseInt(reader.readLine());
                    ArrayList<String> chapterList = new ArrayList<>();
                    for (int i = 0; i < chapters; i++) {
                        chapterList.add(reader.readLine());
                    }
                    int chapterLines = Integer.parseInt(reader.readLine());
                    ArrayList<String> chapterContent = new ArrayList<>();
                    for (int j = 0; j < chapterLines; j++) {
                        chapterContent.add(reader.readLine());
                    }
                    contents.add(new AudioBook(id, title, year, length, AudioBook.TYPENAME, author, narrator,
                            chapterList, chapterContent));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }

        return contents;
    }
}
