package android.ali_fatolahi.tictactoe;

/**
 * An enum used to represent how is a certain cell filled out.
 * Created by Ali_Fatolahi on 1/1/2017.
 */

public enum Symbol {
    AVAILABLE('?'),
    COMPUTER('O'),
    HUMAN('X');

    private final char content;

    Symbol(final char content) {
        this.content = content;
    }

    public char getContent() {
        return content;
    }

    @Override
    public String toString() {
        return Character.toString(content);
    }
}
