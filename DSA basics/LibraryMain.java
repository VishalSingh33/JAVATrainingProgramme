package com.library;

public class LibraryMain {

	public static void main(String[] args) {

	}

}

class Author{
	private String authorName;
	private String description;
	}
	
class Library{
	private String name;
	private String address;
}

class Student extends Account{
	private String schoolName;
	private String schoolId;
	boolean schoolDiscount;
}

class Account{
	private String firstName;
	private String lastName;
	private String id;
	private String password;
	private String status;
	private int age;	
}
class Librarian extends Account{
	boolean addBookItem() {
		return false;
	}
	
	boolean blockMember() {
		return false;
	}
	
	boolean unblockMember() {
		return false;
		
	}
}

class Member extends Account{
	public void dateOfMembership() {
		
	}
	
	public void totalBooksCheckout() {
		
	}	
}

class Book{
	private String title;
	private String subject;
	public String publisher;
	public String language;
	public int numberOfPages;
	public String genre;
}

class BookItem extends Book{
	public String barcode;
	boolean isReferenceOnly;
	public String borrowed;
	public String dueDate;
	public double price;
	public String format;
	public String status;
	public String dateOfPurchase;
	public String publicationDate;
	
}

class LibraryCard{
	public String cardNumber;
	public String barcode;
	public String issuedAt;
	boolean active;
	
}

class BookLending{
	public String creationDate;
	public String dueData;
	
}


