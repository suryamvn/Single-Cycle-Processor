package processor.pipeline;

public class OF_EX_LatchType {
	
	boolean EX_enable;
	int opcode,pc,op1,op2,immx,branchTarget,rd;
	String new_instruction;
	
	public OF_EX_LatchType()
	{
		EX_enable = false;
	}

	public boolean isEX_enable() {
		return EX_enable;
	}

	public void setEX_enable(boolean eX_enable) {
		EX_enable = eX_enable;
	}
	
	public int getopcode(){
		return this.opcode;
	}
	
	public void setopcode(int Opcode){
		this.opcode = Opcode;
	}

	public int getpc(){
		return this.pc;
	}

	public void setpc(int PC){
		this.pc=PC;
	}

	public int getop1(){
		return this.op1;
	}

	public void setop1(int OP1){
		this.op1=OP1;
	}

	public int getop2(){
		return this.op2;
	}

	public void setop2(int OP2){
		this.op2=OP2;
	}

	public int getimmx(){
		return this.immx;
	}

	public void setimmx(int Immx){
		this.immx=Immx;
	}

	public int getbranchTarget(){
		return this.branchTarget;
	}

	public void setbranchTarget(int bt){
		this.branchTarget=bt;
	}

	public int getrd(){
		return this.rd;
	}

	public void setrd(int RD){
		this.rd=RD;
	}
	
	public String getinstruction(){
		return this.new_instruction;
	}

	public void setinstruction(String inst){
		this.new_instruction=inst;
	}
	
}
