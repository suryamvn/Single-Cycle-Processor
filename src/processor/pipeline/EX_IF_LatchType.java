package processor.pipeline;

public class EX_IF_LatchType {
	
	boolean IF_enable,flag;
	int branchpc;
	
	public EX_IF_LatchType(){
		IF_enable = false;
		flag = false;
	}
	
	public boolean getIF_enable() {
		return this.IF_enable;
	}

	public void setIF_enable(boolean IF_enable) {
		this.IF_enable = IF_enable;
	}
	
	public boolean getBranchTaken(){
		return this.flag;
	}
	
	public void setBranchTaken(boolean flag){
		this.flag = flag;
	}
	
	public int getBranchPC() {
		return this.branchpc;
	}

	public void setBranchPC(int branchpc) {
		this.branchpc = branchpc;
	}
}
