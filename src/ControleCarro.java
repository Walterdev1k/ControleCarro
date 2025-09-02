public class ControleCarro {
    private boolean ligado;
    private int velocidade;
    private int marcha; // 0 a 6
    private String direcao;

    public ControleCarro() {
        ligado = false;
        velocidade = 0;
        marcha = 0;
        direcao = "reta";
    }

    public boolean isLigado() {
        return ligado;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public int getMarcha() {
        return marcha;
    }

    public String getDirecao() {
        return direcao;
    }

    public void ligar() {
        if (!ligado) {
            ligado = true;
            System.out.println("Carro ligado!");
        } else {
            System.out.println("O carro já está ligado.");
        }
    }

    public void desligar() {
        if (ligado && velocidade == 0 && marcha == 0) {
            ligado = false;
            System.out.println("Carro desligado!");
        } else {
            System.out.println("Não é possível desligar. O carro deve estar em ponto morto e velocidade 0.");
        }
    }

    public void acelerar() {
        if (!ligado) {
            System.out.println("O carro está desligado!");
            return;
        }
        if (marcha == 0) {
            System.out.println("Não é possível acelerar no ponto morto.");
            return;
        }

        int novaVel = (int) Math.ceil(velocidade * 1.5) + 1;
        if (novaVel > 120) novaVel = 120;

        if (validarVelocidadePorMarcha(novaVel)) {
            velocidade = novaVel;
            System.out.println("Acelerando... Velocidade atual: " + velocidade + " km/h");
        } else {
            // Mensagem detalhada explicando o motivo
            String faixaVelocidade = obterFaixaVelocidadePorMarcha();
            System.out.println("Velocidade não permitida para a marcha atual! " + faixaVelocidade +
                    ", você tentou acelerar para " + novaVel + " km/h");
        }
    }

    public void reduzir() {
        if (!ligado) {
            System.out.println("O carro está desligado!");
            return;
        }
        if (velocidade > 0) {
            int novaVel = (int) Math.floor(velocidade / 1.5);
            if (novaVel < 0) novaVel = 0;

            // Verifica se a nova velocidade é válida para a marcha atual
            if (validarVelocidadePorMarcha(novaVel)) {
                velocidade = novaVel;
                System.out.println("Reduzindo... Velocidade atual: " + velocidade + " km/h");
            } else {
                // Se não for válida, força a redução para a velocidade máxima permitida
                int velMaxima = obterVelocidadeMaximaMarcha();
                velocidade = Math.min(novaVel, velMaxima);
                System.out.println("Reduzindo... Velocidade ajustada para " + velocidade +
                        " km/h (limite da marcha " + marcha + ")");

                // Sugere reduzir a marcha se a velocidade ficou muito baixa
                if (velocidade < obterVelocidadeMinimaMarcha() && marcha > 1) {
                    System.out.println("Sugestão: reduza a marcha para " + (marcha - 1));
                }
            }
        } else {
            System.out.println("O carro já está parado.");
        }
    }

    public void trocarMarcha(int novaMarcha) {
        if (!ligado) {
            System.out.println("O carro está desligado!");
            return;
        }

        // Valida se a nova marcha está dentro do range permitido
        if (novaMarcha < 0 || novaMarcha > 6) {
            System.out.println("Marcha inválida! Deve ser entre 0 e 6.");
            return;
        }

        // Valida se é uma troca sequencial (apenas 1 marcha por vez)
        if (Math.abs(novaMarcha - marcha) != 1) {
            System.out.println("Só é permitido trocar uma marcha por vez (sequencial).");
            return;
        }

        // Valida se a velocidade atual é compatível com a nova marcha
        if (!validarVelocidadePorMarcha(velocidade, novaMarcha)) {
            String faixaVelocidade = obterFaixaVelocidadePorMarcha(novaMarcha);
            System.out.println("Não é possível engatar marcha " + novaMarcha +
                    ". Velocidade atual: " + velocidade + " km/h. " + faixaVelocidade);
            return;
        }

        marcha = novaMarcha;
        System.out.println("Marcha trocada para: " + marcha);
    }

    public void virarEsquerda() {
        if (!ligado) {
            System.out.println("O carro está desligado!");
            return;
        }
        if (velocidade < 1 || velocidade > 40) {
            System.out.println("Não é possível virar! Velocidade deve estar entre 1 e 40 km/h.");
            return;
        }

        // Se já está virando para esquerda, mantém; se está para direita, volta para reta
        if (direcao.equals("direita")) {
            direcao = "reta";
            System.out.println("Voltando para reta antes de virar à esquerda.");
        }

        direcao = "esquerda";
        System.out.println("Virando à esquerda.");
    }

    public void virarDireita() {
        if (!ligado) {
            System.out.println("O carro está desligado!");
            return;
        }
        if (velocidade < 1 || velocidade > 40) {
            System.out.println("Não é possível virar! Velocidade deve estar entre 1 e 40 km/h.");
            return;
        }

        // Se já está virando para direita, mantém; se está para esquerda, volta para reta
        if (direcao.equals("esquerda")) {
            direcao = "reta";
            System.out.println("Voltando para reta antes de virar à direita.");
        }

        direcao = "direita";
        System.out.println("Virando à direita.");
    }

    public void retornarReta() {
        if (!direcao.equals("reta")) {
            direcao = "reta";
            System.out.println("Retornando para reta.");
        }
    }

    public void mostrarVelocidade() {
        System.out.println("Velocidade atual: " + velocidade + " km/h");
    }

    public void mostrarStatus() {
        System.out.println("=== STATUS DO CARRO ===");
        System.out.println("Ligado: " + (ligado ? "Sim" : "Não"));
        System.out.println("Velocidade: " + velocidade + " km/h");
        System.out.println("Marcha: " + marcha);
        System.out.println("Direção: " + direcao);
        System.out.println("========================");
    }

    private boolean validarVelocidadePorMarcha(int vel) {
        return validarVelocidadePorMarcha(vel, this.marcha);
    }

    private boolean validarVelocidadePorMarcha(int vel, int marcha) {
        return switch (marcha) {
            case 1 -> vel >= 0 && vel <= 20;
            case 2 -> vel >= 21 && vel <= 40;
            case 3 -> vel >= 41 && vel <= 60;
            case 4 -> vel >= 61 && vel <= 80;
            case 5 -> vel >= 81 && vel <= 100;
            case 6 -> vel >= 101 && vel <= 120;
            default -> vel == 0; // Marcha 0 (ponto morto) - só velocidade 0
        };
    }

    private String obterFaixaVelocidadePorMarcha() {
        return obterFaixaVelocidadePorMarcha(this.marcha);
    }

    private String obterFaixaVelocidadePorMarcha(int marcha) {
        return switch (marcha) {
            case 1 -> "Marcha 1: 0 a 20 km/h";
            case 2 -> "Marcha 2: 21 a 40 km/h";
            case 3 -> "Marcha 3: 41 a 60 km/h";
            case 4 -> "Marcha 4: 61 a 80 km/h";
            case 5 -> "Marcha 5: 81 a 100 km/h";
            case 6 -> "Marcha 6: 101 a 120 km/h";
            default -> "Ponto morto: apenas 0 km/h";
        };
    }

    private int obterVelocidadeMaximaMarcha() {
        return switch (marcha) {
            case 1 -> 20;
            case 2 -> 40;
            case 3 -> 60;
            case 4 -> 80;
            case 5 -> 100;
            case 6 -> 120;
            default -> 0;
        };
    }

    private int obterVelocidadeMinimaMarcha() {
        return switch (marcha) {
            case 1 -> 0;
            case 2 -> 21;
            case 3 -> 41;
            case 4 -> 61;
            case 5 -> 81;
            case 6 -> 101;
            default -> 0;
        };
    }
}