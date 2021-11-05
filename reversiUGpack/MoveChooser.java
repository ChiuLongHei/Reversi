import java.util.ArrayList;  

public class MoveChooser {
  	public static int[][] squarenumber={	{120,-20,20,5,5,20,-20,120}, 
    							{-20,-40,-5,-5,-5,-5,-40,-20},
    							{20,-5,15,3,3,15,-5,20},
    							{5,-5,3,3,3,3,-5,5},
    							{5,-5,3,3,3,3,-5,5},
    							{20,-5,15,3,3,15,-5,20},
    							{-20,-40,-5,-5,-5,-5,-40,-20},
    							{120,-20,20,5,5,20,-20,120}};
    public static Move chooseMove(BoardState boardState){

    	

	int searchDepth= Othello.searchDepth;
	BoardState boardState1= new BoardState();
	boardState1=boardState.deepCopy();

        ArrayList<Move> moves= boardState1.getLegalMoves();
        if(moves.isEmpty()){
            return null;
	}else {
		int k=0;
		int eval=-9999;
		int j=0;
        for (Move i:moves) {
    		boardState1=boardState.deepCopy();
    		boardState1.makeLegalMove(i.x,i.y);
    		if (minimax(boardState1,6,-9999,9999,true)>eval){
    			k=j;
    			eval=minimax(boardState1,6,-9999,9999,true);
    		}  
    		j=j+1; 		
    	}
    	return moves.get(k);

	}
    }

    public static int minimax(BoardState boardState, int depth, int alpha, int beta, boolean maximizingPlayer){

    	if (depth==0 || boardState.gameOver()){
    		int score=0;
    		for(int i= 0; i < 8; i++){

            	for(int j= 0; j < 8; j++){
                	score=score+boardState.getContents(i,j)*squarenumber[i][j];
            	}
    		}
    		return score;


    	}
    	BoardState boardState1= new BoardState();

    	if (maximizingPlayer){
    		int maxEval= -9999;
    		ArrayList<Move> moves= boardState.getLegalMoves();
    		for (Move i:moves) {
    			boardState1=boardState.deepCopy();
    			boardState1.makeLegalMove(i.x,i.y);
    			int eval=minimax(boardState1,depth-1, alpha, beta,false);
    			maxEval=Math.max(maxEval, eval);
    			alpha=Math.max(alpha,eval);
    			if (beta<=alpha){
    				break;
    			}

    		}
    		return maxEval;

    	}
    	else {
    		int minEval= 9999;
    		ArrayList<Move> moves= boardState.getLegalMoves();
    		for (Move i:moves) {
    			boardState1=boardState.deepCopy();
    			boardState1.makeLegalMove(i.x,i.y);
    			int eval=minimax(boardState1,depth-1, alpha, beta,true);
    			minEval=Math.min(minEval, eval);
    			beta=Math.min(beta,eval);
    			if (beta<=alpha){
    				break;
    			}

    		}
    		return minEval;

    	}
    }
}
