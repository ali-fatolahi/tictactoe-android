package android.ali_fatolahi.tictactoe;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static android.ali_fatolahi.tictactoe.Symbol.AVAILABLE;
import static android.ali_fatolahi.tictactoe.Symbol.COMPUTER;
import static android.ali_fatolahi.tictactoe.Symbol.HUMAN;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by Ali_Fatolahi on 1/1/2017.
 */
public class SymbolTest {
    @Test
    public void testSymbolContents() {
        Map<Symbol, Character> expectedSymbolContent = new HashMap(3);
        expectedSymbolContent.put(AVAILABLE, '?');
        expectedSymbolContent.put(HUMAN, 'X');
        expectedSymbolContent.put(COMPUTER, 'O');

        for (Symbol symbol : Symbol.values()) {
            Character actualContent = symbol.getContent();

            Assert.assertThat(actualContent, is(expectedSymbolContent.get(symbol)));
        }
    }

    @Test
    public void testSymbolToString() {
        Map<Symbol, String> expectedSymbolContent = new HashMap(3);
        expectedSymbolContent.put(AVAILABLE, "?");
        expectedSymbolContent.put(HUMAN, "X");
        expectedSymbolContent.put(COMPUTER, "O");

        for (Symbol symbol : Symbol.values()) {
            String actualContent = symbol.toString();

            Assert.assertThat(actualContent, is(expectedSymbolContent.get(symbol)));
        }
    }
}