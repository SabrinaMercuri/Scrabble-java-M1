package m1.miage.scrabble.serveur;

import m1.miage.scrabble.commun.Identification;
import m1.miage.scrabble.commun.Plateau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.concurrent.TimeUnit;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class GestionnairePartieIT {

    Identification id1;
    Identification id2;

    @SpyBean
    GestionnairePartieWebControlleur webControlleur;

    @Autowired
    GestionnairePartie gestionnairePartie;

    GestionnairePartie gpSpy;

    @BeforeEach
    void setUp()throws Exception {
        id1 = new Identification("J1", "http://127.0.0.1:8081/");
        id2 = new Identification("J2", "http://127.0.0.1:8082/");

        gpSpy = Mockito.spy(gestionnairePartie);
        ReflectionTestUtils.setField(webControlleur, "gestionnairePartie", gpSpy);
    }

    @Test
    void demanderAuJoueurDeJoueurTest() {

        gpSpy.setStopAtEnd(false);

        webControlleur.getValue(id1);
        webControlleur.getValue(id2);

        while (!gpSpy.end) {
            try { TimeUnit.MILLISECONDS.sleep(10000); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }

        verify(gpSpy).lancerPartie();
        verify(gpSpy).initPioche();
        verify(gpSpy, atLeastOnce()).initMain();
        verify(gpSpy, atLeastOnce()).placerMot(any(), any());
        verify(gpSpy, atLeastOnce()).distributionLettres(ArgumentMatchers.anyInt());
        verify(gpSpy).end();

        verify(webControlleur, times(2)).envoyerMainAuJoueur(any(), any());
        verify(webControlleur, atLeastOnce()).demanderAuJoueurDeJouer(any(), any());
        verify(webControlleur, atLeastOnce()).envoyerLettresAuJoueur(any(), any());
        verify(webControlleur, atLeastOnce()).majScoreJoueur(ArgumentMatchers.anyInt(), any());
        verify(webControlleur).envoyerFin();

    }
}
