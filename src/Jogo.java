package br.edu.ifsp.boliche;

public class Jogo {
    private int[] jogadas = new int[21];
    private int indiceJogada = 0;

    public void jogar(int pinos) {
        if (pinos < 0 || pinos > 10) {
            throw new IllegalArgumentException("Pinos inválidos");
        }
        if (encerrado()) {
            throw new IllegalStateException("Partida encerrada");
        }
        
        // Validação V2 para frames 1 a 9
        if (indiceJogada % 2 == 0 && indiceJogada < 18) {
            // é a primeira bola do frame, ok
        } else if (indiceJogada < 18 && indiceJogada % 2 != 0) {
            // segunda bola do frame
            if (jogadas[indiceJogada - 1] + pinos > 10 && jogadas[indiceJogada - 1] != 10) {
                throw new IllegalArgumentException("Soma do frame maior que 10");
            }
        }

        jogadas[indiceJogada++] = pinos;
    }

    public int pontuacao() {
        int pontuacao = 0;
        int cursor = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (ehStrike(cursor)) {
                pontuacao += 10 + eBonusStrike(cursor);
                cursor += 1;
            } else if (ehSpare(cursor)) {
                pontuacao += 10 + eBonusSpare(cursor);
                cursor += 2;
            } else {
                pontuacao += jogadas[cursor] + jogadas[cursor + 1];
                cursor += 2;
            }
        }
        return pontuacao;
    }

    public boolean encerrado() {
        int cursor = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (cursor >= indiceJogada) return false;
            if (ehStrike(cursor)) {
                cursor += 1;
            } else if (ehSpare(cursor)) {
                cursor += 2;
            } else {
                cursor += 2;
            }
        }
        return cursor <= indiceJogada && indiceJogada >= 20; 
    }

    private boolean ehStrike(int cursor) {
        return jogadas[cursor] == 10;
    }

    private boolean ehSpare(int cursor) {
        return jogadas[cursor] + jogadas[cursor + 1] == 10;
    }

    private int eBonusStrike(int cursor) {
        return jogadas[cursor + 1] + jogadas[cursor + 2];
    }

    private int eBonusSpare(int cursor) {
        return jogadas[cursor + 2];
    }
}