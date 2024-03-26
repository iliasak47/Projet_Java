package metier;

public class Compte {
	private String id;
	private String mdp;
	private User user;
	
	public Compte(String id, String mdp, User user) {
		super();
		this.id = id;
		this.mdp = mdp;
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	

}
