package br.com.jhage.pedido_cqrs_core.constante;

public enum StatusPedido {
	
	REALIZADO,
	PRONTO,
	ENTREGUE,
	CANCELADO;
	
	public static StatusPedido get(String find) {

		StatusPedido result = REALIZADO;
		try{
			result  = valueOf(StatusPedido.class, find);
		}catch (IllegalArgumentException e) {
			
			System.out.println("Tamanho Nao Encontrado");
		}
		return result;
	}
}
