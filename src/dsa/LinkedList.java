/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsa;
//
import java.util.Scanner;
import javax.swing.JOptionPane;

class Name {

    String data;
    Name next;
}

class Number {

    String data;
    Number next;
}

public class LinkedList {

    Name headForNames;
    Number headForNumbers;

    public void addContact(String name, String number) {
        Number newNumber = new Number();
        Name newName = new Name();

        newNumber.data = number;
        newName.data = name;

        newNumber.next = null;
        newName.next = null;

        if (headForNames == null) {
            headForNames = newName;
            headForNumbers = newNumber;
        } else {
            Name temp = headForNames;
            Number temp2 = headForNumbers;

            while (temp.next != null) {
                temp = temp.next;
                temp2 = temp2.next;
            }
            temp.next = newName;
            temp2.next = newNumber;
        }
    }

    public String displayAllContacts() {
        Name temp = headForNames;
        Number temp2 = headForNumbers;
        Variables.listOfContacts = "";
        while (temp.next != null) {
            Variables.listOfContacts += temp.data + ": " + temp2.data + "\n";
            temp = temp.next;
            temp2 = temp2.next;
        }
        Variables.listOfContacts += temp.data + ": " + temp2.data + "\n";
        return Variables.listOfContacts;

    }

    public int search(String nameToSearch) {
        if (headForNames == null) {
            JOptionPane.showMessageDialog(null, "no contacts available.");
            return -1;
        } else {
            Name temp = headForNames;
            Number temp2 = headForNumbers;
            int index = 0;
            while (!temp.data.equals(nameToSearch) && temp.next != null) {
                temp = temp.next;
                temp2 = temp2.next;
                index++;

            }
            if (temp.data.equals(nameToSearch)) {

                JOptionPane.showMessageDialog(null, "Contact found\n" + temp.data + ": " + temp2.data);
                return index;
            } else {
                JOptionPane.showMessageDialog(null, "contact not found");
            }
        }
        return -1;
    }

    public void deleteContact(String nameToDelete) {
        if (headForNames == null) {
            JOptionPane.showMessageDialog(null, "No contacts available.");
            return;
        }

        Name temp = headForNames;
        Number temp2 = headForNumbers;
        int index = 0;

        while (temp != null && !temp.data.equals(nameToDelete)) {
            temp = temp.next;
            temp2 = temp2.next;
            index++;
        }
        if (temp == null) {
            JOptionPane.showMessageDialog(null, "Contact not found.");
            return;
        }
        if (index == 0) {
            headForNames = headForNames.next;
            headForNumbers = headForNumbers.next;
            JOptionPane.showMessageDialog(null, "Contact deleted.");
            return;
        }

        Name tempName = headForNames;
        Number tempNumber = headForNumbers;
        int count = 0;

        while (count < index - 1) {
            tempName = tempName.next;
            tempNumber = tempNumber.next;
            count++;
        }
        if (tempName.next.next == null) {
            tempName.next = null;
            tempNumber.next = null;
        } else {
            tempName.next = tempName.next.next;
            tempNumber.next = tempNumber.next.next;
        }

        JOptionPane.showMessageDialog(null, "Contact deleted.");
    }


    public void updateContact(String nameOfContactToUpdate, String newNumber) {
        if (headForNames == null) {
            JOptionPane.showMessageDialog(null, "no contacts available.");
        } else {
            Name temp = headForNames;
            Number temp2 = headForNumbers;
            while (!temp.data.equals(nameOfContactToUpdate) && temp.next != null) {
                temp = temp.next;
                temp2 = temp2.next;
            }
            if (temp.data.equals(nameOfContactToUpdate)) {
                temp2.data = newNumber;
                JOptionPane.showMessageDialog(null, "contact updated\n" + temp.data + ": " + temp2.data);
            } else {
                JOptionPane.showMessageDialog(null, "contact not found");
            }
        }
    }
}
