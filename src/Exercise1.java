/**
 * Exercise 1: Music Track Hierarchy
 * Demonstrates basic superclass/subclass creation and method overriding.
 */
public class Exercise1 {

    // =========================================================
    // SUPERCLASS: Track
    // =========================================================
    public static class Track {

        // TODO 1: Declare three private instance variables:
        //         - title (String)
        //         - artistName (String)
        //         - durationSeconds (int)
        private String title;
        private String artistName;
        private int durationSeconds;

        /**
         * Parameterized constructor for Track.
         * @param title           The title of the track.
         * @param artistName     The name of the artist.
         * @param durationSeconds The length of the track in seconds.
         */
        public Track(String title, String artistName, int durationSeconds) {
            // TODO 2: Assign all three parameters to the instance variables
            this.title = title;
            this.artistName = artistName;
            this.durationSeconds = durationSeconds;
        }

        // TODO 3: Write getters and setters for all three fields
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getArtistName() { return artistName; }
        public void setArtistName(String artistName) { this.artistName = artistName; }

        public int getDurationSeconds() { return durationSeconds; }
        public void setDurationSeconds(int durationSeconds) { this.durationSeconds = durationSeconds; }

        /**
         * Displays information about this track.
         * Format:
         * [Track] Title: <title>
         * Artist: <artistName>
         * Duration: <mm:ss>
         *
         * HINT: Convert durationSeconds to minutes and seconds.
         * minutes = durationSeconds / 60
         * seconds = durationSeconds % 60
         * Use String.format("%02d:%02d", minutes, seconds) for nice formatting.
         */
        public void writeOutput() {
            // TODO 4: Implement this method
            int minutes = durationSeconds / 60;
            int seconds = durationSeconds % 60;
            String time = String.format("%02d:%02d", minutes, seconds);

            System.out.println("[Track] Title: " + title);
            System.out.println("Artist: " + artistName);
            System.out.println("Duration: " + time);
        }
    }


    // =========================================================dfg
    // SUBCLASS: PodcastEpisode
    // =========================================================
    public static class PodcastEpisode extends Track {

        // TODO 5: Declare two private instance variables:
        //         - showName (String)
        //         - episodeNumber (int)
        private String showName;
        private int episodeNumber;

        /**
         * Parameterized constructor for PodcastEpisode.
         * Calls the parent Track constructor using super().
         */
        public PodcastEpisode(String title, String artistName,
                              int durationSeconds, String showName,
                              int episodeNumber) {
            // TODO 6: Call super(...) with the appropriate arguments
            //         Then assign showName and episodeNumber
            super(title, artistName, durationSeconds);
            this.showName = showName;
            this.episodeNumber = episodeNumber;
        }

        // TODO 7: Write getters and setters for showName and episodeNumber
        public String getShowName() { return showName; }
        public void setShowName(String showName) { this.showName = showName; }

        public int getEpisodeNumber() { return episodeNumber; }
        public void setEpisodeNumber(int episodeNumber) { this.episodeNumber = episodeNumber; }

        /**
         * Overrides writeOutput() to include podcast-specific information.
         * First calls super.writeOutput() to print the Track portion,
         * then adds:
         * Show: <showName>
         * Episode #: <episodeNumber>
         */
        @Override
        public void writeOutput() {
            // TODO 8: Call super.writeOutput(), then print the two extra lines
            super.writeOutput();
            System.out.println("Show: " + showName);
            System.out.println("Episode #: " + episodeNumber);
        }
    }


    // =========================================================
    // MAIN METHOD
    // =========================================================
    public static void main(String[] args) {

        // TODO 9: Create a Track object with these values:
        //         title="Neon Lights", artist="Synth Collective", duration=214 seconds
        Track t1 = new Track("Neon Lights", "Synth Collective", 214);

        // TODO 10: Create a PodcastEpisode object with these values:
        //          title="The Future of AI", artist="Dr. Maya Chen",
        //          duration=3600 seconds, showName="TechTalks Weekly", episodeNumber=47
        PodcastEpisode ep1 = new PodcastEpisode("The Future of AI", "Dr. Maya Chen",
                3600, "TechTalks Weekly", 47);

        System.out.println("=== Track Info ===");
        t1.writeOutput();

        System.out.println("\n=== Podcast Episode Info ===");
        ep1.writeOutput();
    }
}
