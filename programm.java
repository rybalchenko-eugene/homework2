import java.util.Stack;

/**
 * расставить шахматных ферзей
 */

// -2 поля доски
// -1 черый цвет
// 1 - белый цвет
// 0 - нет
// 10 - ферзь на черном поле
// 30 - ферзь на белом поле
// 51 - след (битое поле)

public class programm {
    public static void main(String[] args) {
        int num = 8; // размерность доски
        int [][] board = lib.new_board(num);
        int pos_x; // номер клетки в ряду
        Stack<Integer> var_Stack = new Stack<>(); // стек история ходов
        Stack<Integer> move_history = new Stack<>(); // стек история вариантов ходов
        Stack<Integer> next_move = new Stack<>(); // стек возможных следуюих ходов
        int line = 1;
        int star = lib.start();
        var_Stack.add(star); // добавляем первый выбор варика в стек 
        lib.move(board, star, line);
        move_history.add(star); // добавляем в историю ходов
        line++;
        while (line <= 8 ) {
            next_move =lib.check_move(board, line);

            if (!next_move.isEmpty())  {    
                var_Stack.addAll(next_move); // добавляем варики в стек 
                System.out.println(var_Stack);  
                pos_x = var_Stack.pop(); 
                lib.move(board, pos_x, line);// ходим последним вариантом
                var_Stack.add(-9); // фиксируем конец линии в стеке
                move_history.add(pos_x); // добавляем в историю ходов

                lib.board_print(board); // все печататем
                
                line++;
            }
            else {

                System.out.println("Try to find another pos");
                pos_x = var_Stack.pop(); // берем следующий


                while (pos_x == -9){
                    line--;
                    pos_x = var_Stack.pop();
                    board = lib.unmove(board, move_history);
                    System.out.println(var_Stack);
                }
                

                lib.move(board, pos_x, line);
                line++;
                var_Stack.add(-9); // фиксируем конец линии в стеке
                move_history.add(pos_x); // добавляем в историю ходов
                System.out.println(var_Stack);  
                lib.board_print(board);
            }        
        }
    }
}