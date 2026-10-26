package br.edu.ifsp.boliche;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JogoTest {
    private Jogo jogo;

    @BeforeEach
    public void setUp() {
        jogo = new Jogo();
    }

    private ben(int n, int p) {
        for (int i = 0; i < n; i++) jogo.jogar(p);
    }

    @Test
    public void testCanaleta() {
        ben(20, 0);
        assertEquals(0, jogo.pontuacao());
        assertTrue(jogo.encerrado());
    }

    @Test
    public void testTodosUns() {
        ben(20, 1);
        assertEquals(20, jogo.pontuacao());
    }

    @Test
    public void testUmSpare() {
        jogo.jogar(5);
        jogo.jogar(5);
        jogo.jogar(3);
        ben(17, 0);
        assertEquals(16, jogo.pontuacao());
    }

    @Test
    public void testUmStrike() {
        jogo.jogar(10);
        jogo.jogar(3);
        jogo.jogar(4);
        ben(16, 0);
        assertEquals(24, jogo.pontuacao());
    }

    @Test
    public void testSpareEmTodos() {
        ben(21, 5);
        assertEquals(150, jogo.pontuacao());
    }

    @Test
    public void testJogoPerfeito() {
        ben(12, 10);
        assertEquals(300, jogo.pontuacao());
    }

    @Test
    public void testPartidaMista() {
        int[] jogadas = {7,3, 9,0, 10, 8,8, 2,0, 6,4,10,10, 8,1};
        for (int p : jogadas) {
            jogo.jogar(p);
        }
        assertEquals(142, jogo.pontuacao()); // Ajustado para a lógica de boliche padrão
    }

    @Test
    public void testValidacaoPinosInvalidos() {
        assertThrows(IllegalArgumentException.class, () -> jogo.jogar(11));
        assertThrows(IllegalArgumentException.class, () -> jogo.jogar(-1));
    }

    @Test
    public void testValidacaoFrameInvalido() {
        jogo.jogar(7);
        assertThrows(IllegalArgumentException.class, () -> jogo.jogar(4));
    }

    @Test
    public void testJogarAposFim() {
        ben(20, 0);
        assertThrows(IllegalStateException.class, () -> jogo.jogar(1));
    }
}