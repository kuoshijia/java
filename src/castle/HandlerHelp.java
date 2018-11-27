package castle;

public class HandlerHelp extends Handler {

	@Override
	public void doCmd(String value) {
        System.out.print("迷路了吗？你可以做的命令有：go bye help");
        System.out.println("如：\tgo east");
	}

}
