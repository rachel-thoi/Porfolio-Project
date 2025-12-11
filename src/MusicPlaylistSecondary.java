/**
 * Secondary (abstract) implementation of the MusicPlaylist component.
 *
 * This class implements all enhanced methods (and common Object methods) using
 * ONLY the kernel methods and the Standard methods. It has no access to the
 * underlying representation; that will be handled by a concrete primary class
 * later (e.g., MusicPlaylist1).
 *
 * @author Rachel Thoi
 */
public abstract class MusicPlaylistSecondary implements MusicPlaylist {

    /**
     * Protected constructor.
     */
    protected MusicPlaylistSecondary() {
        // Representation is created/managed in the concrete primary class.
    }

    // --------------------------------------------------------------
    // Helper method(s)
    // --------------------------------------------------------------

    /**
     * Copies all songs from {@code source} to {@code destination}, preserving
     * the order of songs in {@code source}. Both playlists are MusicPlaylist
     * values and are restored to their original contents (source is unchanged
     * when this method returns).
     *
     * @param source
     *            playlist to copy from
     * @param destination
     *            playlist to copy to
     */
    private static void copyPlaylist(MusicPlaylist source,
            MusicPlaylist destination) {
        MusicPlaylist temp1 = source.newInstance();
        MusicPlaylist temp2 = source.newInstance();

        // Move source -> temp1 (reverses order)
        while (!source.isEmpty()) {
            String s = source.removeSong();
            temp1.addSong(s);
        }

        // Move temp1 -> temp2 and destination (restores original order)
        while (!temp1.isEmpty()) {
            String s = temp1.removeSong();
            temp2.addSong(s);
            destination.addSong(s);
        }

        // Restore source from temp2
        while (!temp2.isEmpty()) {
            String s = temp2.removeSong();
            source.addSong(s);
        }
    }

    // --------------------------------------------------------------
    // Enhanced methods (implemented using kernel + Standard only)
    // --------------------------------------------------------------

    @Override
    public int size() {
        int count = 0;

        MusicPlaylist temp1 = this.newInstance();
        MusicPlaylist temp2 = this.newInstance();

        // Move everything from this -> temp1, counting
        while (!this.isEmpty()) {
            String s = this.removeSong();
            temp1.addSong(s);
            count++;
        }

        // Restore order using temp2, then back to this
        while (!temp1.isEmpty()) {
            String s = temp1.removeSong();
            temp2.addSong(s);
        }
        while (!temp2.isEmpty()) {
            String s = temp2.removeSong();
            this.addSong(s);
        }

        return count;
    }

    @Override
    public boolean containsSong(String song) {
        boolean found = false;

        MusicPlaylist temp1 = this.newInstance();
        MusicPlaylist temp2 = this.newInstance();

        // Scan through playlist, looking for the song
        while (!this.isEmpty()) {
            String s = this.removeSong();
            temp1.addSong(s);
            if (s.equals(song)) {
                found = true;
            }
        }

        // Restore order
        while (!temp1.isEmpty()) {
            String s = temp1.removeSong();
            temp2.addSong(s);
        }
        while (!temp2.isEmpty()) {
            String s = temp2.removeSong();
            this.addSong(s);
        }

        return found;
    }

    @Override
    public void moveSong(int from, int to) {
        /*
         * TODO: implement using only: - addSong - removeSong - isEmpty - clear
         * - newInstance - transferFrom
         *
         * Suggested idea: - Use one or two temporary playlists. - Walk through
         * the songs while counting indices. - When you reach index 'from',
         * store that song separately. - Rebuild the playlist inserting that
         * stored song at index 'to'.
         */

        throw new UnsupportedOperationException("moveSong not implemented yet");
    }

    @Override
    public void shuffle() {
        /*
         * TODO: implement using only kernel + Standard methods.
         *
         * One simple approach: - Compute n = size(). - Repeatedly choose a
         * random index k in [0, n-1], remove the k-th song into a temporary
         * playlist, and rebuild. - Make sure you preserve all songs and end
         * with the same multiset.
         */

        throw new UnsupportedOperationException("shuffle not implemented yet");
    }

    // --------------------------------------------------------------
    // Common Object methods
    // --------------------------------------------------------------

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        MusicPlaylist temp = this.newInstance();
        copyPlaylist(this, temp);

        int n = temp.size();
        int i = 0;

        while (!temp.isEmpty()) {
            String s = temp.removeSong();
            sb.append(s);
            if (i < n - 1) {
                sb.append(", ");
            }
            i++;
        }

        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof MusicPlaylist)) {
            return false;
        }

        MusicPlaylist other = (MusicPlaylist) obj;

        // Compare sizes first
        if (this.size() != other.size()) {
            return false;
        }

        boolean equal = true;

        MusicPlaylist tempThis = this.newInstance();
        MusicPlaylist tempOther = other.newInstance();

        copyPlaylist(this, tempThis);
        copyPlaylist(other, tempOther);

        while (!tempThis.isEmpty() && equal) {
            String s1 = tempThis.removeSong();
            String s2 = tempOther.removeSong();
            if (!s1.equals(s2)) {
                equal = false;
            }
        }

        return equal;
    }

    @Override
    public int hashCode() {
        // Simple hashCode consistent with equals
        int result = 17;

        MusicPlaylist temp = this.newInstance();
        copyPlaylist(this, temp);

        while (!temp.isEmpty()) {
            String s = temp.removeSong();
            result = 31 * result + s.hashCode();
        }

        return result;
    }
}
