import com.swop.*;
import com.swop.RobotGameWorldType;
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
        assertEquals(gameWorldType.getSupportedActions(), Arrays.asList(RobotAction.MOVE_FORWARD,RobotAction.TURN_LEFT,RobotAction.TURN_RIGHT));
    }

    @Test
    void getSupportedPredicates() {
        assertEquals(gameWorldType.getSupportedPredicates(), Arrays.asList(RobotPredicate.WALL_IN_FRONT));
    }

    @Test
    void createNewInstance() {
        assertNotEquals(gameWorldType.createNewInstance(),null);
    }
}