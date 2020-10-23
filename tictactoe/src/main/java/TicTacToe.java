import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel label = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    private int count = 0;

    TicTacToe() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        label.setBackground(new Color(25, 25, 25));
        label.setForeground(new Color(25, 255, 0));
        label.setFont(new Font("InkFree", Font.BOLD, 75));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("Tic-Tac-Toe");
        label.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 600, 100);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(label);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);
        player1_turn = firstTurn();
        try {
            Thread.sleep(1000);
            if (player1_turn) {
                label.setText("X turn");
            } else {
                label.setText("O turn");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1_turn) {
                    playerTurn(buttons[i], new Color(255, 0, 0), "X", false, "O turn");
                } else {
                    playerTurn(buttons[i], new Color(0, 0, 255), "O", true, "X turn");
                }
            }
        }
    }

    public boolean firstTurn() {
        return (random.nextInt(2) == 0) ? true : false;
    }

    public void playerTurn(JButton button, Color color, String value, boolean turn, String text) {
        if (button.getText().equals("")) {
            button.setForeground(color);
            button.setText(value);
            player1_turn = turn;
            label.setText(text);
            if (!check(value)) {
                checkDraw();
            }
        }
    }

    public boolean checkWinCase(int a, int b, int c, String value) {
        boolean isWin = false;
        if ((buttons[a].getText().equals(value)) &&
                (buttons[b].getText().equals(value)) && (buttons[c].getText().equals(value))) {
            wins(a, b, c, value);
            isWin = true;
        }
        return isWin;
    }

    public boolean check(String value) {
        if ((checkWinCase(0, 1, 2, value)) ||
                (checkWinCase(0, 3, 6, value)) ||
                (checkWinCase(0, 4, 8, value)) ||
                (checkWinCase(3, 4, 5, value)) ||
                (checkWinCase(1, 4, 7, value)) ||
                (checkWinCase(2, 4, 6, value)) ||
                (checkWinCase(6, 7, 8, value)) ||
                (checkWinCase(2, 5, 8, value))) {
            return true;
        } else {
            return false;
        }
    }

    public void checkDraw() {
        if (count == 9) {
            for (int i = 0; i < 9; i++) {
                buttons[i].setEnabled(false);
            }
            label.setText(" It's draw");
        }
    }

    public void wins(int a, int b, int c, String value) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        label.setText(value + " wins");
    }
}
