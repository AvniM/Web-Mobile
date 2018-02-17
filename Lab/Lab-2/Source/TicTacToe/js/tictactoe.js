angular.module("TicTacToe", []).controller("TicTacToeCtrl", function($scope) {
    var unplayedCell = "?";
    var gameOverValue = "N";

    $scope.reset = function() {
        $scope.board = [
            [{value : unplayedCell}, {value : unplayedCell}, {value : unplayedCell}],
            [{value : unplayedCell}, {value : unplayedCell}, {value : unplayedCell}],
            [{value : unplayedCell}, {value : unplayedCell}, {value : unplayedCell}]
        ];

        $scope.currentPlayer = "X";
        $scope.winner = null;
        document.getElementById("result").innerHTML = "";
        gameOverValue = "N";
    };

    $scope.reset();

    $scope.checkGameOver = function() {
        var cell = "";
        //check rows
        for(var i = 0; i < 3; i++) {
            cell = $scope.board[i][0].value;
            if(cell !== unplayedCell &&
                cell === $scope.board[i][1].value &&
                cell === $scope.board[i][2].value) {
                $scope.winner = cell;
                return true;
            }
        }
        //check columns
        for(var j = 0; j < 3; j++) {
            cell = $scope.board[0][j].value;
            if(cell !== unplayedCell &&
                cell === $scope.board[1][j].value &&
                cell === $scope.board[2][j].value) {
                $scope.winner = cell;
                return true;
            }
        }
        //check diagonals
        var k = 1
        cell = $scope.board[k][k].value;
        if(cell !== unplayedCell &&
            cell === $scope.board[k-1][k-1].value &&
            cell === $scope.board[k+1][k+1].value) {
            $scope.winner = cell;
            return true;
        }
        else if(cell !== unplayedCell &&
            cell === $scope.board[k-1][k+1].value &&
            cell === $scope.board[k+1][k-1].value) {
            $scope.winner = cell;
            return true;
        }
        //check tie
        var hasUnplayed = false
        for(i = 0; i < 3; i++) {
            for(j = 0; j < 3; j++) {
                cell = $scope.board[i][j].value;
                if(cell === unplayedCell){
                    hasUnplayed = true;
                }
            }
        }
        if (!hasUnplayed) {
            $scope.winner = unplayedCell;
            return true;
        }

        // if none is true, return false
        return false;

    };

    $scope.unplayed = function(cell) {
        return cell.value == unplayedCell;
    };

    $scope.gameOver = function() {
        return gameOverValue == "Y";
    };

    $scope.flip = function(cell) {
        if (!$scope.checkGameOver()) {
            if ($scope.unplayed) {
                cell.value = $scope.currentPlayer;

                if ($scope.checkGameOver()) {
                    if ($scope.winner == "X") {
                        document.getElementById("result").innerHTML = "Player X won!";
                    } else if ($scope.winner == "O") {
                        document.getElementById("result").innerHTML = "Player O won!";
                    } else {
                        document.getElementById("result").innerHTML = "It's a Tie!";
                    }
                    gameOverValue = "Y";
                } else {
                    if ($scope.currentPlayer == "X") {
                        $scope.currentPlayer = "O";
                    } else {
                        $scope.currentPlayer = "X";
                    }
                }
            }
        }
    };
});