import java.util.ArrayList;

class PacmanMap {
	
	//Lista auxiliar permite encontrar elemento na matriz de adjacencia de acordo com os indices dados
	ArrayList<Integer> listaAuxiliarIndice;
	int[][]map;
	char[][]mapNodes;
	int[][]adjMatr;
	char[]nodes;
	
	PacmanMap(int[][]mapa) {
		this.listaAuxiliarIndice = new ArrayList<Integer>();
		this.map = mapa;
		this.adjMatr = generateAdjMatrix(mapa);
		this.nodes = generateNodes(listaAuxiliarIndice);
	}
	
	public int[][]getMap(){
		return this.map;
	}
	
	public char[][]getMapNodes(){
		return this.mapNodes;
	}
	
	public int[][]getAdjMatr(){
		return this.adjMatr;
	}
	
	public ArrayList<Integer> getListaAuxiliarIndice(){
		return this.listaAuxiliarIndice;
	}
	
	public char[]getNodes(){
		return this.nodes;
	}
	
	public char[] generateNodes(ArrayList<Integer>vertices) {
		char[]nodeList = new char[vertices.size()];
		
		for(int i = 0; i < vertices.size(); i++) {
			nodeList[i] = (char) (i+ 65);
		}
		
		//Assign to mapNodes
		//Gera representacao do mapa em forma de grafo com vertices assinalados por letras.
		//o caminho encontrado sera expresso por essa ordem de vertices.
		mapNodes = new char[map.length][map[0].length];
		int counter = 0;
		for(int i = 0; i< map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				if(map[i][j] != 0) {
					mapNodes[i][j] = nodeList[counter];
					counter++;
				}
			}
		}
		
		
		return nodeList;
	}
	
	public int[][] generateAdjMatrix(int[][] inputMap)
	{
		int paredes = 0;
		
		
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
		
		return adjMatr;
	}
		
		//metodo auxiliar parar encontrar indice correto para a lista de adjacencia
		//Feito para receber listaAuxiliarIndice como input.
		public int reverseIndexLookUp(ArrayList<Integer> listaAuxiliarIndice, int i, int j) {
			int elementInList = 10*i + j;
			int correctIndex =  listaAuxiliarIndice.indexOf( elementInList )  ;
			return correctIndex;
		}
}
