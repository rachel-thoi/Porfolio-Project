import javax.sound.midi.Sequence;

/**
 * Kernel implementation of the MusicPlaylist component using a Sequence<String>
 * as the underlying representation.
 *
 * @convention rep != null and for all i where 0 <= i < rep.length():
 *             rep.entry(i) != null && rep.entry(i).length() > 0
 *
 * @correspondence this = <rep.entry(0), rep.entry(1), ...,
 *                 rep.entry(rep.length() - 1)>
 */
public class MusicPlaylist1L extends MusicPlaylistSecondary {

    /**
     * Underlying representation of this playlist.
     */
    private Sequence<String> rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.rep = new Sequence1L<>();
    }

    /**
     * Default constructor.
     */
    public MusicPlaylist1L() {
        this.createNewRep();
    }

    // --------------------------------------------------------------
    // Standard methods
    // --------------------------------------------------------------

    @Override
    public void clear() {
        this.createNewRep();
    }

    @Override
    public MusicPlaylist newInstance() {
        return new MusicPlaylist1L();
    }

    @Override
    public void transferFrom(MusicPlaylist source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof MusicPlaylist1L : "Violation of: source is of dynamic type MusicPlaylist1L";

        MusicPlaylist1L localSource = (MusicPlaylist1L) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    // --------------------------------------------------------------
    // Kernel methods
    // --------------------------------------------------------------

    @Override
    public void addSong(String song) {
        // Adds song to the end of the sequence
        this.rep.add(this.rep.length(), song);
    }

    @Override
    public String removeSong() {
        assert this.rep.length() > 0 : "Violation of: this is not empty";

        int lastIndex = this.rep.length() - 1;
        return this.rep.remove(lastIndex);
    }

    @Override
    public boolean isEmpty() {
        return this.rep.length() == 0;
    }
}
