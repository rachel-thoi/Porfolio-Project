import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * JUnit test fixture for the secondary behavior of MusicPlaylist.
 *
 * These tests focus on secondary methods such as size, containsSong, moveSong,
 * shuffle, toString, and equals, which are implemented in
 * MusicPlaylistSecondary and used through MusicPlaylist1L.
 *
 * @author Rachel Thoi
 */
public class MusicPlaylistSecondaryTest {

    /**
     * Creates and returns a new, empty MusicPlaylist.
     *
     * @return new empty MusicPlaylist
     */
    private MusicPlaylist constructorTest() {
        return new MusicPlaylist1L();
    }

    /**
     * Test: size() on an empty playlist.
     */
    @Test
    public void testSizeEmpty() {
        MusicPlaylist playlist = this.constructorTest();

        assertEquals("Empty playlist should have size 0", 0, playlist.size());
    }

    /**
     * Test: size() after adding several songs.
     */
    @Test
    public void testSizeAfterAdds() {
        MusicPlaylist playlist = this.constructorTest();

        playlist.addSong("Song A");
        playlist.addSong("Song B");
        playlist.addSong("Song C");

        assertEquals("Playlist should have size 3 after three adds", 3,
                playlist.size());
    }

    /**
     * Test: containsSong() returns true for an existing song.
     */
    @Test
    public void testContainsSongTrue() {
        MusicPlaylist playlist = this.constructorTest();

        playlist.addSong("Song A");
        playlist.addSong("Song B");

        assertTrue("Playlist should contain 'Song A'",
                playlist.containsSong("Song A"));
    }

    /**
     * Test: containsSong() returns false for a non-existing song.
     */
    @Test
    public void testContainsSongFalse() {
        MusicPlaylist playlist = this.constructorTest();

        playlist.addSong("Song A");
        playlist.addSong("Song B");

        assertFalse("Playlist should not contain 'Song C'",
                playlist.containsSong("Song C"));
    }

    /**
     * Test: moveSong() keeps all songs present and size unchanged. This does
     * not assert exact order, but verifies that the method behaves sensibly and
     * does not lose or duplicate songs.
     */
    @Test
    public void testMoveSongKeepsSongs() {
        MusicPlaylist playlist = this.constructorTest();

        playlist.addSong("Song A"); // index 0
        playlist.addSong("Song B"); // index 1
        playlist.addSong("Song C"); // index 2

        playlist.moveSong(2, 0); // conceptually move "Song C" to the front

        assertEquals("Size should remain 3 after moveSong", 3, playlist.size());
        assertTrue("Playlist should still contain Song A",
                playlist.containsSong("Song A"));
        assertTrue("Playlist should still contain Song B",
                playlist.containsSong("Song B"));
        assertTrue("Playlist should still contain Song C",
                playlist.containsSong("Song C"));
    }

    /**
     * Test: shuffle() keeps the same songs and size (order may change).
     */
    @Test
    public void testShuffleKeepsSongsAndSize() {
        MusicPlaylist playlist = this.constructorTest();

        playlist.addSong("Song A");
        playlist.addSong("Song B");
        playlist.addSong("Song C");
        playlist.addSong("Song D");

        int originalSize = playlist.size();

        // Make a copy of what songs are present
        MusicPlaylist copy = playlist.newInstance();
        copy.transferFrom(playlist);
        // Restore playlist from copy so both have same songs
        playlist.transferFrom(copy);

        playlist.shuffle();

        assertEquals("Size should remain the same after shuffle", originalSize,
                playlist.size());
        assertTrue("Playlist should still contain Song A",
                playlist.containsSong("Song A"));
        assertTrue("Playlist should still contain Song B",
                playlist.containsSong("Song B"));
        assertTrue("Playlist should still contain Song C",
                playlist.containsSong("Song C"));
        assertTrue("Playlist should still contain Song D",
                playlist.containsSong("Song D"));
    }

    /**
     * Test: toString() should include song names in some readable form.
     */
    @Test
    public void testToStringContainsSongs() {
        MusicPlaylist playlist = this.constructorTest();

        playlist.addSong("Song A");
        playlist.addSong("Song B");

        String playlistString = playlist.toString();

        assertTrue("toString() should mention 'Song A'",
                playlistString.contains("Song A"));
        assertTrue("toString() should mention 'Song B'",
                playlistString.contains("Song B"));
    }

    /**
     * Test: equals() returns true for playlists with the same contents.
     */
    @Test
    public void testEqualsTrue() {
        MusicPlaylist p1 = this.constructorTest();
        MusicPlaylist p2 = this.constructorTest();

        p1.addSong("Song A");
        p1.addSong("Song B");

        p2.addSong("Song A");
        p2.addSong("Song B");

        assertTrue("Playlists with same songs should be equal", p1.equals(p2));
        assertTrue("Equality should be symmetric", p2.equals(p1));
    }

    /**
     * Test: equals() returns false for playlists with different contents.
     */
    @Test
    public void testEqualsFalse() {
        MusicPlaylist p1 = this.constructorTest();
        MusicPlaylist p2 = this.constructorTest();

        p1.addSong("Song A");
        p1.addSong("Song B");

        p2.addSong("Song A");
        // p2 is missing "Song B"

        assertFalse("Playlists with different contents should not be equal",
                p1.equals(p2));
        assertFalse("Playlists with different contents should not be equal",
                p2.equals(p1));
    }
}
