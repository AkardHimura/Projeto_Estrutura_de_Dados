import java.util.ArrayList;

public class SetePontes {

    // Matriz de adjacência representando as pontes entre os bairros
    private static final int[][] matrizAdjacencia = {
            {0, 1, 1, 0},
            {1, 0, 1, 1},
            {1, 1, 0, 1},
            {0, 1, 1, 0}
    };

    // Vetor de visitados para marcar os bairros já visitados durante a busca em profundidade
    private static boolean[] visitados = new boolean[matrizAdjacencia.length];

    // Lista para armazenar o caminho percorrido durante a busca em profundidade
    private static ArrayList<Integer> caminho = new ArrayList<>();

    // Número de passos realizados durante a busca em profundidade
    private static int passos = 0;

    // Método que realiza a busca em profundidade a partir de um bairro inicial
    private static void dfs(int bairroAtual) {
        // Marca o bairro atual como visitado e adiciona ao caminho percorrido
        visitados[bairroAtual] = true;
        caminho.add(bairroAtual);

        // Incrementa o número de passos
        passos++;

        // Verifica se todos os bairros já foram visitados
        boolean todosVisitados = true;
        for (boolean visitado : visitados) {
            if (!visitado) {
                todosVisitados = false;
                // ainda há bairros para visitar
                break;
            }
        }

        // Se todos os bairros já foram visitados, imprime o caminho percorrido e retorna
        if (todosVisitados) {
            System.out.print("Caminho percorrido: ");
            for (int i = 0; i < caminho.size(); i++) {
                System.out.print((char) ('A' + caminho.get(i)) + " -> ");
            }
            System.out.println((char) ('A' + caminho.get(0)));
            return;
        }

        // Tenta visitar os bairros adjacentes ao bairro atual
        for (int i = 0; i < visitados.length; i++) {
            // Verifica se há uma ponte entre os bairros e se o bairro ainda não foi visitado
            if (matrizAdjacencia[bairroAtual][i] == 1 && !visitados[i]) {
                // Chama a busca em profundidade recursivamente para o próximo bairro
                dfs(i);
            }
        }

        // Remove o último bairro adicionado ao caminho quando não há mais caminhos possíveis e decrementa o número de passos
        caminho.remove(caminho.size() - 1);
        visitados[bairroAtual] = false;
        passos--;
    }

    public static void main(String[] args) {
        // Chama a busca em profundidade a partir do primeiro bairro (A)
        dfs(0);

        // Imprime a matriz de adjacência
        System.out.println("Matriz de adjacência:");
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            for (int j = 0; j < matrizAdjacencia[i].length; j++) {
                System.out.print(matrizAdjacencia[i][j] + " ");
            }
            System.out.println();
        }
    }
}
