package model;

import java.security.Timestamp;

public class Player {
	
	private int pid;
	private String pname;
	private Double pheight;
	private Double pweight;
	private int page;
	private String pteam;
	private int pscore;
	private Timestamp pdate;
	private String pselect;
	private String prank;
	
	public Player() {
		// TODO Auto-generated constructor stub
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Double getPheight() {
		return pheight;
	}

	public void setPheight(Double pheight) {
		this.pheight = pheight;
	}

	public Double getPweight() {
		return pweight;
	}

	public void setPweight(Double pweight) {
		this.pweight = pweight;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getPteam() {
		return pteam;
	}

	public void setPteam(String pteam) {
		this.pteam = pteam;
	}

	public int getPscore() {
		return pscore;
	}

	public void setPscore(int pscore) {
		this.pscore = pscore;
	}

	public Timestamp getPdate() {
		return pdate;
	}

	public void setPdate(Timestamp pdate) {
		this.pdate = pdate;
	}

	public String getPselect() {
		return pselect;
	}

	public void setPselect(String pselect) {
		this.pselect = pselect;
	}

	public String getPrank() {
		return prank;
	}

	public void setPrank(String prank) {
		this.prank = prank;
	}

}
