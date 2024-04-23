package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Connor Grim
 */

public class Main {

    public static String encodingString;
    public static String decodeString;
    public static int index = 0;
    public static int characterCount = 0;

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Please enter file name ending with \".arch\"");
        Scanner sc = new Scanner(System.in);
        String filename = sc.next();
        if (!filename.contains(".arch")) {
            System.out.println("Invalid file extension");
            return;
        }
        File encodeFile = new File(filename);
        sc.close();

        Scanner lineScan = new Scanner(encodeFile);
        int lineCount = 0;
        while (lineScan.hasNext()){
            lineCount++;
            lineScan.nextLine();
        }

        lineScan.close();

        if (lineCount == 2){
            Scanner fileScan = new Scanner(encodeFile);
            encodingString = fileScan.nextLine();
            decodeString = fileScan.nextLine();
        } else{
            Scanner fileScan = new Scanner(encodeFile);
            encodingString = fileScan.nextLine() + "\n";
            encodingString += fileScan.nextLine();
            decodeString = fileScan.nextLine();
        }

        MsgTree root = new MsgTree(encodingString);
        MsgTree.printCodes(root, encodingString);
        decode(root, decodeString);

        double avgBits = Math.round(((double) decodeString.length() / characterCount) * 100.0) / 100.0;
        double savedSpace = Math.round(((1 - ((double) decodeString.length() / (characterCount * 16))) * 100) * 100.0) / 100.0;
        System.out.println("\nStats\n----------\nAverage Bits: " + avgBits + " bits\nTotal Character Count: " + characterCount + "\nTotal Space Saved: " + savedSpace + "%");
    }

    public static void decode(MsgTree codes, String msg) {
        System.out.println("MESSAGE: ");
        while(index < msg.length()){
            MsgTree cur = codes;
            while (cur.left != null && cur.right != null) {
                if (msg.charAt(index) == '0') {
                    cur = cur.left;
                    index++;
                } else if (msg.charAt(index) == '1') {
                    cur = cur.right;
                    index++;
                }
            }
            if (cur.payloadChar == '\n'){
                System.out.print("\n");
            }
            else{
                System.out.print(cur.payloadChar);
                characterCount++;
            }
        }
    }
}
