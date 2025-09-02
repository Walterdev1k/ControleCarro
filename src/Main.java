import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ControleCarro carro = new ControleCarro();
        int opcao;

        do {
            System.out.println("\n==== MENU CARRO ====");
            System.out.println("1 - Ligar carro");
            System.out.println("2 - Desligar carro");
            System.out.println("3 - Acelerar");
            System.out.println("4 - Reduzir velocidade");
            System.out.println("5 - Trocar marcha");
            System.out.println("6 - Virar para esquerda");
            System.out.println("7 - Virar para direita");
            System.out.println("8 - Voltar para reta");
            System.out.println("9 - Verificar velocidade");
            System.out.println("10 - Mostrar status completo");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> carro.ligar();
                case 2 -> carro.desligar();
                case 3 -> carro.acelerar();
                case 4 -> carro.reduzir();
                case 5 -> {
                    System.out.print("Digite a marcha (0 a 6): ");
                    int marcha = scanner.nextInt();
                    carro.trocarMarcha(marcha);
                }
                case 6 -> carro.virarEsquerda();
                case 7 -> carro.virarDireita();
                case 8 -> carro.retornarReta();
                case 9 -> carro.mostrarVelocidade();
                case 10 -> carro.mostrarStatus();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida!");
            }

            // Mostra status atualizado após cada operação (exceto status completo)
            if (opcao != 0 && opcao != 10 && opcao != 9) {
                System.out.println("\n--- Status atual ---");
                System.out.println("Ligado: " + (carro.isLigado() ? "Sim" : "Não"));
                System.out.println("Velocidade: " + carro.getVelocidade() + " km/h");
                System.out.println("Marcha: " + carro.getMarcha());
                System.out.println("Direção: " + carro.getDirecao());
            }

        } while (opcao != 0);

        scanner.close();
    }
}