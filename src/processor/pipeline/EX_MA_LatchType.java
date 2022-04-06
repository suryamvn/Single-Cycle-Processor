package processor.pipeline;

public class EX_MA_LatchType {
	boolean MA_enable;
	int operand,aluresult,OpCode,rd;
	
	public EX_MA_LatchType()
	{
		MA_enable = false;
	}

	public boolean isMA_enable() {
		return MA_enable;
	}

	public void setMA_enable(boolean mA_enable) {
		MA_enable = mA_enable;
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
