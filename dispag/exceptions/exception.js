
class NovoDebitoError extends Error {
    constructor(message) {
      super(message)
      this.name = "ERROR =>> Kafka Producer Novo Debito."
      console.error(this.name)
      console.error(this.message)
      console.error(this.stack)
    }
}


class ExcluirDebitoError extends Error {
  constructor(message) {
    super(message)
    this.name = "Kafka Producer Excluir Debito onError"
    console.error(this.name)
    console.error(this.message)
    console.error(this.stack)
  }
}

class PagarDebitoError extends Error {
  constructor(message) {
    super(message)
    this.name = "Kafka Producer Pagar Debito onError"
    console.error(this.name)
    console.error(this.message)
    console.error(this.stack)
  }
}

class PushTopicError extends Error {
  constructor(message) {
    super(message)
    this.name = "Error ao push Topic:"
    console.error(this.name)
    console.error(this.message)
    console.error(this.stack)
  }
}

class AusenciaHeadersFundamentaisError extends Error {
  constructor(message) {
    super(message)
    this.name = "Ausencia de Headers Fundamentais:"
    console.error(this.name)
    console.error(this.message)
    console.error(this.stack)
  }
}

class TokenExpiradoError extends Error {
  constructor(message) {
    super(message)
    this.name = "Token expirado error"
    console.error(this.name)
    console.error(this.message)
    console.error(this.stack)
  }
}

module.exports = {
  NovoDebitoError,
  ExcluirDebitoError,
  PagarDebitoError,
  PushTopicError,
  AusenciaHeadersFundamentaisError,
  TokenExpiradoError
}

