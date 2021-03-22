//'use strict';
const kafka = require('kafka-node')
const command = require('./command')
const {responseCode: respcod}  = require('../conf/response-code')
const {AusenciaHeadersFundamentaisError: AusenciaHeadersFundamentaisError} = require('../exceptions/exception')
const {TokenExpiradoError: TokenExpiradoError} = require('../exceptions/exception')
const {PushTopicError: PushTopicError} = require('../exceptions/exception')

const pushNovodebito = async event=> {

  let payload = command.createmsgtopushKafka(process.env.KAFKATOPIC_NOVODEBITO, event.body)
  command.pushTopic(payload)
}

const commandNovoDebito = async event =>{
  
  try{
    command.validarTokenExpirado(event)
    command.existHeadertkuuid(event)
    await pushNovodebito(event)   
    
    return respcod.acceptedWithThismessageReturn(event, 'Operacao Realizada Com Sucesso, as acoes serão tomadas no decorrer do tempo')  
  }catch (exception) {
    if (exception instanceof TokenExpiradoError) {
        
      return respcod.tokenNaoAutorizadoReturn(event)
    }
    if (exception instanceof AusenciaHeadersFundamentaisError){
        
      return respcod.ausenciaHeadersFundamentaisReturn(event)
    }
    if (exception instanceof PushTopicError){
        
      return respcod.serviceUnavailableReturn(event)
    }
  }
    
}

module.exports.action = async event =>{

  let result 
  await command.isKafkaOn()
              .then(() => result = commandNovoDebito(event))
              .catch(error=> result = respcod.acceptedWithThismessageReturn(event, error.message))
      
  return result   
}

