package com.tele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Class is used to simulate random win box puzzle game.
 */
class Runner {

    private final static int countOfBoxes = 3; // Assumed number of boxes
    private static BufferedReader reader;

    public static void main(String[] args) throws IOException {
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
        Set<Box> b = new HashSet<>();
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
        System.out.println("Please choose a Box " + getEmptyList(boxes));
        reader = new
                BufferedReader(new InputStreamReader(System.in));
        String inputStr = reader.readLine();

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

        Box boxHost = getSelectedBox(boxes, hostSelection);
        boxHost.setHost(true);
        boxes.add(boxHost);

        //Display List of Boxes
     /*   for (Box bc : boxes) {
            System.out.println(bc.toString());
        }*/

        if (boxHost.isHost() && boxHost.isPrice()) {
            System.out.println("Sorry, You lost the game HOST got found the money");
            System.out.println("Do you like to try again ?");
            reader = new
                    BufferedReader(new InputStreamReader(System.in));
            inputStr = reader.readLine();
            if ("Y".equalsIgnoreCase(inputStr)) {
                play(initBoxList());
            } else {
                System.out.println("Thank you for playing game ...");
                System.exit(0);
            }
        }

        String emptyBoxes = getEmptyList(boxes);

        System.out.println("Do I stand a better chance to win if I change my mind switch to other box " + emptyBoxes + "?");
        reader = new
                BufferedReader(new InputStreamReader(System.in));
        inputStr = reader.readLine();
        if ("y".equalsIgnoreCase(inputStr)) {
            showBox(userBox);
        } else {
            Box b = getSelectedBox(boxes, Integer.parseInt(inputStr));
            b.setUser(true);
            showBox(b);
        }

        System.out.println("Do you like to try again ?");
        reader = new
                BufferedReader(new InputStreamReader(System.in));
        inputStr = reader.readLine();
        if ("y".equalsIgnoreCase(inputStr)) {
            play(initBoxList());
        } else {
            System.out.println("Thank you for playing game ...");
            System.exit(0);
        }
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
        System.out.println(selectdBox.toString());
        if (selectdBox.isUser() && selectdBox.isPrice())
            System.out.println("Congratulation you are Win !!!!");
        else
            System.out.println("Sorry, You lost the game");
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
}