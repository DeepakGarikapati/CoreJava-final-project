package FinalProject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

enum Category {
    FOOD,
    TRAVEL,
    SHOPPING,
    BILLS,
    OTHER
}

class Expense {

    int id;
    String name;
    double amount;
    Category category;
    LocalDate date;

    Expense(int id, String name, double amount, Category category) {

        this.id = id;
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.date = LocalDate.now();
    }
}

public class PersonalExpenseTracker {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Expense> expenses = new ArrayList<>();

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("===== PERSONAL EXPENSE TRACKER =====");
            System.out.println("1. Add Expense");
            System.out.println("2. Display Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Calculate Total Expense");
            System.out.println("5. Find Highest Expense");
            System.out.println("6. Category-wise Expense");
            System.out.println("7. Search Expense");
            System.out.println("8. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addExpense();
                    break;

                case 2:
                    displayExpenses();
                    break;

                case 3:
                    deleteExpense();
                    break;

                case 4:
                    totalExpense();
                    break;

                case 5:
                    highestExpense();
                    break;

                case 6:
                    categoryWiseExpense();
                    break;

                case 7:
                    searchExpense();
                    break;

                case 8:
                    System.out.println("Thank you for using Expense Tracker.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 8);
    }


    static void addExpense() {

        System.out.print("Enter expense ID: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter expense name: ");
        String name = sc.nextLine();

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();

        System.out.println("Select Category:");
        System.out.println("1. FOOD");
        System.out.println("2. TRAVEL");
        System.out.println("3. SHOPPING");
        System.out.println("4. BILLS");
        System.out.println("5. OTHER");

        System.out.print("Enter category: ");
        int choice = sc.nextInt();

        Category category;

        switch (choice) {

            case 1:
                category = Category.FOOD;
                break;

            case 2:
                category = Category.TRAVEL;
                break;

            case 3:
                category = Category.SHOPPING;
                break;

            case 4:
                category = Category.BILLS;
                break;

            default:
                category = Category.OTHER;
        }

        Expense expense = new Expense(id, name, amount, category);

        expenses.add(expense);

        System.out.println("Expense added successfully.");
    }


    static void displayExpenses() {

        if (expenses.isEmpty()) {

            System.out.println("No expenses available.");
            return;
        }

        System.out.println("----- ALL EXPENSES -----");

        for (Expense e : expenses) {

            System.out.println("ID: " + e.id);
            System.out.println("Name: " + e.name);
            System.out.println("Amount: Rs" + e.amount);
            System.out.println("Category: " + e.category);
            System.out.println("Date: " + e.date);
            System.out.println("------------------------");
        }
    }


    static void deleteExpense() {

        System.out.print("Enter expense ID to delete: ");
        int id = sc.nextInt();

        boolean found = false;

        for (int i = 0; i < expenses.size(); i++) {

            if (expenses.get(i).id == id) {

                expenses.remove(i);

                System.out.println("Expense deleted successfully.");

                found = true;

                break;
            }
        }

        if (!found) {

            System.out.println("Expense not found.");
        }
    }


    static void totalExpense() {

        double total = expenses.stream()
                .mapToDouble(e -> e.amount)
                .sum();

        System.out.println("Total Expense: Rs" + total);
    }


    static void highestExpense() {

        if (expenses.isEmpty()) {

            System.out.println("No expenses available.");
            return;
        }

        Expense highest = expenses.stream()
                .max((e1, e2) -> Double.compare(e1.amount, e2.amount))
                .get();

        System.out.println("----- HIGHEST EXPENSE -----");
        System.out.println("Name: " + highest.name);
        System.out.println("Amount: Rs" + highest.amount);
        System.out.println("Category: " + highest.category);
        System.out.println("Date: " + highest.date);
    }


    static void categoryWiseExpense() {

        HashMap<Category, Double> categoryAmount = new HashMap<>();

        for (Expense e : expenses) {

            if (categoryAmount.containsKey(e.category)) {

                double oldAmount = categoryAmount.get(e.category);

                categoryAmount.put(
                        e.category,
                        oldAmount + e.amount
                );

            } else {

                categoryAmount.put(e.category, e.amount);
            }
        }

        System.out.println("----- CATEGORY-WISE EXPENSE -----");

        for (Map.Entry<Category, Double> entry : categoryAmount.entrySet()) {

            System.out.println(
                    entry.getKey() + " : Rs" + entry.getValue()
            );
        }
    }


    static void searchExpense() {

        sc.nextLine();

        System.out.print("Enter expense name to search: ");
        String name = sc.nextLine();

        boolean found = false;

        for (Expense e : expenses) {

            if (e.name.equalsIgnoreCase(name)) {

                System.out.println("Expense Found");
                System.out.println("ID: " + e.id);
                System.out.println("Name: " + e.name);
                System.out.println("Amount: Rs" + e.amount);
                System.out.println("Category: " + e.category);
                System.out.println("Date: " + e.date);

                found = true;
            }
        }

        if (!found) {

            System.out.println("Expense not found.");
        }
    }
}
