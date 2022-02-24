import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    Vehicle vehicle1;
    Vehicle vehicle2;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        System.out.println("@:setUp");
        vehicle1 = new Vehicle();
        vehicle1.setSpeed(5);
        vehicle1.setDir("south");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        vehicle1.finalize();
        System.out.println("totalVehicle:" + Vehicle.totalVehicle());
        System.out.println("@:tearDown");
    }

    @org.junit.jupiter.api.Test
    void testFinalize() {
        int Vumber = 1;
        assertEquals(Vumber,Vehicle.totalVehicle());
    }

    @org.junit.jupiter.api.Test
    void setSpeed() {
    }

    @org.junit.jupiter.api.Test
    void setDir() {
    }

    @org.junit.jupiter.api.Test
    void getSpeed() {
        int speed = 5;
        assertEquals(speed,vehicle1.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void getDir() {
        String dir = "south";
        assertEquals(dir,vehicle1.getDir());
    }

    @org.junit.jupiter.api.Test
    void totalVehicle() {
        vehicle2 = new Vehicle();
        int Vnumber = 2;
        assertEquals(Vnumber,Vehicle.totalVehicle());
    }
}