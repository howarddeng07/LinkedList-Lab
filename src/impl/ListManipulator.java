package impl;

import common.InvalidIndexException;
import common.InvalidListException;
import common.ListNode;
import interfaces.IFilterCondition;
import interfaces.IListManipulator;
import interfaces.IMapTransformation;
import interfaces.IReduceOperator;

/**
 * This class represents the iterative implementation of the IListManipulator interface.
 *
 */
public class ListManipulator implements IListManipulator {

    @Override
    public int size(ListNode head) {
        int size = 1;
        if (head == null){
            return 0;
        }
        ListNode temp = head.next;
         if (temp == head){
            return size;
        }
        while (temp!=head){
            size++;
            temp = temp.next;
        }
        return size;
    }

    @Override
    public boolean isEmpty(ListNode head) {
        if (head == null){
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(ListNode head, Object element) {
        if (head == null){
            return false;
        }
        else if (head.element == element){
            return true;
        }
        ListNode temp = head.next;
        while (temp != head){
            if(temp.element == element){
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public int count(ListNode head, Object element) {
        int count = 0;
        ListNode temp = head;
        while(temp != null){
            if(temp.element == element){
                count++;
            }
            temp = temp.next;
            if (temp == head){
                break;
            }
        }
        return count;
    }

    @Override
    public String convertToString(ListNode head) {
        String str = "";
        ListNode temp = head;
        while(temp != null){
            str += temp.element;
            temp = temp.next;
            if (temp == head){
                break;
            }
            str += ",";
        }
        return str;
    }

    @Override
    public Object getFromFront(ListNode head, int n) throws InvalidIndexException {
        if (n>size(head)||head == null){
            throw new InvalidIndexException();
        }
        ListNode temp = head;
        for (int i = 0; i<n; i++){
            temp = temp.next;
        }
        return temp.element;
    }

    @Override
    public Object getFromBack(ListNode head, int n) throws InvalidIndexException {
        if (size(head)-n<0||head == null){
            throw new InvalidIndexException();
        }
        ListNode temp = head;
        for (int i = 0; i<size(head)-n;i++){
            temp = temp.next;
        }
        return temp.previous.element;
    }

    @Override
    public boolean equals(ListNode head1, ListNode head2) {
        if(size(head1) != size(head2)){
            return false;
        }
        ListNode temp1 = head1;
        ListNode temp2 = head2;
        while(temp1 != null && temp2!= null){
            if (!temp1.element.equals(temp2.element)){
                return false;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
            if(temp1 == head1 || temp2 == head2){
                break;
            }
        }
        return true;
    }

    @Override
    public boolean containsDuplicates(ListNode head) throws InvalidIndexException {
        if (head == null || size(head) == 1) {
            return false;
        }
        for (int i = 0; i < size(head); i++){
            for (int j = i+1; j < size(head); j++){
                if (getFromFront(head,i) == getFromFront(head, j)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public ListNode addHead(ListNode head, ListNode node) {
        node.next = head;
        node.previous = head.previous;
        head.previous.next = node;
        head.previous = node;
        return node;
    }

    @Override
    public ListNode append(ListNode head1, ListNode head2) {
        if (head1 == null){
            return head2;
        }
        else if(head2 == null){
            return head1;
        }
        ListNode last1 = head1.previous;
        ListNode last2 = head2.previous;
        last1.next = head2;
        head2.previous = last1;
        head1.previous = last2;
        last2.next = head1;
        return head1;
    }

    public ListNode insert(ListNode head, ListNode node, int n) throws InvalidIndexException {
        if (n>size(head)||head == null){
            throw new InvalidIndexException();
        }
        ListNode temp = head;
        for (int i = 0; i<n; i++){
            temp = temp.next;
        }
        ListNode prev = temp.previous;
        node.next = temp;
        node.previous = prev;
        prev.next = node;
        temp.previous = node;
        return head;
    }

    public ListNode delete(ListNode head, Object elem) {
        if (head == null){
            return head;
        }
        ListNode temp = head;
        while(temp != null){
            if (temp.element == elem){
                if(size(head) == 1){
                    return null;
                }
                temp.previous.next = temp.next;
                temp.next.previous = temp.previous;
                if (temp == head) head = head.next;
            }
            temp = temp.next;
            if(temp==head)break;
        }
        return head;
    }

    @Override
    public ListNode reverse(ListNode head) {
        if (head == null){
            return head;
        }
        ListNode temp = head;
        while(temp != null){
            ListNode n = temp.next;
            ListNode p = temp.previous;
            temp.next = p;
            temp.previous = n;
            temp = n;
            if (temp == head){
                break;
            }
        }
        return head;

    }

    @Override
    public ListNode split(ListNode head, ListNode node) throws InvalidListException {
        if (head == null || node == null|| head == node){
            throw new InvalidListException();
        }
        ListNode lastofl1 = node.previous;
        ListNode end = head.previous;
        head.previous = lastofl1;
        lastofl1.next = head;
        node.previous = end;
        end.next = node;
        ListNode lol = new ListNode(head);
        lol.next = node;
        return lol;
    }

    @Override
    public ListNode map(ListNode head, IMapTransformation transformation) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object reduce(ListNode head, IReduceOperator operator, Object initial) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListNode filter(ListNode head, IFilterCondition condition) {
        // TODO Auto-generated method stub
        return null;
    }


}
