package edu.iastate.cs228.hw4;
import java.util.Stack;

/**
 * @author Connor Grim
 */

public class MsgTree {
    public char payloadChar;
    public MsgTree left;
    public MsgTree right;
    private static int index = 0;
    private static String binCode;

    /**
     * Constructor for building the tree by encoding string
     *
     * @param encodingString
     */
    public MsgTree(String encodingString){
        int i = 0;
        if (encodingString.length() < 2 || encodingString == null){
            return;
        }

        //Pushes root node to stack
        this.payloadChar = encodingString.charAt(i++);
        Stack<MsgTree> stack = new Stack<>();
        stack.push(this);

        int lastOpt = 1; //1 = pushed, 0 = popped
        MsgTree cur = this;

        //Preorder traversal and adds nodes
        while(i < encodingString.length()){
            MsgTree newNode = new MsgTree(encodingString.charAt(i++));
            if (lastOpt == 1){
                cur.left = newNode;
                if (cur.payloadChar == '^'){
                    stack.push(newNode);
                    lastOpt = 1;
                }
                else{
                    if (!stack.isEmpty()){
                        cur = stack.pop();
                    }
                    lastOpt = 0;
                }
            } else {
                cur.right = newNode;
                if (cur.payloadChar == '^'){
                    stack.push(newNode);
                    lastOpt = 1;
                }
                else{
                    if (!stack.isEmpty()){
                        cur = stack.pop();
                    }
                    lastOpt = 0;
                }
            }
        }

        /*
        //Checks for string size
        if (encodingString.length() < 2 || encodingString == null){
            return;
        }

        //Sets root node as internal node
        this.payloadChar = encodingString.charAt(index);

        //Preorder traversal, adding nodes from characters in the string
        index++;
        this.left = new MsgTree(encodingString.charAt(index));
        if (this.left.payloadChar == '^'){
            this.left = new MsgTree(encodingString);
        }

        //Traverse right
        index++;
        this.right = new MsgTree(encodingString.charAt(index));
        if (this.right.payloadChar == '^'){
            this.right = new MsgTree(encodingString);
        }
        */
    }

    /**
     * Constructor for a single node w/ no children
     *
     * @param payloadChar
     */
    public MsgTree(char payloadChar){
        left = null;
        right = null;
        this.payloadChar = payloadChar;
    }

    public static void printCodes(MsgTree root, String code){
        System.out.println("character code\n-------------------------");
        for (char ch : code.toCharArray()){
            if (ch != '^') {
                getCode(root, ch, binCode = "");
                System.out.println("   " + (ch == '\n' ? "\\n" : ch + " ") + "   " + binCode);
            }
        }
    }

    private static boolean getCode(MsgTree root, char ch, String path){
        if (root != null){
            if (root.payloadChar == ch){
                binCode = path;
                return true;
            }
            return getCode(root.left, ch, path + "0") || getCode(root.right, ch, path + "1");
        }
        return false;
    }
}
