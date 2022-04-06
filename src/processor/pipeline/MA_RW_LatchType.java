package processor.pipeline;

public class MA_RW_LatchType {
	
	boolean RW_enable;
	int loadvalue;
	int operand,aluresult,OpCode,rd;
	
	public MA_RW_LatchType()
	{
		RW_enable = false;
	}

	public boolean isRW_enable() {
		return RW_enable;
	}

	public void setRW_enable(boolean rW_enable) {
		RW_enable = rW_enable;
	}
		
	public int getloadvalue() {
		return this.loadvalue;
	}

	public void setloadvalue(int loadvalue) {
		this.loadvalue = loadvalue;
	}

	public int getOperand2() {
		return this.operand;
	}

	public void setOperand2(int operand) {
		this.operand = operand;
	}
	
	public int getAluResult() {
		return this.aluresult;
	}

	public void setAluResult(int aluresult) {
		this.aluresult = aluresult;
	}
	
	public int getOpCode(){
		return this.OpCode;
	}
	
	public void setOpCode(int OPCode){
		this.OpCode = OPCode;
	}

	public int getrd(){
		return this.rd;
	}
	
	public void setrd(int RD){
		this.rd = RD;
	}
}
