package util;

public interface Payable {

	default void pay() {
		System.out.println("기본 결제 기능");
	}

}
