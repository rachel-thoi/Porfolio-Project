import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Proof-of-concept for a MusicPlaylist component.
 *
 * This is NOT an OSU component yet (no Standard, no kernel/secondary split).
 * The goal is just to prove that the general idea and behavior are reasonable.
 */
public class MusicPlaylistPOC {

    /**
     * Representation of the playlist. For the proof-of-concept, each song is
     * stored as a String (e.g., "Artist - Title").
     */
    private final List<String> songs;

    /**
     * Creates an empty playlist.
     */
    public MusicPlaylistPOC() {
        this.songs = new ArrayList<>();
    }

    // =========================
    // "Kernel-like" operations
    // =========================

    /**
     * Adds a song to the end of the playlist.
     *
     * @param song
     *            the song to add
     */
    public void addSong(String song) {
        if (song == null || song.isEmpty()) {
            throw new IllegalArgumentException("song cannot be null or empty");
        }
        this.songs.add(song);
    }

    /**
     * Removes and returns the most recently added song.
     *
     * @return the removed song
     * @throws IllegalStateException
     *             if the playlist is empty
     */
    public String removeSong() {
        if (this.songs.isEmpty()) {
            throw new IllegalStateException(
                    "cannot remove from an empty playlist");
        }
        // "Most recently added" = last element
        return this.songs.remove(this.songs.size() - 1);
    }

    /**
     * Reports whether the playlist is empty.
     *
     * @return true if there are no songs, false otherwise
     */
    public boolean isEmpty() {
        return this.songs.isEmpty();
    }

    // =========================
    // "Secondary-like" methods
    // =========================

    /**
     * Reports the number of songs in the playlist.
     *
     * @return the number of songs
     */
    public int size() {
        return this.songs.size();
    }

    /**
     * Reports whether the playlist contains a given song.
     *
     * @param song
     *            the song to search for
     * @return true if the song is in the playlist, false otherwise
     */
    public boolean containsSong(String song) {
        return this.songs.contains(song);
    }

    /**
     * Moves a song from one position to another.
     *
     * @param from
     *            index of the song to move (0-based)
     * @param to
     *            target index (0-based)
     */
    public void moveSong(int from, int to) {
        if (from < 0 || from >= this.songs.size() || to < 0
                || to >= this.songs.size()) {
            throw new IndexOutOfBoundsException("from/to index out of range");
        }

        String song = this.songs.remove(from);
        this.songs.add(to, song);
    }

    /**
     * Randomly shuffles the songs in the playlist.
     */
    public void shuffle() {
        Collections.shuffle(this.songs);
    }

    /**
     * Returns a string representation of the playlist with index numbers.
     *
     * @return human-readable playlist
     */
    public String toPrettyString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current Playlist (").append(this.size())
                .append(" songs):\n");
        for (int i = 0; i < this.songs.size(); i++) {
            sb.append("  ").append(i).append(": ").append(this.songs.get(i))
                    .append("\n");
        }
        return sb.toString();
    }

    // =========================
    // Main method: proof-of-concept
    // =========================

    /**
     * Main method to demonstrate the value of the MusicPlaylistPOC component.
     * This acts as a minimal client that shows off a variety of methods.
     */
    public static void main(String[] args) {
        MusicPlaylistPOC playlist = new MusicPlaylistPOC();

        System.out.println("=== Proof-of-Concept: MusicPlaylist ===");
        System.out.println("Is playlist empty? " + playlist.isEmpty());

        // Add a few songs
        playlist.addSong("NewJeans - Super Shy");
        playlist.addSong("LE SSERAFIM - ANTIFRAGILE");
        playlist.addSong("IVE - Love Dive");
        playlist.addSong("Twice - Talk That Talk");

        System.out.println("\nAfter adding songs:");
        System.out.println(playlist.toPrettyString());

        // Show size and containsSong
        System.out.println("Playlist size: " + playlist.size());
        System.out.println("Contains 'IVE - Love Dive'? "
                + playlist.containsSong("IVE - Love Dive"));
        System.out.println("Contains 'BTS - Dynamite'? "
                + playlist.containsSong("BTS - Dynamite"));

        // Move a song
        System.out.println("\nMoving song at index 2 to index 0...");
        playlist.moveSong(2, 0);
        System.out.println(playlist.toPrettyString());

        // Shuffle playlist
        System.out.println("Shuffling playlist...");
        playlist.shuffle();
        System.out.println(playlist.toPrettyString());

        // Remove most recently added song
        System.out.println("Removing the most recently added song...");
        String removed = playlist.removeSong();
        System.out.println("Removed: " + removed);
        System.out.println(playlist.toPrettyString());

        System.out.println("Is playlist empty? " + playlist.isEmpty());
        System.out.println("=== End Proof-of-Concept ===");
    }
}
