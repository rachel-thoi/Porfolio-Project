/**
 * Kernel interface for a MusicPlaylist component.
 *
 * @author Rachel Thoi
 */
public interface MusicPlaylistKernel extends Standard<MusicPlaylist> {

    /**
     * Adds a song to the end of this playlist.
     *
     * @param song
     *            the song to add
     * @updates this
     * @requires song != null && song.length() > 0
     * @ensures this = #this * <song>
     */
    void addSong(String song);

    /**
     * Removes and returns the most recently added song from this playlist.
     *
     * @return the removed song
     * @updates this
     * @requires this is not empty
     * @ensures removeSong = last song of #this and this = #this without its
     *          last song
     */
    String removeSong();

    /**
     * Reports whether this playlist is empty.
     *
     * @return true iff this playlist has no songs
     * @ensures isEmpty = (this has no songs)
     */
    boolean isEmpty();
}
