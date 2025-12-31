import java.util.Scanner;


public class LibraryManagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Book book = new Book();
        int choice;

        do {
            System.out.println("\n--- LIBRARY MENU ---");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Remove Book");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    book.addBook(sc);
                    break;
                case 2:
                    book.viewBooks();
                    break;
                case 3:
                    book.searchBook(sc);
                    break;
                case 4:
                    book.removeBook(sc);
                    break;
                case 5:
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}

/* Parent Class */
class Library {
    int id;
    String title;
    String author;
    int year;

    void setDetails(int i, String t, String a, int y) {
        id = i;
        title = t;
        author = a;
        year = y;
    }

    void showDetails() {
        System.out.println("[" + id + "] " + title + " by " + author + " (" + year + ")");
    }
}

/* Child Class */
class Book extends Library {

    Library[] books = new Library[100];
    int count = 0;

    void addBook(Scanner sc) {
        if (count >= 100) {
            System.out.println("Library is full!");
            return;
        }

        Library b = new Library();

        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        System.out.print("Enter Year: ");
        int year = sc.nextInt();

        b.setDetails(id, title, author, year);
        books[count] = b;
        count++;

        System.out.println("Book added successfully!");
    }

    void viewBooks() {
        if (count == 0) {
            System.out.println("No books available.");
            return;
        }

        for (int i = 0; i < count; i++) {
            books[i].showDetails();
        }
    }

    void searchBook(Scanner sc) {
        System.out.print("Enter title to search: ");
        String search = sc.nextLine().toLowerCase();
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (books[i].title.toLowerCase().contains(search)) {
                books[i].showDetails();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    void removeBook(Scanner sc) {
        System.out.print("Enter book ID to remove: ");
        int id = sc.nextInt();

        for (int i = 0; i < count; i++) {
            if (books[i].id == id) {
                for (int j = i; j < count - 1; j++) {
                    books[j] = books[j + 1];
                }
                count--;
                System.out.println("Book removed successfully.");
                return;
            }
        }

        System.out.println("Book not found.");
    }
}
