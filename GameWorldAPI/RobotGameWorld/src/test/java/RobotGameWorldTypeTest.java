import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class RobotGameWorldTypeTest {
    private RobotGameWorldType gameWorldType;
    @BeforeEach
    void setUp(){
        gameWorldType = new RobotGameWorldType();
    }

    @Test
    void getSupportedActions() {
        assertEquals(gameWorldType.getSupportedActions(), Arrays.asList(Action.MOVE_FORWARD,Action.TURN_LEFT,Action.TURN_RIGHT));
    }

    @Test
    void getSupportedPredicates() {
        assertEquals(gameWorldType.getSupportedPredicates(), Arrays.asList(Predicate.WALL_IN_FRONT));
    }

    @Test
    void createNewInstance() {
        assertNotEquals(gameWorldType.createNewInstance(),null);
    }
}