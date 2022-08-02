package m1.miage.scrabble.commun;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaseTest {

    private Case caseTest;

    @BeforeEach
    public void init() {
        caseTest = new Case(2,5);
    }

    @Test
    void getPosX() {
        assertEquals(2, caseTest.getPosX());
        assertNotEquals(5, caseTest.getPosX());
    }

    @Test
    void getPosY() {
        assertEquals(5, caseTest.getPosY());
        assertNotEquals(2, caseTest.getPosY());
    }
}