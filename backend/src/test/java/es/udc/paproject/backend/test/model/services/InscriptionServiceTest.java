package es.udc.paproject.backend.test.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.*;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.InscriptionService;
import es.udc.paproject.backend.model.services.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class InscriptionServiceTest {

    private final Long NON_EXISTENT_ID = Long.valueOf(-1);
    private final String CARD_NUMBER = "1234567890123456";
    private final LocalDateTime DATE = LocalDateTime.now().plusMonths(2);
    private final float PRICE = (float) 9.95;
    private final int MAX_PARTICIPANTS = 20;
    private final String DESCRIPTION = "description";
    private final String LOCATION = "A Coruña";


    @Autowired
    private UserService userService;

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private SportTestTypeDao sportTestTypeDao;

    @Autowired
    private SportTestDao sportTestDao;

    @Autowired
    private InscriptionService inscriptionService;

    @Autowired
    private InscriptionDao inscriptionDao;

    private User singUpUser(String userName) {

        User user = new User(userName, "password", "firstName",
                "lastName", userName+"@udc.es");

        try {
            userService.signUp(user);
        } catch (DuplicateInstanceException e) {
            throw new RuntimeException(e);
        }

        return user;

    }

    private Province addProvince () {
        return provinceDao.save(new Province("A Coruña"));
    }

    private SportTestType addSportTestType () {
        return sportTestTypeDao.save(new SportTestType("race"));
    }

    private SportTest addSportTest(String name, LocalDateTime date, int maxParticipants) {
        SportTest sportTest = new SportTest(name, DESCRIPTION, date, PRICE, maxParticipants,
                0, addSportTestType(), LOCATION, addProvince());
        return sportTestDao.save(sportTest);
    }
    private SportTest addSportTest(String name, LocalDateTime date) {
        return addSportTest(name, date, MAX_PARTICIPANTS);
    }
    private SportTest addSportTest(String name, int maxParticipants) {
        return addSportTest(name, DATE, maxParticipants);
    }
    private SportTest addSportTest(String name) {
        return addSportTest(name, DATE, MAX_PARTICIPANTS);
    }

    @Test
    public void testScoreOfNonExistingInscription() {
        User user = singUpUser("user");

        assertThrows(InstanceNotFoundException.class, () ->
                inscriptionService.scoreTest(NON_EXISTENT_ID, user.getId(), 3));
    }

    @Test
    public void testInscriptionFromWrongUser() throws AlreadyInscribedException,
            InscriptionDateExpiredException, NoMoreInscriptionsAllowedException, InstanceNotFoundException {
        User user1 = singUpUser("user1");
        User user2 = singUpUser("user2");
        SportTest sportTest = addSportTest("Solidary Race 2021", LocalDateTime.now().plusHours(30));
        Inscription inscription = inscriptionService.inscribe(sportTest.getId(), user2.getId(), CARD_NUMBER);

        sportTest.setDate(LocalDateTime.now().minusDays(17));

        assertThrows(PermissionException.class, () ->
                inscriptionService.scoreTest(inscription.getId(), user1.getId(), 3));
    }

    @Test
    public void testScoreLaterThan15Days() throws AlreadyInscribedException,
            InscriptionDateExpiredException, NoMoreInscriptionsAllowedException, InstanceNotFoundException {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Solidary Race 2021", LocalDateTime.now().plusHours(30));
        Inscription inscription = inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER);

        sportTest.setDate(LocalDateTime.now().minusDays(17));

        assertThrows(DateExpiredException.class, () ->
                inscriptionService.scoreTest(inscription.getId(), user.getId(), 3));
    }

    @Test
    public void testInscriptionAlreadyScored() throws AlreadyInscribedException,
            InscriptionDateExpiredException, NoMoreInscriptionsAllowedException, InstanceNotFoundException,
            PermissionException, DateExpiredException, SportTestNotStartedYetException, InscriptionAlreadyScoredException {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Solidary Race 2021", LocalDateTime.now().plusHours(30));
        Inscription inscription = inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER);

        sportTest.setDate(LocalDateTime.now().minusDays(10));
        inscriptionService.scoreTest(inscription.getId(), user.getId(), 3);

        assertThrows(InscriptionAlreadyScoredException.class, () ->
                inscriptionService.scoreTest(inscription.getId(), user.getId(), 4));

    }

    @Test
    public void testSportTestNotStartedYet() throws AlreadyInscribedException,
            InscriptionDateExpiredException, NoMoreInscriptionsAllowedException, InstanceNotFoundException,
            DateExpiredException, SportTestNotStartedYetException, InscriptionAlreadyScoredException {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Solidary Race 2021", LocalDateTime.now().plusHours(30));
        Inscription inscription = inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER);

        assertThrows(SportTestNotStartedYetException.class, () ->
                inscriptionService.scoreTest(inscription.getId(), user.getId(), 4));

    }

    @Test
    public void testScoreTest() throws AlreadyInscribedException, InscriptionDateExpiredException,
            NoMoreInscriptionsAllowedException, InstanceNotFoundException, PermissionException,
            DateExpiredException, SportTestNotStartedYetException, InscriptionAlreadyScoredException {
        User user1 = singUpUser("user1");
        User user2 = singUpUser("user2");
        SportTest sportTest = addSportTest("Solidary Race 2021", LocalDateTime.now().plusHours(30));
        Inscription inscription1 = inscriptionService.inscribe(sportTest.getId(), user1.getId(), CARD_NUMBER);
        Inscription inscription2 = inscriptionService.inscribe(sportTest.getId(), user2.getId(), CARD_NUMBER);
        Optional<Inscription> foundInscription1 = inscriptionDao.findById(inscription1.getId());
        Optional<Inscription> foundInscription2 = inscriptionDao.findById(inscription2.getId());

        sportTest.setDate(LocalDateTime.now().minusDays(10));
        inscriptionService.scoreTest(inscription1.getId(), user1.getId(), 4);
        inscriptionService.scoreTest(inscription2.getId(), user2.getId(), 2);

        assertEquals(inscription1.getScore(), foundInscription1.get().getScore());
        assertEquals(inscription2.getScore(), foundInscription2.get().getScore());
        assertEquals(3, sportTest.getRating());
        assertEquals(2, sportTest.getNumRatings());
    }

    @Test
    public void testInscribeInNonExistingSportTest() {
        User user = singUpUser("user");

        assertThrows(InstanceNotFoundException.class, () ->
                inscriptionService.inscribe(NON_EXISTENT_ID, user.getId(), CARD_NUMBER));
    }

    @Test
    public void testAlreadyInscribed() throws AlreadyInscribedException, InscriptionDateExpiredException,
            NoMoreInscriptionsAllowedException, InstanceNotFoundException {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Solidary Race 2021");

        inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER);

        assertThrows(AlreadyInscribedException.class, () ->
                inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER));
    }

    @Test
    public void testInscribeInFullSportTest() {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Solidary Race 2021", 0);

        assertThrows(NoMoreInscriptionsAllowedException.class, () ->
                inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER));
    }

    @Test
    public void testInscribeLate() {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Solidary Race 2021", LocalDateTime.now().plusHours(20));

        assertThrows(InscriptionDateExpiredException.class, () ->
                inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER));
    }

    @Test
    public void testInscribe() throws AlreadyInscribedException, InscriptionDateExpiredException,
            NoMoreInscriptionsAllowedException, InstanceNotFoundException {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Solidary Race 2021");
        int prevNumParticipants = sportTest.getNumParticipants();

        Inscription inscription = inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER);
        Optional<Inscription> foundInscription = inscriptionDao.findById(inscription.getId());

        assertTrue(foundInscription.isPresent());
        assertEquals(inscription, foundInscription.get());
        assertEquals(inscription.getCardNumber(), foundInscription.get().getCardNumber());
        assertEquals(inscription.getDorsal(), foundInscription.get().getDorsal());
        assertEquals(inscription.getSportTest(), foundInscription.get().getSportTest());
        assertEquals(inscription.getPrice(), foundInscription.get().getPrice());
        assertEquals(inscription.getReservationDate(), foundInscription.get().getReservationDate());
        assertEquals(inscription.getUser(), foundInscription.get().getUser());
        assertEquals(prevNumParticipants + 1, foundInscription.get().getSportTest().getNumParticipants());

    }

    @Test
    public void testCollectDorsalOfNonExistingSportTest() throws AlreadyInscribedException,
            InscriptionDateExpiredException, NoMoreInscriptionsAllowedException, InstanceNotFoundException {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Urban Race 2021");
        Inscription inscription = inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER);
        sportTest.setDate(LocalDateTime.now().plusHours(10));

        assertThrows(InstanceNotFoundException.class, () ->
                inscriptionService.collectDorsal(NON_EXISTENT_ID, inscription.getId(), CARD_NUMBER));
    }

    @Test
    public void testCollectDorsalOfNonExistingInscription() {
        SportTest sportTest = addSportTest("Urban Race 2021");

        assertThrows(InstanceNotFoundException.class, () ->
                inscriptionService.collectDorsal(sportTest.getId(), NON_EXISTENT_ID, CARD_NUMBER));
    }

    @Test
    public void testCollectDorsalOfNotAssociatedInscription() throws AlreadyInscribedException,
            InscriptionDateExpiredException, NoMoreInscriptionsAllowedException, InstanceNotFoundException {
        User user = singUpUser("user");
        SportTest sportTestValid = addSportTest("Urban Race 2021");
        SportTest sportTestInvalid = addSportTest("Solidary Race 2021");
        Inscription inscription = inscriptionService.inscribe(sportTestValid.getId(), user.getId(), CARD_NUMBER);
        sportTestValid.setDate(LocalDateTime.now().plusHours(10));

        assertThrows(InscriptionNotAssociatedException.class, () ->
                inscriptionService.collectDorsal(sportTestInvalid.getId(), inscription.getId(), CARD_NUMBER));
    }

    @Test
    public void testCollectDorsalSoon() throws AlreadyInscribedException,
            InscriptionDateExpiredException, NoMoreInscriptionsAllowedException, InstanceNotFoundException {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Urban Race 2021");
        Inscription inscription = inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER);
        sportTest.setDate(LocalDateTime.now().plusHours(14));

        assertThrows(NotAllowedYetException.class, () ->
                inscriptionService.collectDorsal(sportTest.getId(), inscription.getId(), CARD_NUMBER));
    }

    @Test
    public void testCollectDorsalLate() throws AlreadyInscribedException,
            InscriptionDateExpiredException, NoMoreInscriptionsAllowedException, InstanceNotFoundException {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Solidary Race 2021");
        Inscription inscription = inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER);
        sportTest.setDate(LocalDateTime.now().minusHours(1));

        assertThrows(CollectDorsalDateExpiredException.class, () ->
                inscriptionService.collectDorsal(sportTest.getId(), inscription.getId(), CARD_NUMBER));
    }

    @Test
    public void testCollectAlreadyCollectedInscription() throws AlreadyInscribedException,
            InscriptionDateExpiredException, NoMoreInscriptionsAllowedException, InstanceNotFoundException,
            NotAllowedYetException, AlreadyCollectedException, IncorrectCardNumberException,
            InscriptionNotAssociatedException, CollectDorsalDateExpiredException {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Urban Race 2021");
        Inscription inscription = inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER);
        sportTest.setDate(LocalDateTime.now().plusHours(10));

        inscriptionService.collectDorsal(sportTest.getId(), inscription.getId(), CARD_NUMBER);

        assertThrows(AlreadyCollectedException.class, () ->
                inscriptionService.collectDorsal(sportTest.getId(), inscription.getId(), CARD_NUMBER));
    }

    @Test
    public void testCollectDorsalWithIncorrectCard () throws AlreadyInscribedException,
            InscriptionDateExpiredException, NoMoreInscriptionsAllowedException, InstanceNotFoundException {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Urban Race 2021");
        Inscription inscription = inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER);
        sportTest.setDate(LocalDateTime.now().plusHours(10));

        assertThrows(IncorrectCardNumberException.class, () ->
                inscriptionService.collectDorsal(sportTest.getId(), inscription.getId(), "1234567890000000"));
    }

    @Test
    public void testCollectDorsal () throws AlreadyInscribedException, InscriptionDateExpiredException,
            NoMoreInscriptionsAllowedException, InstanceNotFoundException,
            NotAllowedYetException, AlreadyCollectedException, IncorrectCardNumberException,
            InscriptionNotAssociatedException, CollectDorsalDateExpiredException {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Urban Race 2021");
        Inscription inscription = inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER);
        sportTest.setDate(LocalDateTime.now().plusHours(10));

        int dorsal = inscriptionService.collectDorsal(sportTest.getId(), inscription.getId(), CARD_NUMBER);

        Optional<Inscription> foundInscription = inscriptionDao.findById(inscription.getId());

        assertTrue(foundInscription.get().isDorsalCollected());
        assertEquals(foundInscription.get().getDorsal(), dorsal);
    }

    @Test
    public void testFindNoInscriptions() {

        User user = singUpUser("user");
        Block<Inscription> expectedIns = new Block<>(new ArrayList<>(), false);

        assertEquals(expectedIns, inscriptionService.findMyInscriptions(user.getId(), 0, 1));
    }

    @Test
    public void testFindInscriptions() throws AlreadyInscribedException, InscriptionDateExpiredException,
            NoMoreInscriptionsAllowedException, InstanceNotFoundException {

        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Urban Race 2021");
        SportTest sportTest2 = addSportTest("Triatlón Teresa Herrera 2021");
        SportTest sportTest3 = addSportTest("Mundialito de futsal 2021");
        sportTest.setDate(LocalDateTime.now().plusMonths(2));
        sportTest2.setDate(LocalDateTime.now().plusMonths(5));
        sportTest3.setDate(LocalDateTime.now().plusMonths(8));

        Inscription inscription1 = inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER);
        Inscription inscription2 = inscriptionService.inscribe(sportTest2.getId(), user.getId(), CARD_NUMBER);
        Inscription inscription3 = inscriptionService.inscribe(sportTest3.getId(), user.getId(), CARD_NUMBER);

        Block<Inscription> expectedBlock = new Block<>(Arrays.asList(inscription1, inscription2), true);
        assertEquals(expectedBlock, inscriptionService.findMyInscriptions(user.getId(), 0, 2));

        expectedBlock = new Block<>(Arrays.asList(inscription3), false);
        assertEquals(expectedBlock, inscriptionService.findMyInscriptions(user.getId(), 1, 2));

    }

}
