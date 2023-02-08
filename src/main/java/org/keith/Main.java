package org.keith;
import org.keith.menu.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.LOGO();
        System.out.println(menu.WELCOME_MSG);
        menu.getMenuInput();
    }
}