package main.java.controller;

import static spark.Spark.externalStaticFileLocation;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import static spark.Spark.threadPool;

import main.java.model.Liste;
import main.java.model.Status;
import main.java.model.Element;
import main.java.model.Tag;
import main.java.model.User;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.io.IOException;
import java.io.StringWriter;

import java.util.*;

import static spark.Spark.*;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import main.java.DAO.MyDOA;

public class Controller {
	/* BENELKADI Safouane El-Amine */


	Configuration configuration = new Configuration(Configuration.VERSION_2_3_19);
	String username;

	boolean authentification = false;

	public void main(String[] args) throws Exception {

//        port(8080);
		int maxThreads = 10;
		int minThreads = 1;
		int timeOutMillis = 30000;
		threadPool(maxThreads, minThreads, timeOutMillis);

		staticFiles.expireTime(600);

		Configuration cfg = new Configuration(Configuration.VERSION_2_3_19);
		cfg.setClassForTemplateLoading(Controller.class, "../../res/templates");

		configuration.setDefaultEncoding("UTF-8");
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		configuration.setLogTemplateExceptions(false);
		cfg.setLocale(Locale.FRANCE);

		externalStaticFileLocation("src/main/res/html");

		// Secure connection :
		// secure("src/main/java/security/keystore.jks", "password", null, null);

		// Routing SparkJava :

		/* ________________________Accueil_________________________________________ */
		init();
		get("/", (req, res) -> {
			StringWriter writer = new StringWriter();
			if (authentification == true) {
				res.redirect("/openUser");
				return "";
			}
			try {
				Map<String, Object> input = new HashMap<String, Object>();
				Template template = cfg.getTemplate("connexion.ftl");

				template.process(input, writer);
			} catch (TemplateException | IOException e) {
				Spark.halt(500);
			}
			return writer;
		});
		/* ________________________Accueil_________________________________________ */

		get("/openUser", (request, res) -> {
			StringWriter writer = new StringWriter();
			if (authentification == false) {
				res.redirect("/");
			}
			try {

				Map<String, Object> input = new HashMap<String, Object>();

				int id = MyDOA.getUserID(username);
				List<Liste> listes = MyDOA.getAllLists(id);

				List<Tag> tags = MyDOA.getAllTagnames(MyDOA.getUserID(username));

				input.put("systemsTag", tags);
				input.put("systems", listes);
				input.put("idUser", id);
				Template template = cfg.getTemplate("openUser.ftl");

				template.process(input, writer);

			} catch (Exception e) {
				Spark.halt(500);
			}

			return writer;

		});

		/* ________________________Connexion_________________________________________ */
		post("/connexion", (Request request, Response res) -> {

			// HttpSession session = request.session().raw();
			// session.setAttribute;
			StringWriter writer = new StringWriter();
			try {
				username = request.queryParams("username") != null ? request.queryParams("username") : "unknown";
				String password = request.queryParams("password") != null ? request.queryParams("password") : "unknown";


				if (MyDOA.connection(username, password) == false) {

					res.redirect("/");

				} else {

					authentification = true;

					res.redirect("/openUser");
				}

			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;
		});
		/* ________________________Connexion_________________________________________ */

		/*
		 * ________________________Deconnexion_________________________________________
		 */
		get("/logOut", (request, respons) -> {
			StringWriter writer = new StringWriter();

			try {
				authentification = false;
				Map<String, Object> input = new HashMap<String, Object>();
				Template template = cfg.getTemplate("connexion.ftl");
				template.process(input, writer);
			} catch (Exception e) {
				Spark.halt(500);
			}

			return writer;
		});
		/*
		 * ________________________Deconnexion_________________________________________
		 */

		/* ________________________Profil_________________________________________ */
		get("/profil", (req, res) -> {

			StringWriter writer = new StringWriter();
			if (authentification) {
				try {

					Map<String, Object> input = new HashMap<String, Object>();
					Template template = cfg.getTemplate("profil.ftl");
					input.put("id", MyDOA.getUserID(username));
					input.put("username", username);

					template.process(input, writer);
				} catch (TemplateException | IOException e) {
					Spark.halt(500);
				}
				return writer;
			} else
				res.redirect("/");
			return "";
		});
		/* ________________________Profil_________________________________________ */

		/* _______________________Creation de compte _____________________________ */

		get("/createAccount", (request, res) -> {
			StringWriter writer = new StringWriter();

			try {
				Map<String, Object> input = new HashMap<String, Object>();
				Template template = cfg.getTemplate("createAccount.ftl");
				template.process(input, writer);
			} catch (Exception e) {
				Spark.halt(500);
			}

			return writer;
		});

		post("/createAccount", (request, res) -> {
			StringWriter writer = new StringWriter();

			try {

				authentification = true;
				username = request.queryParams("username") != null ? request.queryParams("username") : "unknown";
				String password = request.queryParams("password") != null ? request.queryParams("password") : "unknown";

				MyDOA.addUser(username, password);


				res.redirect("/openUser");

			} catch (Exception e) {
				Spark.halt(500);
			}

			return writer;

		});

		post("/openFormUser", (request, res) -> {

			StringWriter writer = new StringWriter();
			Map<String, Object> input = new HashMap<String, Object>();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String id = request.queryParams("id") != null ? request.queryParams("id") : "unknown";
				input.put("idUser", id);

				Template template = cfg.getTemplate("openFormUser.ftl");

				template.process(input, writer);
			} catch (Exception e) {
				Spark.halt(500);
			}

			return writer;
		});

		post("/addUser", (request, res) -> {
			StringWriter writer = new StringWriter();

			try {

				String username2 = request.queryParams("username") != null ? request.queryParams("username")
						: "unknown";
				String password2 = request.queryParams("password") != null ? request.queryParams("password")
						: "unknown";

				MyDOA.addUser(username2, password2);

				res.redirect("/admin");

			} catch (Exception e) {
				Spark.halt(500);
			}

			return writer;

		});

		post("/openFormUpdateUser", (request, res) -> {

			StringWriter writer = new StringWriter();
			Map<String, Object> input = new HashMap<String, Object>();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String id = request.queryParams("id") != null ? request.queryParams("id") : "unknown";
				input.put("idUser", id);

				Template template = cfg.getTemplate("openFormUpdateUser.ftl");

				template.process(input, writer);
			} catch (Exception e) {
				Spark.halt(500);
			}

			return writer;
		});

