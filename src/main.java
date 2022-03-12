import java.util.*;

public class main {

	public static void main(String[] args) {
		
		int[][]mapa1 = {
				{1,1,1,1},
				{1,0,0,1 },
				{1,1,0,1 },
				{1,1,1,1 }};
		System.out.println("Mapa Inicial:");
		print2D(mapa1);
		
		int[][]adjMatr = generateAdjMatrix(mapa1);
		
		System.out.println("Nova Matriz de Adjacencia:");
		print2D(adjMatr);
		
		//Faltando: Implementar e Aplicar busca em Largura (BFS)
	}
	
	public static int[][] generateAdjMatrix(int[][] inputMap)
	{
		int paredes = 0;
		//Lista auxiliar permite encontrar elemento na matriz de adjacencia de acordo com os indices dados
		ArrayList<Integer> listaAuxiliarIndice = new ArrayList<Integer>();
		
		for(int i = 0; i< inputMap.length; i++) {
			for(int j = 0; j < inputMap[0].length; j++) {
				if(inputMap[i][j] == 0) paredes++; 
				else listaAuxiliarIndice.add( 10*i + j );
			}
		}
		
		//matriz ja initializada por padrao do java com todos os valores igual a 0.
		int vertices = inputMap.length*inputMap.length - paredes;
		int[][]adjMatr = new int[vertices][vertices];
		
		int count = 0;
		
		for(int i = 0; i< inputMap.length; i++) {
			for(int j = 0; j < inputMap.length; j++) {
				if(inputMap[i][j] == 1)	{
					//olhar para esquerda
					if(j > 0 && inputMap[i][j-1] == 1) {
						adjMatr[count][count-1] = 1;
					}
					//olhar para direita
					if(j< inputMap[0].length-1 && inputMap[i][j+1] == 1) {
						adjMatr[count][count+1] = 1;	
					}	
					//olhar para cima
					if(i > 0 && inputMap[i-1][j] == 1) {
					//needs reverse lookup to find row in adjMatr only by having [i] and [j] values from the indexes
						adjMatr[count][reverseIndexLookUp(listaAuxiliarIndice, i-1, j) ] = 1;
					}						
					//olhar para baixo	
					if(i< inputMap.length-1 && inputMap[i+1][j] == 1) {
						adjMatr[count][reverseIndexLookUp(listaAuxiliarIndice, i+1, j) ] = 1;	
					}	
					count++;
				}		
			}
		}
		
		System.out.println("Nova Lista Auxiliar:");
		System.out.println(listaAuxiliarIndice.toString());
		
		return adjMatr;
	}
	
	//metodo auxiliar para impressao formatada
	public static void print2D(int mat[][])
    {
        // Loop through all rows
        for (int[] row : mat)
 
            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }
	
	//metodo auxiliar parar encontrar indice correto para a lista de adjacencia
	//Feito para receber listaAuxiliarIndice como input.
	public static int reverseIndexLookUp(ArrayList<Integer> listaAuxiliarIndice, int i, int j) {
		int elementInList = 10*i + j;
		int correctIndex =  listaAuxiliarIndice.indexOf( elementInList )  ;
		return correctIndex;
	}

}
