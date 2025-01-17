package chess;

import java.util.Collection;

public class QueenMovesCalculator implements PieceMovesCalculator {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> bishopMovesList = new BishopMovesCalculator().pieceMoves(board, position);
        Collection<ChessMove> rookMovesList = new RookMovesCalculator().pieceMoves(board, position);
        bishopMovesList.addAll(rookMovesList);
        return bishopMovesList;
    }
}
