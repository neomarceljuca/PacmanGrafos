import java.util.*;

class MenorCaminho {
    int[][] adjacencyM;
    char[] nodes;
    int[] prev;
    int[] visitados;
    int comeco, fim;

    MenorCaminho(int[][] adjacencyM, char[] nodes, int comeco, int fim){
        this.adjacencyM = adjacencyM;
        this.nodes = nodes;
        this.comeco=comeco;
        this.fim=fim;
        this.visitados= new int[nodes.length];
        this.prev = new int[nodes.length];
        Arrays.fill(this.prev, -1);
    }

    public void bfs(){
        Queue<Integer> queue = new LinkedList<>();
        visitados[comeco] = 1;
        queue.add(comeco);

        while(!queue.isEmpty()){
            int node_atual = queue.poll();
            int node_vizinho;

            while((node_vizinho =vizinhosNaoVisitados(node_atual)) != -1){
                visitados[node_vizinho] = 1;
                queue.add(node_vizinho);
                prev[node_vizinho] = node_atual;
                if(node_vizinho== fim){
                    queue.clear();
                    break;
                }
            }
        }

        tracar_rota();
    }

    private int vizinhosNaoVisitados(int index){
        for(int i=0; i<adjacencyM.length; i++){
            if(adjacencyM[index][i] == 1 && visitados[i] == 0){
                return i;
            }
        }
        return -1;
    }

    private void tracar_rota(){
        int node =fim;
        List<Character> route = new ArrayList<>();
        while(node!=-1){
            route.add(nodes[node]);
            node = prev[node];
        }
        Collections.reverse(route);
        System.out.println("Menor rota entre vertices " + nodes[comeco] + " e " + nodes[fim] +":"); 
        System.out.println(route);
    }
}