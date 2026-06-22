package sn.iage.isi.main;

import sn.iage.isi.entities.Book;
import sn.iage.isi.entities.Category;
import sn.iage.isi.repositories.BookRepository;
import sn.iage.isi.repositories.CategoryRepository;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CategoryRepository categoryRepository = new CategoryRepository();
        BookRepository bookRepository = new BookRepository();

        Category roman = categoryRepository.create(
                Category.builder().name("Roman").build()
        );
        System.out.println("Catégorie créée : " + roman);

        Book b1 = bookRepository.createBook(
                Book.builder()
                        .title("Une si longue Lettre")
                        .author("Mariama Bâ")
                        .publicationYear(1979)
                        .countPages(165)
                        .category(roman)
                        .build()
        );
        System.out.println("Livre créé (b1) : " + b1);

        Book b2 = bookRepository.createBook(
                Book.builder()
                        .title("Le Petit Prince")
                        .author("Antoine de Saint-Exupéry")
                        .publicationYear(1943)
                        .countPages(93)
                        .category(roman)
                        .build()
        );
        System.out.println("Livre créé (b2) : " + b2);

        System.out.println("\n--- Tous les livres ---");
        for (Book b : bookRepository.ListAllBooks()) {
            System.out.println(b);
        }

        System.out.println("\n--- findBookById(" + b1.getId() + ") ---");
        System.out.println(bookRepository.findBookById(b1.getId()));

        System.out.println("\n--- findBookByIsbn(" + b1.getIsbn() + ") ---");
        System.out.println(bookRepository.findBookByIsbn(b1.getIsbn()));

        System.out.println("\n--- updateBook ---");
        Book updated = bookRepository.updateBook(b2.getId(),
                Book.builder()
                        .title("Le Petit Prince (Édition Spéciale)")
                        .author("Antoine de Saint-Exupéry")
                        .publicationYear(1943)
                        .countPages(110)
                        .category(roman)
                        .build()
        );
        System.out.println("Livre après modification : " + updated);

        System.out.println("\n--- ListeBooksByCategory(\"Roman\") ---");
        for (Book b : bookRepository.ListeBooksByCategory("Roman")) {
            System.out.println(b);
        }

        System.out.println("\n--- searchBooksByTitle(\"Prince\") ---");
        for (Book b : bookRepository.searchBooksByTitle("Prince")) {
            System.out.println(b);
        }

        System.out.println("\n--- searchBooksByAuthor(\"Mariama\") ---");
        for (Book b : bookRepository.searchBooksByAuthor("Mariama")) {
            System.out.println(b);
        }

        System.out.println("\n--- searchBooksAfterYear(1900) ---");
        for (Book b : bookRepository.searchBooksAfterYear(1900)) {
            System.out.println(b);
        }

        System.out.println("\n--- countBooksByCategory() ---");
        Map<String, Long> counts = bookRepository.countBooksByCategory();
        for (Map.Entry<String, Long> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("\n--- countAllBooks() ---");
        System.out.println("Total : " + bookRepository.countAllBooks());

        System.out.println("\n--- deleteBook(" + b1.getId() + ") ---");
        bookRepository.deleteBook(b1.getId());
        System.out.println("Total après suppression : " + bookRepository.countAllBooks());
    }
}