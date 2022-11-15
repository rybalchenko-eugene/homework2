import java.util.Scanner;
import java.util.Stack;


public class lib {
    /**
     * Генерит новую чистую доску с полями
     * @param num
     * @return
     */
    public static int [][] new_board(int num) {
        int [][] board = new int [num+2][num+2];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
                }
            System.out.println();

        }
        for (int i = 0; i < board.length; i++) {
            board[0][i] = -3;
            board[board.length - 1][i] = -3;
            board[i][0] = -3;
            board[i][board.length - 1] = -3;
        }
        return board;
    }

    public static int start(){
        Scanner in = new Scanner(System.in);
        System.out.println("Введите начальное число х первой строки (1-8): ");
        int num = in.nextInt();
        System.out.println("Ответ - " + (num));
        in.close();
        return num;
    }

    public static void board_print(int [][]lst) {   
        int flag = -1;     
        for (int i = 0; i < lst.length; i++) {
            for (int j = 0; j < lst[i].length; j++) {
                if (lst[i][j] > 2) {
                    if (lst[i][j] >= 1) System.out.print(lst[i][j]);
                    else System.out.print(lst[i][j] + " ");
                }
                if (lst[i][j] == -3) System.out.print("▓▓");
                if (lst[i][j] == -2) System.out.print("▒▒");
                if (lst[i][j] == 0 && flag == -1) System.out.print("██");
                if (lst[i][j] == 0 && flag == 1) System.out.print("  ");
                flag = -flag;
            }
            System.out.println();
            flag = -flag;
        }
    }

    public static void move(int [][] board, int x, int y) {  
        for (int j = 1; j < board.length-1; j++) {            
            for (int i = 1; i < board.length-1; i++) {
                if (i == x) board[j][i] = -2;
                if (i == x - j + y) board[j][i] = -2;
                if (i == x + j - y) board[j][i] = -2;
                if (j == y) board[j][i] = -2;
            }
        }
        board[y][x] = 11;
    }

    public static int [][] unmove(int [][] board, Stack<Integer> mov_history) { 
        System.out.println("Отменяем ход на линии " + mov_history.pop());
        board = new_board(board.length-2);
        for (int i = 0; i < mov_history.size(); i++) {
            move(board, mov_history.get(i), i+1);
        }
        System.out.println("remove");
        board_print(board);
        return board;
    }

    // public static Queue<Integer> check_move(int [][] board, int color) {
    //     Queue<Integer> queue_move = new LinkedList<>() ;
    //     int count = 0;
    //     for (int i = 1; i < board.length-1; i++) {
    //         for (int j = 1; j < board.length-1; j++) {
    //             if (board[i][j] == color) {                   
    //                 queue_move.add(j);
    //                 queue_move.add(i);
    //                 count++;
    //             }     
    //         }
    //     }
    //     System.out.println(count);
    //     System.out.println(queue_move);
    //     return queue_move;
    // }

    /**
     * добавляем возможные в этой линии ходы в стек
     * @param board
     * @param line
     * @return стек возможных ходов
     */
    public static Stack<Integer> check_move(int [][] board, int line) {
        Stack<Integer> queue_move = new Stack<>() ;
            for (int j = 1; j < board.length-1; j++) {
                if (board[line][j] == 0) {                   
                    queue_move.add(j);
                } 
            }
            // if (!queue_move.isEmpty() && line < 8) { // проверка второй строки, кроме последней
            //     for (int j = 1; j < board.length-1; j++) {
            //         if (board[line+1][j] == 0) {   
            //             break;                
            //         } 
            //     }
            // }
            // else queue_move.clear(); 

        return queue_move;
    }

    // public static void recurs(int line, int [][] board) {
    //     for (int i = 1; i < 9; i++) {
    //         if (board[line][i] == 0) {
    //             System.out.println("congratulations");
    //             move(board, i, line);
    //             board_print(board);
    //         } else {
    //             recurs(line-1, board);
    //         }
    //     }
    // }
    
}

