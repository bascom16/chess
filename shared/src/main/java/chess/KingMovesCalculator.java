package chess;

import java.util.ArrayList;
import java.util.Collection;

public class KingMovesCalculator implements PieceMovesCalculator{
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        ArrayList<ChessMove> moveList = new ArrayList<>();
        int myRow = position.getRow();
        int myCol = position.getColumn();
        ChessGame.TeamColor myColor = board.getPiece(position).getTeamColor();
        int[] rowDirection = {-1, 0, 1};
        int[] colDirection = {-1, 0, 1};
        for (int i : rowDirection) {
            for (int j : colDirection) {
                if (!(i == 0 && j == 0)) {
                    ChessPosition nextPosition = new ChessPosition(myRow + i, myCol + j);
                    if (checkMove(board, myColor, nextPosition)) {
                        moveList.add(new ChessMove(position, nextPosition, null));
                    }
                }
            }
        }
        return moveList;
    }
}
