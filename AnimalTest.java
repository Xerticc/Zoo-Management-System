import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    @Test
    void testPenguinCreation() {
        Penguin p = new Penguin("Tux", 4, "Black", 15.5, 20.0);
        assertEquals("Tux", p.getName());
        assertEquals("Black", p.getColour());
        assertEquals(4, p.getAge());
    }
}
