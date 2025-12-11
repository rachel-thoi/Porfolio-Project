/**
 * Enhanced interface for a MusicPlaylist component.
 *
 * @author Rachel Thoi
 */
public interface MusicPlaylist extends MusicPlaylistKernel {

    /**
     * Reports the number of songs in this playlist.
     *
     * @return the size of this playlist
     * @ensures size = number of songs in this
     */
    int size();

    /**
     * Reports whether this playlist contains the given song.
     *
     * @param song
     *            the song to search for
     * @return true iff the given song is in this playlist
     * @ensures containsSong = (song is equal to at least one song in this)
     */
    boolean containsSong(String song);

    /**
     * Moves the song currently at position {@code from} to position {@code to}.
     *
     * @param from
     *            the index of the song to move (0-based)
     * @param to
     *            the target index (0-based)
     * @updates this
     * @requires 0 <= from < size() and 0 <= to < size()
     * @ensures the same multiset of songs is in this, but the song formerly at
     *          position {@code from} is now at position {@code to}
     */
    void moveSong(int from, int to);

    /**
     * Randomly reorders the songs in this playlist.
     *
     * @updates this
     * @ensures this is a permutation of #this
     */
    void shuffle();
}