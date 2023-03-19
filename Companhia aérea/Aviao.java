
public class Aviao extends Aeronave
{
	public Passageiro[][] lugares;
	public int fileiras;
	public int assentos;
	
	public Aviao(String modelo, int fileiras, int assentos)
	{
		super(modelo);
		this.lugares = new Passageiro[fileiras][assentos];
		this.fileiras = fileiras;
		this.assentos = assentos;
	}

	public Passageiro getPassageiro(int fileira, int assento) {
		return this.lugares[fileira][assento];
	}

	public void setPassageiro(int fileira, int assento, Passageiro pessoa) {
		this.lugares[fileira][assento] = pessoa;
	}
	
	public boolean verificaLugarOcupado (int fileira, int assento){
		if (this.lugares[fileira][assento] == null)
			return false;
		else return true;
	}
	
	public int getFileiras() {
		return fileiras;
	}

	public int getAssentos() {
		return assentos;
	}
}
