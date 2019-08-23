package my.coding.challenge;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static ConsoleLogger logger = new ConsoleLogger();
    public static void main(String... args) {
        /*STEP 0: READ A FILE PATH FROM CONSOLE*/
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter full path to the text file: ");
        String filePath = scanner.nextLine();
        /*STEP 1: READ ALL WORDS FROM FILE AND INITIALIZE THE OCCURRENCE HASHMAP*/
        Map<String, Integer> occurenceMap = null;
        try {
            WordExtractor wordExtractor = new TextFileWordExtractor(new File(filePath));
            occurenceMap = wordExtractor.extractWordOccurrence();
        } catch (IOException e) {
            logger.print("Exception while reading text file or file path is non-existent");
            e.printStackTrace();
            return;
        }
        logger.print("Step 1 done, file loaded");

        /*STEP 2: PROCESS THE MAP AND BUILD THE LEAF NODES*/
        // sort the map in ascending order, we build leaf nodes from least occurrence words first
        List<Map.Entry<String, Integer>> entryList = occurenceMap.entrySet()
                .stream()
                .sorted((o1, o2) -> o1.getValue() - o2.getValue())
                .collect(Collectors.toList());
        // build the leaf nodes from the word list
        Map.Entry<String, Integer> entry = null;
        // linked list is chosen because we involve lots of removing, adding and sorting list of nodes
        LinkedList<TreeNode> nodes = new LinkedList<>();
        for (int i = 0; i < entryList.size(); i += 1) {
            entry = entryList.get(i);
            TreeNode leafNode = new TreeNode(entry.getValue(), entry.getKey(), true);
            nodes.add(leafNode);
        }
        logger.print("Step 2 done, leaf nodes created");

        /*STEP 3: BUILD THE PARENT NODES FROM LEAF NODES UNTIL WE FORM A WHOLE TREE*/
        while(nodes.size() > 1) {
            //pop the first 2 nodes (least occurrences)
            TreeNode firstNode = nodes.pop();
            TreeNode secondNode = nodes.pop();
            TreeNode parentNode = TreeNode.makeParentNode(firstNode, secondNode);
            insertNodeIntoList(parentNode, nodes);
        }
        logger.print("Step 3 done, the entire tree created");
        /*STEP 4: PRINT THE TREE*/
        TreeNode.printToConsole(nodes.get(0));
    }

    /**
     * This method inserts new node in a linked list, maintaining its ascending order
     * @param node new node to be inserted
     * @param list a linked list of nodes in ascending order
     */
    private static void insertNodeIntoList(TreeNode node, LinkedList<TreeNode> list) {
        int i = 0;
        while(i < list.size() && node.getValue() > list.get(i).getValue()) {
            i += 1;
        }
        if (i >= list.size()) {
            list.addLast(node);
        } else {
            list.add(i, node);
        }
    }
}