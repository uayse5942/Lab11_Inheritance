# Lab 11: Inheritance, Polymorphism, and Interfaces (Part 1)

### Course: CENG112 Computer Programming II (Java)
### Topic: Inheritance Basics, Superclass/Subclass Relationships, and Method Overriding

---

## Lab Setup

- **IDE:** IntelliJ IDEA, Eclipse, or VS Code with Java Extension Pack
- **No external libraries required** — standard Java only
- Create a new Java project called `Lab11_Inheritance`
- Create a separate `.java` file for each exercise (e.g., `Exercise1.java`, `Exercise2.java`)
- Each exercise that contains multiple classes can either use separate files or **nested/inner class structure** as shown in the starter code

> **Note on nested static classes:** All inner classes in this lab are declared `public static class`:
> 1. The `main()` method is `static`, so it can only instantiate `static` nested classes directly
> 2. This allows all related classes to exist in a single file for simplicity, avoiding multi-file package setup
> 3. Non-static inner classes would require an instance of the outer `Exercise` class to instantiate, which is unnecessary here

> **Recall from Week 10:** You previously used packages and multi-file structure for organization. This week we use single-file static nested classes to focus purely on inheritance concepts without package management overhead.

> 💡 **Tip:** Read each exercise fully before writing any code. Understanding the big picture first saves time!

---

## Exercise 1: Building a Music Track Hierarchy 

**Objective:** Practice creating a basic superclass with instance variables and methods, then extend it with a subclass that adds new fields and overrides a display method.

---

### Problem Statement

You are building a music streaming app. All content in the app is a type of `Track`. A general `Track` has a **title**, a **artist name**, and a **duration in seconds**.

A `PodcastEpisode` **is a** `Track`, but it also has a **episode number** and a **show name**.

