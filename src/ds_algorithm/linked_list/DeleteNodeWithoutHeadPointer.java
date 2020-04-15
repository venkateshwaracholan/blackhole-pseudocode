/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.linked_list;

/**
 *
 * @author vshanmugham
 */
public class DeleteNodeWithoutHeadPointer {
  
}

/*

Given only a pointer/reference to a node to be deleted in a singly linked list, how do you delete it?
Given a pointer to a node to be deleted, delete the node. Note that we don’t have pointer to head node.

A simple solution is to traverse the linked list until you find the node you want to delete. But this solution requires pointer to the head node which contradicts the problem statement.
Fast solution is to copy the data from the next node to the node to be deleted and delete the next node. Something like following.
   // Find next node using next pointer
    struct Node *temp  = node_ptr->next;

    // Copy data of next node to this node
    node_ptr->data  = temp->data;
 
   // Unlink next node
    node_ptr->next  = temp->next;

    // Delete next node
    free(temp);





*/