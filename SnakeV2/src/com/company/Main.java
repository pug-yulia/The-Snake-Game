package com.company;

public class Main {
    public static void main(String[] args) {
        ControllerMenu controllerMenu = new ControllerMenu();
        ViewMenu viewMenu = new ViewMenu();
        viewMenu.setController(controllerMenu);
        controllerMenu.setView(viewMenu);
        Model model = new Model();
        controllerMenu.setModel(model);

        controllerMenu.start();

    }
}
