package main.java.DAO;

import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import main.java.controller.Hash;
import main.java.model.Element;
import main.java.model.Liste;
import main.java.model.Tag;
import main.java.model.User;
import main.java.model.Status;

public class MyDOA {
	/* BENELKADI Safouane El-Amine */

	static Sql2o sql2o;

	static {
		sql2o = new Sql2o("jdbc:h2:file:./h2/BDDF", "usr", "mdp");
	}

	// Retourne un modele liste
	public static List<Liste> getAllLists(int UserID) {
		String sql = "SELECT id , titre, description " + "FROM liste " + "WHERE id IN (SELECT lid " + "FROM usrlist "
				+ "WHERE uid = :uidParam )  " + "AND id NOT IN (" + "SELECT IDSL FROM sublists " + "WHERE IDL IN (  "
				+ "SELECT LID FROM USRLIST " + "WHERE UID = :uidParam))";

		try (Connection con = sql2o.open()) {
			return con.createQuery(sql).addParameter("uidParam", UserID).executeAndFetch(Liste.class);
		}
	}

	public static List<Liste> getAllsubLists(int ListID) {
		String sql = "SELECT id , titre, description " + "FROM liste " + "WHERE id IN (SELECT IDSL " + "FROM sublists "
				+ "WHERE IDL = :lidParam) ";

		try (Connection con = sql2o.open()) {
			return con.createQuery(sql).addParameter("lidParam", ListID).executeAndFetch(Liste.class);
		}
	}

	public static List<Element> getAllElements(int ListeID) {
		String sql = "SELECT id, titre, description, dcreation, dmodification " + "FROM element "
				+ "WHERE id IN (SELECT eid " + "FROM listelem " + "WHERE lid = :lidParam) ";
		;

		try (Connection con = sql2o.open()) {
			return con.createQuery(sql).addParameter("lidParam", ListeID).executeAndFetch(Element.class);
		}
	}

	public static boolean connection(String username, String password) {
		List<User> user;
		String sql = "SELECT PASSWORD " + "FROM USER " + "WHERE USERNAME = :usrParam ";

		try (Connection con = sql2o.open()) {
			user = con.createQuery(sql).addParameter("usrParam", username).executeAndFetch(User.class);
		}

		if ((user.get(0).getPassword()).equals(Integer.toString(Hash.sha1(username + password)))) {
			return true;
		} else
			return false;
	}

	public static int getUserID(String username) {
		String sql = "SELECT id " + "FROM USER " + "WHERE username = :usrParam ";
		try (Connection con = sql2o.open()) {
			return con.createQuery(sql).addParameter("usrParam", username).executeAndFetch(User.class).get(0).getId();
		}

	}

	public static Liste getListeById(int idList) {
		try (Connection con = sql2o.open()) {
			final String sql = "SELECT * FROM LISTE WHERE ID = :idList";
			return con.createQuery(sql).addParameter("idList", idList).executeAndFetchFirst(Liste.class);

		}
	}

	public static Element getElementById(int idElem) {
		try (Connection con = sql2o.open()) {
			final String sql = "SELECT * FROM ELEMENT WHERE ID = :idElem";
			return con.createQuery(sql).addParameter("idElem", idElem).executeAndFetchFirst(Element.class);

		}
	}

	public static User getUserByList(int idList) {
		try (Connection con = sql2o.open()) {
			String sql = "SELECT ID, USERNAME, PASSWORD FROM USER WHERE ID IN ("
					+ "SELECT UID FROM USRLIST WHERE LID = :idList)";
			return con.createQuery(sql).addParameter("idList", idList).executeAndFetchFirst(User.class);

		}
	}

	public static Liste getListByElemId(int idElem) {
		try (Connection con = sql2o.open()) {
			String sql = "SELECT ID, TITRE, DESCRIPTION FROM LISTE WHERE ID IN ("
					+ "SELECT LID FROM LISTELEM WHERE EID = :idElem)";
			return con.createQuery(sql).addParameter("idElem", idElem).executeAndFetchFirst(Liste.class);

		}
	}

	/* _________________CREATION _______________________________________________ */
	public static void addUser(String username, String password) {

		try (Connection con = sql2o.open()) {
			String sql = "INSERT INTO USER (USERNAME , PASSWORD ) " + "VALUES(:username, :password)";

			con.createQuery(sql).addParameter("username", username)
					.addParameter("password", Integer.toString(Hash.sha1(username + password))).executeUpdate();
			con.commit();
		}
	}

	public static void addElement(int idList, String titre, String description) {
		try (Connection con = sql2o.open()) {
			String sql = "INSERT INTO ELEMENT (TITRE , DESCRIPTION,  DCREATION , DMODIFICATION ) "
					+ "VALUES( :titre, :description, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";

			Object idElem = con.createQuery(sql, true).addParameter("titre", titre)
					.addParameter("description", description).executeUpdate().getKey();

			sql = "INSERT INTO LISTELEM (LID , EID) VALUES ( :idList , :idElem)";
			con.createQuery(sql).addParameter("idList", idList).addParameter("idElem", idElem).executeUpdate();

			con.commit();
		}
	}

