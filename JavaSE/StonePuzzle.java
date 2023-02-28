package com.cases.stonepuzzle;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class StonePuzzle extends JFrame {
    int[][] data = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    int[][] vData = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    int row;
    int column;
    int X;
    int Y;

    int step;

    {
        System.out.println("游戏启动！");
    }

    public StonePuzzle() {
        initData();
        initFrame();
        drawView();
        super.setVisible(true);
    }

    private void initData() {
        step = 0;
        Random r = new Random();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                int randomX = r.nextInt(4);
                int randomY = r.nextInt(4);
                int temp = data[i][j];
                data[i][j] = data[randomX][randomY];
                data[randomX][randomY] = temp;
            }
        }
        getZeoLocation();
    }

    private void getZeoLocation() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] == 0) {
                    row = i;
                    column = j;
                    System.out.println("0号元素坐标为：X:" + row + " Y:" + column);
                    X = (50 + 100 * column);
                    Y = (90 + 100 * row);
                    System.out.println("0号元素位置为：X:" + X + " Y:" + Y);
                }
            }
        }
    }

    private void initFrame() {
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setLayout(null);
        super.setTitle("StonePuzzle V1.0");
        super.setSize(514, 595);
        super.setResizable(false);
        super.setLayout(null);
        super.setLocationRelativeTo(null);
        super.setAlwaysOnTop(false);
        super.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 70) {
                    data = new int[][]{
                            {1, 2, 3, 4},
                            {5, 6, 7, 8},
                            {9, 10, 11, 12},
                            {13, 14, 15, 0}
                    };
                }
                drawView();
            }
        });
    }

    private void drawView() {
        super.getContentPane().removeAll();

        if (victory()) {
            JLabel victory = new JLabel(new ImageIcon("D:\\study_Java\\石头迷阵\\win.png"));
            victory.setBounds(124, 230, 266, 88);
            getContentPane().add(victory);
        }

        JLabel stepBox = new JLabel("步数：" + step);
        stepBox.setBounds(50, 20, 100, 20);
        super.getContentPane().add(stepBox);

        JButton restart = new JButton("重新开始");
        restart.setBounds(360, 20, 100, 20);
        restart.setFocusable(false);
        restart.addActionListener(e -> {
            initData();
            drawView();
        });
        super.getContentPane().add(restart);

        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                JLabel stone = new JLabel(new ImageIcon("D:\\study_Java\\石头迷阵\\" + data[i][j] + ".png"));
                stone.setBounds(50 + 100 * j, 90 + 100 * i, 100, 100);
                stone.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        move(e);
                        step++;
                        drawView();
                    }
                });
                super.getContentPane().add(stone);
            }
        }

        JLabel background = new JLabel(new ImageIcon("D:\\study_Java\\石头迷阵\\background.png"));
        background.setBounds(26, 30, 450, 484);
        super.getContentPane().add(background);

        super.getContentPane().repaint();
    }

    private void move(@NotNull MouseEvent e) {
        if (victory()) return;

        int clickX = Integer.parseInt(e.getSource().toString().split(",")[1]);
        int clickY = Integer.parseInt(e.getSource().toString().split(",")[2]);
        if (X == clickX && clickY + 100 == Y) {
            int temp = data[row][column];
            data[row][column] = data[row - 1][column];
            data[row - 1][column] = temp;
            row--;
            Y = (90 + 100 * row);
        } else if (X == clickX && clickY - 100 == Y) {
            int temp = data[row][column];
            data[row][column] = data[row + 1][column];
            data[row + 1][column] = temp;
            row++;
            Y = (90 + 100 * row);
        } else if (clickX + 100 == X && Y == clickY) {
            int temp = data[row][column];
            data[row][column] = data[row][column - 1];
            data[row][column - 1] = temp;
            column--;
            X = (50 + 100 * column);
        } else if (clickX - 100 == X && Y == clickY) {
            int temp = data[row][column];
            data[row][column] = data[row][column + 1];
            data[row][column + 1] = temp;
            column++;
            X = (50 + 100 * column);
        } else {
            System.out.println("无此方块");
        }
    }

    private boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (vData[i][j] != data[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
