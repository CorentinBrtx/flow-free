package model;

public class Extremite {
	
	protected Tuyau tuyau;
	private Case caseCorrespondante;
	
	public Extremite(Tuyau tuyau) {
		this.tuyau = tuyau;
	}

	public Tuyau getTuyau() {
		return this.tuyau;
	}

	public Case getCaseCorrespondante() {
		return caseCorrespondante;
	}

	public void setCaseCorrespondante(Case caseCorrespondante) {
		this.caseCorrespondante = caseCorrespondante;
	}

}
