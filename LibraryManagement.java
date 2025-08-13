import java.io.*;
import java.util.*;

class Book implements Serializable {
    private int id;
    private String title;
    private String author;
    private int year;

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " by " + author + " (" + year + ")";
    }
}

public class LibraryManagement {
    private static ArrayList<Book> books = new ArrayList<>();
    private static final String FILE_NAME = "library.dat";

    public static void main(String[] args) {
        loadBooks();
        Scanner sc = new Scanner(System.in);
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
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> addBook(sc);
                case 2 -> viewBooks();
                case 3 -> searchBook(sc);
                case 4 -> removeBook(sc);
                case 5 -> saveBooks();
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 5);

        sc.close();
    }

    private static void addBook(Scanner sc) {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        System.out.print("Enter author: ");
        String author = sc.nextLine();
        System.out.print("Enter year: ");
        int year = sc.nextInt();

        books.add(new Book(id, title, author, year));
        System.out.println("Book added successfully!");
    }

    private static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private static void searchBook(Scanner sc) {
        System.out.print("Enter title to search: ");
        String searchTitle = sc.nextLine().toLowerCase();
        boolean found = false;
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(searchTitle)) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) System.out.println("No matching books found.");
    }

    private static void removeBook(Scanner sc) {
        System.out.print("Enter book ID to remove: ");
        int id = sc.nextInt();
        books.removeIf(b -> b.getId() == id);
        System.out.println("Book removed if it existed.");
    }

    @SuppressWarnings("unchecked")
    private static void loadBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            books = (ArrayList<Book>) ois.readObject();
        } catch (Exception e) {
            // Ignore if file not found or empty
        }
    }

    private static void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(books);
            System.out.println("Books saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }
}
