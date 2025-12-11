/**
 * Simple demo showing basic usage of the MusicPlaylist component.
 *
 * This example creates a playlist, adds songs, removes a song, checks if the
 * playlist is empty, and prints the playlist at each step.
 *
 * @author Rachel Thoi
 */
public final class PlaylistDemo1 {

    /**
     * Private constructor to prevent instantiation.
     */
    private PlaylistDemo1() {
        // not called
    }

    /**
     * Main method for basic MusicPlaylist usage.
     *
     * @param args
     *            command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== PlaylistDemo1: Basic MusicPlaylist Usage ===");

        // Create a new playlist
        MusicPlaylist playlist = new MusicPlaylist1L();

        System.out.println("Is playlist empty? " + playlist.isEmpty());
        System.out.println("Current playlist: " + playlist);
        System.out.println();

        // Add some songs
        System.out.println("Adding songs to the playlist...");
        playlist.addSong("Love Dive - IVE");
        playlist.addSong("OMG - NewJeans");
        playlist.addSong("Lovesick Girls - BLACKPINK");
        playlist.addSong("Next Level - aespa");

        System.out.println("Playlist after adding songs: " + playlist);
        System.out.println("Playlist size: " + playlist.size());
        System.out.println("Contains 'OMG - NewJeans'? "
                + playlist.containsSong("OMG - NewJeans"));
        System.out.println("Contains 'Hype Boy - NewJeans'? "
                + playlist.containsSong("Hype Boy - NewJeans"));
        System.out.println();

        // Remove the most recently added song
        System.out.println("Removing the last added song...");
        String removed = playlist.removeSong();
        System.out.println("Removed: " + removed);
        System.out.println("Playlist after removal: " + playlist);
        System.out.println("Playlist size: " + playlist.size());
        System.out.println();

        // Check empty again
        System.out.println("Is playlist empty now? " + playlist.isEmpty());
        System.out.println("Final playlist: " + playlist);

        System.out.println("=== End of PlaylistDemo1 ===");
    }
}
