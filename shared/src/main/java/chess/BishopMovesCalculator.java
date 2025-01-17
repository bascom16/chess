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
        int i = 0, j = 0;

        return moveList;
    }

}
