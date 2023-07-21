package org.jsp.userProduct.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.userProduct.dao.ProductDao;
import org.jsp.userProduct.dao.UserDao;
import org.jsp.userProduct.dto.Product;
import org.jsp.userProduct.dto.User;

public class UserProduct {
	static ProductDao prodDao= new ProductDao();
	static UserDao usrDao = new UserDao();
	static Scanner s= new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("1.Register User");
		System.out.println("2.Verify User By Phone and password");
		System.out.println("3.Verify User By Email and password");
		System.out.println("4.Update User");
		System.out.println("5.Add Product");
		System.out.println("6.View Product By User Id");
		System.out.println("7.View Product By User phone and password");
		int choice = s.nextInt();
		switch (choice) {
		case 1: {
			saveUser();
			break;
		}
		case 2: {
			verifyUserByPhonePswd();
			break;
		}
		case 3: {
			verifyUserByEmailPswd();
			break;
		}
		case 4: {
			updateUser();
			break;
		}
		case 5: {
			addProduct();
			break;
		}
		case 6: {
			viewProdByUserId();
			break;
		}
		case 7: {
			viewProdByUserPhonPswd();
			break;
		}
	
	}
}
		public static void saveUser() {
			System.out.println("Enter user name, phone, email and password");
			String name= s.next();
			long phone=s.nextLong();
			String email= s.next();
			String password= s.next();
			User u= new User();
			u.setName(name);
			u.setPhone(phone);
			u.setEmail(email);
			u.setPassword(password);
			u = usrDao.registerUser(u);
			System.out.println("User saved with Id:" + u.getId());
		}
		
		public static void verifyUserByPhonePswd() {
			System.out.println("Enter phone and password to verify Employee");
			long phone=s.nextLong();
			String password=s.next();
			User u= usrDao.verifyUser(phone, password);
			if (u != null) {
				System.out.println("User Id:" + u.getId());
				System.out.println("User Name:" + u.getName());
				System.out.println("User Phone:" + u.getPhone());
				System.out.println("User Email:" + u.getEmail());
				System.out.println("User Password:" + u.getPassword());
			} else {
				System.err.println("Invalid Phone or Password");
			}

		}
		
		public static void verifyUserByEmailPswd() {
			System.out.println("Enter email and password to verify Employee");
			String email=s.next();
			String password=s.next();
			User u= usrDao.verifyUsers(email, password);
			if (u != null) {
				System.out.println("User Id:" + u.getId());
				System.out.println("User Name:" + u.getName());
				System.out.println("User Phone:" + u.getPhone());
				System.out.println("User Email:" + u.getEmail());
				System.out.println("User Password:" + u.getPassword());
			} else {
				System.err.println("Invalid Email or Password");
			}

		}
		
		public static void updateUser() {
			System.out.println("Enter user id to update");
			int id=s.nextInt();
			System.out.println("Enter user name, phone, email and password");
			String name= s.next();
			long phone=s.nextLong();
			String email= s.next();
			String password= s.next();
			User u= new User();
			u.setId(id);
			u.setName(name);
			u.setPhone(phone);
			u.setEmail(email);
			u.setPassword(password);
			u = usrDao.updateUser(u);
			System.out.println("User saved with Id:" + u.getId());
		}
		
		public static void addProduct() {
			System.out.println("Enter the User id to add Product");
			int user_id = s.nextInt();
			System.out.println("Enter Product name, Description, brand, price");
			String name=s.next();
			String descri=s.next();
			String brand=s.next();
			double price=s.nextDouble();
			Product p=new Product();
			p.setName(name);
			p.setDescri(descri);
			p.setBrand(brand);
			p.setPrice(price);
			p=prodDao.addProduct(p, user_id);
			if (p != null)
				System.out.println("Product saved with id:" + p.getId());
			else
				System.err.println("Invalid Product id");
		}
		
		public static void viewProdByUserId() {
			System.out.println("Enter User id to View Products");
			int id=s.nextInt();
			List<Product> prod = prodDao.viewProducts(id);
			if (prod.size() > 0) {
				for (Product p : prod) {
					System.out.println("Product Id:" + p.getId());
					System.out.println("Product Name:" + p.getName());
					System.out.println("Product Desc:" + p.getDescri());
					System.out.println("Product Brand:" + p.getBrand());
					System.out.println("Product Price:" + p.getPrice());
					System.out.println("---------------------------------------");
				}
			} else {
				System.err.println("You have entered an Invalid User id");
			}
		}
		
		public static void viewProdByUserPhonPswd() {
			System.out.println("Enter Phone and Password to view Products");
			long phone=s.nextLong();
			String email=s.next();
			Product p = prodDao.viewProducts(phone, email);
			if (p != null) {
					System.out.println("Product Id:" + p.getId());
					System.out.println("Product Name:" + p.getName());
					System.out.println("Product Desc:" + p.getDescri());
					System.out.println("Product Brand:" + p.getBrand());
					System.out.println("Product Price:" + p.getPrice());
					System.out.println("---------------------------------------");
				}
			 else {
				System.err.println("You have entered an Invalid phone and email");
			}
		}
				
}
