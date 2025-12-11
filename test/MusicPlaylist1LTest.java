import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * JUnit test fixture for MusicPlaylist1L (kernel implementation).
 *
 * These tests focus on kernel and Standard methods: addSong, removeSong,
 * isEmpty, clear, newInstance, transferFrom.
 *
 * @author Rachel Thoi
 */
public class MusicPlaylist1LTest {

    /**
     * Creates and returns a new, empty MusicPlaylist.
     *
     * @return new empty MusicPlaylist
     */
    private MusicPlaylist constructorTest() {
        return new MusicPlaylist1L();
    }

    /**
     * Test: default constructor creates an empty playlist.
     */
    @Test
    public void testConstructorIsEmpty() {
        MusicPlaylist playlist = this.constructorTest();

        assertTrue("New playlist should be empty", playlist.isEmpty());
        assertEquals("New playlist should have size 0", 0, playlist.size());
    }

    /**
     * Test: addSong once to an empty playlist.
     */
    @Test
    public void testAddSongOne() {
        MusicPlaylist playlist = this.constructorTest();

        playlist.addSong("Love Dive - IVE");

        assertFalse("Playlist should not be empty after add",
                playlist.isEmpty());
        assertEquals("Playlist size should be 1", 1, playlist.size());
        assertTrue("Playlist should contain the added song",
                playlist.containsSong("Love Dive - IVE"));
    }

    /**
     * Test: addSong several times and removeSong once.
     */
    @Test
    public void testAddSongMultipleThenRemoveSong() {
        MusicPlaylist playlist = this.constructorTest();

        playlist.addSong("OMG - NewJeans");
        playlist.addSong("Lovesick Girls - BLACKPINK");
        playlist.addSong("Next Level - aespa");

        assertEquals("Size should be 3 after three adds", 3, playlist.size());

        String removed = playlist.removeSong();
        assertEquals("removeSong should remove most recently added song",
                "Next Level - aespa", removed);

        assertEquals("Size should be 2 after one removal", 2, playlist.size());
        assertFalse("Removed song should no longer be in playlist",
                playlist.containsSong("Next Level - aespa"));
        assertTrue("Older songs should still be present",
                playlist.containsSong("OMG - NewJeans"));
        assertTrue("Older songs should still be present",
                playlist.containsSong("Lovesick Girls - BLACKPINK"));
    }

    /**
     * Test: clear() on a non-empty playlist.
     */
    @Test
    public void testClear() {
        MusicPlaylist playlist = this.constructorTest();

        playlist.addSong("Hype Boy - NewJeans");
        playlist.addSong("ANTIFRAGILE - LE SSERAFIM");

        playlist.clear();

        assertTrue("Playlist should be empty after clear()",
                playlist.isEmpty());
        assertEquals("Size should be 0 after clear()", 0, playlist.size());
    }

    /**
     * Test: newInstance() creates a distinct empty playlist.
     */
    @Test
    public void testNewInstance() {
        MusicPlaylist playlist = this.constructorTest();
        playlist.addSong("Fearless - LE SSERAFIM");

        MusicPlaylist other = playlist.newInstance();

        assertTrue("newInstance() result should be empty", other.isEmpty());
        assertEquals("newInstance() result should have size 0", 0,
                other.size());
        assertFalse("newInstance() should not contain songs from original",
                other.containsSong("Fearless - LE SSERAFIM"));
    }

    /**
     * Test: transferFrom() moves all contents from source to destination.
     */
    @Test
    public void testTransferFrom() {
        MusicPlaylist source = this.constructorTest();
        source.addSong("Hype Boy - NewJeans");
        source.addSong("OMG - NewJeans");

        MusicPlaylist destination = this.constructorTest();

        destination.transferFrom(source);

        assertTrue("Source should be empty after transferFrom",
                source.isEmpty());
        assertEquals("Source size should be 0 after transferFrom", 0,
                source.size());

        assertFalse("Destination should not be empty after transferFrom",
                destination.isEmpty());
        assertEquals("Destination size should be 2 after transferFrom", 2,
                destination.size());
        assertTrue("Destination should contain songs moved from source",
                destination.containsSong("Hype Boy - NewJeans"));
        assertTrue("Destination should contain songs moved from source",
                destination.containsSong("OMG - NewJeans"));
    }
}
