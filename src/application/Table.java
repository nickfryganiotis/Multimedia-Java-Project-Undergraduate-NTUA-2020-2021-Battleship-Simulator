package application;

import java.util.ArrayList;



public class Table {
	private boolean table [][];
	private int carrier[];
	private int battleship[];
	private int cruiser[];
	private int submarine[];
	private int destroyer[];
	private int size[];
	
	public Table() {
		table = new boolean [10][10];
		carrier = new int [4];
		battleship = new int [4];
		cruiser = new int [4];
		submarine = new int [4];
		destroyer = new int [4];
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				table[i][j] = false;
			}
		}
		
		for(int i=0; i<4; i++) {
			carrier[i] = 0;
			battleship[i] = 0;
			cruiser[i] = 0;
			submarine[i] = 0;
			destroyer[i] = 0;
		}
		
		size = new int[5];
		size[0] = 5;
		size[1] = 4;
		size[2] = 3;
		size[3] = 3;
		size[4] = 2;
	}
	public void set(ArrayList<String> input) {
		try {
			int i=0;
			int counter = 0;
			int count_ships[] = new int[5];
			for(int t=0; t<5; t++) count_ships[t] = 0;
			
			while(i<input.size()) {
				ArrayList<Integer> tmp = new ArrayList<Integer>();
				for(int j=0; j<4; j++) {
					if(input.get(i+j)!="\n") {
						tmp.add(Integer.parseInt(input.get(i+j)));
					}
					else {
						throw new MissingInputException("There is some information missing for the input");
					}
				}
				
				
				count_ships[tmp.get(0)-1]++;
				if(count_ships[tmp.get(0)-1] > 1) {
					throw new InvalidCountException("There is more than one ships for a type of ship");
				}
				
				if((tmp.get(1)) < 0 || (tmp.get(1)>9) || (tmp.get(2)<0) || (tmp.get(2)>9)  ) {
					throw new OversizeException("The ship is out of table's bounds:("+tmp.get(1)+","+tmp.get(2)+")-(...,...)");
				}
				
				if(tmp.get(3)==2) {
					if((tmp.get(1)+size[tmp.get(0)-1])>9) {
						throw new OversizeException("The ship is out of table's bounds:("+tmp.get(1)+","+tmp.get(2)+")-("+(tmp.get(1)+size[tmp.get(0)-1])+","+tmp.get(2)+")");
					}
				}
				else {
					if((tmp.get(2)+size[tmp.get(0)-1])>9) {
						throw new OversizeException("The ship is out of table's bounds:("+tmp.get(1)+","+tmp.get(2)+")-("+tmp.get(1)+","+(tmp.get(2)+size[tmp.get(0)-1])+")");
					}
				}
				
				int dx = 0;
				int dy = 0;
				int prev_x = -1;
				int prev_y = -1;
				for(int k=0; k<size[tmp.get(0)-1]; k++) {
					
					if(table[tmp.get(1)+dx][tmp.get(2)+dy]) {
						throw new OverlapTilesException("Ship in the same position");
					}
					if((tmp.get(1)+dx)<9) {
						if(table[tmp.get(1)+dx+1][tmp.get(2)+dy]) {
							throw new AdjacentTilesException("Tangent ships");
						}
					}
					if((tmp.get(1)+dx)>0) {
						if((table[tmp.get(1)+dx-1][tmp.get(2)+dy])&&((tmp.get(1)+dx-1)!=prev_x)) {
							throw new AdjacentTilesException("Tangent ships");
						}
					}
					if((tmp.get(2)+dy)<9) {
						if(table[tmp.get(1)+dx][tmp.get(2)+dy+1]) {
							throw new AdjacentTilesException("Tangent ships");
						}
					}
					if((tmp.get(2)+dy)>0&&((tmp.get(2)+dy-1)!=prev_y)) {
						if(table[tmp.get(1)+dx][tmp.get(2)+dy-1]) {
							for(int t=0; t<10; t++) {
								for(int s=0; s<10; s++) {
									System.out.print(table[t][s]+" ");
								}
								System.out.println();
							}
							throw new AdjacentTilesException("Tangent ships");
						}
					}
					table[tmp.get(1)+dx][tmp.get(2)+dy] = true;
					prev_x = tmp.get(1)+dx;
					prev_y = tmp.get(2)+dy;
					if((tmp.get(3)==1) && (k<(size[tmp.get(0)-1]-1))) {
						dy++;
					}
					else if((tmp.get(3)==2) && (k<(size[tmp.get(0)-1]-1))){
						dx++;
					}
				}
				if(tmp.get(0)==1) {
					carrier[0] = tmp.get(1);
					carrier[1] = tmp.get(2);
					carrier[2] = tmp.get(1)+dx;
					carrier[3] = tmp.get(2)+dy;
				}
				else if(tmp.get(0)==2) {
					battleship[0] = tmp.get(1);
					battleship[1] = tmp.get(2);
					battleship[2] = tmp.get(1)+dx;
					battleship[3] = tmp.get(2)+dy;
				}
				else if(tmp.get(0)==3) {
					cruiser[0] = tmp.get(1);
					cruiser[1] = tmp.get(2);
					cruiser[2] = tmp.get(1)+dx;
					cruiser[3] = tmp.get(2)+dy;
				}
				else if(tmp.get(0)==4) {
				    submarine[0] = tmp.get(1);
					submarine[1] = tmp.get(2);
					submarine[2] = tmp.get(1)+dx;
					submarine[3] = tmp.get(2)+dy;
				}
				else {
					destroyer[0] = tmp.get(1);
					destroyer[1] = tmp.get(2);
					destroyer[2] = tmp.get(1)+dx;
					destroyer[3] = tmp.get(2)+dy;
				}
					
				counter++;
				i+=5;
				
			}
			if(counter<5) {
				throw new MissingInputException("There is some information missing for the input");
			}
		}catch(MissingInputException|OversizeException|OverlapTilesException|AdjacentTilesException|InvalidCountException e) {
			for(int i=0; i<10; i++) {
				for(int j=0; j<10; j++) {
					table[i][j] = false;
				}
			}
			for(int i=0; i<4; i++) {
				carrier[i] = 0;
				battleship[i] = 0;
				cruiser[i] = 0;
				submarine[i] = 0;
				destroyer[i] = 0;
			}
			System.out.println(e.getMessage());
		}
	}
	public boolean[][] getTable(){
		return table;
	}
	public int[] getCarrier() {
		return carrier;
	}
	public int[] getBattleship() {
		return battleship;
	}
	public int[] getCruiser() {
		return cruiser;
	}
	public int[] getSubmarine() {
		return submarine;
	}
	public int[] getDestroyer() {
		return destroyer;
	}
	
}
