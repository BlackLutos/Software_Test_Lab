import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)


class StrangeGameTest {

    //Hour hour = mock(Hour.class);
    StrangeGame strangeGame;

    @Mock
    private StrangeGame strangeGame2;
    class OpenTime extends Hour{
        public int getHour(){
            return 12;
        }
    }
    class PriosnStub extends Prison{
        public void imprisonment (Player player) throws InterruptedException {
            Thread.sleep(1); // 7 days
            //Thread.sleep(3); // 7 days
        }
    }

    @Test
    void test_b() throws InterruptedException {
        //Hour hour = new Hour();
        //System.out.println(hour.getHour());

        strangeGame2 = new StrangeGame();
        strangeGame2.hour = new OpenTime();
        strangeGame2.prison = new PriosnStub();
        System.out.println(strangeGame2.hour.getHour());
        Player player = new Player();
        String message = strangeGame2.enterGame(player);
        assertEquals("After a long period of punishment, the player can leave! :)",message);
        System.out.println(message);
    }
    @Mock
    private StrangeGame strangeGame3;
    class CloseTime extends Hour{
        public int getHour(){
            return 11;
        }
    }

    @Test
    void test_a() throws InterruptedException {
        //Hour hour = new Hour();
        //System.out.println(hour.getHour());

        strangeGame3 = new StrangeGame();
        strangeGame3.hour = new CloseTime();
        strangeGame3.prison = new PriosnStub();
        System.out.println(strangeGame3.hour.getHour());
        Player player = new Player();
        String message = strangeGame3.enterGame(player);
        //assertEquals("Welcome!",message);
        System.out.println(message);
        ArrayList log = strangeGame3.prison.getLog();
        ArrayList zero = new ArrayList<>();
        assertEquals(zero,log);
        System.out.println(strangeGame3.prison.getLog());

    }
    @Mock
    private StrangeGame strangeGame4;

    @Test
    void test_c() throws InterruptedException {
        strangeGame4 = new StrangeGame();
        strangeGame4.hour = new OpenTime();
        strangeGame4.prison = new PriosnStub();
        System.out.println(strangeGame4.hour.getHour());
        Player player1 = new Player("01",-1);
        strangeGame4.enterGame(player1);
        Player player2 = new Player("02",-1);
        strangeGame4.enterGame(player2);
        Player player3 = new Player("03",-1);
        strangeGame4.enterGame(player3);
        System.out.println(strangeGame4.prison.getLog());
        StrangeGame spy = spy(strangeGame4);
        //when(spy.prison.getLog().size()).thenReturn(100);
        int PlayNumber = 3;
        assertEquals(PlayNumber,spy.prison.getLog().size());
        //System.out.println(message);
    }

    @Mock
    private StrangeGame strangeGame5;

    class MyGameDB implements GAMEDb{

        @Override
        public int getScore(String playerid) {
            return 100;
        }
    }

    @Test
    void test_d(){
        strangeGame5 = new StrangeGame();
        Player player1 = new Player("310581027",-1);
        strangeGame5.db = new MyGameDB();
        int points = strangeGame5.getScore(player1.getPlayerId());
        assertEquals(100,points);
        System.out.println(points);

    }
    class MypaypalService implements paypalService{

        @Override
        public String doDonate() {
            return "Success";
        }
    }
    @Test
    void test_e(){
        strangeGame = new StrangeGame();
        System.out.println(strangeGame.hour.getHour());
        MypaypalService paypal = new MypaypalService();
        String donateResult = strangeGame.donate(paypal);
        assertEquals("Thank you",donateResult);

    }


}
