package org.agoncal.training.javaee.view;

import org.agoncal.training.javaee.model.Book;
import org.agoncal.training.javaee.model.CD;
import org.agoncal.training.javaee.model.Language;
import org.agoncal.training.javaee.service.ItemService;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@Named
@RequestScoped
public class ItemController {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private ItemService itemService;

    private Book book = new Book();
    private List<Book> bookList = new ArrayList<>();
    private String tags;

    private CD cd = new CD();
    private List<CD> cdList = new ArrayList<>();

    @Inject
    private Logger logger;

    // ======================================
    // =          Lifecycle methods         =
    // ======================================

    @PostConstruct
    private void initList() {
        logger.debug("ItemController.initList()");
        bookList = itemService.findAllBooks();
        cdList = itemService.findAllCDs();
    }

    // ======================================
    // =           Public Methods           =
    // ======================================

    public String doCreateBook() {
        logger.debug("ItemController.doCreateBook():" + book);
        book.setTags(transformToList(tags));
        itemService.createBook(book);
        bookList = itemService.findAllBooks();
        book = new Book();
        return "newBook.xhtml";
    }

    public String doCreateCD() {
        logger.debug("ItemController.doCreateCD():" + cd);
        itemService.createCD(cd);
        cdList = itemService.findAllCDs();
        cd = new CD();
        return "newCD.xhtml";
    }

    // ======================================
    // =           Private methods          =
    // ======================================

    private List<String> transformToList(String tagsRequestParameter) {
        logger.debug("ItemController.transformToList():" + tagsRequestParameter);
        if (tagsRequestParameter == null)
            return null;
        List<String> listOfTags = new ArrayList<>();
        StringTokenizer tokens = new StringTokenizer(tagsRequestParameter, ",");
        while (tokens.hasMoreElements()) {
            listOfTags.add(((String) tokens.nextElement()).trim());
        }
        return listOfTags;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public CD getCd() {
        return cd;
    }

    public void setCd(CD cd) {
        this.cd = cd;
    }

    public List<CD> getCdList() {
        return cdList;
    }

    public void setCdList(List<CD> cdList) {
        this.cdList = cdList;
    }

    public Language[] getLanguages() {
        return Language.values();
    }
}