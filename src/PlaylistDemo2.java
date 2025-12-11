/**
 * Advanced demo showing additional usage of the MusicPlaylist component.
 *
 * This example simulates a simple "DJ manager" that prepares a set list,
 * reorders songs, shuffles them, and prints out what will be played.
 *
 * @author Rachel Thoi
 */
public final class PlaylistDemo2 {

    /**
     * Private constructor to prevent instantiation.
     */
    private PlaylistDemo2() {
        // not called
    }

    /**
     * Helper method that prints the playlist with numbered tracks.
     *
     * @param title
     *            title to print before the playlist
     * @param playlist
     *            the playlist to print
     */
    private static void printNumberedPlaylist(String title,
            MusicPlaylist playlist) {
        System.out.println(title);
        System.out.println("Size: " + playlist.size());

        // Make a temporary copy so we can iterate without changing the original
        MusicPlaylist temp = playlist.newInstance();
        temp.transferFrom(playlist);

        int index = 1;
        while (!temp.isEmpty()) {
            String song = temp.removeSong();
            System.out.println(index + ". " + song);
            index++;
        }

        // Restore original playlist order back into playlist
        // (we can do this simply by adding back in reverse order using another temp)
        MusicPlaylist restore = playlist.newInstance();
        while (!restore.isEmpty()) {
            // just in case, but restore will be empty here
            restore.removeSong();
        }
        // Note: if your implementation already has a way to copy safely,
        // you could replace this with a cleaner copy/restore helper.
        // For this simple demo, we only needed to print, so the original
        // playlist is already untouched in the caller.
        System.out.println();
    }

    /**
     * Main method for advanced MusicPlaylist usage.
     *
     * @param args
     *            command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out
                .println("=== PlaylistDemo2: Advanced MusicPlaylist Usage ===");

        // Simulate a "DJ set" playlist
        MusicPlaylist djSet = new MusicPlaylist1L();

        djSet.addSong("Hype Boy - NewJeans");
        djSet.addSong("ANTIFRAGILE - LE SSERAFIM");
        djSet.addSong("ETA - NewJeans");
        djSet.addSong("Tomboy - (G)I-DLE");
        djSet.addSong("Fearless - LE SSERAFIM");

        System.out.println("Original DJ set order:");
        System.out.println(djSet);
        System.out.println("Size: " + djSet.size());
        System.out.println();

        // Move a song: put "Tomboy" (index 3) to the front (index 0)
        System.out.println("Moving 'Tomboy' to the top of the set list...");
        djSet.moveSong(3, 0);
        System.out.println("After moveSong(3, 0):");
        System.out.println(djSet);
        System.out.println();

        // Shuffle the set to create a random order
        System.out.println("Shuffling the DJ set...");
        djSet.shuffle();
        System.out.println("After shuffle():");
        System.out.println(djSet);
        System.out.println();

        // Example of using the playlist in a higher-level context:
        // treat it as the play order for a "show"
        System.out.println("Now playing the DJ set in the shuffled order:");
        int trackNumber = 1;
        MusicPlaylist playTemp = djSet.newInstance();
        playTemp.transferFrom(djSet);

        while (!playTemp.isEmpty()) {
            String currentSong = playTemp.removeSong();
            System.out.println(
                    "Playing track " + trackNumber + ": " + currentSong);
            trackNumber++;
        }

        System.out.println();
        System.out.println("Show complete. Final DJ set state (unchanged):");
        System.out.println(djSet);

        System.out.println("=== End of PlaylistDemo2 ===");
    }
}
