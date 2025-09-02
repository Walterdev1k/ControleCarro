import java.util.Scanner;

public class ControleCarro {
    private boolean ligado;
    private int marcha;
    private int velocidade;
    private boolean motorIniciado;

    public ControleCarro() {
        this.ligado = false;
        this.marcha = 0;
        this.velocidade = 0;
        this.motorIniciado = false;
    }

    public void ligarCarro() {
        if (!ligado) {
            ligado = true;
            System.out.println("✅ Carro ligado (em ponto morto)");
        } else {
            System.out.println("❌ O carro já está ligado");
        }
    }

    public void iniciarCarro() {
        if (!ligado) {
            System.out.println("❌ O carro está desligado. Ligue primeiro.");
            return;
        }

        if (!motorIniciado && marcha == 0) {
            motorIniciado = true;
            System.out.println("🚗 Motor iniciado! Pronto para andar.");
        } else if (motorIniciado) {
            System.out.println("❌ O motor já está iniciado");
        } else {
            System.out.println("❌ Para iniciar o motor, coloque em ponto morto (marcha 0)");
        }
    }

    public void acelerar() {
        if (!verificarCarroPronto()) return;

        if (marcha == 0) {
            System.out.println("❌ Não é possível acelerar em ponto morto");
            return;
        }

        if (velocidade < 120) {
            velocidade++;
            System.out.println("📈 Acelerando... Velocidade: " + velocidade + "km/h");
            verificarMarchaAdequada();
        } else {
            System.out.println("🚫 Velocidade máxima atingida (120km/h)");
        }
    }

    public void diminuirVelocidade() {
        if (!verificarCarroPronto()) return;

        if (velocidade > 0) {
            velocidade--;
            System.out.println("📉 Diminuindo velocidade... Velocidade: " + velocidade + "km/h");
            verificarMarchaAdequada();
        } else {
            System.out.println("🚫 Velocidade mínima atingida (0km/h)");
        }
    }

    public void virar(String direcao) {
        if (!verificarCarroPronto()) return;

        if (velocidade >= 1 && velocidade <= 40) {
            System.out.println("🔄 Virando para " + direcao + " na velocidade de " + velocidade + "km/h");
        } else {
            System.out.println("❌ Só é possível virar entre 1km/h e 40km/h. Velocidade atual: " + velocidade + "km/h");
        }
    }

    public void verificarVelocidade() {
        if (!ligado) {
            System.out.println("❌ Carro desligado");
            return;
        }
        System.out.println("📊 Velocidade atual: " + velocidade + "km/h");
        System.out.println("⚙️  Marcha atual: " + marcha);
    }

    public void trocarMarcha(int novaMarcha) {
        if (!verificarCarroPronto()) return;

        if (novaMarcha < 0 || novaMarcha > 6) {
            System.out.println("❌ Marcha inválida. Use de 0 a 6.");
            return;
        }

        if (Math.abs(novaMarcha - marcha) > 1) {
            System.out.println("❌ Não é possível pular marchas");
            return;
        }

        if (verificarMarchaCompativel(novaMarcha)) {
            marcha = novaMarcha;
            System.out.println("🔧 Marcha trocada para: " + marcha);
        } else {
            System.out.println("❌ Velocidade " + velocidade + "km/h não compatível com marcha " + novaMarcha);
        }
    }

    public void desligarCarro() {
        if (ligado && marcha == 0 && velocidade == 0) {
            ligado = false;
            motorIniciado = false;
            System.out.println("🔴 Carro desligado com sucesso");
        } else if (!ligado) {
            System.out.println("❌ O carro já está desligado");
        } else {
            System.out.println("❌ Para desligar, coloque em ponto morto (marcha 0) e pare o carro (0km/h)");
        }
    }

    private boolean verificarCarroPronto() {
        if (!ligado) {
            System.out.println("❌ Carro desligado");
            return false;
        }
        if (!motorIniciado) {
            System.out.println("❌ Motor não iniciado");
            return false;
        }
        return true;
    }

    private boolean verificarMarchaCompativel(int marcha) {
        switch (marcha) {
            case 0: return velocidade == 0;
            case 1: return velocidade >= 0 && velocidade <= 20;
            case 2: return velocidade >= 21 && velocidade <= 40;
            case 3: return velocidade >= 41 && velocidade <= 60;
            case 4: return velocidade >= 61 && velocidade <= 80;
            case 5: return velocidade >= 81 && velocidade <= 100;
            case 6: return velocidade >= 101 && velocidade <= 120;
            default: return false;
        }
    }

    private void verificarMarchaAdequada() {
        if (!verificarMarchaCompativel(marcha)) {
            System.out.println("⚠️  Atenção: Marcha " + marcha + " não é adequada para " + velocidade + "km/h");
        }
    }

    public void mostrarStatus() {
        System.out.println("\n=== STATUS DO CARRO ===");
        System.out.println("🔌 Ligado: " + (ligado ? "Sim" : "Não"));
        System.out.println("🚗 Motor: " + (motorIniciado ? "Iniciado" : "Parado"));
        System.out.println("⚙️  Marcha: " + marcha);
        System.out.println("📊 Velocidade: " + velocidade + "km/h");
        System.out.println("=======================\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ControleCarro carro = new ControleCarro();
        int opcao;

        do {
            System.out.println("\n🎮 MENU DO CARRO");
            System.out.println("1️⃣  - Ligar carro");
            System.out.println("2️⃣  - Iniciar motor");
            System.out.println("3️⃣  - Acelerar");
            System.out.println("4️⃣  - Diminuir velocidade");
            System.out.println("5️⃣  - Virar esquerda");
            System.out.println("6️⃣  - Virar direita");
            System.out.println("7️⃣  - Verificar velocidade");
            System.out.println("8️⃣  - Trocar marcha");
            System.out.println("9️⃣  - Desligar carro");
            System.out.println("🔟  - Mostrar status");
            System.out.println("0️⃣  - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    carro.ligarCarro();
                    break;
                case 2:
                    carro.iniciarCarro();
                    break;
                case 3:
                    carro.acelerar();
                    break;
                case 4:
                    carro.diminuirVelocidade();
                    break;
                case 5:
                    carro.virar("esquerda");
                    break;
                case 6:
                    carro.virar("direita");
                    break;
                case 7:
                    carro.verificarVelocidade();
                    break;
                case 8:
                    System.out.print("Digite a nova marcha (0-6): ");
                    int novaMarcha = scanner.nextInt();
                    carro.trocarMarcha(novaMarcha);
                    break;
                case 9:
                    carro.desligarCarro();
                    break;
                case 10:
                    carro.mostrarStatus();
                    break;
                case 0:
                    System.out.println("👋 Encerrando programa...");
                    break;
                default:
                    System.out.println("❌ Opção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
