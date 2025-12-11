/**
 * Secondary (abstract) implementation of the MusicPlaylist component.
 *
 * This class implements all *enhanced* methods (and common Object methods)
 * using ONLY the kernel methods (addSong, removeSong, isEmpty) and the Standard
 * methods (clear, newInstance, transferFrom). It does NOT know anything about
 * the underlying representation.
 *
 * @author Rachel Thoi
 */
public abstract class MusicPlaylistSecondary implements MusicPlaylist {

    /**
     * No-argument constructor.
     */
    protected MusicPlaylistSecondary() {
        // Nothing to initialize; representation is handled in the
        // concrete primary class later (e.g., MusicPlaylist1).
    }

    // --------------------------------------------------------------
    // Helper method(s)
    // --------------------------------------------------------------

    /**
     * Copies all songs from {@code source} to {@code destination}, preserving
     * the order of songs in {@code source}.
     *
     * This method uses only kernel + Standard methods.
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

        /*
         * Move songs from source -> temp1 while counting. Order is reversed in
         * temp1.
         */
        while (!source.isEmpty()) {
            String s = source.removeSong();
            temp1.addSong(s);
        }

        /*
         * Move songs from temp1 -> temp2 (restores original order), and into
         * destination as we go.
         */
        while (!temp1.isEmpty()) {
            String s = temp1.removeSong();
            temp2.addSong(s); // temp2 now has original order
            destination.addSong(s);
        }

        /*
         * Restore songs back into source from temp2 so source is unchanged.
         */
        while (!temp2.isEmpty()) {
            String s = temp2.removeSong();
            source.addSong(s);
        }
    }

    // --------------------------------------------------------------
    // Enhanced methods
    // --------------------------------------------------------------

    @Override
    public int size() {
        int count = 0;

        MusicPlaylist temp1 = this.newInstance();
        MusicPlaylist temp2 = this.newInstance();

        // Move everything to temp1, counting
        while (!this.isEmpty()) {
            String s = this.removeSong();
            temp1.addSong(s);
            count++;
        }

        // Restore order using temp2
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

        // Walk through playlist, looking for the song
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
         * STARTER STUB: This is left as a TODO for you to implement.
         *
         * Requirements (from your enhanced interface): - Use only addSong,
         * removeSong, isEmpty, clear, newInstance, transferFrom. - Move the
         * song currently at index 'from' to index 'to' and keep all other songs
         * in the same relative order.
         *
         * Suggested approach: - Use one or two temporary playlists. - Walk
         * through the playlist while counting indices. - When you reach 'from',
         * save that song separately. - Rebuild the playlist inserting the saved
         * song at position 'to'.
         */

        throw new UnsupportedOperationException("moveSong not implemented yet");
    }

    @Override
    public void shuffle() {
        /*
         * STARTER STUB: This is left as a TODO for you to implement.
         *
         * Simple approach: - Use size() to know how many songs there are. -
         * Repeatedly choose a random index, pull that song out into a temporary
         * playlist, and rebuild.
         *
         * Remember: you may only use kernel + Standard methods.
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

        int i = 0;
        int n = temp.size(); // uses the enhanced size() we wrote above

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

        // First, compare sizes
        if (this.size() != other.size()) {
            return false;
        }

        boolean equal = true;

        MusicPlaylist tempThis = this.newInstance();
        MusicPlaylist tempOther = other.newInstance();

        // Copy both playlists into temps (preserving originals)
        copyPlaylist(this, tempThis);
        copyPlaylist(other, tempOther);

        // Compare elements in order
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
        /*
         * Optional, but nice to include if you override equals. This is a
         * simple starter implementation; you can refine it.
         */

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
