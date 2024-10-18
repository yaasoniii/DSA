/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsa;

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
            int index = 0;
            while (temp != null) {
                if (temp.data.equalsIgnoreCase(name)) {
                    JOptionPane.showMessageDialog(null, "name already exists");
                    return;
                }
                temp = temp.next;
                temp2 = temp2.next;
                index++;

            }
            temp = headForNames;
            temp2 = headForNumbers;

            while (temp.next != null) {
                temp = temp.next;
                temp2 = temp2.next;
            }
            temp.next = newName;
            temp2.next = newNumber;
            Variables.phonebook.insertionSort();

        }
    }

    public String displayAllContacts() {
        Variables.phonebook.insertionSort();
        if (headForNames != null) {
            Name temp = headForNames;
            Number temp2 = headForNumbers;
            Variables.listOfContacts = "";
            while (temp.next != null) {
                Variables.listOfContacts += temp.data + ": " + temp2.data + "\n";
                temp = temp.next;
                temp2 = temp2.next;
            }
            Variables.listOfContacts += temp.data + ": " + temp2.data + "\n";
            
        }
        else {
            Variables.listOfContacts = "no contacts available.";
        }
        return Variables.listOfContacts;

    }

    public void deleteContact(String nameToDelete) {
        if (headForNames == null) {
            JOptionPane.showMessageDialog(null, "No contacts available.");
        } 
        else if(headForNames.next == null) {
            headForNames = headForNames.next;
            headForNumbers = headForNumbers.next;
        }
        else {
            Name temp = headForNames;
            Number temp2 = headForNumbers;
            int index = 0;

            while (!temp.data.equalsIgnoreCase(nameToDelete)) {
                temp = temp.next;
                temp2 = temp2.next;
                index++;
            }

            if (!temp.data.equalsIgnoreCase(nameToDelete)) {
                JOptionPane.showMessageDialog(null, "Contact not found.");
            }

            else {
                Name tempName = headForNames;
                Number tempNumber = headForNumbers;
                int count = 0;

                while (count < index - 1) {
                    tempName = tempName.next;
                    tempNumber = tempNumber.next;
                    count++;
                }

                Name currentName = tempName.next;
                Number currentNumber = tempNumber.next;

                if (currentName.next != null) {
                    tempName.next = currentName.next;
                    tempNumber.next = currentNumber.next;
                    System.out.println("Contact deleted.");
                } else {
                    tempName.next = null;
                    tempNumber.next = null;
                    JOptionPane.showMessageDialog(null, "Contact deleted.");
                }
            }
        }
        Variables.phonebook.insertionSort();
    }

    public void updateContact(String nameOfContactToUpdate, String newNumber, String newName) {
        if (headForNames == null) {
            JOptionPane.showMessageDialog(null, "no contacts available.");
        } else {
            Name temp = headForNames;
            Number temp2 = headForNumbers;
            while (!temp.data.equalsIgnoreCase(nameOfContactToUpdate) && temp.next != null) {
                temp = temp.next;
                temp2 = temp2.next;
            }
            if (temp.data.equalsIgnoreCase(nameOfContactToUpdate)) {
                temp.data = newName;
                temp2.data = newNumber;
                Variables.phonebook.insertionSort();
                JOptionPane.showMessageDialog(null, "contact updated\n" + temp.data + ": " + temp2.data);
            } else {
                JOptionPane.showMessageDialog(null, "contact not found");
            }
        }
        Variables.phonebook.insertionSort();
    }
    public void insertionSort() {
        if (headForNames == null || headForNames.next == null) {
            return;
        }
        Name sortedNames = null;
        Number sortedNumbers = null;
        Name currentName = headForNames;
        Number currentNumber = headForNumbers;

        while (currentName != null) {
            Name nextName = currentName.next;
            Number nextNumber = currentNumber.next;

            if (sortedNames == null || sortedNames.data.compareToIgnoreCase(currentName.data) > 0) {
                currentName.next = sortedNames;
                currentNumber.next = sortedNumbers;
                sortedNames = currentName;
                sortedNumbers = currentNumber;
            } else {
                Name tempName = sortedNames;
                Number tempNumber = sortedNumbers;

                while (tempName.next != null && tempName.next.data.compareToIgnoreCase(currentName.data) < 0) {
                    tempName = tempName.next;
                    tempNumber = tempNumber.next;
                }

                currentName.next = tempName.next;
                currentNumber.next = tempNumber.next;
                tempName.next = currentName;
                tempNumber.next = currentNumber;
            }

            currentName = nextName;
            currentNumber = nextNumber;
        }
        headForNames = sortedNames;
        headForNumbers = sortedNumbers;
    }
    public void binarySearch(String nameToSearch) {

        Variables.phonebook.insertionSort();

        int left = 0;
        int right = 0;

        Name temp = headForNames;
        while (temp != null) {
            right++;
            temp = temp.next;
        }
        right--;  // Adjust as right was incremented one extra time

        while (left <= right) {
            int mid = left + (right - left) / 2;

            Name midName = headForNames;
            Number midNumber = headForNumbers;
            int count = 0;
            while (count < mid) {
                midName = midName.next;
                midNumber = midNumber.next;
                count++;
            }

            if (midName.data.equalsIgnoreCase(nameToSearch)) {
                JOptionPane.showMessageDialog(null, "Contact found\n" + midName.data + ": " + midNumber.data);
                return;
            } else if (midName.data.compareToIgnoreCase(nameToSearch) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        JOptionPane.showMessageDialog(null, "Contact not found");
    }


}
