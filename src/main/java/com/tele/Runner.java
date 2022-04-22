package com.tele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Class is used to simulate random win box puzzle game.
 */
class Runner {

    private final static int countOfBoxes = 3; // Assumed number of boxes
    private static BufferedReader reader;

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Monty Hall problem - Puzzle");
        Runner runner = new Runner();
        Set<Box> boxes = runner.initBoxList();
        runner.play(boxes);
    }

    /**
     * Method used to initialize new win boxes with random win massage.
     *
     * @return
     */
    private Set<Box> initBoxList() {
        Set<Box> b = new LinkedHashSet<>();
        for (int i = 1; i < (countOfBoxes + 1); i++) {
            b.add(new Box(false, false, false, i));
        }
        return b;
    }

    /**
     * Method include read inputs and actions for given selection
     *
     * @param boxes
     * @throws IOException
     */
    private void play(Set<Box> boxes) throws IOException {
        String lstr = getEmptyList(boxes);
        System.out.println("Please choose a Box " + lstr);
        String inputStr = getInputString(String.valueOf(lstr));
        int priceChoose = randomGenarator(3);
        Box boxWin = getSelectedBox(boxes, priceChoose);
        boxWin.setPrice(true);
        boxes.add(boxWin);

        //User first selection
        int usersSelected = Integer.parseInt(inputStr);
        Box userBox = getSelectedBox(boxes, usersSelected);
        userBox.setUser(true);
        boxes.add(userBox);

        //Host selection
        int hostSelection = randomGenarator(3);
        while (hostSelection == usersSelected) {
            hostSelection = randomGenarator(3);
        }

        ;
        for(Box bo :boxes){
            if(!bo.isPrice() && !bo.isUser()){
                bo.setHost(true);
                boxes.add(bo);
                System.out.println("Host picked Empty box "+ bo.getCount());
                break;
            }
        }

        //Display List of Boxes
        //showBoxes(boxes);

        String emptyBoxes = getEmptyList(boxes);

        System.out.println("Do I stand a better chance to win if I change my mind switch to other box select your choice (" + emptyBoxes + ")?");
        inputStr = getInputString(String.valueOf(emptyBoxes));
        if ("y".equalsIgnoreCase(inputStr)) {
            showBox(userBox);
        } else {
            Box b = getSelectedBox(boxes, Integer.parseInt(inputStr));
            b.setUser(true);
            showBox(b);
        }

        System.out.println("Do you like to try again (Y/N)?");
        inputStr = getInputString("Y,N,y,n");
        if ("y".equalsIgnoreCase(inputStr)) {
            play(initBoxList());
        } else {
            System.out.println("Thank you for playing game ...");
            System.exit(0);
        }
    }

    /**
     * Method contain Input reader and validation.
     * @param validate
     * @return
     * @throws IOException
     */
    private String getInputString(String validate) throws IOException {
        String inputStr;
        reader = new
                BufferedReader(new InputStreamReader(System.in));
        inputStr = reader.readLine();
        System.out.println("Your input values : " + inputStr);
        char[] ch = new char[validate.length()];

        boolean contain = false;
        for(char c:ch){
            //System.out.println(inputStr);
            if(validate.contains(String.valueOf(inputStr))){
                contain = true;
                break;
            }
        }
        if(!contain){
            System.out.println("Invalid input try again... values "+ validate +" required");
            inputStr = getInputString(validate);
        }

        return inputStr;
    }

    /**
     * Used to get the Unselected Boxes
     * @param boxes
     * @return
     */
    private String getEmptyList(Set<Box> boxes) {
        String emptyBoxes = "";
        for (Box b : boxes) {
            if (!b.isHost()) {
                emptyBoxes += b.getCount() + " ";
            }
        }
        return emptyBoxes;
    }

    /**
     * Used to retrive selected Box
     * @param boxes
     * @param selected
     * @return
     */
    private Box getSelectedBox(Set<Box> boxes, int selected) {
        for (Box b : boxes) {
            if (b.getCount() == selected) {
                return b;
            }
        }
        return null;
    }

    /**
     * Methos used to show the results
     *
     * @param selectdBox
     */
    private void showBox(Box selectdBox) {
        //System.out.println(selectdBox.toString());
        if (selectdBox.isUser() && selectdBox.isPrice())
            System.out.println("!!! Congratulation you are Win !!!!");
        else
            System.out.println("Sorry, You lost the Money ... ");
    }

    /**
     * Randon number generator within given count
     *
     * @param count
     * @return
     */
    private int randomGenarator(int count) {
        Random generator = new Random();
        int winbox = generator.nextInt();
        return (1 + Math.abs(winbox) % count);
    }

    private void showBoxes(Set<Box> setOfBoxes){
        setOfBoxes.stream().forEach(System.out::println);
    }
}