package m1.miage.scrabble.commun;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IdentificationTest {

    private Identification id;

    @BeforeEach
    public void init() {
        id = new Identification("toto", "http://localhost:8081");
    }

    @Test
    public void getNom(){
        assertEquals("toto", id.getNom());
    }

    @Test
    public void getUrl(){
        assertEquals("http://localhost:8081", id.getUrl());
    }

}