Your tasks:
1. Build the `Track` superclass with the fields described above, a parameterized constructor, getters/setters, and a `writeOutput()` method.
2. Build the `PodcastEpisode` subclass that extends `Track`, adds its own fields, calls `super(...)` in its constructor, and **overrides** `writeOutput()` to print all information (using `super.writeOutput()` to reuse the parent's output).
3. Write a `main` method that creates one `Track` object and one `PodcastEpisode` object and calls `writeOutput()` on each.

---

### Starter Code

```java
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
        // Add the remaining two variables here...


        /**
         * Parameterized constructor for Track.
         * @param title          The title of the track.
         * @param artistName     The name of the artist.
         * @param durationSeconds The length of the track in seconds.
         */
        public Track(String title, String artistName, int durationSeconds) {
            // TODO 2: Assign all three parameters to the instance variables
        }

        // TODO 3: Write getters and setters for all three fields


        /**
         * Displays information about this track.
         * Format:
         *   [Track] Title: <title>
         *   Artist: <artistName>
         *   Duration: <mm:ss>
         *
         * HINT: Convert durationSeconds to minutes and seconds.
         *       minutes = durationSeconds / 60
         *       seconds = durationSeconds % 60
         *       Use String.format("%02d:%02d", minutes, seconds) for nice formatting.
         */
        public void writeOutput() {
            // TODO 4: Implement this method
        }
    }


    // =========================================================
    // SUBCLASS: PodcastEpisode
    // =========================================================
    public static class PodcastEpisode extends Track {

        // TODO 5: Declare two private instance variables:
        //         - showName (String)
        //         - episodeNumber (int)


        /**
         * Parameterized constructor for PodcastEpisode.
         * Calls the parent Track constructor using super().
         */
        public PodcastEpisode(String title, String artistName,
                              int durationSeconds, String showName,
                              int episodeNumber) {
            // TODO 6: Call super(...) with the appropriate arguments
            //         Then assign showName and episodeNumber
        }

        // TODO 7: Write getters and setters for showName and episodeNumber

        /**
         * Overrides writeOutput() to include podcast-specific information.
         * First calls super.writeOutput() to print the Track portion,
         * then adds:
         *   Show: <showName>
         *   Episode #: <episodeNumber>
         */
        @Override
        public void writeOutput() {
            // TODO 8: Call super.writeOutput(), then print the two extra lines
        }
    }


    // =========================================================
    // MAIN METHOD
    // =========================================================
    public static void main(String[] args) {

        // TODO 9: Create a Track object with these values:
        //         title="Neon Lights", artist="Synth Collective", duration=214 seconds
        Track t1 = null; // replace null with the real constructor call

        // TODO 10: Create a PodcastEpisode object with these values:
        //          title="The Future of AI", artist="Dr. Maya Chen",
        //          duration=3600 seconds, showName="TechTalks Weekly", episodeNumber=47
        PodcastEpisode ep1 = null; // replace null with the real constructor call

        System.out.println("=== Track Info ===");
        t1.writeOutput();

        System.out.println("\n=== Podcast Episode Info ===");
        ep1.writeOutput();
    }
}
```

---

### Expected Output

```
=== Track Info ===
[Track] Title: Neon Lights
Artist: Synth Collective
Duration: 03:34

=== Podcast Episode Info ===
[Track] Title: The Future of AI
Artist: Dr. Maya Chen
Duration: 60:00
Show: TechTalks Weekly
Episode #: 47
```

---

### Hints

- For `writeOutput()` in `Track`, remember `String.format("%02d:%02d", minutes, seconds)` pads single digits with a leading zero (e.g., `3` becomes `03`).
- The `super(...)` call in `PodcastEpisode`'s constructor **must be the very first line** — the compiler will give an error if it is not.
- `super.writeOutput()` inside `PodcastEpisode.writeOutput()` calls the **parent's version** of the method, so you don't have to reprint the title, artist, and duration.

---

## Exercise 2: Space Mission Vehicles

**Objective:** Practice multi-level inheritance (a chain of three classes), correct use of `super` at each level, and tracing constructor call order.

---

### Problem Statement

A space agency tracks all of its vehicles. The hierarchy is:

```
SpaceVehicle  (base — has: vehicleID, manufacturer, maxSpeedKmh)
     └── Rocket  (extends SpaceVehicle — adds: thrustKN, fuelType)
              └── CrewRocket  (extends Rocket — adds: crewCapacity, missionName)
```

Each class must:
- Have a **parameterized constructor** that calls `super(...)` to initialize the parent's fields
- Have **getters** for all its own fields
- **Override** `writeOutput()` to print all fields **of that class and all ancestors** (use `super.writeOutput()` to chain upward)

Write a `main` method that:
1. Creates a `SpaceVehicle`, a `Rocket`, and a `CrewRocket` object
2. Calls `writeOutput()` on each
3. Also prints a **separator line** between each vehicle's output

---

### Starter Code

```java
/**
 * Exercise 2: Space Mission Vehicle Hierarchy (Three Levels)
 * Demonstrates multi-level inheritance and chained super() calls.
 */
public class Exercise2 {

    // =========================================================
    // LEVEL 1 — BASE CLASS: SpaceVehicle
    // =========================================================
    public static class SpaceVehicle {
        // TODO 1: Declare private instance variables:
        //         - vehicleID (String)
        //         - manufacturer (String)
        //         - maxSpeedKmh (double)
        private String vehicleID;
        // Add the remaining two variables here...


        /**
         * Parameterized constructor for SpaceVehicle.
         * @param vehicleID     The ID of the vehicle.
         * @param manufacturer The manufacturer of the vehicle.
         * @param maxSpeedKmh  The maximum speed in km/h.
         */
        public SpaceVehicle(String vehicleID, String manufacturer, double maxSpeedKmh) {
            // TODO 2: Assign all parameters to the instance variables
        }

        // TODO 3: Write getters for all three fields (getVehicleID, getManufacturer, getMaxSpeedKmh)

        /**
         * Prints base vehicle information.
         * Format:
         *   Vehicle ID   : <vehicleID>
         *   Manufacturer : <manufacturer>
         *   Max Speed    : <maxSpeedKmh> km/h
         */
        public void writeOutput() {
            // TODO 4: Implement this method to print all fields
        }
    }


    // =========================================================
    // LEVEL 2 — SUBCLASS: Rocket  (extends SpaceVehicle)
    // =========================================================
    public static class Rocket extends SpaceVehicle {

        // TODO 1: Add private fields: thrustKN (double), fuelType (String)

        /**
         * Constructor — must call super() first.
         * Parameters: vehicleID, manufacturer, maxSpeedKmh, thrustKN, fuelType
         */
        public Rocket(String vehicleID, String manufacturer,
                      double maxSpeedKmh, double thrustKN, String fuelType) {
            // TODO 2: Call super(...) then assign thrustKN and fuelType
        }

        // TODO 3: Add getters for thrustKN and fuelType

        /**
         * Overrides writeOutput() — calls super.writeOutput() first,
         * then prints thrustKN and fuelType.
         */
        @Override
        public void writeOutput() {
            // TODO 4: super.writeOutput() then print rocket-specific fields
        }
    }


    // =========================================================
    // LEVEL 3 — SUB-SUBCLASS: CrewRocket  (extends Rocket)
    // =========================================================
    public static class CrewRocket extends Rocket {

        // TODO 5: Add private fields: crewCapacity (int), missionName (String)

        /**
         * Constructor — must call super() first.
         * Parameters: vehicleID, manufacturer, maxSpeedKmh,
         *             thrustKN, fuelType, crewCapacity, missionName
         */
        public CrewRocket(String vehicleID, String manufacturer,
                          double maxSpeedKmh, double thrustKN,
                          String fuelType, int crewCapacity, String missionName) {
            // TODO 6: Call super(...) then assign crewCapacity and missionName
        }

        // TODO 7: Add getters for crewCapacity and missionName

        /**
         * Overrides writeOutput() — calls super.writeOutput() first,
         * then prints crewCapacity and missionName.
         */
        @Override
        public void writeOutput() {
            // TODO 8: super.writeOutput() then print crew-specific fields
        }
    }


    // =========================================================
    // MAIN METHOD
    // =========================================================
    public static void main(String[] args) {
        String separator = "----------------------------------------";

        // TODO 9: Create a SpaceVehicle: ID="SV-001", manufacturer="OrbitalTech",
        //         maxSpeed=28000.0
        SpaceVehicle sv = null; // replace with constructor

        // TODO 10: Create a Rocket: ID="RK-042", manufacturer="AstroForge",
        //          maxSpeed=35000.0, thrustKN=7600.5, fuelType="Liquid Hydrogen"
        Rocket rk = null; // replace with constructor

        // TODO 11: Create a CrewRocket: ID="CR-007", manufacturer="GalaxyCorp",
        //          maxSpeed=40000.0, thrustKN=9500.0, fuelType="Methane",
        //          crewCapacity=6, missionName="Artemis IV"
        CrewRocket cr = null; // replace with constructor

        System.out.println("=== Space Vehicle ===");
        sv.writeOutput();
        System.out.println(separator);

        System.out.println("=== Rocket ===");
        rk.writeOutput();
        System.out.println(separator);

        System.out.println("=== Crew Rocket ===");
        cr.writeOutput();
        System.out.println(separator);

        // TODO 12: Create an array of SpaceVehicle objects containing one of each vehicle type:
        //          SpaceVehicle, Rocket, CrewRocket
        // Loop through the array and use instanceof to check if each vehicle is a CrewRocket
        // If it is, print: "Crew vehicle: [mission name]"
        // Hint: You will need to cast to CrewRocket to access getMissionName()
    }
}
```

---

### Expected Output

```
=== Space Vehicle ===
Vehicle ID   : SV-001
Manufacturer : OrbitalTech
Max Speed    : 28000.0 km/h
----------------------------------------
=== Rocket ===
Vehicle ID   : RK-042
Manufacturer : AstroForge
Max Speed    : 35000.0 km/h
Thrust       : 7600.5 kN
Fuel Type    : Liquid Hydrogen
----------------------------------------
=== Crew Rocket ===
Vehicle ID   : CR-007
Manufacturer : GalaxyCorp
Max Speed    : 40000.0 km/h
Thrust       : 9500.0 kN
Fuel Type    : Methane
Crew Capacity: 6
Mission      : Artemis IV
----------------------------------------
Crew vehicle: Artemis IV
```

---

### Hints

- Each level's `writeOutput()` should **only print its own fields** — the parent fields are handled by `super.writeOutput()`.
- The chain is: `CrewRocket.writeOutput()` → calls `Rocket.writeOutput()` → calls `SpaceVehicle.writeOutput()`. This means the output always appears from top (most general) to bottom (most specific).
- When writing `CrewRocket`'s constructor, the `super(...)` call passes **all five** `Rocket` parameters — make sure you pass them in the correct order.
