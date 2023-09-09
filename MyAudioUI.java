//Name: Parampreet Chauhan
//Student Number: 501150706

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI {
    public static void main(String[] args) throws IOException {
        // Simulation of audio content in an online store
        // The songs, podcasts, audiobooks in the store can be downloaded to your
        // library
        AudioContentStore store = new AudioContentStore();
        // Create my music library
        Library library = new Library();

        // Process keyboard actions (Please uncomment the below code if you want to
        // process from keyboard)
        Scanner scanner = new Scanner(System.in);
        System.out.print(">");
        while (scanner.hasNextLine()) {
            try {
                String action = scanner.nextLine();

                if (action == null || action.equals("")) {
                    System.out.print("\n>");
                    continue;
                } else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
                    return;

                else if (action.equalsIgnoreCase("STORE")) // List all songs
                {
                    store.listAll();
                } else if (action.equalsIgnoreCase("SONGS")) // List all songs
                {
                    library.listAllSongs();
                } else if (action.equalsIgnoreCase("BOOKS")) // List all songs
                {
                    library.listAllAudioBooks();
                }
                // else if (action.equalsIgnoreCase("PODCASTS")) // List all songs
                else if (action.equalsIgnoreCase("ARTISTS")) // List all songs
                {
                    library.listAllArtists();
                } else if (action.equalsIgnoreCase("PLAYLISTS")) // List all play lists
                {
                    library.listAllPlaylists();
                } else if (action.equalsIgnoreCase("DOWNLOAD")) {
                    int fromindex = 0;
                    int toIndex = 0;

                    System.out.print("From Store Content #: ");
                    if (scanner.hasNextInt()) {
                        fromindex = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("To Store Content #: ");
                        toIndex = scanner.nextInt();
                        scanner.nextLine();
                    }
                    library.download(fromindex, toIndex);
                } else if (action.equalsIgnoreCase("PLAYSONG")) {
                    System.out.print("Song Number: ");
                    if (scanner.hasNextInt()) {
                        // consume the nl character since nextInt() does not
                        scanner.nextLine();
                    }
                } else if (action.equalsIgnoreCase("BOOKTOC")) {
                    int index = 0;

                    System.out.print("Audio Book Number: ");
                    if (scanner.hasNextInt()) {
                        index = scanner.nextInt();
                        scanner.nextLine();
                    }
                    if (!library.printAudioBookTOC(index))
                        System.out.println(library.getErrorMessage());
                } else if (action.equalsIgnoreCase("PLAYBOOK")) {
                    int index = 0;

                    System.out.print("Audio Book Number: ");
                    if (scanner.hasNextInt()) {
                        index = scanner.nextInt();
                    }
                    int chapter = 0;
                    System.out.print("Chapter: ");
                    if (scanner.hasNextInt()) {
                        chapter = scanner.nextInt();
                        scanner.nextLine();
                    }
                    if (!library.playAudioBook(index, chapter))
                        System.out.println(library.getErrorMessage());
                }
                // else if (action.equalsIgnoreCase("PODTOC")) {
                // int index = 0;
                // int season = 0;
                //
                // System.out.print("Podcast Number: ");
                // if (scanner.hasNextInt()) {
                // index = scanner.nextInt();
                // }
                // System.out.print("Season: ");
                // if (scanner.hasNextInt()) {
                // season = scanner.nextInt();
                // scanner.nextLine();
                // }
                // if (!library.printPodcastEpisodes(index, season))
                // System.out.println(library.getErrorMessage());
                // }
                // else if (action.equalsIgnoreCase("PLAYPOD")) {
                // int index = 0;
                //
                // System.out.print("Podcast Number: ");
                // if (scanner.hasNextInt()) {
                // index = scanner.nextInt();
                // scanner.nextLine();
                // }
                // int season = 0;
                // System.out.print("Season: ");
                // if (scanner.hasNextInt()) {
                // season = scanner.nextInt();
                // scanner.nextLine();
                // }
                // int episode = 0;
                // System.out.print("Episode: ");
                // if (scanner.hasNextInt()) {
                // episode = scanner.nextInt();
                // scanner.nextLine();
                // }
                // if (!library.playPodcast(index, season, episode))
                // System.out.println(library.getErrorMessage());
                // }
                else if (action.equalsIgnoreCase("PLAYALLPL")) {
                    String title = "";

                    System.out.print("Playlist Title: ");
                    if (scanner.hasNextLine()) {
                        title = scanner.nextLine();
                    }
                    if (!library.playPlaylist(title))
                        System.out.println(library.getErrorMessage());
                } else if (action.equalsIgnoreCase("PLAYPL")) {
                    String title = "";
                    int index = 0;

                    System.out.print("Playlist Title: ");
                    if (scanner.hasNextLine()) {
                        title = scanner.nextLine();
                    }
                    System.out.print("Content Number: ");
                    if (scanner.hasNextInt()) {
                        index = scanner.nextInt();
                        scanner.nextLine();
                    }
                    if (!library.playPlaylist(title, index))
                        System.out.println(library.getErrorMessage());
                }
                // Delete a song from the library and any play lists it belongs to
                else if (action.equalsIgnoreCase("DELSONG")) {
                    int songNum = 0;

                    System.out.print("Library Song #: ");
                    if (scanner.hasNextInt()) {
                        songNum = scanner.nextInt();
                        scanner.nextLine();
                    }

                    if (!library.deleteSong(songNum))
                        System.out.println(library.getErrorMessage());
                } else if (action.equalsIgnoreCase("MAKEPL")) {
                    String title = "";

                    System.out.print("Playlist Title: ");
                    if (scanner.hasNextLine()) {
                        title = scanner.nextLine();
                    }
                    if (!library.makePlaylist(title))
                        System.out.println(library.getErrorMessage());
                } else if (action.equalsIgnoreCase("PRINTPL")) // print playlist content
                {
                    String title = "";

                    System.out.print("Playlist Title: ");
                    if (scanner.hasNextLine())
                        title = scanner.nextLine();

                    if (!library.printPlaylist(title))
                        System.out.println(library.getErrorMessage());
                }
                // Add content from library (via index) to a playlist
                else if (action.equalsIgnoreCase("ADDTOPL")) {
                    int contentIndex = 0;
                    String contentType = "";
                    String playlist = "";

                    System.out.print("Playlist Title: ");
                    if (scanner.hasNextLine())
                        playlist = scanner.nextLine();

                    System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
                    if (scanner.hasNextLine())
                        contentType = scanner.nextLine();

                    System.out.print("Library Content #: ");
                    if (scanner.hasNextInt()) {
                        contentIndex = scanner.nextInt();
                        scanner.nextLine(); // consume nl
                    }

                    if (!library.addContentToPlaylist(contentType, contentIndex, playlist))
                        System.out.println(library.getErrorMessage());
                }
                // Delete content from play list
                else if (action.equalsIgnoreCase("DELFROMPL")) {
                    int contentIndex = 0;
                    String playlist = "";

                    System.out.print("Playlist Title: ");
                    if (scanner.hasNextLine())
                        playlist = scanner.nextLine();

                    System.out.print("Playlist Content #: ");
                    if (scanner.hasNextInt()) {
                        contentIndex = scanner.nextInt();
                        scanner.nextLine(); // consume nl
                    }
                    if (!library.delContentFromPlaylist(contentIndex, playlist))
                        System.out.println(library.getErrorMessage());
                } else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
                {
                    library.sortSongsByYear();
                } else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
                {
                    library.sortSongsByName();
                } else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
                {
                    library.sortSongsByLength();
                } else if (action.equalsIgnoreCase("SEARCH")) {
                    System.out.println("Title:");
                    String title = scanner.nextLine();
                    int index = store.searchByTitle(title);
                    if (index != -1) {
                        System.out.println("Content found at index " + (index + 1) + ":");
                        store.getContent(index + 1).printInfo();
                    } else {
                        System.out.println("Content not found.");
                    }
                } else if (action.equalsIgnoreCase("SEARCHA")) {
                    System.out.println("Artist:");
                    String art = scanner.nextLine();
                    ArrayList<Integer> index = store.searchByArtist(art);
                    if (index.isEmpty()) {
                        System.out.println("No audio content found with artist " + art);
                    } else {
                        System.out.println("Audio content with artist " + art + ":");
                        for (int i : index) {
                            System.out.println(i + ": ");
                            store.getContent(i).printInfo();
                        }
                    }
                } else if (action.equalsIgnoreCase("SEARCHG")) {
                    System.out.println("Genre [POP, ROCK,JAZZ,HIPHOP,RAP, CLASSICAL]:");
                    String gen = scanner.nextLine();
                    store.searchByGenre(gen);
                } else if (action.equalsIgnoreCase("DOWNLOADA")) {
                    System.out.println("Artist Name:");
                    String art = scanner.nextLine();
                    store.downloadByArtist(art);
                } else if (action.equalsIgnoreCase("DOWNLOADG")) {
                    System.out.println("Genre:");
                    String gen = scanner.nextLine();
                    store.downloadByGenre(gen);
                } else if (action.equalsIgnoreCase("SEARCHP")) {
                    System.out.println("Search Partially");
                    String tar = scanner.nextLine();
                    store.searchPartial(tar);
                }
                System.out.print("\n>");
            } catch (AudioContentNotFoundException | InvalidAudioContentTypeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
