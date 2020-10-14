

class AgendarAtendimentoError extends Error {
    constructor(message) {
      super(message)
      this.name = "Kafka Producer Novo Debito Agendar Atendimento onError"
      console.error(this.name)
      console.error(this.message)
      console.error(this.stack)
    }
}


module.exports = {
  AgendarAtendimentoError
}