	public static void addList(int idUser, String titre, String description) {
		try (Connection con = sql2o.open()) {
			String sql = "INSERT INTO LISTE ( TITRE , DESCRIPTION ) " + "VALUES ( :titre, :description)";

			Object idListe = con.createQuery(sql, true).addParameter("titre", titre)
					.addParameter("description", description).executeUpdate().getKey();

			sql = "INSERT INTO USRLIST ( UID, LID) " + "VALUES ( :idUser, :idListe)";
			con.createQuery(sql).addParameter("idUser", idUser).addParameter("idListe", idListe).executeUpdate();
			con.commit();
		}
	}

	public static void addSubList(int idListe, String titre, String description) {
		try (Connection con = sql2o.open()) {
			int idUser = getUserByList(idListe).getId();
			String sql = "INSERT INTO LISTE ( TITRE , DESCRIPTION ) " + "VALUES ( :titre, :description)";

			Object idSubListe = con.createQuery(sql, true).addParameter("titre", titre)
					.addParameter("description", description).executeUpdate().getKey();

			sql = "INSERT INTO SUBLISTS ( IDL, IDSL) " + "VALUES ( :idListe, :idSubListe)";
			con.createQuery(sql).addParameter("idListe", idListe).addParameter("idSubListe", idSubListe.toString())
					.executeUpdate();

			sql = "INSERT INTO USRLIST ( UID, LID) " + "VALUES ( :idUser, :idSubListe)";
			con.createQuery(sql).addParameter("idUser", idUser).addParameter("idSubListe", idSubListe.toString())
					.executeUpdate();

			con.commit();
		}
	}
	/* _________________CREATION _______________________________________________ */

	/*
	 * _________________Modification _______________________________________________
	 */
	public static void updateElement(int idElement, String titre, String description) {
		try (Connection con = sql2o.open()) {
			String sql = "UPDATE ELEMENT SET titre = :titre , DESCRIPTION = :description, DMODIFICATION = CURRENT_TIMESTAMP "
					+ "WHERE ID = :idElement";

			con.createQuery(sql).addParameter("idElement", idElement).addParameter("titre", titre)
					.addParameter("description", description).executeUpdate();
			con.commit();
		}
	}

	public static void updateListe(int idListe, String titre, String description) {
		try (Connection con = sql2o.open()) {
			String sql = "UPDATE LISTE SET titre = :titre , DESCRIPTION = :description " + "WHERE ID = :idListe";

			con.createQuery(sql).addParameter("idListe", idListe).addParameter("titre", titre)
					.addParameter("description", description).executeUpdate();
			con.commit();
		}
	}

	public static void updateUser(int id, String username, String password) {
		try (Connection con = sql2o.open()) {
			String sql = "UPDATE USER SET username = :username , password = :password " + "WHERE ID = :id";
			con.createQuery(sql).addParameter("id", id).addParameter("username", username)
					.addParameter("password", Integer.toString(Hash.sha1(username + password))).executeUpdate();
			con.commit();
		}
	}
	/*
	 * _________________Modification _______________________________________________
	 */

	/*
	 * _________________Suppression _______________________________________________
	 */
	public static void deleteList(int idList) {
		try (Connection con = sql2o.open()) {

			String sql = "DELETE FROM ELEMENT WHERE ID IN (" + "SELECT EID FROM LISTELEM WHERE LID = :idList)";
			con.createQuery(sql).addParameter("idList", idList).executeUpdate();

			sql = "DELETE FROM ELEMENT WHERE ID IN (" + "SELECT EID FROM LISTELEM WHERE LID IN ("
					+ "SELECT IDSL FROM SUBLISTS WHERE IDL = :idList))";
			con.createQuery(sql).addParameter("idList", idList).executeUpdate();

			sql = "DELETE FROM LISTE WHERE ID IN (" + "SELECT IDSL FROM SUBLISTS WHERE IDL = :idList)";
			con.createQuery(sql).addParameter("idList", idList).executeUpdate();

			sql = "DELETE FROM LISTE WHERE ID = :idList";
			con.createQuery(sql).addParameter("idList", idList).executeUpdate();
			con.commit();
		}
	}

	public static void deleteElement(int idElem) {
		try (Connection con = sql2o.open()) {
			String sql = "DELETE FROM ELEMENT WHERE ID = :idElem";
			con.createQuery(sql).addParameter("idElem", idElem).executeUpdate();
			con.commit();
		}
	}

	public static void deleteUser(int id) {
		try (Connection con = sql2o.open()) {
			if (id != 1) {
				String sql = "DELETE FROM USER WHERE ID = :id";
				con.createQuery(sql).addParameter("id", id).executeUpdate();
				con.commit();
			}
		}

	}
	/*
	 * _________________Suppression _______________________________________________
	 */

	public static boolean isAdmin(int id) {
		try (Connection con = sql2o.open()) {

			return (id == 1);
		}
	}

