package chess;

import java.util.ArrayList;

public class Rook extends Piece {
    
    public Rook(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        super(pieceFile, pieceRank, isWhite);
    }

    @Override
    public PieceType getWhitePieceType() {
        return PieceType.WR;
    }

    @Override
    public PieceType getBlackPieceType() {
        return PieceType.BR;
    }

    @Override
    public boolean getIsWhite() {
        return isWhite;
    }

    @Override
    public PieceFile getPieceFile() {
        return pieceFile;
    }

    @Override
    public int getPieceRank() {
        return pieceRank;
    }

    @Override
    public boolean isMoveValid(int newRank, PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard,
            boolean playerWhite, char promotionPiece) {

        if (newRank == pieceRank && newFile == pieceFile) {
            return false;
        }

        int rankDifference = Math.abs(newRank - pieceRank);
        int fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());

        if (rankDifference != 0 && fileDifference != 0) {
            return false;
        }

        if (rankDifference == 0) {
            int fileDirection = Integer.compare(newFile.ordinal(), pieceFile.ordinal());
            PieceFile[] files = PieceFile.values();
            int i = pieceFile.ordinal() + fileDirection;
            while (files[i] != newFile) {
                PieceFile file = files[i];
                for (ReturnPiece piece : piecesOnBoard) {
                    if (piece.pieceRank == pieceRank && piece.pieceFile == file) {
                        return false;
                    }
                }
                i += fileDirection;
            }
        } else {
            int rankDirection = Integer.compare(newRank, pieceRank);
            int rank = pieceRank + rankDirection;
            while (rank != newRank) {
                for (ReturnPiece piece : piecesOnBoard) {
                    if (piece.pieceRank == rank && piece.pieceFile == pieceFile) {
                        return false;
                    }
                }
                rank += rankDirection;
            }
        }

        for (ReturnPiece piece : piecesOnBoard) {
            if (piece.pieceRank == newRank && piece.pieceFile.toString().charAt(0) == newFile.toString().charAt(0)) {
                if (piece.pieceType.toString().charAt(0) == 'W' && !playerWhite) {
                    if (piece.pieceType.toString().charAt(1) != 'K') {
                        capture(pieceFile, pieceRank, newFile, newRank, piecesOnBoard);
                    }
                    return true;
                } else {
                    if (piece.pieceType.toString().charAt(0) == 'B' && playerWhite) {
                        if (piece.pieceType.toString().charAt(1) != 'K') {
                            capture(pieceFile, pieceRank, newFile, newRank, piecesOnBoard);
                        }
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
