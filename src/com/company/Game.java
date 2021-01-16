package com.company;

import com.company.commands.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class Game {
    public static final int FRAME_WIDTH = 1000;
    public static final int FRAME_HEIGHT = 500;

    private JFrame frame;
    private Ranking ranking;
    private LevelFileReader levelFileReader;
    private PositionStrategy strategy;

    public String getUsername() {
        return username;
    }

    private String username;
    private Level level;

    public Game() {
        // @TODO: add positionStrategy field
        ranking = new Ranking();
        levelFileReader = LevelFileReader.getInstance();
        username = "";
        initScreen();
    }

    public void setStrategy(PositionStrategy strategy) {
        this.strategy = strategy;
    }

    public JFrame getFrame() {
        return frame;
    }

    private void initScreen() {
        frame = new JFrame("Arkanoid");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.getContentPane().setBackground(Color.white);
        frame.setLayout(new FlowLayout());
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        enterName();
    }

    private void enterName() {
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JLabel inputLabel = new JLabel("Enter your name:", JLabel.LEFT);
        addMargin(inputLabel, 0, FRAME_WIDTH / 4, 0, 0);
        inputLabel.setPreferredSize(new Dimension(FRAME_WIDTH, 20));
        inputLabel.setFont(new Font("Lato", Font.BOLD, 15));

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(FRAME_WIDTH / 2, 30));

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(FRAME_WIDTH / 2, 30));

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText();
                if(Pattern.matches("[a-zA-Z]+[0-9]*", inputText)) {
                    username = inputText;
                    displayStartView();
                } else {
                    displayDialog("Username must contain at least one letter and optionally numbers");
                    textField.setText("");
                }
            }
        });

        frame.add(inputLabel);
        frame.add(textField);
        frame.add(submitButton);

        frame.setVisible(true);
    }

    public void displayStartView() {
        clearScreen();
        frame.setLayout(new BorderLayout(20, 60));

        JLabel titleLabel = new JLabel("Arkanoid", JLabel.CENTER);
        titleLabel.setFont(new Font("serif", Font.BOLD, 25));

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBackground(Color.WHITE);

        frame.add(titleLabel, BorderLayout.PAGE_START);
        frame.add(controlPanel, BorderLayout.CENTER);

        addNavButtons(controlPanel, true);

        frame.setVisible(true);
    }

    public void displayAuthorsView() {
        clearScreen();
        frame.setLayout(new FlowLayout());

        String text = "This is an university project, which idea was to get accustomed to implementing design patterns.";
        JLabel infoLabel = new JLabel(text, JLabel.CENTER);
        infoLabel.setSize(new Dimension(600, 400));

        JButton button = new JButton("Go Back");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Command command = new GoBackCommand(Game.this);
                executeCommand(command);
            }
        });

        frame.add(infoLabel);
        frame.add(button);

        frame.setVisible(true);
    }

    public void showRanking() {
        clearScreen();
        frame.setLayout(new BorderLayout(20, 60));

        JLabel leadersLabel = new JLabel("Leaderboard", JLabel.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBackground(Color.WHITE);

        frame.add(leadersLabel, BorderLayout.PAGE_START);

        RankingAdapter adapter = new RankingAdapter();
        adapter.setTableData(ranking);
        JTable table = new JTable(adapter);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.PAGE_END);

        addNavButtons(controlPanel, false);

        frame.setVisible(true);
    }

    public void startLevel() {
        // reset previous history so user cant undo to level that has already ended
        resetHistory();

        clearScreen();
        frame.setLayout(new BorderLayout());

        JMenuBar menuBar = addMenuBar();
        frame.add(menuBar, BorderLayout.PAGE_START);

        // probably there should be an option to pass parameters to start level and adjust builder and level according to this
        PositionStrategy strategy = new RowsPositionStrategy(new StoneLevelBuilder(this), levelFileReader.readFile(1));
        level = strategy.arrangeObjects();

        frame.getContentPane().add(level, BorderLayout.CENTER);

        level.requestFocusInWindow();
        frame.setVisible(true);

    }

    public void displayLevelEndView(Score score) {

        ranking.addScore(score);

        clearScreen();
        frame.setLayout(new BorderLayout(20, 60));

        JLabel headerLabel = new JLabel("",JLabel.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBackground(Color.WHITE);

        frame.add(headerLabel, BorderLayout.PAGE_START);
        frame.add(controlPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        String congratsString = "<html>Congratulations " + score.getUsername() + "!<br/>" +
                "You scored " + score.getPoints() + " points!<html/>";
        headerLabel.setText(congratsString);
        headerLabel.setFont(new Font("Lato", Font.BOLD, 20));
        addMargin(headerLabel, 50, 10, 10, 10);

        addNavButtons(controlPanel, true);

        frame.setVisible(true);
    }

    private void executeCommand(Command command) {
        command.execute();
    }

    public void clearScreen() {
        frame.getContentPane().removeAll();
        frame.validate();
        frame.repaint();
    }

    private void addNavButtons(JPanel panel, boolean isStart) {
        JButton startButton = new JButton("New Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton rankingButton = new JButton("Show Ranking");
        rankingButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton authorsButton = new JButton("Authors");
        authorsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton goBackButton = new JButton("Go Back");
        goBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Command command = new StartLevelCommand(Game.this);
                executeCommand(command);
            }
        });

        authorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Command command = new ShowAuthorsCommand(Game.this);
                executeCommand(command);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Command command = new ExitCommand(Game.this);
                executeCommand(command);
            }
        });

        rankingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Command command = new ShowRankingCommand(Game.this);
                executeCommand(command);
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Command command = new GoBackCommand(Game.this);
                executeCommand(command);
            }
        });

        panel.add(startButton);
        panel.add(Box.createVerticalStrut(20));

        if(isStart) {
            panel.add(authorsButton);
            panel.add(Box.createVerticalStrut(20));
            panel.add(rankingButton);
        } else {
            panel.add(goBackButton);
        }
        panel.add(Box.createVerticalStrut(20));

        panel.add(exitButton);
        panel.add(Box.createVerticalStrut(20));
    }

    private void addMargin(JComponent cmp, int top, int left, int bottom, int right) {
        Border border = cmp.getBorder();
        Border margin = new EmptyBorder(top, left, bottom, right);
        cmp.setBorder(new CompoundBorder(border, margin));
    }

    private JMenuBar addMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Revert");

        JMenuItem prevItem = new JMenuItem("Prev");
        prevItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // prev saved snapshot
                // trigger undo method upon clicking into prev, it returns a game to state from X seconds
                undo(false);
            }
        });

        JMenuItem randomItem = new JMenuItem("Random");
        randomItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // random saved snapshot
                // returns game state to random state within history array bounds
                undo(true);


            }
        });

        menu.add(prevItem);
        menu.add(randomItem);

        menuBar.add(menu);

        return menuBar;
    }

    private void displayDialog(String message) {
        JDialog dialog = new JDialog(frame, "Info", true);
        dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

        JLabel label = new JLabel(message, JLabel.CENTER);

        label.setPreferredSize(new Dimension(400, 40));

        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });

        dialog.add(label);
        dialog.add(okButton);

        dialog.setSize(400, 180);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
    //
    public List<LevelSnapshot> history = new ArrayList<>();

    public void undo(Boolean useRandom){
        // Check if there is any previous state to return to
        System.out.println(history.size());
        if(!history.isEmpty()) {

//            clearScreen();
//            frame.setLayout(new BorderLayout());

//            JMenuBar menuBar = addMenuBar();
//            frame.add(menuBar, BorderLayout.PAGE_START);

            // depending on useRandom property determien whether to use last save or random one
            LevelSnapshot levelSS;
            if(useRandom == true){
                Random random = new Random();
                int randomInt = random.nextInt(history.size());
                // Take random level snapshot within bounds
                levelSS = history.get(randomInt);
                // Remove it from list
//                history.remove(randomInt);
            }else{
                // Take most recent level snapshot
                levelSS = history.get(history.size()-1);
                // Remove it from list
//                history.remove(history.size()-1);
            }
            // Update level with old data from snap shot
//            levelSS = history.get(0);
            level.restore(levelSS);
            level.repaint();

//            frame.getContentPane().add(level, BorderLayout.CENTER);

//            level.requestFocusInWindow();
//            frame.setVisible(true);
        }
    }
    // clears whole history this method should trigger when user starts a new game
    public void resetHistory(){
        history.clear();
    }
}
