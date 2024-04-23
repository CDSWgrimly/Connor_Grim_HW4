import java.util.Stack;

public class MsgTree {
    private char payloadChar;
    private MsgTree left;
    private MsgTree right;
    private static int staticCharIdx = 0;
    private static String binCode;
    private static int index = -1;

    /**
     * Constructor for building the tree by encoding string
     *
     * @param encodingString
     */
    public MsgTree(String encodingString){
        if (encodingString.length() == 0 || encodingString == null){
            return;
        }
        MsgTree newNode;
        if(encodingString.charAt(index++) == '^'){
            newNode = new MsgTree(' ');
        }
        else {
            new MsgTree(encodingString.charAt(index));
            return;
        }
        newNode.left = new MsgTree(encodingString);
        newNode.right = new MsgTree(encodingString);
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
        System.out.println("character\tcode\n-------------------------");

    }
}
