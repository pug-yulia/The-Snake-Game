package com.company;

public class ControllerMenu {
    private ViewMenu viewMenu;
    private Model model;

    public void start() {
        viewMenu.create();
        updateScores();
    }

    public void handleExitButtonClick() {
        viewMenu.disposeFrame();
    }

    public void handlePlayButtonClick() {
        switchToGame();
    }

    private void switchToGame() {
        viewMenu.disposeFrame();

        Controller controller = new Controller();
        View view = new View();
        controller.setView(view);
        view.setController(controller);
        controller.setModel(model);

        controller.start();
    }

    private void updateScores() {
        viewMenu.setScoreLabelText(model.getCurrentScore());
        viewMenu.setBestScoreLabel(model.getBestScore());
    }

    public void setView(ViewMenu viewMenu) {
        this.viewMenu = viewMenu;
    }

    public void setModel(Model model) {
        this.model = model;
    }

}