	public static List<User> getAllUsers() {
		try (Connection con = sql2o.open()) {

			String sql = "SELECT * FROM USER";
			return con.createQuery(sql).executeAndFetch(User.class);

		}
	}

	public static void shareList(int userId, int lId) {
		try (Connection con = sql2o.open()) {
			String sql = "INSERT INTO USRLIST (UID, LID) VALUES ( :uid , :lid )";
			con.createQuery(sql).addParameter("uid", userId).addParameter("lid", lId).executeUpdate();
			con.commit();
		}
	}

	public static User getUserByID(String idUser) {
		try (Connection con = sql2o.open()) {
			final String sql = "SELECT * FROM USER WHERE ID = :idUser";
			return con.createQuery(sql).addParameter("idUser", idUser).executeAndFetchFirst(User.class);

		}
	}

	public static void shareElem(int idList, int idElem) {
		try (Connection con = sql2o.open()) {
			String sql = "INSERT INTO LISTELEM (LID, EID) VALUES ( :lid , :eid )";
			con.createQuery(sql).addParameter("lid", idList).addParameter("eid", idElem).executeUpdate();
			con.commit();
		}
	}

	public static void addTag(String idElem, String tag, int idUser) {
		try (Connection con = sql2o.open()) {
			String sql = "INSERT INTO TAG (UID, EID, TAGNAME) VALUES (:idUser , :idElem , :Tag )";
			con.createQuery(sql).addParameter("idElem", idElem).addParameter("Tag", tag).addParameter("idUser", idUser)
					.executeUpdate();
			con.commit();

		}
	}

	public static List<Tag> getAllTagnames(int idUser) {
		try (Connection con = sql2o.open()) {
			String sql = "SELECT MIN(id) AS id, UID, MIN(eid) as eid, tagname " + "FROM TAG WHERE UID = :idUser "
					+ "GROUP BY tagname ";
			return con.createQuery(sql).addParameter("idUser", idUser).executeAndFetch(Tag.class);
		}
	}

	public static void deleteTag(int id, int idUser) {
		try (Connection con = sql2o.open()) {
			String sql = "DELETE FROM TAG WHERE TAGNAME= :tagname AND UID = :idUser";
			con.createQuery(sql).addParameter("tagname", getTagnameById(id)).addParameter("idUser", idUser)
					.executeUpdate();
			con.commit();
		}
	}

	public static String getTagnameById(int id) {
		try (Connection con = sql2o.open()) {
			String sql = "SELECT * FROM TAG WHERE ID = :id";
			return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Tag.class).getTagname();
		}

	}

	public static void deleteTagElement(String tagname, int id) {
		try (Connection con = sql2o.open()) {
			String sql = "DELETE FROM TAG WHERE TAGNAME= :tagname AND EID= :id";
			con.createQuery(sql).addParameter("tagname", tagname).addParameter("id", id).executeUpdate();
			con.commit();
		}
	}

	public static List<Element> getAllTagElements(String tagname, int idUser) {
		try (Connection con = sql2o.open()) {
			String sql = "SELECT * " + "FROM ELEMENT " + "WHERE ID IN ( "
					+ "SELECT EID FROM TAG WHERE TAGNAME= :tagname AND UID = :idUser )";
			return con.createQuery(sql).addParameter("tagname", tagname).addParameter("idUser", idUser)
					.executeAndFetch(Element.class);
		}
	}

	public static List<Status> getAllStatus() {
		try (Connection con = sql2o.open()) {
			String sql = "SELECT * " + "FROM STATUS";
			return con.createQuery(sql).executeAndFetch(Status.class);
		}
	}

	public static void addStat(int idElem, String stat) {
		try (Connection con = sql2o.open()) {
			deleteAllElemStatus(idElem);

			String sql = "INSERT INTO STATUS (EID, OPTION) VALUES ( :idElem , :stat )";
			con.createQuery(sql).addParameter("idElem", idElem).addParameter("stat", stat).executeUpdate();
			con.commit();
		}
	}

	private static void deleteAllElemStatus(int idElem) {
		try (Connection con = sql2o.open()) {
			String sql = "DELETE FROM STATUS WHERE EID = :idElem";
			con.createQuery(sql).addParameter("idElem", idElem).executeUpdate();
			con.commit();
		}
	}

}

/*
 * Ajout d'un element. public static void addElement(String titre, String desc){
 * String insertSql =
 * "insert into myTable(TITRE, DESCRIPTION, DCREATION, DMODIFICATION) " +
 * "values (:titreParam, :descParam, :cParam, :mParam)";
 * 
 * try (Connection con = sql2o.open()) { Object insertedId =
 * con.createQuery(insertSql, true) .addParameter("titreParam", titre)
 * .addParameter("descriptionParam", desc) .addParameter("cParam", new
 * Timestamp(System.currentTimeMillis())) .addParameter("mParam", new
 * Timestamp(System.currentTimeMillis())) .executeUpdate() .getKey();
 * 
 * } }
 */
