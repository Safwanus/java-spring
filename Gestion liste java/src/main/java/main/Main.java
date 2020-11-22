package main.java.main;

import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

import main.java.DAO.MyDOA;
import main.java.controller.Controller;
import main.java.controller.Hash;
import main.java.model.Liste;
import main.java.model.Status;
import main.java.model.Tag;
import main.java.model.User;
import main.java.model.Element;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) throws Exception {
		/* BENELKADI Safouane El-Amine */
		Controller c = new Controller();
		c.main(args);

		/*
		 * 
		 * 
		 * 
		 * 
		 * l.forEach( new Consumer<Liste>() {
		 * 
		 * @Override public void accept(Liste arg0) {
		 * System.out.println(doa.getAllElements(arg0.getId())); }}); }
		 */

		// MyDOA.getAllTagnames(MyDOA.getUserID("admin"));
		/*
		 * MyDOA model = new MyDOA(); model.getAllTagnames().forEach(new Consumer<Tag>()
		 * {
		 * 
		 * @Override public void accept(Tag arg0) {
		 * System.out.println(arg0.getTagname()); }
		 * 
		 * });
		 */

		/*
		 * MyDOA doa = new MyDOA(); doa.getAllUsers().forEach(new Consumer<User>() {
		 * 
		 * @Override public void accept(User t) { System.out.println(t.getId()); }
		 * 
		 * });
		 */

		/*
		 * doa.getAllLists(1).forEach(new Consumer<Liste>() {
		 * 
		 * @Override public void accept(Liste arg0) { System.out.println(arg0.getId());}
		 * });; doa.getAllElements(11).forEach(new Consumer<Element>() {
		 * 
		 * @Override public void accept(Element arg0) {
		 * System.out.println(arg0.getId());} });;; doa.updateElement(7, "titre2",
		 * "description"); System.out.println(doa.getElementById(7).getTitre());
		 */

	}

}
