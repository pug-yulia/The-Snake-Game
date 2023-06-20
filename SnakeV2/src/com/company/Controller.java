package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private static final int WIDTH = 25;
    private static final int HEIGHT = 25;
    private static final int SQUARE_SIZE = 25;
    private static final int WIDTH_PIXELS = WIDTH * SQUARE_SIZE;
    private static final int HEIGHT_PIXELS = HEIGHT * SQUARE_SIZE;
    private static final int DELAY = 250;

    private View view;
    private Model model;
    private Graphics graphics;
    private Direction direction = Direction.RIGHT;
    private final List<Position> list = new ArrayList<>();
    private Position applePosition;
    private Direction previousDirection = direction;

    public void start() {
        view.create(WIDTH_PIXELS, HEIGHT_PIXELS);
        list.add(new Position(WIDTH / 2, HEIGHT / 2));
        generateApple();
        run();
    }

    private void run() {
        while (true) {
            render();
            delay();
            Position position = new Position(getHeadPosition());
            position.move(direction);
            if (isCollisionWithSelf(position)) {
                updateScore();
                view.disposeFrame();
                switchToMenu();
                break;
            }
            list.add(position);
            previousDirection = direction;
            if (isOutOfBounds()) {
                updateScore();
                view.disposeFrame();
                switchToMenu();
                break;
            }
            checkCollision();
        }
    }

    private void render() {
        BufferedImage image = new BufferedImage(WIDTH_PIXELS, HEIGHT_PIXELS, BufferedImage.TYPE_INT_RGB);
        graphics = image.getGraphics();
        drawSnake();
        drawApple();
        graphics.setFont(new Font("", Font.PLAIN, 30));
        graphics.drawString(String.valueOf(list.size() - 1), 30, 50);
        view.setImage(image);
    }

    public void handleKeyPress(int keyCode) {
        Direction nextDirection;
        switch (keyCode) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> nextDirection = Direction.UP;
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> nextDirection = Direction.DOWN;
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> nextDirection = Direction.RIGHT;
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> nextDirection = Direction.LEFT;
            default -> {
                return;
            }
        }
        if (nextDirection.isOpposite(previousDirection)) {
            return;
        }
        direction = nextDirection;
    }

    private void drawSnake() {
        for (Position position : list) {
            drawSquare(position, Color.GREEN);
        }
    }

    private void drawSquare(Position position, Color color) {
        graphics.setColor(color);
        graphics.fillRect(SQUARE_SIZE * position.getX(), SQUARE_SIZE * position.getY(), SQUARE_SIZE, SQUARE_SIZE);
    }

    public void drawApple() {
        drawSquare(applePosition, Color.RED);
    }

    public void generateApple() {
        do {
            applePosition = new Position(getRandomNum(WIDTH), getRandomNum(HEIGHT));
        } while (list.contains(applePosition));
    }

    private void checkCollision() {
        if (applePosition.equals(getHeadPosition())) {
            generateApple();
        } else {
            list.remove(0);
        }
    }

    private boolean isCollisionWithSelf(Position position) {
        return list.contains(position);
    }

    private boolean isOutOfBounds() {
        Position head = getHeadPosition();
        return head.getX() >= WIDTH || head.getX() < 0 || head.getY() >= HEIGHT || head.getY() < 0;
    }

    private Position getHeadPosition() {
        return list.get(list.size() - 1);
    }

    private int getRandomNum(int max) {
        return (int) (Math.random() * max);
    }

    private void updateScore() {
        model.setCurrentScore(list.size() - 2);
        model.compareScores();
    }

    private void switchToMenu() {
        disposeView();

        ControllerMenu controllerMenu = new ControllerMenu();
        ViewMenu viewMenu = new ViewMenu();
        viewMenu.setController(controllerMenu);
        controllerMenu.setView(viewMenu);
        //Model model = new Model();
        controllerMenu.setModel(model);

        controllerMenu.start();
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    private void disposeView() {
        if (view != null) {
            view.disposeFrame();
        }
    }

    private void delay() {
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
