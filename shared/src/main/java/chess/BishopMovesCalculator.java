package chess;

import java.util.ArrayList;
import java.util.Collection;

public class BishopMovesCalculator implements PieceMovesCalculator {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        ArrayList<ChessMove> moveList = new ArrayList<>();
        // Check upper right++, upper left+-, lower right-+, lower left--
        int myRow = position.getRow();
        int myCol = position.getColumn();
        ChessGame.TeamColor myColor = board.getPiece(position).getTeamColor();
        int[] rowDirection = {1, -1};
        int[] colDirection = {1, -1};
        for (int rowShift : rowDirection) {
            for (int colShift : colDirection) {
                int i = rowShift;
                int j = colShift;
                while (true) {
                    ChessPosition nextPosition = new ChessPosition(myRow + i, myCol + j);
                    if (checkMove(board, myColor, nextPosition)) {
                        moveList.add(new ChessMove(position, nextPosition, null));
                        /* Check to see if next piece is blocking path */
                        if (board.getPiece(nextPosition) != null) { break; }
                        i = i + rowShift;
                        j = j + colShift;
                    } else { break; }
                }
            }
        }
        return moveList;
    }
}
