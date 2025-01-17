package chess;

import java.util.ArrayList;
import java.util.Collection;

public class RookMovesCalculator implements PieceMovesCalculator{
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        ArrayList<ChessMove> moveList = new ArrayList<>();
        // Check vertical up and down, horizontal right and left
        int myRow = position.getRow();
        int myCol = position.getColumn();
        ChessGame.TeamColor myColor = board.getPiece(position).getTeamColor();
        int[] rowDirection = {1, -1};
        for (int rowShift : rowDirection) {
            int i = rowShift;
            while (true) {
                ChessPosition nextPosition = new ChessPosition(myRow + i, myCol);
                if (checkMove(board, myColor, nextPosition)) {
                    moveList.add(new ChessMove(position, nextPosition, null));
                    /* Check to see if next piece is blocking path */
                    if (board.getPiece(nextPosition) != null) { break; }
                    i = i + rowShift;
                } else { break; }
            }
        }
        int[] colDirection = {1, -1};
        for (int colShift : colDirection) {
            int j = colShift;
            while (true) {
                ChessPosition nextPosition = new ChessPosition(myRow, myCol + j);
                if (checkMove(board, myColor, nextPosition)) {
                    moveList.add(new ChessMove(position, nextPosition, null));
                    /* Check to see if next piece is blocking path */
                    if (board.getPiece(nextPosition) != null) { break; }
                    j = j + colShift;
                } else { break; }
            }
        }
        return moveList;
    }
}