		post("/updateUser", (request, res) -> {
			StringWriter writer = new StringWriter();

			try {

				String username2 = request.queryParams("username") != null ? request.queryParams("username")
						: "unknown";
				String password2 = request.queryParams("password") != null ? request.queryParams("password")
						: "unknown";
				String id = request.queryParams("idUser") != null ? request.queryParams("idUser") : "unknown";

				MyDOA.updateUser(Integer.parseInt(id), username2, password2);

				res.redirect("/admin");

			} catch (Exception e) {
				Spark.halt(500);
			}

			return writer;

		});

		/* _______________________Creation de compte _____________________________ */

		post("/openList", (request, res) -> {
			StringWriter writer = new StringWriter();
			Map<String, Object> input = new HashMap<String, Object>();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}

			try {
				String id = request.queryParams("id") != null ? request.queryParams("id") : "unknown";

				Liste liste = MyDOA.getListeById(Integer.parseInt(id));
				String title = liste.getTitre();

				List<Element> elements = MyDOA.getAllElements(liste.getId());

				input.put("systems", elements);
				input.put("title", title);
				input.put("idList", id);

				List<Liste> sublists = MyDOA.getAllsubLists(liste.getId());
				List<Status> status = MyDOA.getAllStatus();

				input.put("status", status);
				input.put("systemsListe", sublists);

				Template template = cfg.getTemplate("openList.ftl");

				template.process(input, writer);

			} catch (Exception e) {
				Spark.halt(500);
			}

			return writer;

		});

		post("/deleteList", (request, res) -> {

			StringWriter writer = new StringWriter();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String id = request.queryParams("id") != null ? request.queryParams("id") : "unknown";

				MyDOA.deleteList(Integer.parseInt(id));

				res.redirect("/openUser");
			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;
		});

		post("/openFormAddList", (request, res) -> {

			StringWriter writer = new StringWriter();
			Map<String, Object> input = new HashMap<String, Object>();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String id = request.queryParams("idUser") != null ? request.queryParams("idUser") : "unknown";
				input.put("idUser", id);

				Template template = cfg.getTemplate("openFormAddList.ftl");

				template.process(input, writer);
			} catch (Exception e) {
				Spark.halt(500);
			}

			return writer;
		});

		post("/addNewList", (request, res) -> {

			StringWriter writer = new StringWriter();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String id = request.queryParams("idUser") != null ? request.queryParams("idUser") : "unknown";
				String title = request.queryParams("title") != null ? request.queryParams("title") : "unknown";
				String description = request.queryParams("description") != null ? request.queryParams("description")
						: "unknown";

				MyDOA.addList(Integer.parseInt(id), title, description);

				res.redirect("/openUser");
			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;

		});

		post("/openForm", (request, res) -> {

			StringWriter writer = new StringWriter();
			Map<String, Object> input = new HashMap<String, Object>();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String id = request.queryParams("idList") != null ? request.queryParams("idList") : "unknown";

				Liste liste = MyDOA.getListeById(Integer.parseInt(id));
				String title = liste.getTitre();

				input.put("idList", id);
				input.put("title", title);

				Template template = cfg.getTemplate("openForm.ftl");

				template.process(input, writer);
			} catch (Exception e) {
				Spark.halt(500);
			}

			return writer;
		});

		post("/openFormUpdateList", (request, res) -> {

			StringWriter writer = new StringWriter();
			Map<String, Object> input = new HashMap<String, Object>();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String id = request.queryParams("id") != null ? request.queryParams("id") : "unknown";

				Liste l = MyDOA.getListeById(Integer.parseInt(id));
				String titre = l.getTitre();
				String description = l.getDescription();

				input.put("idList", id);
				input.put("titre", titre);
				input.put("description", description);

				Template template = cfg.getTemplate("openFormUpdateList.ftl");

				template.process(input, writer);
			} catch (Exception e) {
				Spark.halt(500);
			}

			return writer;
		});
		post("/updateListe", (request, res) -> {

			StringWriter writer = new StringWriter();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String id = request.queryParams("idList") != null ? request.queryParams("idList") : "unknown";
				String name = request.queryParams("titre") != null ? request.queryParams("titre") : "unknown";
				String description = request.queryParams("description") != null ? request.queryParams("description")
						: "unknown";

				MyDOA.updateListe(Integer.parseInt(id), name, description);

				res.redirect("/openUser");
			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;

		});

		/* ________________Waiting for sublists ______________________________ */
		post("/openFormAddSubList", (request, res) -> {

			StringWriter writer = new StringWriter();
			Map<String, Object> input = new HashMap<String, Object>();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String id = request.queryParams("idList2") != null ? request.queryParams("idList2") : "unknown";
				input.put("idList", id);

				Template template = cfg.getTemplate("openFormAddSubList.ftl");

				template.process(input, writer);
			} catch (Exception e) {
				Spark.halt(500);
			}

			return writer;
		});

		post("/addNewSubList", (request, res) -> {

			StringWriter writer = new StringWriter();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				Map<String, Object> input = new HashMap<String, Object>();

				String id = request.queryParams("idList") != null ? request.queryParams("idList") : "unknown";
				String title = request.queryParams("title") != null ? request.queryParams("title") : "unknown";
				String description = request.queryParams("description") != null ? request.queryParams("description")
						: "unknown";

				Liste liste = MyDOA.getListeById(Integer.parseInt(id));
				String titleliste = liste.getTitre();

				MyDOA.addSubList(Integer.parseInt(id), title, description);

				List<Element> elements = MyDOA.getAllElements(liste.getId());

				input.put("systems", elements);
				input.put("title", titleliste);
				input.put("idList", id);

				List<Liste> sublists = MyDOA.getAllsubLists(liste.getId());

				input.put("systemsListe", sublists);
				input.put("status", MyDOA.getAllStatus());

				Template template = cfg.getTemplate("openList.ftl");

				template.process(input, writer);

			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;

		});

		/* ________________Elements __________________________________________ */

		post("/openFormUpdateElement", (request, res) -> {

			StringWriter writer = new StringWriter();
			Map<String, Object> input = new HashMap<String, Object>();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String id = request.queryParams("id") != null ? request.queryParams("id") : "unknown";

				Element e = MyDOA.getElementById(Integer.parseInt(id));
				String titre = e.getTitre();
				String description = e.getDescription();

				input.put("idElement", id);
				input.put("name", titre);
				input.put("description", description);

				Template template = cfg.getTemplate("openFormUpdateElement.ftl");

				template.process(input, writer);
			} catch (Exception e) {
				Spark.halt(500);
			}

			return writer;
		});

		post("/updateElement", (request, res) -> {

			StringWriter writer = new StringWriter();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String id = request.queryParams("idElement") != null ? request.queryParams("idElement") : "unknown";
				String name = request.queryParams("name") != null ? request.queryParams("name") : "unknown";
				String description = request.queryParams("description") != null ? request.queryParams("description")
						: "unknown";

				MyDOA.updateElement(Integer.parseInt(id), name, description);

				Liste liste = MyDOA.getListByElemId(Integer.parseInt(id));
				List<Element> elements = MyDOA.getAllElements(liste.getId());

				Map<String, Object> input = new HashMap<String, Object>();
				String titleliste = liste.getTitre();
				input.put("systems", elements);
				input.put("title", titleliste);
				input.put("idList", id);

				List<Liste> sublists = MyDOA.getAllsubLists(liste.getId());

				input.put("systemsListe", sublists);
				input.put("status", MyDOA.getAllStatus());

				Template template = cfg.getTemplate("openList.ftl");

				template.process(input, writer);
			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;

		});

		post("/addNewElement", (request, res) -> {

			StringWriter writer = new StringWriter();
			Map<String, Object> input = new HashMap<String, Object>();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String id = request.queryParams("idList") != null ? request.queryParams("idList") : "unknown";
				String name = request.queryParams("name") != null ? request.queryParams("name") : "unknown";
				String description = request.queryParams("description") != null ? request.queryParams("description")
						: "unknown";

				Liste liste = MyDOA.getListeById(Integer.parseInt(id));
				String title = liste.getTitre();

				MyDOA.addElement(Integer.parseInt(id), name, description);

				List<Element> elements = MyDOA.getAllElements(liste.getId());

				input.put("systems", elements);
				input.put("title", title);
				input.put("idList", id);

				List<Liste> sublists = MyDOA.getAllsubLists(liste.getId());

				input.put("systemsListe", sublists);
				input.put("status", MyDOA.getAllStatus());

				Template template = cfg.getTemplate("openList.ftl");

				template.process(input, writer);

			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;

		});

		post("/deleteElement", (request, res) -> {

			StringWriter writer = new StringWriter();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String id = request.queryParams("id") != null ? request.queryParams("id") : "unknown";

				Liste liste = MyDOA.getListByElemId(Integer.parseInt(id));

				MyDOA.deleteElement(Integer.parseInt(id));

				List<Element> elements = MyDOA.getAllElements(liste.getId());

				Map<String, Object> input = new HashMap<String, Object>();
				String titleliste = liste.getTitre();
				input.put("systems", elements);
				input.put("title", titleliste);
				input.put("idList", id);

				List<Liste> sublists = MyDOA.getAllsubLists(liste.getId());

				input.put("systemsListe", sublists);
				input.put("status", MyDOA.getAllStatus());

				Template template = cfg.getTemplate("openList.ftl");

				template.process(input, writer);
			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;
		});

		get("/admin", (request, res) -> {

			StringWriter writer = new StringWriter();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}

			try {
				int id = MyDOA.getUserID(username);

				if (MyDOA.isAdmin(id) == false) {
					res.redirect("/openUser");
					return "";
				}

				Map<String, Object> input = new HashMap<String, Object>();
				List<User> users = MyDOA.getAllUsers();

				input.put("systems", users);

				Template template = cfg.getTemplate("admin.ftl");

				template.process(input, writer);

			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;
		});

		post("/deleteUser", (request, res) -> {

			StringWriter writer = new StringWriter();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String id = request.queryParams("id") != null ? request.queryParams("id") : "unknown";

				MyDOA.deleteUser(Integer.parseInt(id));

				res.redirect("admin");
			} catch (Exception e) {
				Spark.halt(500);
			}
			System.out.println(writer);
			return writer;
		});

		post("/shareForm", (request, res) -> {

			StringWriter writer = new StringWriter();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String id = request.queryParams("id") != null ? request.queryParams("id") : "unknown";

				Map<String, Object> input = new HashMap<String, Object>();

				input.put("idList", id);

				Template template = cfg.getTemplate("shareForm.ftl");

				template.process(input, writer);

			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;
		});

		post("/share", (request, res) -> {

			StringWriter writer = new StringWriter();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String idUser = request.queryParams("idUser") != null ? request.queryParams("idUser") : "unknown";
				String idList = request.queryParams("idList") != null ? request.queryParams("idList") : "unknown";

				Map<String, Object> input = new HashMap<String, Object>();

				MyDOA.shareList(Integer.parseInt(idUser), Integer.parseInt(idList));
				String username = MyDOA.getUserByID(idUser).getUsername();

				input.put("idList", idList);
				input.put("username", username);

				Template template = cfg.getTemplate("success.ftl");

				template.process(input, writer);

			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;
		});

		post("/shareFormElem", (request, res) -> {

			StringWriter writer = new StringWriter();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String id = request.queryParams("id") != null ? request.queryParams("id") : "unknown";

				Map<String, Object> input = new HashMap<String, Object>();

				input.put("idElem", id);

				Template template = cfg.getTemplate("shareFormElement.ftl");

				template.process(input, writer);

			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;
		});

		post("/shareElem", (request, res) -> {

			StringWriter writer = new StringWriter();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String idList = request.queryParams("idList") != null ? request.queryParams("idList") : "unknown";
				String idElem = request.queryParams("idElem") != null ? request.queryParams("idElem") : "unknown";

				Map<String, Object> input = new HashMap<String, Object>();

				MyDOA.shareElem(Integer.parseInt(idList), Integer.parseInt(idElem));
				String title = MyDOA.getListeById(Integer.parseInt(idList)).getTitre();

				input.put("idElem", idElem);
				input.put("titre", title);

				Template template = cfg.getTemplate("successElem.ftl");

				template.process(input, writer);

			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;
		});

		post("/tagForm", (request, res) -> {

			StringWriter writer = new StringWriter();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String id = request.queryParams("id") != null ? request.queryParams("id") : "unknown";

				Map<String, Object> input = new HashMap<String, Object>();

				input.put("idElem", id);

				Template template = cfg.getTemplate("tagForm.ftl");

				template.process(input, writer);

			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;
		});

		post("/addTag", (request, res) -> {

			StringWriter writer = new StringWriter();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String Tag = request.queryParams("Tag") != null ? request.queryParams("Tag") : "unknown";
				String idElem = request.queryParams("idElem") != null ? request.queryParams("idElem") : "unknown";

				MyDOA.addTag(idElem, Tag,MyDOA.getUserID(username));

				res.redirect("/openUser");

			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;
		});

		post("/deleteTag", (request, res) -> {

			StringWriter writer = new StringWriter();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String id = request.queryParams("id") != null ? request.queryParams("id") : "unknown";

				MyDOA.deleteTag(Integer.parseInt(id),MyDOA.getUserID(username));

				res.redirect("/openUser");

			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;
		});

		post("/openTag", (request, res) -> {

			StringWriter writer = new StringWriter();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String id = request.queryParams("id") != null ? request.queryParams("id") : "unknown";
				String tagname = MyDOA.getTagnameById(Integer.parseInt(id));
				Map<String, Object> input = new HashMap<String, Object>();

				List<Element> elements = MyDOA.getAllTagElements(tagname, MyDOA.getUserID(username));

				input.put("systems", elements);
				input.put("tagname", tagname);
				input.put("status", MyDOA.getAllStatus());

				Template template = cfg.getTemplate("openTag.ftl");

				template.process(input, writer);

			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;
		});

		post("/deleteTagElement", (request, res) -> {

			StringWriter writer = new StringWriter();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String id = request.queryParams("id") != null ? request.queryParams("id") : "unknown";
				String tagname = request.queryParams("tagname") != null ? request.queryParams("tagname") : "unknown";
				MyDOA.deleteTagElement(tagname, Integer.parseInt(id));

				res.redirect("/openUser");

			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;
		});

		post("/status", (request, res) -> {

			StringWriter writer = new StringWriter();

			if (authentification == false) {
				res.redirect("/");
				return "";
			}
			try {
				String id = request.queryParams("id") != null ? request.queryParams("id") : "unknown";
				String stat = request.queryParams("stat") != null ? request.queryParams("stat") : "unknown";
				Liste liste = MyDOA.getListByElemId(Integer.parseInt(id));

				MyDOA.addStat(Integer.parseInt(id), stat);

				List<Element> elements = MyDOA.getAllElements(liste.getId());

				Map<String, Object> input = new HashMap<String, Object>();
				String titleliste = liste.getTitre();
				input.put("systems", elements);
				input.put("title", titleliste);
				input.put("idList", id);

				List<Liste> sublists = MyDOA.getAllsubLists(liste.getId());

				input.put("systemsListe", sublists);
				input.put("status", MyDOA.getAllStatus());

				Template template = cfg.getTemplate("openList.ftl");

				template.process(input, writer);
			} catch (Exception e) {
				Spark.halt(500);
			}
			return writer;
		});

	}
}