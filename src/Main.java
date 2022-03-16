import java.util.*;

public class Main {

	public static void main(String[] args) {
		//representacao fisica do mapa
		int[][]mapa1 = {
				{1,1,1,1},
				{1,0,0,1 },
				{1,1,0,1 },
				{1,1,1,1 }};
		PacmanMap myMap = new PacmanMap(mapa1);
		
		System.out.println("Mapa Inicial (Input):");
		print2D(myMap.getMap());
		
		System.out.println("Nova Matriz de Adjacencia:");
		print2D(myMap.getAdjMatr());
		
		System.out.println("Mapa com vertices nomeados:");
		print2D(myMap.getMapNodes());
		//Aplicar busca em Largura (BFS)
		//Realizando e imprimindo 
		MenorCaminho menorCaminho = new MenorCaminho(myMap.getAdjMatr(), myMap.getNodes(), 7, 12);
		menorCaminho.bfs();
		
	}
	
	
	
	
	//metodos auxiliares para impressao formatada
	public static void print2D(int mat[][])
    {
        // Loop through all rows
        for (int[] row : mat)
 
            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }
	
	public static void print2D(char mat[][])
    {
        for (char[] row : mat)
            System.out.println(Arrays.toString(row));
    }

}
