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
        private String manufacturer;
        private double maxSpeedKmh;

        /**
         * Parameterized constructor for SpaceVehicle.
         * @param vehicleID     The ID of the vehicle.
         * @param manufacturer The manufacturer of the vehicle.
         * @param maxSpeedKmh  The maximum speed in km/h.
         */
        public SpaceVehicle(String vehicleID, String manufacturer, double maxSpeedKmh) {
            // TODO 2: Assign all parameters to the instance variables
            this.vehicleID = vehicleID;
            this.manufacturer = manufacturer;
            this.maxSpeedKmh = maxSpeedKmh;
        }

        // TODO 3: Write getters for all three fields (getVehicleID, getManufacturer, getMaxSpeedKmh)
        public String getVehicleID() { return vehicleID; }
        public String getManufacturer() { return manufacturer; }
        public double getMaxSpeedKmh() { return maxSpeedKmh; }

        /**
         * Prints base vehicle information.
         * Format:
         * Vehicle ID   : <vehicleID>
         * Manufacturer : <manufacturer>
         * Max Speed    : <maxSpeedKmh> km/h
         */
        public void writeOutput() {
            // TODO 4: Implement this method to print all fields
            System.out.println("Vehicle ID   : " + vehicleID);
            System.out.println("Manufacturer : " + manufacturer);
            System.out.println("Max Speed    : " + maxSpeedKmh + " km/h");
        }
    }


    // =========================================================
    // LEVEL 2 — SUBCLASS: Rocket  (extends SpaceVehicle)
    // =========================================================
    public static class Rocket extends SpaceVehicle {

        // TODO 1: Add private fields: thrustKN (double), fuelType (String)
        private double thrustKN;
        private String fuelType;

        /**
         * Constructor — must call super() first.
         * Parameters: vehicleID, manufacturer, maxSpeedKmh, thrustKN, fuelType
         */
        public Rocket(String vehicleID, String manufacturer,
                      double maxSpeedKmh, double thrustKN, String fuelType) {
            // TODO 2: Call super(...) then assign thrustKN and fuelType
            super(vehicleID, manufacturer, maxSpeedKmh);
            this.thrustKN = thrustKN;
            this.fuelType = fuelType;
        }

        // TODO 3: Add getters for thrustKN and fuelType
        public double getThrustKN() { return thrustKN; }
        public String getFuelType() { return fuelType; }

        /**
         * Overrides writeOutput() — calls super.writeOutput() first,
         * then prints thrustKN and fuelType.
         */
        @Override
        public void writeOutput() {
            // TODO 4: super.writeOutput() then print rocket-specific fields
            super.writeOutput();
            System.out.println("Thrust       : " + thrustKN + " kN");
            System.out.println("Fuel Type    : " + fuelType);
        }
    }


    // =========================================================
    // LEVEL 3 — SUB-SUBCLASS: CrewRocket  (extends Rocket)
    // =========================================================
    public static class CrewRocket extends Rocket {

        // TODO 5: Add private fields: crewCapacity (int), missionName (String)
        private int crewCapacity;
        private String missionName;

        /**
         * Constructor — must call super() first.
         * Parameters: vehicleID, manufacturer, maxSpeedKmh,
         * thrustKN, fuelType, crewCapacity, missionName
         */
        public CrewRocket(String vehicleID, String manufacturer,
                          double maxSpeedKmh, double thrustKN,
                          String fuelType, int crewCapacity, String missionName) {
            // TODO 6: Call super(...) then assign crewCapacity and missionName
            super(vehicleID, manufacturer, maxSpeedKmh, thrustKN, fuelType);
            this.crewCapacity = crewCapacity;
            this.missionName = missionName;
        }

        // TODO 7: Add getters for crewCapacity and missionName
        public int getCrewCapacity() { return crewCapacity; }
        public String getMissionName() { return missionName; }

        /**
         * Overrides writeOutput() — calls super.writeOutput() first,
         * then prints crewCapacity and missionName.
         */
        @Override
        public void writeOutput() {
            // TODO 8: super.writeOutput() then print crew-specific fields
            super.writeOutput();
            System.out.println("Crew Capacity: " + crewCapacity);
            System.out.println("Mission      : " + missionName);
        }
    }


    // =========================================================
    // MAIN METHOD
    // =========================================================
    public static void main(String[] args) {
        String separator = "----------------------------------------";

        // TODO 9: Create a SpaceVehicle: ID="SV-001", manufacturer="OrbitalTech",
        //         maxSpeed=28000.0
        SpaceVehicle sv = new SpaceVehicle("SV-001", "OrbitalTech", 28000.0);

        // TODO 10: Create a Rocket: ID="RK-042", manufacturer="AstroForge",
        //          maxSpeed=35000.0, thrustKN=7600.5, fuelType="Liquid Hydrogen"
        Rocket rk = new Rocket("RK-042", "AstroForge", 35000.0, 7600.5, "Liquid Hydrogen");

        // TODO 11: Create a CrewRocket: ID="CR-007", manufacturer="GalaxyCorp",
        //          maxSpeed=40000.0, thrustKN=9500.0, fuelType="Methane",
        //          crewCapacity=6, missionName="Artemis IV"
        CrewRocket cr = new CrewRocket("CR-007", "GalaxyCorp", 40000.0, 9500.0, "Methane", 6, "Artemis IV");

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
        SpaceVehicle[] fleet = {sv, rk, cr};

        // Loop through the array and use instanceof to check if each vehicle is a CrewRocket
        for (SpaceVehicle v : fleet) {
            if (v instanceof CrewRocket) {
                // If it is, print: "Crew vehicle: [mission name]"
                // Hint: You will need to cast to CrewRocket to access getMissionName()
                CrewRocket temp = (CrewRocket) v;
                System.out.println("Crew vehicle: " + temp.getMissionName());
            }
        }
    }
}