package uk.ac.cam.dm894.alphabetapruning;

public class Tree {
    Tree leftBranch;
    Tree middleBranch;
    Tree rightBranch;
    String id;
    Integer value;

    Tree(Integer value, String id, Tree leftBranch, Tree middleBranch, Tree rightBranch) {
        this.value = value;
        this.id = id;
        this.leftBranch = leftBranch;
        this.middleBranch = middleBranch;
        this.rightBranch = rightBranch;
    }
}