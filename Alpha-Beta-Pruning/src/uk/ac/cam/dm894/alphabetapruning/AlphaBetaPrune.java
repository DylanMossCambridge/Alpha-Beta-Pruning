package uk.ac.cam.dm894.alphabetapruning;

public class AlphaBetaPrune {

    Integer[] treeValues = new Integer[]{ 1, -15, 2, 19, 18, 23, 4, 3, 2, 1, 7, 8, 9, 10, -2, 5, -1, -30, 4, 7, 20, -1, -1, -5 };
    int index = 0;

    public static void main(String[] args) {
        AlphaBetaPrune abp = new AlphaBetaPrune();
        Tree tree = abp.treeMaker(0, "");
        System.out.printf("Final results: %d\n", abp.algorithm(tree, true, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    public Tree treeMaker(int depth, String id) {
        if (depth == 0) {
            return new Tree(null, id, treeMaker(depth + 1, id + "l"), treeMaker(depth + 1, id + "m"), treeMaker(depth + 1, id + "r"));
        }
        if (depth == 4) {
            Tree tree = new Tree(treeValues[index], id, null, null, null);
            index += 1;
            return tree;
        } else {
            return new Tree(null, id, treeMaker(depth + 1, id + "l"), null, treeMaker(depth + 1, id + "r"));
        }
    }

    public Integer algorithm(Tree tree, boolean minimise, Integer alpha, Integer beta) {
        if (tree == null) { return null; }
        if (tree.value == null) {
            if(minimise) {
                Integer left = algorithm(tree.leftBranch, !minimise ,alpha, beta);
                beta = Integer.min(left, beta);
                if (beta <= alpha) {
                    if (tree.middleBranch != null) {
                        System.out.printf("Pruned %s\n", tree.middleBranch.id);
                    }
                    System.out.printf("Pruned %s\n", tree.rightBranch.id);
                    return beta;
                }
                Integer middle = algorithm(tree.middleBranch, !minimise, alpha, beta);
                if (middle != null) { beta = Integer.min(middle, beta); }
                if (beta <= alpha) {
                    System.out.printf("Pruned %s\n", tree.rightBranch.id);
                    return beta;
                }
                Integer right = algorithm(tree.rightBranch, !minimise, alpha,  beta);
                beta = Integer.min(right, beta);
                return beta;
            } else {
                Integer left = algorithm(tree.leftBranch, !minimise ,alpha, beta);
                alpha = Integer.max(left, alpha);
                if (beta <= alpha) {
                    if (tree.middleBranch != null) {
                        System.out.printf("Pruned %s\n", tree.middleBranch.id);
                    }
                    System.out.printf("Pruned %s\n", tree.rightBranch.id);
                    return alpha; }
                Integer middle = algorithm(tree.middleBranch, !minimise, alpha, beta);
                if (middle != null) { alpha = Integer.max(middle, alpha); }
                if (beta <= alpha) {
                    System.out.printf("Pruned %s\n", tree.rightBranch.id);
                    return alpha;
                }
                Integer right = algorithm(tree.rightBranch, !minimise, alpha,  beta);
                alpha = Integer.max(right, alpha);
                return alpha;
            }
        } else {
            return tree.value;
        }
    }
}

